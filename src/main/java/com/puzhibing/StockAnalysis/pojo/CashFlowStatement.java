package com.puzhibing.StockAnalysis.pojo;

/**
 * 现金流附注
 */
public class CashFlowStatement extends StockDataBean {

    private String np;//净利润

    private String aip;//资产减值准备

    private String dofagadadopba;//固定资产折旧、汽油资产拆耗、生产性生物资产折旧

    private String aoia;//无形资产摊销

    private String aoltpe;//长期待摊费用摊销

    private String loiffaiaaolta;//处置固定资产、无形资产和其他长期资产的损失（收益）

    private String losofa;//固定资产报废损失

    private String lofvc;//公允值变动损失

    private String fc;//财务费用

    private String ll;//投资损失

    private String ditad;//递延所得税资产减少

    private String iiiditl;//递延所得税负债增加

    private String lr;//存货的减少

    private String diori;//经营性应收项目的减少

    private String iiopi;//经营性应付项目的增加

    private String other;//其他

    private String ncffoac;//经营活动产生的现金流量净额

    private String dtc;//债务转为资本

    private String scbdwoy;//一年内到期的可转换公司债券

    private String flofa;//融资租入固定资产

    private String cateotp;//现金的期末余额

    private String iboc;//现金的期初余额

    private String eboce;//现金等价物的期末余额

    private String iboce;//现金等价物的期初余额
    
    private String niicace;//现金及现金等价物净增加额

	public String getNp() {
		return np;
	}

	public void setNp(String np) {
		this.np = np;
	}

	public String getAip() {
		return aip;
	}

	public void setAip(String aip) {
		this.aip = aip;
	}

	public String getDofagadadopba() {
		return dofagadadopba;
	}

	public void setDofagadadopba(String dofagadadopba) {
		this.dofagadadopba = dofagadadopba;
	}

	public String getAoia() {
		return aoia;
	}

	public void setAoia(String aoia) {
		this.aoia = aoia;
	}

	public String getAoltpe() {
		return aoltpe;
	}

	public void setAoltpe(String aoltpe) {
		this.aoltpe = aoltpe;
	}

	public String getLoiffaiaaolta() {
		return loiffaiaaolta;
	}

	public void setLoiffaiaaolta(String loiffaiaaolta) {
		this.loiffaiaaolta = loiffaiaaolta;
	}

	public String getLosofa() {
		return losofa;
	}

	public void setLosofa(String losofa) {
		this.losofa = losofa;
	}

	public String getLofvc() {
		return lofvc;
	}

	public void setLofvc(String lofvc) {
		this.lofvc = lofvc;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public String getLl() {
		return ll;
	}

	public void setLl(String ll) {
		this.ll = ll;
	}

	public String getDitad() {
		return ditad;
	}

	public void setDitad(String ditad) {
		this.ditad = ditad;
	}

	public String getIiiditl() {
		return iiiditl;
	}

	public void setIiiditl(String iiiditl) {
		this.iiiditl = iiiditl;
	}

	public String getLr() {
		return lr;
	}

	public void setLr(String lr) {
		this.lr = lr;
	}

	public String getDiori() {
		return diori;
	}

	public void setDiori(String diori) {
		this.diori = diori;
	}

	public String getIiopi() {
		return iiopi;
	}

	public void setIiopi(String iiopi) {
		this.iiopi = iiopi;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getNcffoac() {
		return ncffoac;
	}

	public void setNcffoac(String ncffoac) {
		this.ncffoac = ncffoac;
	}

	public String getDtc() {
		return dtc;
	}

	public void setDtc(String dtc) {
		this.dtc = dtc;
	}

	public String getScbdwoy() {
		return scbdwoy;
	}

	public void setScbdwoy(String scbdwoy) {
		this.scbdwoy = scbdwoy;
	}

	public String getFlofa() {
		return flofa;
	}

	public void setFlofa(String flofa) {
		this.flofa = flofa;
	}

	public String getCateotp() {
		return cateotp;
	}

	public void setCateotp(String cateotp) {
		this.cateotp = cateotp;
	}

	public String getIboc() {
		return iboc;
	}

	public void setIboc(String iboc) {
		this.iboc = iboc;
	}

	public String getEboce() {
		return eboce;
	}

	public void setEboce(String eboce) {
		this.eboce = eboce;
	}

	public String getIboce() {
		return iboce;
	}

	public void setIboce(String iboce) {
		this.iboce = iboce;
	}

	public String getNiicace() {
		return niicace;
	}

	public void setNiicace(String niicace) {
		this.niicace = niicace;
	}

	@Override
	public String toString() {
		return "CashFlowStatement{" +
				"np='" + np + '\'' +
				", aip='" + aip + '\'' +
				", dofagadadopba='" + dofagadadopba + '\'' +
				", aoia='" + aoia + '\'' +
				", aoltpe='" + aoltpe + '\'' +
				", loiffaiaaolta='" + loiffaiaaolta + '\'' +
				", losofa='" + losofa + '\'' +
				", lofvc='" + lofvc + '\'' +
				", fc='" + fc + '\'' +
				", ll='" + ll + '\'' +
				", ditad='" + ditad + '\'' +
				", iiiditl='" + iiiditl + '\'' +
				", lr='" + lr + '\'' +
				", diori='" + diori + '\'' +
				", iiopi='" + iiopi + '\'' +
				", other='" + other + '\'' +
				", ncffoac='" + ncffoac + '\'' +
				", dtc='" + dtc + '\'' +
				", scbdwoy='" + scbdwoy + '\'' +
				", flofa='" + flofa + '\'' +
				", cateotp='" + cateotp + '\'' +
				", iboc='" + iboc + '\'' +
				", eboce='" + eboce + '\'' +
				", iboce='" + iboce + '\'' +
				", niicace='" + niicace + '\'' +
				'}';
	}
}
