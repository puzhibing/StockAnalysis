package com.puzhibing.StockAnalysis.pojo;

import java.util.Date;

/**
 * 抽象共有的属性类
 * @author Administrator
 *
 */
public abstract class TotalBean {

	public String id;//数据id
	
	public String del;//删除状态（0：正常，-1删除）
	
	public String insertUserId;//添加人员id
	
	public Date insertTime;//添加时间
	
	public String updateUserId;//修改人员id
	
	public Date updateTime;//修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(String insertUserId) {
		this.insertUserId = insertUserId;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "TotalBean [id=" + id + ", del=" + del + ", insertUserId=" + insertUserId
				+ ", insertTime=" + insertTime + ", updateUserId=" + updateUserId + ", updateTime=" + updateTime + "]";
	}

	
	
	
	
}
