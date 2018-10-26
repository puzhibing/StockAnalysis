package com.puzhibing.StockAnalysis.pojo;

/**
 * 非流动资产
 * @author Administrator
 *
 */
public class NonCurrentAssets extends StockDataBean {

	private String afsfa;//可供出售金融资产
	
	private String haei;//持有到期投资
	
	private String ltr;//长期应收款
	
	private String ltbi;//长期股权投资
	
	private String ire;//投资性房地产
	
	private String fixedAssets;//固定资产
	
	private String cap;//在建工程
	
	private String engineerMaterial;//工程物资
	
	private String fac;//固定资产清理
	
	private String pba;//生产性生物资产
	
	private String gasolineAssets;//汽油资产
	
	private String intangibleAssets;//无形资产
	
	private String de;//开发支出
	
	private String goodwill;//商誉
	
	private String ltpe;//长期待摊费用
	
	private String dta;//递延所得税资产
	
	private String onca;//其他非流动资产

	private String tnca;//非流动资产合计

	public String getAfsfa() {
		return afsfa;
	}

	public void setAfsfa(String afsfa) {
		this.afsfa = afsfa;
	}

	public String getHaei() {
		return haei;
	}

	public void setHaei(String haei) {
		this.haei = haei;
	}

	public String getLtr() {
		return ltr;
	}

	public void setLtr(String ltr) {
		this.ltr = ltr;
	}

	public String getLtbi() {
		return ltbi;
	}

	public void setLtbi(String ltbi) {
		this.ltbi = ltbi;
	}

	public String getIre() {
		return ire;
	}

	public void setIre(String ire) {
		this.ire = ire;
	}

	public String getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(String fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getEngineerMaterial() {
		return engineerMaterial;
	}

	public void setEngineerMaterial(String engineerMaterial) {
		this.engineerMaterial = engineerMaterial;
	}

	public String getFac() {
		return fac;
	}

	public void setFac(String fac) {
		this.fac = fac;
	}

	public String getPba() {
		return pba;
	}

	public void setPba(String pba) {
		this.pba = pba;
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

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getGoodwill() {
		return goodwill;
	}

	public void setGoodwill(String goodwill) {
		this.goodwill = goodwill;
	}

	public String getLtpe() {
		return ltpe;
	}

	public void setLtpe(String ltpe) {
		this.ltpe = ltpe;
	}

	public String getDta() {
		return dta;
	}

	public void setDta(String dta) {
		this.dta = dta;
	}

	public String getOnca() {
		return onca;
	}

	public void setOnca(String onca) {
		this.onca = onca;
	}

	public String getTnca() {
		return tnca;
	}

	public void setTnca(String tnca) {
		this.tnca = tnca;
	}

	@Override
	public String toString() {
		return "NonCurrentAssets{" +
				"afsfa='" + afsfa + '\'' +
				", haei='" + haei + '\'' +
				", ltr='" + ltr + '\'' +
				", ltbi='" + ltbi + '\'' +
				", ire='" + ire + '\'' +
				", fixedAssets='" + fixedAssets + '\'' +
				", cap='" + cap + '\'' +
				", engineerMaterial='" + engineerMaterial + '\'' +
				", fac='" + fac + '\'' +
				", pba='" + pba + '\'' +
				", gasolineAssets='" + gasolineAssets + '\'' +
				", intangibleAssets='" + intangibleAssets + '\'' +
				", de='" + de + '\'' +
				", goodwill='" + goodwill + '\'' +
				", ltpe='" + ltpe + '\'' +
				", dta='" + dta + '\'' +
				", onca='" + onca + '\'' +
				", tnca='" + tnca + '\'' +
				'}';
	}
}
