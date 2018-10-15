package com.puzhibing.StockAnalysis.pojo;

/**
 * 非流动资产
 * @author Administrator
 *
 */
public class NonCurrentAssets extends StockDataBean {

	private String availableForSaleFinancialAssets;//可供出售金融资产
	
	private String holdingAnExpiredInvestment;//持有到期投资
	
	private String longTermReceivables;//长期应收款
	
	private String longTermEquityInvestment;//长期股权投资
	
	private String investmentRealEstate;//投资性房地产
	
	private String fixedAssets;//固定资产
	
	private String constructionInProgress;//在建工程
	
	private String engineerMaterial;//工程物资
	
	private String fixedAssetsCleanup;//固定资产清理
	
	private String productiveBiologicalAssets;//生产性生物资产
	
	private String gasolineAssets;//汽油资产
	
	private String intangibleAssets;//无形资产
	
	private String developmentExpenditure;//开发支出
	
	private String goodwill;//商誉
	
	private String longTermPrepaidExpenses;//长期待摊费用
	
	private String deferredTaxAssets;//递延所得税资产
	
	private String otherNonCurrentAssets;//其他非流动资产

	public String getAvailableForSaleFinancialAssets() {
		return availableForSaleFinancialAssets;
	}

	public void setAvailableForSaleFinancialAssets(String availableForSaleFinancialAssets) {
		this.availableForSaleFinancialAssets = availableForSaleFinancialAssets;
	}

	public String getHoldingAnExpiredInvestment() {
		return holdingAnExpiredInvestment;
	}

	public void setHoldingAnExpiredInvestment(String holdingAnExpiredInvestment) {
		this.holdingAnExpiredInvestment = holdingAnExpiredInvestment;
	}

	public String getLongTermReceivables() {
		return longTermReceivables;
	}

	public void setLongTermReceivables(String longTermReceivables) {
		this.longTermReceivables = longTermReceivables;
	}

	public String getLongTermEquityInvestment() {
		return longTermEquityInvestment;
	}

	public void setLongTermEquityInvestment(String longTermEquityInvestment) {
		this.longTermEquityInvestment = longTermEquityInvestment;
	}

	public String getInvestmentRealEstate() {
		return investmentRealEstate;
	}

	public void setInvestmentRealEstate(String investmentRealEstate) {
		this.investmentRealEstate = investmentRealEstate;
	}

	public String getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(String fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public String getConstructionInProgress() {
		return constructionInProgress;
	}

	public void setConstructionInProgress(String constructionInProgress) {
		this.constructionInProgress = constructionInProgress;
	}

	public String getEngineerMaterial() {
		return engineerMaterial;
	}

	public void setEngineerMaterial(String engineerMaterial) {
		this.engineerMaterial = engineerMaterial;
	}

	public String getFixedAssetsCleanup() {
		return fixedAssetsCleanup;
	}

	public void setFixedAssetsCleanup(String fixedAssetsCleanup) {
		this.fixedAssetsCleanup = fixedAssetsCleanup;
	}

	public String getProductiveBiologicalAssets() {
		return productiveBiologicalAssets;
	}

	public void setProductiveBiologicalAssets(String productiveBiologicalAssets) {
		this.productiveBiologicalAssets = productiveBiologicalAssets;
	}

	public String getGasolineAssets() {
		return gasolineAssets;
	}

	public void setGasolineAssets(String gasolineAssets) {
		this.gasolineAssets = gasolineAssets;
	}

	public String getIntangibleAssets() {
		return intangibleAssets;
	}

	public void setIntangibleAssets(String intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}

	public String getDevelopmentExpenditure() {
		return developmentExpenditure;
	}

	public void setDevelopmentExpenditure(String developmentExpenditure) {
		this.developmentExpenditure = developmentExpenditure;
	}

	public String getGoodwill() {
		return goodwill;
	}

	public void setGoodwill(String goodwill) {
		this.goodwill = goodwill;
	}

	public String getLongTermPrepaidExpenses() {
		return longTermPrepaidExpenses;
	}

	public void setLongTermPrepaidExpenses(String longTermPrepaidExpenses) {
		this.longTermPrepaidExpenses = longTermPrepaidExpenses;
	}

	public String getDeferredTaxAssets() {
		return deferredTaxAssets;
	}

	public void setDeferredTaxAssets(String deferredTaxAssets) {
		this.deferredTaxAssets = deferredTaxAssets;
	}

	public String getOtherNonCurrentAssets() {
		return otherNonCurrentAssets;
	}

	public void setOtherNonCurrentAssets(String otherNonCurrentAssets) {
		this.otherNonCurrentAssets = otherNonCurrentAssets;
	}

	@Override
	public String toString() {
		return "NonCurrentAssets [availableForSaleFinancialAssets=" + availableForSaleFinancialAssets
				+ ", holdingAnExpiredInvestment=" + holdingAnExpiredInvestment + ", longTermReceivables="
				+ longTermReceivables + ", longTermEquityInvestment=" + longTermEquityInvestment
				+ ", investmentRealEstate=" + investmentRealEstate + ", fixedAssets=" + fixedAssets
				+ ", constructionInProgress=" + constructionInProgress + ", engineerMaterial=" + engineerMaterial
				+ ", fixedAssetsCleanup=" + fixedAssetsCleanup + ", productiveBiologicalAssets="
				+ productiveBiologicalAssets + ", gasolineAssets=" + gasolineAssets + ", intangibleAssets="
				+ intangibleAssets + ", developmentExpenditure=" + developmentExpenditure + ", goodwill=" + goodwill
				+ ", longTermPrepaidExpenses=" + longTermPrepaidExpenses + ", deferredTaxAssets=" + deferredTaxAssets
				+ ", otherNonCurrentAssets=" + otherNonCurrentAssets + "]";
	}
	
	
}
