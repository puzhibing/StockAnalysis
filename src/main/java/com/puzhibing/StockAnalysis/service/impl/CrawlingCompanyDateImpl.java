package com.puzhibing.StockAnalysis.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.CompanyMapper;
import com.puzhibing.StockAnalysis.dao.mapper.CompanyStockMapper;
import com.puzhibing.StockAnalysis.pojo.Company;
import com.puzhibing.StockAnalysis.pojo.CompanyStock;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.reptileBean.SHData;
import com.puzhibing.StockAnalysis.pojo.reptileBean.SHJSONData;
import com.puzhibing.StockAnalysis.service.CompanyService;
import com.puzhibing.StockAnalysis.service.CrawlingCompanyDate;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;

/**
 * 爬取企业数据
 * @author Administrator
 *
 */
@Service
public class CrawlingCompanyDateImpl implements CrawlingCompanyDate {
	
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private CompanyStockMapper companyStockMapper;
	
	private ResultBean<Object> resultUtil;
	

	
	
	
	@Override
	public ResultBean<Object> crawlingShanghai(String type , String stockTypeId , String stockExchangeId , String token) throws IOException {
		resultUtil = new ResultBean<>();
		
		String path = "http://query.sse.com.cn/security/stock/getStockListData2.do";
		String jsonCallBack = "jsonpCallback21116";
		String isPagination = "true";
		String stockCode = "";
		String csrcCode = "";
		String areaName = "";
		String stockType = "1";
		String pageHelpCacheSize = "1";
		String pageHelpBeginPage = "1";
		String pageHelpPageSize = "1";
		String pageHelpPageNo = "1";
		String num = "1541335792225";

		if(type.equals("B")) {
			jsonCallBack = "jsonpCallback88103";
			stockType = "2";
			num = "1541335792226";
		}
		
		path += "?&jsonCallBack=" + jsonCallBack + "&isPagination=" + isPagination + "&stockCode=" + stockCode + "&csrcCode=" + csrcCode +
				"&areaName=" + areaName + "&stockType=" + stockType + "&pageHelp.cacheSize=" + pageHelpCacheSize + "&pageHelp.beginPage=" + pageHelpBeginPage + 
				"&pageHelp.pageSize=" + pageHelpPageSize + "&pageHelp.pageNo" + pageHelpPageNo + "&_=" + num;
		
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw e;
		}
		
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			
			httpURLConnection.addRequestProperty("Host", "query.sse.com.cn");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
			httpURLConnection.addRequestProperty("Accept", "*/*");
			httpURLConnection.addRequestProperty("Referer", "http://www.sse.com.cn/assortment/stock/list/share/");
			httpURLConnection.addRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
			httpURLConnection.addRequestProperty("Cookie", "yfx_c_g_u_id_10000042=_ck18110321095819678150158351323; yfx_mr_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_mr_f_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_key_10000042=; yfx_f_l_v_t_10000042=f_t_1541250598896__r_t_1541335693023__v_t_1541335791818__r_c_1; VISITED_MENU=%5B%2210948%22%2C%228528%22%5D");
			
			httpURLConnection.connect();
			// 定义BufferedReader输入流来读取URL的响应
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            
			result = result.substring(result.indexOf("(") + 1, result.lastIndexOf(")"));
			
			SHJSONData shjsonData = JSON.parseObject(result, SHJSONData.class);
			
			Boolean b = SHparsingStorage(shjsonData , stockTypeId , stockExchangeId , token);
			if(b) {
				resultUtil.setB(true);
			}else {
				resultUtil.setB(false);
				resultUtil.setResult("处理异常");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return resultUtil;
	}
	
	
	
	/**
	 * 解析存储数据
	 * @param shjsonData
	 * @return
	 */
	public boolean SHparsingStorage(SHJSONData shjsonData , String stockTypeId , String stockExchangeId , String token) {
		boolean b = false;
		if(shjsonData != null && !(StringUtils.isEmpty(stockTypeId) && StringUtils.isEmpty(stockExchangeId) && StringUtils.isEmpty(token))) {
			try {
				List<Company> list = companyMapper.selectAllCompany();
				
				//添加数据
				List<SHData> list2 = shjsonData.getPageHelp().getData();
				String companyId = "";
				for (SHData shData : list2) {
					boolean bo = false;
					for (Company company : list) {
						//判断是否存在
						if(shData.getCOMPANY_ABBR().equals(company.getChName())) {
							bo = true;
							companyId = company.getId();
							break;
						}
					}
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
					String id = uuidutil.getUUID();
					if(bo) {//存在，只添加关系数据
						CompanyStock companyStock = companyStockMapper.selectCompanyStockByCompanyIdAndStockTypeId(companyId, stockTypeId);
						if(companyStock == null) {
							CompanyStock companyStock1 = new CompanyStock();
							companyStock1.setId(uuidutil.getUUID());
							companyStock1.setCompanyId(id);
							companyStock1.setStockCode(shData.getSECURITY_CODE_A());
							companyStock1.setStockTypeId(stockTypeId);
							companyStock1.setStockExchangeId(stockExchangeId);
							companyStock1.setListingTime(simpleDateFormat.parse(shData.getLISTING_DATE()));
							companyStock1.setDel("0");
							companyStock1.setInsertTime(new Date());
							companyStock1.setInsertUserId(tokenutil.tokenToUser(token).getId());
							companyStockMapper.insertCompanyStock(companyStock1);
						}
						
					}else {//不存在
						
						Company company = new Company();
						
						company.setId(id);
						company.setChName(shData.getCOMPANY_ABBR());
						company.setChShortName(shData.getCOMPANY_ABBR());
						company.setEnName("");
						company.setEnShortName("");
						company.setRegisterTime(new Date());
						company.setUrl("");
						company.setDel("0");
						company.setInsertTime(new Date());
						company.setInsertUserId(tokenutil.tokenToUser(token).getId());
						
						CompanyStock companyStock = new CompanyStock();
						companyStock.setId(uuidutil.getUUID());
						companyStock.setCompanyId(id);
						companyStock.setStockCode(shData.getSECURITY_CODE_A());
						companyStock.setStockTypeId(stockTypeId);
						companyStock.setStockExchangeId(stockExchangeId);
						companyStock.setListingTime(simpleDateFormat.parse(shData.getLISTING_DATE()));
						companyStock.setDel("0");
						companyStock.setInsertTime(new Date());
						companyStock.setInsertUserId(tokenutil.tokenToUser(token).getId());
						
						companyMapper.insertCompany(company);
						companyStockMapper.insertCompanyStock(companyStock);
						b = true;
						
						
						
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	
	
	
	
}
