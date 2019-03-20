package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.IndustrySql;
import com.puzhibing.StockAnalysis.pojo.Industry;

public interface IndustryMapper {

	
	/**
	 * 根据code查询数据
	 * @param code
	 * @return
	 */
	@SelectProvider(type = IndustrySql.class , method = "selectIndustryByCode")
	Industry selectIndustryByCode(String code);


	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	@SelectProvider(type = IndustrySql.class , method = "selectIndustryById")
	Industry selectIndustryById(String id);
	
	
	/**
	 * 添加数据
	 * @param industry
	 */
	@InsertProvider(type = IndustrySql.class , method = "insertIndustry")
	void insertIndustry(Industry industry);
	
	
	
	/**
	 * 修改数据
	 * @param industry
	 */
	@UpdateProvider(type = IndustrySql.class , method = "updateIndustry")
	void updateIndustry(Industry industry);
	
	
	/**
	 * 删除数据
	 * @param industry
	 */
	@DeleteProvider(type = IndustrySql.class , method = "deleteIndustry")
	void deleteIndustry(Industry industry);
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@SelectProvider(type = IndustrySql.class , method = "selectAllIndustry")
	List<Industry> selectAllIndustry();


	/**
	 * 根据父类id查询数据
	 * @param parentId
	 * @return
	 */
	@SelectProvider(type = IndustrySql.class , method = "selectDataByParentId")
	List<Industry> selectDataByParentId(String parentId);
}
