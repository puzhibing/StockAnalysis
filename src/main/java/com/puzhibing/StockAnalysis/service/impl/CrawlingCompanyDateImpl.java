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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.puzhibing.StockAnalysis.dao.mapper.CompanyMapper;
import com.puzhibing.StockAnalysis.dao.mapper.CompanyStockMapper;
import com.puzhibing.StockAnalysis.pojo.Company;
import com.puzhibing.StockAnalysis.pojo.CompanyStock;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.reptileBean.SHData;
import com.puzhibing.StockAnalysis.pojo.reptileBean.SZData;
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
		String pageHelpPageSize = "100";
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
			
			System.err.println(result);
//			SHJSONData shjsonData = JSON.parseObject(result, SHJSONData.class);
			
			resultUtil = SHparsingStorage(result , stockTypeId , stockExchangeId , token);
			
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
	@Transactional//开启事务
	public ResultBean<Object> SHparsingStorage(String result , String stockTypeId , String stockExchangeId , String token) {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(!(StringUtils.isEmpty(result) && StringUtils.isEmpty(stockTypeId) && StringUtils.isEmpty(stockExchangeId) && StringUtils.isEmpty(token))) {
			try {
				List<Company> list = companyMapper.selectAllCompany();
				
				//添加数据
				JSONArray jsonArray = JSON.parseObject(result).getJSONArray("result");
				List<SHData> list2 = new ArrayList<>();
				for (Object object : jsonArray) {
					list2.add((SHData)object);
				}
				
				String companyId = "";
				
				int cn = 0;//记录需要添加的数量
				int csn = 0;//记录需要添加的数量
				int companyNum = 0;//记录成功添加的数量
				int companyStockNum = 0;//记录成功添加的数据
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
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String id = uuidutil.getUUID();
					if(bo) {//存在，只添加关系数据
						CompanyStock companyStock = companyStockMapper.selectCompanyStockByCompanyIdAndStockTypeId(companyId, stockTypeId);
						if(companyStock == null) {
							csn++;
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
							companyStockNum++;//计数器
						}
						
					}else {//不存在
						cn++;
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
						
						csn++;
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
						companyNum++;
						companyStockMapper.insertCompanyStock(companyStock);
						companyStockNum++;
						
					}
				}
				
				resultBean.setB(true);
				resultBean.setResult("总数据为：" + list2.size() + "条；需要添加企业数量：" + cn + "条；成功添加企业数量：" + companyNum +
						"条；需要添加证券数量：" + csn + "条；成功添加证券数量：" + companyStockNum);
			} catch (Exception e) {
				e.printStackTrace();
				resultBean.setB(false);
				resultBean.setResult("数据处理异常");
			}
		}
		return resultBean;
	}



	
	
	
	
	@Override
	public ResultBean<Object> crawlingShenzhen(String type, String stockTypeId, String stockExchangeId, String token) {
		resultUtil = new ResultBean<>();
		if(!(StringUtils.isEmpty(type) && StringUtils.isEmpty(stockTypeId) && StringUtils.isEmpty(stockExchangeId) && StringUtils.isEmpty(token))) {
			String path = "http://www.szse.cn/api/report/ShowReport/data";
			String SHOWTYPE = "JSON";
			String CATALOGID = "1110";
			String TABKEY = "tab1";
			String PAGENO = "1";
			String random = "0.5098245544096867";
			if(type.equals("B")) {
				TABKEY = "tab2";
				random = "0.899993813738384";
			}
			path += "?SHOWTYPE=" + SHOWTYPE + "&CATALOGID=" + CATALOGID + "&TABKEY=" + TABKEY + "&PAGENO=" + PAGENO + "&random=" + random;
			
			URL url = null;
			try {
				url = new URL(path);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpURLConnection httpURLConnection;
			try {
				httpURLConnection = (HttpURLConnection)url.openConnection();
				httpURLConnection.addRequestProperty("Host", "www.szse.cn");
				httpURLConnection.addRequestProperty("Connection", "keep-alive");
				httpURLConnection.addRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
				httpURLConnection.addRequestProperty("X-Request-Type", "ajax");
				httpURLConnection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
				httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
				httpURLConnection.addRequestProperty("Content-Type", "application/json");
				httpURLConnection.addRequestProperty("Referer", "http://www.szse.cn/market/stock/list/index.html");
				httpURLConnection.addRequestProperty("Accept-Encoding", "gzip, deflate");
				httpURLConnection.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
				
				httpURLConnection.connect();
				
				// 定义BufferedReader输入流来读取URL的响应
	            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream() , "UTF-8"));
	            String result = "";
	            String line;
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.err.println(result);
	            
	            
	            
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}

	
	
	public void SZparsingStorage(String result , String type, String stockTypeId , String stockExchangeId , String token) {
		if(!StringUtils.isEmpty(result)) {
			JSONArray jsonArray = null;
	        if(type.equals("B")) {
	        	jsonArray = JSON.parseArray(result).getJSONObject(1).getJSONArray("data");
	        }else {
	        	jsonArray = JSON.parseArray(result).getJSONObject(0).getJSONArray("data");
			}
	        
	        try {
	        	List<Company> list = companyMapper.selectAllCompany();
	        	
	        	List<SZData> list2 = new ArrayList<>();
		        for (Object object : jsonArray) {
		        	list2.add((SZData)object);
				}
	        	
		        for (SZData szData : list2) {
					for (Company company : list) {
//						if(szData.)
					}
				}
	        	
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	        
	        
	        
		}
		
		
	}
	
	
	
}
