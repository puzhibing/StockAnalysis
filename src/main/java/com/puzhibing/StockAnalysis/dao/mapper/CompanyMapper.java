package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.CompanySql;
import com.puzhibing.StockAnalysis.pojo.Company;

public interface CompanyMapper {

	
	/**
	 * 添加数据
	 * @param company
	 */
	@InsertProvider(type = CompanySql.class , method = "insertCompany")
	void insertCompany(Company company);
	
	
	
	
	/**
	 * 修改数据
	 * @param company
	 */
	@UpdateProvider(type = CompanySql.class , method = "updateCompany")
	void updateCompany(Company company);
	
	
	
	/**
	 * 删除数据
	 * @param company
	 */
	@UpdateProvider(type = CompanySql.class , method = "deleteCompany")
	void deleteCompany(Company company);
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@SelectProvider(type = CompanySql.class , method = "selectAllCompany")
	List<Company> selectAllCompany();
	
	
	
	
	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 */
	@SelectProvider(type = CompanySql.class , method = "selectCompanyById")
	@Results({
		@Result(property = "id" , column = "id" , id = true),
		@Result(property = "chName" , column = "chName"),
		@Result(property = "chShortName" , column = "chShortName"),
		@Result(property = "enName" , column = "enName"),
		@Result(property = "enShortName" , column = "enShortName"),
		@Result(property = "registerTime" , column = "registerTime"),
		@Result(property = "industry" , column = "id" , many=@Many(
				select = "com.puzhibing.StockAnalysis.dao.mapper.IndustryMapper.selectIndustryById"
				)),
		@Result(property = "url" , column = "url"),
		@Result(property = "companyStocks" , column = "id" , many=@Many(
				select = "com.puzhibing.StockAnalysis.dao.mapper.CompanyStockMapper.selectCompanyStockByCompanyId"
				)),
		@Result(property = "del" , column = "del"),
		@Result(property = "insertUserId" , column = "insertUserId"),
		@Result(property = "insertTime" , column = "insertTime"),
		@Result(property = "updateUserId" , column = "updateUserId"),
		@Result(property = "updateTime" , column = "updateTime")
	})
	Company selectCompanyInfoById(String id);
	
	
	
	
	/**
	 * 根据英文或中文名称动态查询
	 * @param name
	 * @return
	 */
	@SelectProvider(type = CompanySql.class , method = "selectCompanyLikeName")
	List<Company> selectCompanyLikeName(String name);
	
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	@SelectProvider(type = CompanySql.class , method = "selectCompanyById")
	Company selectCompanyById(String id);
}
