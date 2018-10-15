package com.puzhibing.StockAnalysis.pojo;


/**
 * 非流动负债
 * @author Administrator
 *
 */
public class NonCurrentLiabilities extends StockDataBean {

	private String longTermLoan;//长期借款
	
	private String bondsPayable;//应付债券
	
	private String longTermPayables;//长期应付款
	
	private String specialPayable;//专项应付款
	
	private String estimatedLiabilities;//预计负债
	
	private String deferredIncomeTaxLiabilities;//递延所得税负债
	
	private String otherNonCurrentLiabilities;//其他非流动负债

	public String getLongTermLoan() {
		return longTermLoan;
	}

	public void setLongTermLoan(String longTermLoan) {
		this.longTermLoan = longTermLoan;
	}

	public String getBondsPayable() {
		return bondsPayable;
	}

	public void setBondsPayable(String bondsPayable) {
		this.bondsPayable = bondsPayable;
	}

	public String getLongTermPayables() {
		return longTermPayables;
	}

	public void setLongTermPayables(String longTermPayables) {
		this.longTermPayables = longTermPayables;
	}

	public String getSpecialPayable() {
		return specialPayable;
	}

	public void setSpecialPayable(String specialPayable) {
		this.specialPayable = specialPayable;
	}

	public String getEstimatedLiabilities() {
		return estimatedLiabilities;
	}

	public void setEstimatedLiabilities(String estimatedLiabilities) {
		this.estimatedLiabilities = estimatedLiabilities;
	}

	public String getDeferredIncomeTaxLiabilities() {
		return deferredIncomeTaxLiabilities;
	}

	public void setDeferredIncomeTaxLiabilities(String deferredIncomeTaxLiabilities) {
		this.deferredIncomeTaxLiabilities = deferredIncomeTaxLiabilities;
	}

	public String getOtherNonCurrentLiabilities() {
		return otherNonCurrentLiabilities;
	}

	public void setOtherNonCurrentLiabilities(String otherNonCurrentLiabilities) {
		this.otherNonCurrentLiabilities = otherNonCurrentLiabilities;
	}

	@Override
	public String toString() {
		return "NonCurrentLiabilities [longTermLoan=" + longTermLoan + ", bondsPayable=" + bondsPayable
				+ ", longTermPayables=" + longTermPayables + ", specialPayable=" + specialPayable
				+ ", estimatedLiabilities=" + estimatedLiabilities + ", deferredIncomeTaxLiabilities="
				+ deferredIncomeTaxLiabilities + ", otherNonCurrentLiabilities=" + otherNonCurrentLiabilities + "]";
	}
	
	
}
