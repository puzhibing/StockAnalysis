package com.puzhibing.StockAnalysis.pojo;


/**
 * 流动负债
 * @author Administrator
 *
 */
public class CurrentLiabilities extends StockDataBean {

	private String shortTermLoan;//短期借款
	
	private String transactionalFinancialLiabilities;//交易性金融负债
	
	private String billsPayable;//应付票据
	
	private String accountsPayable;//应付账款
	
	private String advancePayment;//预收款项
	
	private String payrollPayable;//应付职工薪酬
	
	private String taxesPayable;//应交税费
	
	private String interestPayable;//应付利息
	
	private String dividendPayable;//应付股利
	
	private String otherPayables;//其他应付款
	
	private String nonCurrentLiabilitiesDueWithinOneYear;//一年内到期的非流动负债
	
	private String otherCurrentLiabilities;//其他流动负债

	public String getShortTermLoan() {
		return shortTermLoan;
	}

	public void setShortTermLoan(String shortTermLoan) {
		this.shortTermLoan = shortTermLoan;
	}

	public String getTransactionalFinancialLiabilities() {
		return transactionalFinancialLiabilities;
	}

	public void setTransactionalFinancialLiabilities(String transactionalFinancialLiabilities) {
		this.transactionalFinancialLiabilities = transactionalFinancialLiabilities;
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

	public String getNonCurrentLiabilitiesDueWithinOneYear() {
		return nonCurrentLiabilitiesDueWithinOneYear;
	}

	public void setNonCurrentLiabilitiesDueWithinOneYear(String nonCurrentLiabilitiesDueWithinOneYear) {
		this.nonCurrentLiabilitiesDueWithinOneYear = nonCurrentLiabilitiesDueWithinOneYear;
	}

	public String getOtherCurrentLiabilities() {
		return otherCurrentLiabilities;
	}

	public void setOtherCurrentLiabilities(String otherCurrentLiabilities) {
		this.otherCurrentLiabilities = otherCurrentLiabilities;
	}

	@Override
	public String toString() {
		return "CurrentLiabilities [shortTermLoan=" + shortTermLoan + ", transactionalFinancialLiabilities="
				+ transactionalFinancialLiabilities + ", billsPayable=" + billsPayable + ", accountsPayable="
				+ accountsPayable + ", advancePayment=" + advancePayment + ", payrollPayable=" + payrollPayable
				+ ", taxesPayable=" + taxesPayable + ", interestPayable=" + interestPayable + ", dividendPayable="
				+ dividendPayable + ", otherPayables=" + otherPayables + ", nonCurrentLiabilitiesDueWithinOneYear="
				+ nonCurrentLiabilitiesDueWithinOneYear + ", otherCurrentLiabilities=" + otherCurrentLiabilities + "]";
	}
	
	
}
