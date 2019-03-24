package com.puzhibing.StockAnalysis.pojo;


/**
 * 流动资产
 * @author Administrator
 *
 */
public class CurrentAssets extends StockDataBean {
	
	private String moneyFunds;//货币资金

	private String wof;//拆出资金
	
	private String tfa;//交易性金融资产

	private String dfa;//衍生金融资产

	private String bbrfa;//买入返售金融资产
	
	private String billReceivable;//应收票据
	
	private String accountsReceivable;//应收账款
	
	private String prepayments;//预付账款
	
	private String interestReceivable;//应收利息
	
	private String dividendReceivable;//应收股利
	
	private String otherReceivables;//其他应收款
	
	private String stock;//存货

	private String diahfs;//划分为持有待售的资产
	
	private String ncadwoy;//一年内到期的非流动资产
	
	private String oca;//其他流动资产
	
	private String tca;//流动资产合计

	public String getMoneyFunds() {
		return moneyFunds;
	}

	public void setMoneyFunds(String moneyFunds) {
		this.moneyFunds = moneyFunds;
	}

	public String getWof() {
		return wof;
	}

	public void setWof(String wof) {
		this.wof = wof;
	}

	public String getTfa() {
		return tfa;
	}

	public void setTfa(String tfa) {
		this.tfa = tfa;
	}

	public String getDfa() {
		return dfa;
	}

	public void setDfa(String dfa) {
		this.dfa = dfa;
	}

	public String getBbrfa() {
		return bbrfa;
	}

	public void setBbrfa(String bbrfa) {
		this.bbrfa = bbrfa;
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

	public String getDiahfs() {
		return diahfs;
	}

	public void setDiahfs(String diahfs) {
		this.diahfs = diahfs;
	}

	public String getNcadwoy() {
		return ncadwoy;
	}

	public void setNcadwoy(String ncadwoy) {
		this.ncadwoy = ncadwoy;
	}

	public String getOca() {
		return oca;
	}

	public void setOca(String oca) {
		this.oca = oca;
	}

	public String getTca() {
		return tca;
	}

	public void setTca(String tca) {
		this.tca = tca;
	}

	@Override
	public String toString() {
		return "CurrentAssets{" +
				"moneyFunds='" + moneyFunds + '\'' +
				", wof='" + wof + '\'' +
				", tfa='" + tfa + '\'' +
				", dfa='" + dfa + '\'' +
				", bbrfa='" + bbrfa + '\'' +
				", billReceivable='" + billReceivable + '\'' +
				", accountsReceivable='" + accountsReceivable + '\'' +
				", prepayments='" + prepayments + '\'' +
				", interestReceivable='" + interestReceivable + '\'' +
				", dividendReceivable='" + dividendReceivable + '\'' +
				", otherReceivables='" + otherReceivables + '\'' +
				", stock='" + stock + '\'' +
				", diahfs='" + diahfs + '\'' +
				", ncadwoy='" + ncadwoy + '\'' +
				", oca='" + oca + '\'' +
				", tca='" + tca + '\'' +
				'}';
	}
}
