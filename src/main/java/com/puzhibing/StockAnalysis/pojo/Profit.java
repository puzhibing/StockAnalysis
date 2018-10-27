package com.puzhibing.StockAnalysis.pojo;


/**
 * 利润
 * @author Administrator
 *
 */
public class Profit extends StockDataBean {

    private String businessIncome;//营业收入

    private String operatingCost;//营业成本

    private String btaa;//营业税金及附加

    private String sellingExpenses;//销售费用

    private String managementCost;//管理费用

    private String financialCost;//财务费用

    private String ail;//资产减值损失

    private String fvci;//公允价值变动收益

    private String ifi;//投资收益

    private String iiojvajv;//联营企业和合营企业投资收益

    private String oii;//其他投资收益

    private String operatingProfit;//营业利润

    private String noi;//营业外收入

    private String noe;//营业外支出

    private String paldoia;//非流动资产处置损益

    private String onoe;//其他营业外支出

    private String totalProfit;//利润总额

    private String ite;//所得税费用

    private String netProfit;//净利润

    private String eps;//每股收益

    private String beps;//基本每股收益

    private String deps;//稀释每股收益

    public String getBusinessIncome() {
        return businessIncome;
    }

    public void setBusinessIncome(String businessIncome) {
        this.businessIncome = businessIncome;
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

    public String getFvci() {
        return fvci;
    }

    public void setFvci(String fvci) {
        this.fvci = fvci;
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

    public String getPaldoia() {
        return paldoia;
    }

    public void setPaldoia(String paldoia) {
        this.paldoia = paldoia;
    }

    public String getOnoe() {
        return onoe;
    }

    public void setOnoe(String onoe) {
        this.onoe = onoe;
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
                ", operatingCost='" + operatingCost + '\'' +
                ", btaa='" + btaa + '\'' +
                ", sellingExpenses='" + sellingExpenses + '\'' +
                ", managementCost='" + managementCost + '\'' +
                ", financialCost='" + financialCost + '\'' +
                ", ail='" + ail + '\'' +
                ", fvci='" + fvci + '\'' +
                ", ifi='" + ifi + '\'' +
                ", iiojvajv='" + iiojvajv + '\'' +
                ", oii='" + oii + '\'' +
                ", operatingProfit='" + operatingProfit + '\'' +
                ", noi='" + noi + '\'' +
                ", noe='" + noe + '\'' +
                ", paldoia='" + paldoia + '\'' +
                ", onoe='" + onoe + '\'' +
                ", totalProfit='" + totalProfit + '\'' +
                ", ite='" + ite + '\'' +
                ", netProfit='" + netProfit + '\'' +
                ", eps='" + eps + '\'' +
                ", beps='" + beps + '\'' +
                ", deps='" + deps + '\'' +
                '}';
    }
}
