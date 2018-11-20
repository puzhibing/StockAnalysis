package com.puzhibing.StockAnalysis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.puzhibing.StockAnalysis.controller.UserController;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.CrawlingCompanyDate;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class StockAnalysisApplicationTests {
	
	@Autowired
	private CrawlingCompanyDate crawlingCompanyDate;
	
	@Autowired
	private UserController userController;

	@SuppressWarnings("rawtypes")
	@Test
	public void contextLoads() {
		ResultBean resultBean = userController.verifyLogin("admin", "admin");
		if(resultBean.getB()) {
			ResultBean resultBean1 = crawlingCompanyDate.crawlingCompany("A", "", "", "",
					(String)resultBean.getResult());
			System.out.println(resultBean1.getResult());
		}
	}

}
