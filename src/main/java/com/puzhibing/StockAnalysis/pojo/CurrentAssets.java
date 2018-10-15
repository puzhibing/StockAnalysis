package com.puzhibing.StockAnalysis.pojo;


/**
 * 流动资产
 * @author Administrator
 *
 */
public class CurrentAssets extends StockDataBean {
	
	private String moneyFunds;//货币资金
	
	private String transactionalFinancialAssets;//交易性金融资产
	
	private String billReceivable;//应收票据
	
	private String accountsReceivable;//应收账款
	
	private String prepayments;//预付账款
	
	private String interestReceivable;//应收利息
	
	private String dividendReceivable;//应收股利
	
	private String otherReceivables;//其他应收款
	
	private String stock;//存货
	
	private String nonCurrentAssetsDueWithinOneYear;//一年内到期的非流动资产
	
	private String otherCurrentAssets;//其他流动资产

	public String getMoneyFunds() {
		return moneyFunds;
	}

	public void setMoneyFunds(String moneyFunds) {
		this.moneyFunds = moneyFunds;
	}

	public String getTransactionalFinancialAssets() {
		return transactionalFinancialAssets;
	}

	public void setTransactionalFinancialAssets(String transactionalFinancialAssets) {
		this.transactionalFinancialAssets = transactionalFinancialAssets;
	}

	public String getBillReceivable() {
		return billReceivable;
	}

	public void setBillReceivable(String billReceivable) {
		this.billReceivable = billReceivable;
	}

	public String getAccountsReceivable() {
		return accountsReceivable;
	}

	public void setAccountsReceivable(String accountsReceivable) {
		this.accountsReceivable = accountsReceivable;
	}

	public String getPrepayments() {
		return prepayments;
	}

	public void setPrepayments(String prepayments) {
		this.prepayments = prepayments;
	}

	public String getInterestReceivable() {
		return interestReceivable;
	}

	public void setInterestReceivable(String interestReceivable) {
		this.interestReceivable = interestReceivable;
	}

	public String getDividendReceivable() {
		return dividendReceivable;
	}

	public void setDividendReceivable(String dividendReceivable) {
		this.dividendReceivable = dividendReceivable;
	}

	public String getOtherReceivables() {
		return otherReceivables;
	}

	public void setOtherReceivables(String otherReceivables) {
		this.otherReceivables = otherReceivables;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getNonCurrentAssetsDueWithinOneYear() {
		return nonCurrentAssetsDueWithinOneYear;
	}

	public void setNonCurrentAssetsDueWithinOneYear(String nonCurrentAssetsDueWithinOneYear) {
		this.nonCurrentAssetsDueWithinOneYear = nonCurrentAssetsDueWithinOneYear;
	}

	public String getOtherCurrentAssets() {
		return otherCurrentAssets;
	}

	public void setOtherCurrentAssets(String otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}

	@Override
	public String toString() {
		return "CurrentAssets [moneyFunds=" + moneyFunds + ", transactionalFinancialAssets="
				+ transactionalFinancialAssets + ", billReceivable=" + billReceivable + ", accountsReceivable="
				+ accountsReceivable + ", prepayments=" + prepayments + ", interestReceivable=" + interestReceivable
				+ ", dividendReceivable=" + dividendReceivable + ", otherReceivables=" + otherReceivables + ", stock="
				+ stock + ", nonCurrentAssetsDueWithinOneYear=" + nonCurrentAssetsDueWithinOneYear
				+ ", otherCurrentAssets=" + otherCurrentAssets + "]";
	}
	
	
	
}
