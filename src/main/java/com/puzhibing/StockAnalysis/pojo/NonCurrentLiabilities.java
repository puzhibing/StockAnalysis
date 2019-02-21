package com.puzhibing.StockAnalysis.pojo;


/**
 * 非流动负债
 * @author Administrator
 *
 */
public class NonCurrentLiabilities extends StockDataBean {

	private String ltl;//长期借款
	
	private String bondsPayable;//应付债券
	
	private String ltp;//长期应付款
	
	private String specialPayable;//专项应付款
	
	private String estimatedLiabilities;//预计负债

	private String deferredIncome;//递延收益
	
	private String ditl;//递延所得税负债
	
	private String dncl;//其他非流动负债

	private String tncl;//非流动负债合计

	public String getLtl() {
		return ltl;
	}

	public void setLtl(String ltl) {
		this.ltl = ltl;
	}

	public String getBondsPayable() {
		return bondsPayable;
	}

	public void setBondsPayable(String bondsPayable) {
		this.bondsPayable = bondsPayable;
	}

	public String getLtp() {
		return ltp;
	}

	public void setLtp(String ltp) {
		this.ltp = ltp;
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

	public String getDeferredIncome() {
		return deferredIncome;
	}

	public void setDeferredIncome(String deferredIncome) {
		this.deferredIncome = deferredIncome;
	}

	public String getDitl() {
		return ditl;
	}

	public void setDitl(String ditl) {
		this.ditl = ditl;
	}

	public String getDncl() {
		return dncl;
	}

	public void setDncl(String dncl) {
		this.dncl = dncl;
	}

	public String getTncl() {
		return tncl;
	}

	public void setTncl(String tncl) {
		this.tncl = tncl;
	}

	@Override
	public String toString() {
		return "NonCurrentLiabilities{" +
				"ltl='" + ltl + '\'' +
				", bondsPayable='" + bondsPayable + '\'' +
				", ltp='" + ltp + '\'' +
				", specialPayable='" + specialPayable + '\'' +
				", estimatedLiabilities='" + estimatedLiabilities + '\'' +
				", deferredIncome='" + deferredIncome + '\'' +
				", ditl='" + ditl + '\'' +
				", dncl='" + dncl + '\'' +
				", tncl='" + tncl + '\'' +
				'}';
	}
}
