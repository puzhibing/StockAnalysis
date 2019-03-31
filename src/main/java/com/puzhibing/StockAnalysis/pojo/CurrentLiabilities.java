package com.puzhibing.StockAnalysis.pojo;


/**
 * 流动负债
 * @author Administrator
 *
 */
public class CurrentLiabilities extends StockDataBean {

	private String stl;//短期借款
	
	private String uf;//拆入资金

	private String cwstb;//应付短期债券

	private String tfl;//交易性金融负债

	private String dfl;//衍生金融负债

	private String srfa;//卖出回购金融资产款
	
	private String billsPayable;//应付票据
	
	private String accountsPayable;//应付账款
	
	private String advancePayment;//预收款项
	
	private String payrollPayable;//应付职工薪酬
	
	private String taxesPayable;//应交税费
	
	private String interestPayable;//应付利息
	
	private String dividendPayable;//应付股利
	
	private String otherPayables;//其他应付款
	
	private String nldwoy;//一年内到期的非流动负债
	
	private String ocl;//其他流动负债

	private String tcl;//流动负债合计

	public String getStl() {
		return stl;
	}

	public void setStl(String stl) {
		this.stl = stl;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCwstb() {
		return cwstb;
	}

	public void setCwstb(String cwstb) {
		this.cwstb = cwstb;
	}

	public String getTfl() {
		return tfl;
	}

	public void setTfl(String tfl) {
		this.tfl = tfl;
	}

	public String getDfl() {
		return dfl;
	}

	public void setDfl(String dfl) {
		this.dfl = dfl;
	}

	public String getSrfa() {
		return srfa;
	}

	public void setSrfa(String srfa) {
		this.srfa = srfa;
	}

	public String getBillsPayable() {
		return billsPayable;
	}

	public void setBillsPayable(String billsPayable) {
		this.billsPayable = billsPayable;
	}

	public String getAccountsPayable() {
		return accountsPayable;
	}

	public void setAccountsPayable(String accountsPayable) {
		this.accountsPayable = accountsPayable;
	}

	public String getAdvancePayment() {
		return advancePayment;
	}

	public void setAdvancePayment(String advancePayment) {
		this.advancePayment = advancePayment;
	}

	public String getPayrollPayable() {
		return payrollPayable;
	}

	public void setPayrollPayable(String payrollPayable) {
		this.payrollPayable = payrollPayable;
	}

	public String getTaxesPayable() {
		return taxesPayable;
	}

	public void setTaxesPayable(String taxesPayable) {
		this.taxesPayable = taxesPayable;
	}

	public String getInterestPayable() {
		return interestPayable;
	}

	public void setInterestPayable(String interestPayable) {
		this.interestPayable = interestPayable;
	}

	public String getDividendPayable() {
		return dividendPayable;
	}

	public void setDividendPayable(String dividendPayable) {
		this.dividendPayable = dividendPayable;
	}

	public String getOtherPayables() {
		return otherPayables;
	}

	public void setOtherPayables(String otherPayables) {
		this.otherPayables = otherPayables;
	}

	public String getNldwoy() {
		return nldwoy;
	}

	public void setNldwoy(String nldwoy) {
		this.nldwoy = nldwoy;
	}

	public String getOcl() {
		return ocl;
	}

	public void setOcl(String ocl) {
		this.ocl = ocl;
	}

	public String getTcl() {
		return tcl;
	}

	public void setTcl(String tcl) {
		this.tcl = tcl;
	}


	@Override
	public String toString() {
		return "CurrentLiabilities{" +
				"stl='" + stl + '\'' +
				", uf='" + uf + '\'' +
				", cwstb='" + cwstb + '\'' +
				", tfl='" + tfl + '\'' +
				", dfl='" + dfl + '\'' +
				", srfa='" + srfa + '\'' +
				", billsPayable='" + billsPayable + '\'' +
				", accountsPayable='" + accountsPayable + '\'' +
				", advancePayment='" + advancePayment + '\'' +
				", payrollPayable='" + payrollPayable + '\'' +
				", taxesPayable='" + taxesPayable + '\'' +
				", interestPayable='" + interestPayable + '\'' +
				", dividendPayable='" + dividendPayable + '\'' +
				", otherPayables='" + otherPayables + '\'' +
				", nldwoy='" + nldwoy + '\'' +
				", ocl='" + ocl + '\'' +
				", tcl='" + tcl + '\'' +
				'}';
	}
}
