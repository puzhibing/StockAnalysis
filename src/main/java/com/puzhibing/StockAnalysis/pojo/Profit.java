package com.puzhibing.StockAnalysis.pojo;


/**
 * 利润
 * @author Administrator
 *
 */
public class Profit extends StockDataBean {

    private String businessIncome;//营业收入

    private String interestIncome;//利息收入

    private String earnedPremium;//已赚保费

    private String faci;//手续费及佣金收入

    private String toi;//营业总收入

    private String operatingCost;//营业成本

    private String btaa;//营业税金及附加

    private String sellingExpenses;//销售费用

    private String managementCost;//管理费用

    private String financialCost;//财务费用

    private String ail;//资产减值损失

    private String toc;//营业总成本

    private String fvci;//公允价值变动收益
    
    private String adi;//资产处置收益

    private String ifi;//投资收益

    private String iiojvajv;//联营企业和合营企业投资收益

    private String oii;//其他投资收益

    private String exchangeGains;//汇兑收益

    private String otherIncome;//其他收益

    private String operatingProfit;//营业利润

    private String noi;//营业外收入

    private String noe;//营业外支出

    private String totalProfit;//利润总额

    private String ite;//所得税费用

    private String netProfit;//净利润

    private String natfoci;//其他综合收益的税后净额

    private String tci;//综合收益总额

    private String eps;//每股收益

    private String beps;//基本每股收益

    private String deps;//稀释每股收益

    public String getBusinessIncome() {
        return businessIncome;
    }

    public void setBusinessIncome(String businessIncome) {
        this.businessIncome = businessIncome;
    }

    public String getInterestIncome() {
        return interestIncome;
    }

    public void setInterestIncome(String interestIncome) {
        this.interestIncome = interestIncome;
    }

    public String getEarnedPremium() {
        return earnedPremium;
    }

    public void setEarnedPremium(String earnedPremium) {
        this.earnedPremium = earnedPremium;
    }

    public String getFaci() {
        return faci;
    }

    public void setFaci(String faci) {
        this.faci = faci;
    }

    public String getToi() {
        return toi;
    }

    public void setToi(String toi) {
        this.toi = toi;
    }

    public String getOperatingCost() {
        return operatingCost;
    }

    public void setOperatingCost(String operatingCost) {
        this.operatingCost = operatingCost;
    }

    public String getBtaa() {
        return btaa;
    }

    public void setBtaa(String btaa) {
        this.btaa = btaa;
    }

    public String getSellingExpenses() {
        return sellingExpenses;
    }

    public void setSellingExpenses(String sellingExpenses) {
        this.sellingExpenses = sellingExpenses;
    }

    public String getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(String managementCost) {
        this.managementCost = managementCost;
    }

    public String getFinancialCost() {
        return financialCost;
    }

    public void setFinancialCost(String financialCost) {
        this.financialCost = financialCost;
    }

    public String getAil() {
        return ail;
    }

    public void setAil(String ail) {
        this.ail = ail;
    }

    public String getToc() {
        return toc;
    }

    public void setToc(String toc) {
        this.toc = toc;
    }

    public String getFvci() {
        return fvci;
    }

    public void setFvci(String fvci) {
        this.fvci = fvci;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getIfi() {
        return ifi;
    }

    public void setIfi(String ifi) {
        this.ifi = ifi;
    }

    public String getIiojvajv() {
        return iiojvajv;
    }

    public void setIiojvajv(String iiojvajv) {
        this.iiojvajv = iiojvajv;
    }

    public String getOii() {
        return oii;
    }

    public void setOii(String oii) {
        this.oii = oii;
    }

    public String getExchangeGains() {
        return exchangeGains;
    }

    public void setExchangeGains(String exchangeGains) {
        this.exchangeGains = exchangeGains;
    }

    public String getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(String otherIncome) {
        this.otherIncome = otherIncome;
    }

    public String getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(String operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public String getNoi() {
        return noi;
    }

    public void setNoi(String noi) {
        this.noi = noi;
    }

    public String getNoe() {
        return noe;
    }

    public void setNoe(String noe) {
        this.noe = noe;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getIte() {
        return ite;
    }

    public void setIte(String ite) {
        this.ite = ite;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit;
    }

    public String getNatfoci() {
        return natfoci;
    }

    public void setNatfoci(String natfoci) {
        this.natfoci = natfoci;
    }

    public String getTci() {
        return tci;
    }

    public void setTci(String tci) {
        this.tci = tci;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getBeps() {
        return beps;
    }

    public void setBeps(String beps) {
        this.beps = beps;
    }

    public String getDeps() {
        return deps;
    }

    public void setDeps(String deps) {
        this.deps = deps;
    }

    @Override
    public String toString() {
        return "Profit{" +
                "businessIncome='" + businessIncome + '\'' +
                ", interestIncome='" + interestIncome + '\'' +
                ", earnedPremium='" + earnedPremium + '\'' +
                ", faci='" + faci + '\'' +
                ", toi='" + toi + '\'' +
                ", operatingCost='" + operatingCost + '\'' +
                ", btaa='" + btaa + '\'' +
                ", sellingExpenses='" + sellingExpenses + '\'' +
                ", managementCost='" + managementCost + '\'' +
                ", financialCost='" + financialCost + '\'' +
                ", ail='" + ail + '\'' +
                ", toc='" + toc + '\'' +
                ", fvci='" + fvci + '\'' +
                ", adi='" + adi + '\'' +
                ", ifi='" + ifi + '\'' +
                ", iiojvajv='" + iiojvajv + '\'' +
                ", oii='" + oii + '\'' +
                ", exchangeGains='" + exchangeGains + '\'' +
                ", otherIncome='" + otherIncome + '\'' +
                ", operatingProfit='" + operatingProfit + '\'' +
                ", noi='" + noi + '\'' +
                ", noe='" + noe + '\'' +
                ", totalProfit='" + totalProfit + '\'' +
                ", ite='" + ite + '\'' +
                ", netProfit='" + netProfit + '\'' +
                ", natfoci='" + natfoci + '\'' +
                ", tci='" + tci + '\'' +
                ", eps='" + eps + '\'' +
                ", beps='" + beps + '\'' +
                ", deps='" + deps + '\'' +
                '}';
    }
}
