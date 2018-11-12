package com.puzhibing.StockAnalysis;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.puzhibing.StockAnalysis.service.CrawlingCompanyDate;
import com.puzhibing.StockAnalysis.service.impl.CrawlingCompanyDateImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StockAnalysisApplicationTests {

	@Test
	public void contextLoads() {
		CrawlingCompanyDate cr = new CrawlingCompanyDateImpl();
		try {
			cr.crawlingShanghai("B", "OI", "IO", "IKOL");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
