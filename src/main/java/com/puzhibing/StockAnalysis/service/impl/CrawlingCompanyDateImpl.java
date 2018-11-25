package com.puzhibing.StockAnalysis.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.pojo.reptileBean.SHCompanyInfo;
import com.puzhibing.StockAnalysis.pojo.reptileBean.SHData;
import com.puzhibing.StockAnalysis.pojo.reptileBean.SZCompanyInfo;
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
	
	private User user;
	

	
	
	/**
	 * 爬取企业数据
	 * @param type
	 * @param stockTypeId
	 * @param stockExchangeName
	 * @param stockExchangeId
	 * @param token
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultBean<Object> crawlingCompany(String type, String stockTypeId, String stockExchangeName,
			String stockExchangeId, String token) {
		
		try {
			user = tokenutil.tokenToUser(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int pageNo = 1;//爬取的页数
		int count = 0;//总数据
		int cn = 0;//记录需要添加企业的数量
		int csn = 0;//记录需要添加证券的数量
		int companyNum = 0;//记录成功添加的数量
		int companyStockNum = 0;//记录成功添加的数据
		ResultBean resultBean = null;
		if("上海证券交易所".equals(stockExchangeName.trim())) {
			//调取上海爬虫爬取数据
			boolean b = true;
			while (b) {
				resultBean = this.crawlingShanghai(type, stockTypeId, stockExchangeId, pageNo);
				if("0".equals(resultBean.getResult())) {
					//获取数据完毕
					b = false;
				}else {
					String[] strs = ((String)resultBean.getResult()).split(";");
					count += Integer.valueOf(strs[0]);
					cn += Integer.valueOf(strs[1]);
					companyNum += Integer.valueOf(strs[2]);
					csn += Integer.valueOf(strs[3]);
					companyStockNum += Integer.valueOf(strs[4]);
				}
				pageNo++;
			}
			
		}else if ("深圳证券交易所".equals(stockExchangeName.trim())) {
			//调取深圳爬虫爬取数据
			boolean b = true;
			while (b) {
				resultBean = this.crawlingShenzhen(type, stockTypeId, stockExchangeId, pageNo);
				if("0".equals(resultBean.getResult())) {
					//获取数据完毕
					b = false;
				}else {
					String[] strs = ((String)resultBean.getResult()).split(";");
					count += Integer.valueOf(strs[0]);
					cn += Integer.valueOf(strs[1]);
					companyNum += Integer.valueOf(strs[2]);
					csn += Integer.valueOf(strs[3]);
					companyStockNum += Integer.valueOf(strs[4]);
				}
				pageNo++;
			}
		}
		
		resultUtil = new ResultBean<>();
		resultUtil.setB(resultBean.getB());
		resultUtil.setResult("总数据为：" + count + "条；需要添加企业数量：" + cn + "条；成功添加企业数量：" + companyNum +
				"条；需要添加证券数量：" + csn + "条；成功添加证券数量：" + companyStockNum + "条");
		return resultUtil;
	}
	
	
	/**
	 * 
	 *    爬取上海股票数据
	 * @param type 数据类型A/B
	 * @param stockTypeId
	 * @param stockExchangeId
	 * @param token
	 * 
	 */
	public ResultBean<Object> crawlingShanghai(String type , String stockTypeId , String stockExchangeId, int pageNo) {
		resultUtil = new ResultBean<>();
		
		String path = "http://query.sse.com.cn/security/stock/getStockListData2.do";
		String jsonCallBack = "jsonpCallback21116";
		String isPagination = "true";
		String stockCode = "";
		String csrcCode = "";
		String areaName = "";
		String stockType = "1";
		String pageHelpCacheSize = "1";
		String pageHelpBeginPage = String.valueOf(pageNo);
		String pageHelpPageSize = "25";
		String pageHelpPageNo = String.valueOf(pageNo);
		String pageHelpEndPage = String.valueOf((pageNo * 10) + 1);
		Long num = new Date().getTime();

		if(type.equals("B")) {
			jsonCallBack = "jsonpCallback88103";
			stockType = "2";
		}
		
		path += "?&jsonCallBack=" + jsonCallBack + "&isPagination=" + isPagination + "&stockCode=" + stockCode + "&csrcCode=" + csrcCode +
				"&areaName=" + areaName + "&stockType=" + stockType + "&pageHelp.cacheSize=" + pageHelpCacheSize + "&pageHelp.beginPage=" + pageHelpBeginPage + 
				"&pageHelp.pageSize=" + pageHelpPageSize + "&pageHelp.pageNo" + pageHelpPageNo + "&pageHelp.endPage=" + pageHelpEndPage + "&_=" + num;
		
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
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
			httpURLConnection.addRequestProperty("Cookie", "yfx_c_g_u_id_10000042=_ck18110321095819678150158351323; yfx_mr_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_mr_f_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_key_10000042=; yfx_f_l_v_t_10000042=f_t_1541250598896__r_t_1541335693023__v_t_" + new Date().getTime() + "__r_c_1; VISITED_MENU=%5B%2210948%22%2C%228528%22%5D");
			
			httpURLConnection.connect();
			// 定义BufferedReader输入流来读取URL的响应
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            
			result = result.substring(result.indexOf("(") + 1, result.lastIndexOf(")"));
			
			resultUtil = SHparsingStorage(type, result, stockTypeId, stockExchangeId);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultUtil;
	}
	
	
	/**
	 * 爬取企业详细数据
	 * @param type 类型
	 * @param code 证券编号
	 * @return SHCompanyInfo 企业详情对象
	 */
	public SHCompanyInfo SHcrawlingCompanyInfo(String type, String code) {
		String path = "http://query.sse.com.cn/commonQuery.do";
		String jsonCallBack = "jsonpCallback80869";
		String isPagination = "false";
		String sqlId = "COMMON_SSE_ZQPZ_GP_GPLB_C";
		String productid = code;
		Long num = new Date().getTime();
		
		SHCompanyInfo shCompanyInfo = null;
		if("B".equals(type)) {
			jsonCallBack = "jsonpCallback5804";
		}
		
		URL url = null;
		try {
			url = new URL(path + "?jsonCallBack=" + jsonCallBack + "&isPagination=" + isPagination + 
					"&sqlId=" + sqlId + "&productid=" + productid + "&_=" + num);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.addRequestProperty("Host", "query.sse.com.cn");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
			httpURLConnection.addRequestProperty("Accept", "*/*");
			httpURLConnection.addRequestProperty("Referer", "http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=" + code);
			httpURLConnection.addRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
			httpURLConnection.addRequestProperty("Cookie", "yfx_c_g_u_id_10000042=_ck18110321095819678150158351323; yfx_mr_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_mr_f_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_key_10000042=; VISITED_COMPANY_CODE=%5B%22600000%22%2C%22600054%22%5D; VISITED_STOCK_CODE=%5B%22600000%22%2C%22600054%22%5D; seecookie=%5B600000%5D%3A%u6D66%u53D1%u94F6%u884C%2C%5B600054%5D%3A%u9EC4%u5C71%u65C5%u6E38; yfx_f_l_v_t_10000042=f_t_1541250598896__r_t_1542531972130__v_t_" + new Date().getTime() + "__r_c_3; VISITED_MENU=%5B%228483%22%2C%228349%22%2C%228473%22%2C%228535%22%2C%228536%22%2C%228482%22%2C%228528%22%2C%229057%22%2C%229059%22%2C%229055%22%2C%229062%22%5D");
			
			httpURLConnection.connect();
			// 定义BufferedReader输入流来读取URL的响应
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            
			result = result.substring(result.indexOf("(") + 1, result.lastIndexOf(")"));
			shCompanyInfo = JSON.parseObject(result).getJSONArray("result").getObject(0, SHCompanyInfo.class);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shCompanyInfo;
	}
	
	
	
	
	/**
	 * 解析存储数据
	 * @param shjsonData
	 * @return
	 */
	@Transactional//开启事务
	public ResultBean<Object> SHparsingStorage(String type, String result , String stockTypeId , String stockExchangeId) {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(!(StringUtils.isEmpty(result) && StringUtils.isEmpty(stockTypeId) && StringUtils.isEmpty(stockExchangeId))) {
			List<SHData> list2 = null;
			int cn = 0;//记录需要添加企业的数量
			int csn = 0;//记录需要添加证券的数量
			int companyNum = 0;//记录成功添加的数量
			int companyStockNum = 0;//记录成功添加的数据
			try {
				List<Company> list = companyMapper.selectAllCompany();
				
				//添加数据
				JSONArray jsonArray = JSON.parseObject(result).getJSONArray("result");
				list2 = jsonArray.toJavaList(SHData.class);
				if(list2.size() > 0) {
					for (SHData shData : list2) {
						SHCompanyInfo shCompanyInfo = null;
						String stockCode = "";
						//获取企业详情数据
						if("B".equals(type)) {
							shCompanyInfo = this.SHcrawlingCompanyInfo(type, shData.getSECURITY_CODE_B());
							stockCode = shData.getSECURITY_CODE_B();
						}else {
							shCompanyInfo = this.SHcrawlingCompanyInfo(type, shData.getSECURITY_CODE_A());
							stockCode = shData.getSECURITY_CODE_A();
						}
						
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						
						if(0 == list.size()) {
							String id = uuidutil.getUUID();
							//不存在数据
							cn++;
							Company company1 = new Company();
							company1.setId(id);
							company1.setChName(shCompanyInfo.getFULLNAME());
							company1.setChShortName(shCompanyInfo.getCOMPANY_ABBR());
							company1.setEnName(shCompanyInfo.getFULL_NAME_IN_ENGLISH());
							company1.setEnShortName(shCompanyInfo.getENGLISH_ABBR());
							company1.setRegisterTime(null);
							company1.setUrl(shCompanyInfo.getWWW_ADDRESS());
							company1.setDel("0");
							company1.setInsertTime(new Date());
							company1.setInsertUserId(user.getId());
							
							csn++;
							CompanyStock companyStock = new CompanyStock();
							companyStock.setId(uuidutil.getUUID());
							companyStock.setCompanyId(id);
							companyStock.setStockCode(stockCode);
							companyStock.setStockTypeId(stockTypeId);
							companyStock.setStockExchangeId(stockExchangeId);
							String date = shData.getLISTING_DATE();
							if(date.matches("\\d{2,4}-{1}\\d{1,2}-{1}\\d{1,2}")) {
								companyStock.setListingTime(simpleDateFormat.parse(date));
							}else {
								companyStock.setListingTime(null);
							}
							companyStock.setDel("0");
							companyStock.setInsertTime(new Date());
							companyStock.setInsertUserId(user.getId());
							
							companyMapper.insertCompany(company1);
							companyNum++;
							companyStockMapper.insertCompanyStock(companyStock);
							companyStockNum++;
							continue;
						}
						
						boolean a = true;
						boolean b = true;
						Company company = null;
						for (Company company1 : list) {
							//判断是否存在
							if(company1.getChName().equals(shCompanyInfo.getFULLNAME())) {
								//存在数据，只添加其他数据
								a = false;
								company = company1;
								
								CompanyStock companyStock = companyStockMapper.selectCompanyStockByCompanyIdAndStockTypeId(company1.getId(), stockTypeId);
								if(companyStock != null) {
									b = false;
									break;
								}
								break;
								
							}
							
						}
						
						if(a) {//企业数据不存在
							cn++;
							Company company1 = new Company();
							company1.setId(uuidutil.getUUID());
							company1.setChName(shCompanyInfo.getFULLNAME());
							company1.setChShortName(shCompanyInfo.getCOMPANY_ABBR());
							company1.setEnName(shCompanyInfo.getFULL_NAME_IN_ENGLISH());
							company1.setEnShortName(shCompanyInfo.getENGLISH_ABBR());
							company1.setRegisterTime(null);
							company1.setUrl(shCompanyInfo.getWWW_ADDRESS());
							company1.setDel("0");
							company1.setInsertTime(new Date());
							company1.setInsertUserId(user.getId());
							companyMapper.insertCompany(company1);
							company = company1;
							companyNum++;
						}
						
						if(b) {
							csn++;
							CompanyStock companyStock = new CompanyStock();
							companyStock.setId(uuidutil.getUUID());
							companyStock.setCompanyId(company.getId());
							companyStock.setStockCode(stockCode);
							companyStock.setStockTypeId(stockTypeId);
							companyStock.setStockExchangeId(stockExchangeId);
							String date = shData.getLISTING_DATE();
							if(date.matches("\\d{2,4}-{1}\\d{1,2}-{1}\\d{1,2}")) {
								companyStock.setListingTime(simpleDateFormat.parse(date));
							}else {
								companyStock.setListingTime(null);
							}
							companyStock.setDel("0");
							companyStock.setInsertTime(new Date());
							companyStock.setInsertUserId(user.getId());
							companyStockMapper.insertCompanyStock(companyStock);
							companyStockNum++;//计数器
						}
						
						
					}
					resultBean.setB(true);
					resultBean.setResult(list2.size() + ";" + cn + ";" + companyNum + ";" + csn + ";" + companyStockNum);
				}else {
					//数据请求完毕
					resultBean.setB(true);
					resultBean.setResult("0");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				resultBean.setB(false);
				resultBean.setResult(list2.size() + ";" + cn + ";" + companyNum + ";" + csn + ";" + companyStockNum);
			}
		}
		return resultBean;
	}



	
	

	public ResultBean<Object> crawlingShenzhen(String type, String stockTypeId, String stockExchangeId, int pageNo) {
		if(!(StringUtils.isEmpty(type) && StringUtils.isEmpty(stockTypeId) && StringUtils.isEmpty(stockExchangeId))) {
			String path = "http://www.szse.cn/api/report/ShowReport/data";
			String SHOWTYPE = "JSON";
			String CATALOGID = "1110";
			String TABKEY = "tab1";
			String PAGENO = String.valueOf(pageNo);
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
	            
	            resultUtil = this.SZparsingStorage(result, type, stockTypeId, stockExchangeId);
	            
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return resultUtil;
	}

	
	/**
	 * 
	 */
	public SZCompanyInfo SZcrawlingCompanyInfo(String type, String code) {
		SZCompanyInfo szCompanyInfo = null;
		String path = "http://www.szse.cn/api/report/index/companyGeneralization";
		String random = "0.0481434856715397";
		String secCode = code;
		if("B".equals(type)) {
			random = "0.2198368856605164";
		}
		
		URL url = null;
		try {
			url = new URL(path + "?random=" + random + "&secCode=" + secCode);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.addRequestProperty("Host", "www.szse.cn");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
			httpURLConnection.addRequestProperty("X-Request-Type", "ajax");
			httpURLConnection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
			httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
			httpURLConnection.addRequestProperty("Content-Type", "application/json");
			httpURLConnection.addRequestProperty("Referer", "http://www.szse.cn/certificate/individual/index.html?code=" + secCode);
			httpURLConnection.addRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.addRequestProperty("Accept-Language", "Accept-Language");
			httpURLConnection.connect();
			// 定义BufferedReader输入流来读取URL的响应
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            szCompanyInfo = JSON.parseObject(result).getObject("data", SZCompanyInfo.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return szCompanyInfo;
	}
	
	
	
	
	/**
	 * 
	 * @param result
	 * @param type
	 * @param stockTypeId
	 * @param stockExchangeId
	 * @param token
	 */
	public ResultBean<Object> SZparsingStorage(String result , String type, String stockTypeId , String stockExchangeId) {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(!(StringUtils.isEmpty(result) && StringUtils.isEmpty(stockTypeId) && StringUtils.isEmpty(stockExchangeId))) {
			JSONArray jsonArray = null;
			List<SZData> list2 = null;
			int cn = 0;//记录需要添加企业的数量
			int csn = 0;//记录需要添加证券的数量
			int companyNum = 0;//记录成功添加的数量
			int companyStockNum = 0;//记录成功添加的数据
	        if(type.equals("B")) {
	        	jsonArray = JSON.parseArray(result).getJSONObject(1).getJSONArray("data");
	        }else {
	        	jsonArray = JSON.parseArray(result).getJSONObject(0).getJSONArray("data");
			}
	        
	        try {
	        	List<Company> list = companyMapper.selectAllCompany();
	        	list2 = jsonArray.toJavaList(SZData.class);
	        	
	        	if(list2.size() > 0) {
	        		for (SZData szData : list2) {
		        		SZCompanyInfo szCompanyInfo = this.SZcrawlingCompanyInfo(type, szData.getBgdm());
		        		
		        		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        		if(0 == list.size()) {
			        		String id = uuidutil.getUUID();
							//不存在数据
							cn++;
							Company company1 = new Company();
							company1.setId(id);
							company1.setChName(szCompanyInfo.getGsqc());
							company1.setChShortName(szCompanyInfo.getAgjc());
							if("B".equals(type)) {
								company1.setChShortName(szCompanyInfo.getBgjc());
							}
							company1.setEnName(szCompanyInfo.getYwqc());
							company1.setEnShortName("");
							company1.setRegisterTime(simpleDateFormat.parse(szCompanyInfo.getBgssrq()));
							company1.setUrl(szCompanyInfo.getHttp());
							company1.setDel("0");
							company1.setInsertTime(new Date());
							company1.setInsertUserId(user.getId());
							
							csn++;
							CompanyStock companyStock = new CompanyStock();
							companyStock.setId(uuidutil.getUUID());
							companyStock.setCompanyId(id);
							companyStock.setStockCode(szData.getBgdm());
							companyStock.setStockTypeId(stockTypeId);
							companyStock.setStockExchangeId(stockExchangeId);
							companyStock.setListingTime(simpleDateFormat.parse(szData.getBgssrq()));
							companyStock.setDel("0");
							companyStock.setInsertTime(new Date());
							companyStock.setInsertUserId(user.getId());
							
							companyMapper.insertCompany(company1);
							companyNum++;
							companyStockMapper.insertCompanyStock(companyStock);
							companyStockNum++;
							continue;
			        	}
		        		
		        		boolean a = true;
						boolean b = true;
						Company company = null;
						for (Company company1 : list) {
							//判断是否存在
							if(company1.getChName().equals(szCompanyInfo.getGsqc())) {
								//存在数据，只添加其他数据
								a = false;
								company = company1;
								
								CompanyStock companyStock = companyStockMapper.selectCompanyStockByCompanyIdAndStockTypeId(company1.getId(), stockTypeId);
								if(companyStock != null) {
									b = false;
									break;
								}
								break;
							}
						}

						if(a) {//企业数据不存在
							cn++;
							Company company1 = new Company();
							company1.setId(uuidutil.getUUID());
							company1.setChName(szCompanyInfo.getGsqc());
							company1.setChShortName(szCompanyInfo.getAgjc());
							if("B".equals(type)) {
								company1.setChShortName(szCompanyInfo.getBgjc());
							}
							company1.setEnName(szCompanyInfo.getYwqc());
							company1.setEnShortName("");
							company1.setRegisterTime(simpleDateFormat.parse(szCompanyInfo.getBgssrq()));
							company1.setUrl(szCompanyInfo.getHttp());
							company1.setDel("0");
							company1.setInsertTime(new Date());
							company1.setInsertUserId(user.getId());
							companyMapper.insertCompany(company1);
							company = company1;
							companyNum++;
						}
						
						if(b) {
							csn++;
							CompanyStock companyStock = new CompanyStock();
							companyStock.setId(uuidutil.getUUID());
							companyStock.setCompanyId(company.getId());
							companyStock.setStockCode(szData.getBgdm());
							companyStock.setStockTypeId(stockTypeId);
							companyStock.setStockExchangeId(stockExchangeId);
							companyStock.setListingTime(simpleDateFormat.parse(szData.getBgssrq()));
							companyStock.setDel("0");
							companyStock.setInsertTime(new Date());
							companyStock.setInsertUserId(user.getId());
							companyStockMapper.insertCompanyStock(companyStock);
							companyStockNum++;//计数器
						}
						
						
					}
					resultBean.setB(true);
					resultBean.setResult(list2.size() + ";" + cn + ";" + companyNum + ";" + csn + ";" + companyStockNum);
		        	
	        	}else {
	        		resultBean.setB(true);
					resultBean.setResult("0");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				resultBean.setB(false);
				resultBean.setResult(list2.size() + ";" + cn + ";" + companyNum + ";" + csn + ";" + companyStockNum);
			}
		}
		return resultBean;
	}



	


	
	
	
}
