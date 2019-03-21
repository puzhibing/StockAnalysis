
var token = "";

$(function () {
	
	//获取token
    token = getURLParameters('token');

    calculation();//给标签绑定数据绑定事件

    $(".menu nav").click(function () {
        switchNav(this);
    });

    //绑定表单值发生变化处理函数
    $('.securitiesNumber').bind('input propertychange', function() {
        fuzzyAcquisition(this);
    });




    $(document).click(function () {
        var se = $('.selectionPanel');
        if(se.css('display') == 'block'){
            se.hide();
        }
    });


    function stopPropagation(e) {
        if (e.stopPropagation)
            e.stopPropagation();
        else
            e.cancelBubble = true;
    }

    $('.selectionPanel').bind('click',function(e){
        stopPropagation(e);
    });


    //保存流动资产
    $(".saveCurrentAssets").click(function () {
        saveCurrentAssets();
    });

    //保存非流动资产
    $(".saveNonCurrentAssets").click(function () {
        saveNonCurrentAssets();
    });

    //保存流动负债
    $(".saveCurrentLiabilities").click(function () {
        saveCurrentLiabilities();
    });

    //保存非流动负债
    $('.saveNonCurrentLiabilities').click(function () {
        saveNonCurrentLiabilities();
    });

    //保存所有者权益
    $('.saveOwnersEquity').click(function () {
        saveOwnersEquity();
    });

    //保存利润
    $('.saveProfit').click(function () {
        saveProfit();
    });

    //保存现金流
    $('.saveCashFlow').click(function () {
        saveCashFlow();
    });

    //保存现金流附注
    $('.saveCashFlowStatement').click(function () {
        saveCashFlowStatement();
    });

    //保存所有者权益变动
    $('.saveOwnersEquityChange').click(function () {
        saveOwnersEquityChange();
    });

});

//定义切换窗口的效果
function switchNav(nav){
    $(".dataType").hide();
    $(nav).siblings().css({
        "background-color":"#ECECEC",
        "border-bottom" : "1px solid #BCBCBC",
        "color":"#2B2B2B"
    });
    $(nav).css({
        "background-color":"#FFFFFF",
        "border-bottom" : "1px solid #FFFFFF",
        "color":"#75A2A5"
    });

    var id = $(nav).attr("class");
    $("#" + id).show();
}





//动态模糊筛选获取公司数据接口
function fuzzyAcquisition(v) {
    $(v).siblings(".selectionPanel").show();
    var value = $(v).val();
    if(value.length < 5){
        return;
    }

    $.ajax({
        url: "/selectAllDataLikeCode",
        type: "POST",
        data: {
            stockCode: value
        },
        success: function (res) {
            $(v).siblings(".selectionPanel").html('');
            if(res.b){
                var data = res.result;
                if(data.length > 0){
                    var str = '<ul>';
                    for(var i = 0; i < data.length; i++){
                        str += '<li id="' + data[i].companyStock.id +  '" code="' + data[i].companyStock.stockCode + '" type="' + data[i].companyStock.stockTypeId[0].name + '" name="' + data[i].company.chName + '" onclick="selectionPanel(this)">' +
                            '<span>' + data[i].companyStock.stockTypeId[0].name + " | " + data[i].companyStock.stockCode + " | " + data[i].company.chName + '</span></li>';
                    }
                    str += '<ul/>';
                    $(v).siblings(".selectionPanel").html(str);
                }

            }
        }
    });
}


//选择面板值选中触发的事件
function selectionPanel(li){
    var th = $(li);
    var id = th.attr("id");
    var code = th.attr("code");
    var type = th.attr("type");
    var name = th.attr("name");

    $(".companyStockId").val(id);
    th.parents(".selectionPanel").siblings(".securitiesNumber").val(code);
    th.parents("tr").siblings("tr").find(".securitiesType").val(type);
    th.parents("tr").siblings("tr").find(".companyName").val(name);
    th.parent("ul").parent("div").hide();
}


//保存流动资产数据
function saveCurrentAssets(){
    var div = $('.saveCurrentAssets').parents("#currentAssets");
    var id = div.find(".id").val();//数据id
    var companyStockId = $(".companyStockId").val();//企业证券id
    var dataTime = div.find(".dataTime").val();//数据日期
    var currencyUnit = div.find(".currencyUnit").val();//单位
    var moneyFunds = div.find(".moneyFunds").val();//货币资金
    var WOF = div.find(".WOF").val();//拆出资金
    var TFA = div.find(".TFA").val();//交易性金融资产
    var DFA = div.find(".DFA").val();//衍生金融资产
    var BBRFA = div.find(".BBRFA").val();//买入返售金融资产
    var billReceivable = div.find(".billReceivable").val();//应收票据
    var accountsReceivable = div.find(".accountsReceivable").val();//应收账款
    var prepayments = div.find(".prepayments").val();//预付账款
    var interestReceivable = div.find(".interestReceivable").val();//应收利息
    var dividendReceivable = div.find(".dividendReceivable").val();//应收股利
    var otherReceivables = div.find(".otherReceivables").val();//其他应收款
    var stock = div.find(".stock").val();//存货
    var NCADWOY = div.find(".NCADWOY").val();//一年内到期的非流动资产
    var OCA = div.find(".OCA").val();//其他流动资产
    var TCA = div.find(".TCA").val();//流动资产合计

    if(companyStockId == "" || dataTime ==""){
        alert('数据录入异常');
        return;
    }

    var formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('moneyFunds', moneyFunds);
    formData.append('wof', WOF);
    formData.append('tfa', TFA);
    formData.append('dfa', DFA);
    formData.append('bbrfa', BBRFA);
    formData.append('billReceivable', billReceivable);
    formData.append('accountsReceivable', accountsReceivable);
    formData.append('prepayments', prepayments);
    formData.append('interestReceivable', interestReceivable);
    formData.append('dividendReceivable', dividendReceivable);
    formData.append('otherReceivables', otherReceivables);
    formData.append('stock', stock);
    formData.append('ncadwoy', NCADWOY);
    formData.append('oca', OCA);
    formData.append('tca', TCA);
    formData.append('token', token);

    $.ajax({
        url: "/insertCurrentAssets",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
                alert('数据添加成功');
            	resetCurrentAssets();
            }
        }
    });

}


//重置流动资产表单
function resetCurrentAssets(){
	var div = $('.saveCurrentAssets').parents("#currentAssets");
	div.find(".id").val("");//数据id
    $(".companyStockId").val("");//企业证券id
    div.find(".dataTime").val("");//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected' , 'selected');
    div.find(".moneyFunds").val("");//货币资金
    div.find(".WOF").val("");//拆出资金
    div.find(".TFA").val("");//交易性金融资产
    div.find(".DFA").val("");//衍生金融资产
    div.find(".BBRFA").val("");//买入返售金融资产
    div.find(".billReceivable").val("");//应收票据
    div.find(".accountsReceivable").val("");//应收账款
    div.find(".prepayments").val("");//预付账款
    div.find(".interestReceivable").val("");//应收利息
    div.find(".dividendReceivable").val("");//应收股利
    div.find(".otherReceivables").val("");//其他应收款
    div.find(".stock").val("");//存货
    div.find(".NCADWOY").val("");//一年内到期的非流动资产
    div.find(".OCA").val("");//其他流动资产
    div.find(".TCA").val("");//流动资产合计
}






//保存非流动资产
function saveNonCurrentAssets(){
    var div = $('.saveNonCurrentAssets').parents("#nonCurrentAssets");
    var id = div.find(".id").val();//数据id
    var companyStockId = $(".companyStockId").val();//企业证券id
    var dataTime = div.find(".dataTime").val();//数据日期
    var currencyUnit = div.find(".currencyUnit").val();//单位
    var AFSFA = div.find(".AFSFA").val();//可供出售金融资产
    var HAEI = div.find(".HAEI").val();//持有到期投资
    var LTR = div.find(".LTR").val();//长期应收款
    var LTEI = div.find(".LTEI").val();//长期股权投资
    var IRE = div.find(".IRE").val();//投资性房地产
    var fixedAssets = div.find(".fixedAssets").val();//固定资产
    var CAP = div.find(".CAP").val();//在建工程
    var engineerMaterial = div.find(".engineerMaterial").val();//工程物资
    var FAC = div.find(".FAC").val();//固定资产清理
    var PBA = div.find(".PBA").val();//生产性生物资产
    var gasolineAssets = div.find(".gasolineAssets").val();//汽油资产
    var intangibleAssets = div.find(".intangibleAssets").val();//无形资产
    var DE = div.find(".DE").val();//开发支出
    var goodwill = div.find(".goodwill").val();//商誉
    var LTPE = div.find(".LTPE").val();//长期待摊费用
    var DTA = div.find(".DTA").val();//递延所得税资产
    var ONCA = div.find(".ONCA").val();//其他非流动资产
    var TNCA = div.find(".TNCA").val();//非流动资产合计

    if(companyStockId == "" || dataTime ==""){
        alert('数据录入异常');
        return;
    }

    var formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('afsfa', AFSFA);
    formData.append('haei', HAEI);
    formData.append('ltr', LTR);
    formData.append('ltbi', LTEI);
    formData.append('ire', IRE);
    formData.append('fixedAssets', fixedAssets);
    formData.append('cap', CAP);
    formData.append('engineerMaterial', engineerMaterial);
    formData.append('fac', FAC);
    formData.append('pba', PBA);
    formData.append('gasolineAssets', gasolineAssets);
    formData.append('intangibleAssets', intangibleAssets);
    formData.append('de', DE);
    formData.append('goodwill', goodwill);
    formData.append('ltpe', LTPE);
    formData.append('dta', DTA);
    formData.append('onca', ONCA);
    formData.append('tnca', TNCA)
    formData.append('token', token);
    
    $.ajax({
        url: '/insertNonCurrentAssets',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
                alert('数据添加成功');
            	resetNonCurrentAssets();
            }
        }
    });
}


//重置非流动资产表单
function resetNonCurrentAssets(){
	var div = $('.saveNonCurrentAssets').parents("#nonCurrentAssets");
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".AFSFA").val('');//可供出售金融资产
    div.find(".HAEI").val('');//持有到期投资
    div.find(".LTR").val('');//长期应收款
    div.find(".LTEI").val('');//长期股权投资
    div.find(".IRE").val('');//投资性房地产
    div.find(".fixedAssets").val('');//固定资产
    div.find(".CAP").val('');//在建工程
    div.find(".engineerMaterial").val('');//工程物资
    div.find(".FAC").val('');//固定资产清理
    div.find(".PBA").val('');//生产性生物资产
    div.find(".gasolineAssets").val('');//汽油资产
    div.find(".intangibleAssets").val('');//无形资产
    div.find(".DE").val('');//开发支出
    div.find(".goodwill").val('');//商誉
    div.find(".LTPE").val('');//长期待摊费用
    div.find(".DTA").val('');//递延所得税资产
    div.find(".ONCA").val('');//其他非流动资产
    div.find(".TNCA").val('');//非流动资产合计
}




//保存流动负债
function saveCurrentLiabilities(){
    var div = $('.saveCurrentLiabilities').parents("#currentLiabilities");
    var id = div.find(".id").val();//数据id
    var companyStockId = $(".companyStockId").val();//企业证券id
    var dataTime = div.find(".dataTime").val();//数据日期
    var currencyUnit = div.find(".currencyUnit").val();//单位
    var STL = div.find(".STL").val();//短期借款
    var UF = div.find(".UF").val();//拆入资金
    var TFL = div.find(".TFL").val();//交易性金融负债
    var DFL = div.find(".DFL").val();//衍生金融负债
    var SRFA = div.find(".SRFA").val();//卖出回购金融资产款
    var billsPayable = div.find(".billsPayable").val();//应付票据
    var accountsPayable = div.find(".accountsPayable").val();//应付账款
    var advancePayment = div.find(".advancePayment").val();//预收款项
    var payrollPayable = div.find(".payrollPayable").val();//应付职工薪酬
    var taxesPayable = div.find(".taxesPayable").val();//应交税费
    var interestPayable = div.find(".interestPayable").val();//应付利息
    var dividendPayable = div.find(".dividendPayable").val();//应付股利
    var otherPayables = div.find(".otherPayables").val();//其他应付款
    var NLDWOY = div.find(".NLDWOY").val();//一年内到期的非流动负债
    var OCL = div.find(".OCL").val();//其他流动负债
    var TCL = div.find(".TCL").val();//流动负债合计

    if(companyStockId == "" || dataTime ==""){
        alert('数据录入异常');
        return;
    }

    var formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('stl', STL);
    formData.append('uf', UF);
    formData.append('tfl', TFL);
    formData.append('dfl', DFL);
    formData.append('srfa', SRFA);
    formData.append('billsPayable', billsPayable);
    formData.append('accountsPayable', accountsPayable);
    formData.append('advancePayment', advancePayment);
    formData.append('payrollPayable', payrollPayable);
    formData.append('taxesPayable', taxesPayable);
    formData.append('interestPayable', interestPayable);
    formData.append('dividendPayable', dividendPayable);
    formData.append('otherPayables', otherPayables);
    formData.append('nldwoy', NLDWOY);
    formData.append('ocl', OCL);
    formData.append('tcl', TCL);
    formData.append('token', token);

    $.ajax({
        url: '/insertCurrentLiabilities',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
                alert('数据添加成功');
            	resetCurrentLiabilities();
            }
        }
    });

}


//重置流动负债表单
function resetCurrentLiabilities(){
	var div = $('.saveCurrentLiabilities').parents("#currentLiabilities");
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".STL").val('');//短期借款
    div.find(".UF").val('');//拆入资金
    div.find(".TFL").val('');//交易性金融负债
    div.find(".DFL").val('');//衍生金融负债
    div.find(".SRFA").val('');//卖出回购金融资产款
    div.find(".billsPayable").val('');//应付票据
    div.find(".accountsPayable").val('');//应付账款
    div.find(".advancePayment").val('');//预收款项
    div.find(".payrollPayable").val('');//应付职工薪酬
    div.find(".taxesPayable").val('');//应交税费
    div.find(".interestPayable").val('');//应付利息
    div.find(".dividendPayable").val('');//应付股利
    div.find(".otherPayables").val('');//其他应付款
    div.find(".NLDWOY").val('');//一年内到期的非流动负债
    div.find(".OCL").val('');//其他流动负债
    div.find(".TCL").val('');//流动负债合计
}



//保存非流动负债
function saveNonCurrentLiabilities(){
    var div = $('.saveNonCurrentLiabilities').parents("#nonCurrentLiabilities");
    var id = div.find(".id").val();//数据id
    var companyStockId = $(".companyStockId").val();//企业证券id
    var dataTime = div.find(".dataTime").val();//数据日期
    var currencyUnit = div.find(".currencyUnit").val();//单位
    var LTL = div.find(".LTL").val();//长期借款
    var bondsPayable = div.find(".bondsPayable").val();//应付债券
    var LTP = div.find(".LTP").val();//长期应付款
    var specialPayable = div.find(".specialPayable").val();//专项应付款
    var estimatedLiabilities = div.find(".estimatedLiabilities").val();//预计负债
    var deferredIncome = div.find(".deferredIncome").val();//递延收益
    var DITL = div.find(".DITL").val();//递延所得税负债
    var ONCL = div.find(".ONCL").val();//其他非流动负债
    var TNCL = div.find(".TNCL").val();//非流动负债合计

    if(companyStockId == "" || dataTime ==""){
        alert('数据录入异常');
        return;
    }

    var formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('ltl', LTL);
    formData.append('bondsPayable', bondsPayable);
    formData.append('ltp', LTP);
    formData.append('specialPayable', specialPayable);
    formData.append('estimatedLiabilities', estimatedLiabilities);
    formData.append('deferredIncome', deferredIncome);
    formData.append('ditl', DITL);
    formData.append('dncl', ONCL);
    formData.append('tncl', TNCL);
    formData.append('token', token);

    $.ajax({
        url: '/insertNonCurrentLiabilities',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
                alert('数据添加成功');
            	resetNonCurrentLiabilities();
            }
        }
    });
}


//重置非流动负债表单
function resetNonCurrentLiabilities(){
	var div = $('.saveNonCurrentLiabilities').parents("#nonCurrentLiabilities");
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".LTL").val('');//长期借款
    div.find(".bondsPayable").val('');//应付债券
    div.find(".LTP").val('');//长期应付款
    div.find(".specialPayable").val('');//专项应付款
    div.find(".estimatedLiabilities").val('');//预计负债
    div.find(".DITL").val('');//递延所得税负债
    div.find(".ONCL").val('');//其他非流动负债
    div.find(".TNCL").val('');//非流动负债合计
}



//保存所有者权益
function saveOwnersEquity(){
    var div = $('.saveOwnersEquity').parents("#ownersEquity");
    var id = div.find(".id").val();//数据id
    var companyStockId = $(".companyStockId").val();//企业证券id
    var dataTime = div.find(".dataTime").val();//数据日期
    var currencyUnit = div.find(".currencyUnit").val();//单位
    var PIC = div.find(".PIC").val();//股本
    var OEI = div.find(".OEI").val();//其他权益工具
    var capitalReserve = div.find(".capitalReserve").val();//资本公积
    var OCI = div.find(".OCI").val();//其他综合收益
    var LTS = div.find(".LTS").val();//减：库存股
    var specialReserves = div.find(".specialReserves").val();//专项储备
    var surplusReserve = div.find(".surplusReserve").val();//盈余公积
    var GRP = div.find(".GRP").val();//一般风险准备
    var undistributedProfit = div.find(".undistributedProfit").val();//未分配利润
    var TOEATTPC = div.find(".TOEATTPC").val();//归属于母公司所有者权益合计
    var MSE = div.find(".MSE").val();//少数股东权益
    var TOE = div.find(".TOE").val();//所有者权益合计

    if(companyStockId == "" || dataTime ==""){
        alert('数据录入异常');
        return;
    }

    var formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('pic', PIC);
    formData.append('oei', OEI);
    formData.append('capitalReserve', capitalReserve);
    formData.append('oci', OCI);
    formData.append('lts', LTS);
    formData.append('specialReserves', specialReserves);
    formData.append('surplusReserve', surplusReserve);
    formData.append('grp', GRP);
    formData.append('undistributedProfit', undistributedProfit);
    formData.append('toeattpc', TOEATTPC);
    formData.append('mse', MSE);
    formData.append('toe', TOE);
    formData.append('token', token);

    $.ajax({
        url: '/insertOwnersEquity',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
                alert('数据添加成功');
            	resetOwnersEquity();
            }
        }
    });
}


//重置所有者权益表单
function resetOwnersEquity(){
	var div = $('.saveOwnersEquity').parents("#ownersEquity");
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".PIC").val('');//股本
    div.find(".OEI").val('');//其他权益工具
    div.find(".capitalReserve").val('');//资本公积
    div.find(".OCI").val('');//其他综合收益
    div.find(".LTS").val('');//减：库存股
    div.find(".specialReserves").val('');//专项储备
    div.find(".surplusReserve").val('');//盈余公积
    div.find(".GRP").val('');//一般风险准备
    div.find(".undistributedProfit").val('');//未分配利润
    div.find(".TOEATTPC").val('');//归属于母公司所有者权益合计
    div.find(".MSE").val('');//少数股东权益
    div.find(".TOE").val('');//所有者权益合计
}


//保存利润
function saveProfit(){
    var div = $('.saveProfit').parents("#profit");
    var id = div.find(".id").val();//数据id
    var companyStockId = $(".companyStockId").val();//企业证券id
    var dataTime = div.find(".dataTime").val();//数据日期
    var currencyUnit = div.find(".currencyUnit").val();//单位
    var businessIncome = div.find(".businessIncome").val();//营业收入
    var interestIncome = div.find(".interestIncome").val();//利息收入
    var earnedPremium = div.find(".earnedPremium").val();//已赚保费
    var FACI = div.find(".FACI").val();//手续费及佣金收入
    var TOI = div.find(".TOI").val();//营业总收入
    var operatingCost = div.find(".operatingCost").val();//营业成本
    var BTAA = div.find(".BTAA").val();//营业税金及附加
    var sellingExpenses = div.find(".sellingExpenses").val();//销售费用
    var managementCost = div.find(".managementCost").val();//管理费用
    var financialCost = div.find(".financialCost").val();//财务费用
    var AIL = div.find(".AIL").val();//资产减值损失
    var TOC = div.find(".TOC").val();//营业总成本
    var FVCI = div.find(".FVCI").val();//公允价值变动收益
    var ADI = div.find(".ADI").val();//资产处置收益
    var IFI = div.find(".IFI").val();//投资收益
    var IIOJVAJV = div.find(".IIOJVAJV").val();//联营企业和合营企业投资收益
    var OII = div.find(".OII").val();//其他投资收益
    var exchangeGains = div.find(".exchangeGains").val();//汇兑收益
    var otherIncome = div.find(".otherIncome").val();//其他收益
    var operatingProfit = div.find(".operatingProfit").val();//营业利润
    var NOI = div.find(".NOI").val();//营业外收入
    var NOE = div.find(".NOE").val();//营业外支出
    var totalProfit = div.find(".totalProfit").val();//利润总额
    var ITE = div.find(".ITE").val();//所得税费用
    var netProfit = div.find(".netProfit").val();//净利润
    var NATFOCI = div.find(".NATFOCI").val();//其他综合收益的税后净额
    var TCI = div.find(".TCI").val();//综合收益总额
    var EPS = div.find(".EPS").val();//每股收益
    var BEPS = div.find(".BEPS").val();//基本每股收益
    var DEPS = div.find(".DEPS").val();//稀释每股收益

    if(companyStockId == "" || dataTime ==""){
        alert('数据录入异常');
        return;
    }

    var formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('businessIncome', businessIncome);
    formData.append('interestIncome', interestIncome);
    formData.append('earnedPremium', earnedPremium);
    formData.append('faci', FACI);
    formData.append('toi', TOI);
    formData.append('operatingCost', operatingCost);
    formData.append('btaa', BTAA);
    formData.append('sellingExpenses', sellingExpenses);
    formData.append('managementCost', managementCost);
    formData.append('financialCost', financialCost);
    formData.append('ail', AIL);
    formData.append('toc', TOC);
    formData.append('fvci', FVCI);
    formData.append('adi', ADI);
    formData.append('ifi', IFI);
    formData.append('iiojvajv', IIOJVAJV);
    formData.append('oii', OII);
    formData.append('exchangeGains', exchangeGains);
    formData.append('otherIncome', otherIncome);
    formData.append('operatingProfit', operatingProfit);
    formData.append('noi', NOI);
    formData.append('noe', NOE);
    formData.append('totalProfit', totalProfit);
    formData.append('ite', ITE);
    formData.append('netProfit', netProfit);
    formData.append('natfoci', NATFOCI);
    formData.append('tci', TCI);
    formData.append('eps', EPS);
    formData.append('beps', BEPS);
    formData.append('deps', DEPS);
    formData.append('token', token);

    $.ajax({
        url: '/insertProfit',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
                alert('数据添加成功');
            	resetProfit();
            }
        }
    });
}

//重置利润表单
function resetProfit(){
	var div = $('.saveProfit').parents("#profit");
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".businessIncome").val('');//营业收入
    div.find(".interestIncome").val('');//利息收入
    div.find(".earnedPremium").val('');//已赚保费
    div.find(".FACI").val('');//手续费及佣金收入
    div.find(".TOI").val('');//营业总收入
    div.find(".operatingCost").val('');//营业成本
    div.find(".BTAA").val('');//营业税金及附加
    div.find(".sellingExpenses").val('');//销售费用
    div.find(".managementCost").val('');//管理费用
    div.find(".financialCost").val('');//财务费用
    div.find(".AIL").val('');//资产减值损失
    div.find(".TOC").val('');//营业总成本
    div.find(".FVCI").val('');//公允价值变动收益
    div.find(".ADI").val('');//资产处置收益
    div.find(".IFI").val('');//投资收益
    div.find(".IIOJVAJV").val('');//联营企业和合营企业投资收益
    div.find(".OII").val('');//其他投资收益
    div.find(".exchangeGains").val('');//汇兑收益
    div.find(".otherIncome").val('');//其他收益
    div.find(".operatingProfit").val('');//营业利润
    div.find(".NOI").val('');//营业外收入
    div.find(".NOE").val('');//营业外支出
    div.find(".totalProfit").val('');//利润总额
    div.find(".ITE").val('');//所得税费用
    div.find(".netProfit").val('');//净利润
    div.find(".NATFOCI").val('');//其他综合收益的税后净额
    div.find(".TCI").val('');//综合收益总额
    div.find(".EPS").val('');//每股收益
    div.find(".BEPS").val('');//基本每股收益
    div.find(".DEPS").val('');//稀释每股收益
}


//保存现金流
function saveCashFlow(){
    var div = $('.saveCashFlow').parents("#cashFlow")
    var id = div.find(".id").val();//数据id
    var companyStockId = $(".companyStockId").val();//企业证券id
    var dataTime = div.find(".dataTime").val();//数据日期
    var currencyUnit = div.find(".currencyUnit").val();//单位
    var CRFSOGAS = div.find(".CRFSOGAS").val();//销售商品、提供劳务收到的现金
    var ROT = div.find(".ROT").val();//收到的税费返还
    var OCRTBAR = div.find(".OCRTBAR").val();//收到的其他与经营活动相关的现金
    var CIIOA = div.find(".CIIOA").val();//经营活动现金流入小计
    var CPFGAS = div.find(".CPFGAS").val();//购买商品、接受劳务支付的现金
    var CPTEAPTE = div.find(".CPTEAPTE").val();//支付给职工以及为职工支付的现金
    var POATOT = div.find(".POATOT").val();//支付的各项税费
    var POOCRTBA = div.find(".POOCRTBA").val();//支付其他与经营活动有关的现金
    var CFFOA = div.find(".CFFOA").val();//经营活动现金流出小计
    var NCFFOA = div.find(".NCFFOA").val();//经营活动产生的现金流净额
    var CRFIR = div.find(".CRFIR").val();//收回投资收到的现金
    var CRFII = div.find(".CRFII").val();//取得投资收益收到的现金
    var NCIDOFAIAAOAITPP = div.find(".NCIDOFAIAAOAITPP").val();//处置固定资产、无形资产和其他上期资产收回的现金净额
    var NCRFDOSAOBU = div.find(".NCRFDOSAOBU").val();//处置子公司及其他营业单位收到的现金净额
    var OCRTIAHBR = div.find(".OCRTIAHBR").val();//收到其他与投资活动相关的现金
    var CIOIA = div.find(".CIOIA").val();//投资活动现金流入小计
    var COFAIAAOCPFPI = div.find(".COFAIAAOCPFPI").val();//构建固定资产、无形资产和其他上期投资支付的现金
    var CPFI = div.find(".CPFI").val();//投资支付的现金
    var NCPBSAOBU = div.find(".NCPBSAOBU").val();//取得子公司及其他营业单位支付的现金净额
    var POOCRTIA = div.find(".POOCRTIA").val();//支付其他与投资活动有关的现金
    var CFOOIA = div.find(".CFOOIA").val();//投资活动现金流出小计
    var NCFFIA = div.find(".NCFFIA").val();//投资活动产生的现金流净额
    var CRFI = div.find(".CRFI").val();//吸收投资收到的现金
    var CRFB = div.find(".CRFB").val();//取得借款收到的现金
    var OCRTFAR = div.find(".OCRTFAR").val();//收到其他与筹资活动有关的现金
    var CIOFA = div.find(".CIOFA").val();//筹资活动现金流入小计
    var CFDR = div.find(".CFDR").val();//偿还债务支出的现金
    var CPFDPOIP = div.find(".CPFDPOIP").val();//分配股利、利润或偿付利息支付的现金
    var POOCRTFA = div.find(".POOCRTFA").val();//支付其他与筹资活动有关的现金
    var CFOOFA = div.find(".CFOOFA").val();//筹资活动现金流出小计
    var NCFGBFRA = div.find(".NCFGBFRA").val();//筹资活动产生的现金流量净额
    var TEOERFOCACE = div.find(".TEOERFOCACE").val();//汇率变动对现金及现金等价物的影响
    var NIICACE = div.find('.NIICACE').val();//现金及现金等价物净增加额
    var CACEATBOTY = div.find(".CACEATBOTY").val();//期初现金及现金等价物余额
    var BOCACEAEOT = div.find(".BOCACEAEOT").val();//期末现金及现金等价物余额

    if(companyStockId == "" || dataTime ==""){
        alert('数据录入异常');
        return;
    }

    var formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('crfsogas', CRFSOGAS);
    formData.append('ort', ROT);
    formData.append('cortbar', OCRTBAR);
    formData.append('ciioa', CIIOA);
    formData.append('cpfgas', CPFGAS);
    formData.append('cpteapte', CPTEAPTE);
    formData.append('poatot', POATOT);
    formData.append('poocrtba', POOCRTBA);
    formData.append('cffoa', CFFOA);
    formData.append('ncffoa', NCFFOA);
    formData.append('crfir', CRFIR);
    formData.append('crfii', CRFII);
    formData.append('ncidofaiaaoaitpp', NCIDOFAIAAOAITPP);
    formData.append('ncrfdosaobu', NCRFDOSAOBU);
    formData.append('ocrtiahbr', OCRTIAHBR);
    formData.append('cioia', CIOIA);
    formData.append('cofaiaaocpfpi', COFAIAAOCPFPI);
    formData.append('cpfi', CPFI);
    formData.append('ncpbsaobu', NCPBSAOBU);
    formData.append('poocrtia', POOCRTIA);
    formData.append('cfooia', CFOOIA);
    formData.append('ncffia', NCFFIA);
    formData.append('crfi', CRFI);
    formData.append('crfb', CRFB);
    formData.append('ocrtfar', OCRTFAR);
    formData.append('ciofa', CIOFA);
    formData.append('cfdr', CFDR);
    formData.append('cpfdpoip', CPFDPOIP);
    formData.append('poocrtfa', POOCRTFA);
    formData.append('cfoofa', CFOOFA);
    formData.append('ncfgbfra', NCFGBFRA);
    formData.append('teoerfocace', TEOERFOCACE);
    formData.append('niicace', NIICACE);
    formData.append('caceatboty', CACEATBOTY);
    formData.append('bocaceaeot', BOCACEAEOT);
    formData.append('token', token);


    $.ajax({
        url: '/insertCashFlow',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
                alert('数据添加成功');
            	resetCashFlow();
            }
        }
    });
}


//重置现金流表单
function resetCashFlow(){
	var div = $('.saveCashFlow').parents("#cashFlow")
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".CRFSOGAS").val('');//销售商品、提供劳务收到的现金
    div.find(".ROT").val('');//收到的税费返还
    div.find(".OCRTBAR").val('');//收到的其他与经营活动相关的现金
    div.find(".CIIOA").val('');//经营活动现金流入小计
    div.find(".CPFGAS").val('');//购买商品、接受劳务支付的现金
    div.find(".CPTEAPTE").val('');//支付给职工以及为职工支付的现金
    div.find(".POATOT").val('');//支付的各项税费
    div.find(".POOCRTBA").val('');//支付其他与经营活动有关的现金
    div.find(".CFFOA").val('');//经营活动现金流出小计
    div.find(".NCFFOA").val('');//经营活动产生的现金流净额
    div.find(".CRFIR").val('');//收回投资收到的现金
    div.find(".CRFII").val('');//取得投资收益收到的现金
    div.find(".NCIDOFAIAAOAITPP").val('');//处置固定资产、无形资产和其他上期资产收回的现金净额
    div.find(".NCRFDOSAOBU").val('');//处置子公司及其他营业单位收到的现金净额
    div.find(".OCRTIAHBR").val('');//收到其他与投资活动相关的现金
    div.find(".CIOIA").val('');//投资活动现金流入小计
    div.find(".COFAIAAOCPFPI").val('');//构建固定资产、无形资产和其他上期投资支付的现金
    div.find(".CPFI").val('');//投资支付的现金
    div.find(".NCPBSAOBU").val('');//取得子公司及其他营业单位支付的现金净额
    div.find(".POOCRTIA").val('');//支付其他与投资活动有关的现金
    div.find(".CFOOIA").val('');//投资活动现金流出小计
    div.find(".NCFFIA").val('');//投资活动产生的现金流净额
    div.find(".CRFI").val('');//吸收投资收到的现金
    div.find(".CRFB").val('');//取得借款收到的现金
    div.find(".OCRTFAR").val('');//收到其他与筹资活动有关的现金
    div.find(".CIOFA").val('');//筹资活动现金流入小计
    div.find(".CFDR").val('');//偿还债务支出的现金
    div.find(".CPFDPOIP").val('');//分配股利、利润或偿付利息支付的现金
    div.find(".POOCRTFA").val('');//支付其他与筹资活动有关的现金
    div.find(".CFOOFA").val('');//筹资活动现金流出小计
    div.find(".NCFGBFRA").val('');//筹资活动产生的现金流量净额
    div.find(".TEOERFOCACE").val('');//汇率变动对现金及现金等价物的影响
    div.find(".NIICACE").val('');//现金及现金等价物净增加额
    div.find(".CACEATBOTY").val('');//期初现金及现金等价物余额
    div.find(".BOCACEAEOT").val('');//期末现金及现金等价物余额
}


//保存现金流附注
function saveCashFlowStatement(){
    var div = $('.saveCashFlowStatement').parents("#cashFlowStatement")
    var id = div.find(".id").val();//数据id
    var companyStockId = $(".companyStockId").val();//企业证券id
    var dataTime = div.find(".dataTime").val();//数据日期
    var currencyUnit = div.find(".currencyUnit").val();//单位
    var NP = div.find(".NP").val();//净利润
    var AIP = div.find(".AIP").val();//资产减值准备
    var DOFAGADADOPBA = div.find(".DOFAGADADOPBA").val();//固定资产折旧、汽油资产拆耗、生产性生物资产折旧
    var AOIA = div.find(".AOIA").val();//无形资产摊销
    var AOLTPE = div.find(".AOLTPE").val();//长期待摊费用摊销
    var LOIFFAIAAOLTA = div.find(".LOIFFAIAAOLTA").val();//处置固定资产、无形资产和其他长期资产的损失（收益）
    var LOSOFA = div.find(".LOSOFA").val();//固定资产报废损失
    var LOFVC = div.find(".LOFVC").val();//公允值变动损失
    var FC = div.find(".FC").val();//财务费用
    var LL = div.find(".LL").val();//投资损失
    var DITAD = div.find(".DITAD").val();//递延所得税资产减少
    var IIIDITL = div.find(".IIIDITL").val();//递延所得税负债增加
    var LR = div.find(".LR").val();//存货的减少
    var DIORI = div.find(".DIORI").val();//经营性应收项目的减少
    var IIOPI = div.find(".IIOPI").val();//经营性应付项目的增加
    var Other = div.find(".Other").val();//其他
    var NCFFOAc = div.find(".NCFFOAc").val();//经营活动产生的现金流量净额
    var DTC = div.find(".DTC").val();//债务转为资本
    var SCBDWOY = div.find(".SCBDWOY").val();//一年内到期的可转换公司债券
    var FLOFA = div.find(".FLOFA").val();//融资租入固定资产
    var CATEOTP = div.find(".CATEOTP").val();//现金的期末余额
    var IBOC = div.find(".IBOC").val();//现金的期初余额
    var EBOCE = div.find(".EBOCE").val();//现金等价物的期末余额
    var IBOCE = div.find(".IBOCE").val();//现金等价物的期初余额
    var NIICACE = div.find(".NIICACE").val();//现金及现金等价物净增加额

    if(companyStockId == "" || dataTime ==""){
        alert('数据录入异常');
        return;
    }

    var formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('np', NP);
    formData.append('aip', AIP);
    formData.append('dofagadadopba', DOFAGADADOPBA);
    formData.append('aoia', AOIA);
    formData.append('aoltpe', AOLTPE);
    formData.append('loiffaiaaolta', LOIFFAIAAOLTA);
    formData.append('losofa', LOSOFA);
    formData.append('lofvc', LOFVC);
    formData.append('fc', FC);
    formData.append('ll', LL);
    formData.append('ditad', DITAD);
    formData.append('iiiditl', IIIDITL);
    formData.append('lr', LR);
    formData.append('diori', DIORI);
    formData.append('iiopi', IIOPI);
    formData.append('other', Other);
    formData.append('ncffoac', NCFFOAc);
    formData.append('dtc', DTC);
    formData.append('scbdwoy', SCBDWOY);
    formData.append('flofa', FLOFA);
    formData.append('cateotp', CATEOTP);
    formData.append('iboc', IBOC);
    formData.append('eboce', EBOCE);
    formData.append('iboce', IBOCE);
    formData.append('niicace', NIICACE);
    formData.append('token', token);


    $.ajax({
        url: '/insertCashFlowStatement',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
                alert('数据添加成功');
                resetCashFlowStatement();
            }
        }
    });
}


//重置现金流附注
function resetCashFlowStatement(){
    var div = $('.saveCashFlowStatement').parents("#cashFlowStatement")
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".NP").val('');//净利润
    div.find(".AIP").val('');//资产减值准备
    div.find(".DOFAGADADOPBA").val('');//固定资产折旧、汽油资产拆耗、生产性生物资产折旧
    div.find(".AOIA").val('');//无形资产摊销
    div.find(".AOLTPE").val('');//长期待摊费用摊销
    div.find(".LOIFFAIAAOLTA").val('');//处置固定资产、无形资产和其他长期资产的损失（收益）
    div.find(".LOSOFA").val('');//固定资产报废损失
    div.find(".LOFVC").val('');//公允值变动损失
    div.find(".FC").val('');//财务费用
    div.find(".LL").val('');//投资损失
    div.find(".DITAD").val('');//递延所得税资产减少
    div.find(".IIIDITL").val('');//递延所得税负债增加
    div.find(".LR").val('');//存货的减少
    div.find(".DIORI").val('');//经营性应收项目的减少
    div.find(".IIOPI").val('');//经营性应付项目的增加
    div.find(".Other").val('');//其他
    div.find(".NCFFOAc").val('');//经营活动产生的现金流量净额
    div.find(".DTC").val('');//债务转为资本
    div.find(".SCBDWOY").val('');//一年内到期的可转换公司债券
    div.find(".FLOFA").val('');//融资租入固定资产
    div.find(".CATEOTP").val('');//现金的期末余额
    div.find(".IBOC").val('');//现金的期初余额
    div.find(".EBOCE").val('');//现金等价物的期末余额
    div.find(".IBOCE").val('');//现金等价物的期初余额
    div.find(".NIICACE").val('');///现金及现金等价物净增加额
}



//保存所有者权益变动
function saveOwnersEquityChange(){
    var div = $('.saveOwnersEquityChange').parents("#ownersEquityChange");
    var id = div.find(".id").val();//数据id
    var companyStockId = $(".companyStockId").val();//企业证券id
    var dataTime = div.find(".dataTime").val();//数据日期
    var currencyUnit = div.find(".currencyUnit").val();//单位
    var YEBATEOLY = div.find(".YEBATEOLY").val();//上年年末余额
    var APC = div.find(".APC").val();//会计政策变更
    var EEC = div.find(".EEC").val();//前期差错更正
    var BATBOTY = div.find(".BATBOTY").val();//本年年初余额
    var AOIODITCY = div.find(".AOIODITCY").val();//本年增减变动金额
    var NP = div.find(".NP").val();//净利润
    var GALDIIOE = div.find(".GALDIIOE").val();//直接计入所有者权益的利得和损失
    var NCIFVOAFSFA = div.find(".NCIFVOAFSFA").val();//可供出售金融资产公允值变动净额
    var TIOCIOORAIOIUUEM = div.find(".TIOCIOORAIOIUUEM").val();//权益法下被投资单位其他所有者权益变动的影响
    var ITRTOEI = div.find(".ITRTOEI").val();//与计入所有者权益项目相关的所得税影响
    var other1 = div.find(".other1").val();//其他
    var OIARC = div.find(".OIARC").val();//所有者投入和减少资本
    var CIBO = div.find(".CIBO").val();//所有者投入资本
    var OEIHI = div.find(".OEIHI").val();//其他权益工具持有者投入
    var TAOSPIITOE = div.find(".TAOSPIITOE").val();//股份支付计入所有者权益的金额
    var other2 = div.find(".other2").val();//其他
    var PD = div.find(".PD").val();//利润分配
    var ESR = div.find(".ESR").val();//提取盈余公积
    var EGRP = div.find(".EGRP").val();//提取一般风险准备
    var DOOOS = div.find(".DOOOS").val();//对所有者（或股东）的分配
    var other3 = div.find(".other3").val();//其他
    var ITOOE = div.find(".ITOOE").val();//所有者权益内部结转
    var CSICOCS = div.find(".CSICOCS").val();//资本公积转增资本（或股本）
    var SSTICOCS = div.find(".SSTICOCS").val();//盈余公积转增资本（或股本）
    var SSTMUFL = div.find(".SSTMUFL").val();//盈余公积弥补亏损
    var other4 = div.find(".other4").val();//其他
    var specialReserves = div.find(".specialReserves").val();//专项储备
    var currentExtraction = div.find(".currentExtraction").val();//本期提取
    var currentUse = div.find(".currentUse").val();//本期使用
    var other5 = div.find(".other5").val();//其他
    var BATEOTY = div.find(".BATEOTY").val();//本年年末余额

    if(companyStockId == "" || dataTime ==""){
        alert('数据录入异常');
        return;
    }

    var formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('yebateoly', YEBATEOLY);
    formData.append('apc', APC);
    formData.append('eec', EEC);
    formData.append('batboty', BATBOTY);
    formData.append('aoioditcy', AOIODITCY);
    formData.append('np', NP);
    formData.append('galdiioe', GALDIIOE);
    formData.append('ncifvoafsfa', NCIFVOAFSFA);
    formData.append('tiociooraioiuuem', TIOCIOORAIOIUUEM);
    formData.append('itrtoei', ITRTOEI);
    formData.append('other1', other1);
    formData.append('oiarc', OIARC);
    formData.append('cibo', CIBO);
    formData.append('oeihi', OEIHI);
    formData.append('taospiitoe', TAOSPIITOE);
    formData.append('other2', other2);
    formData.append('pd', PD);
    formData.append('esr', ESR);
    formData.append('egrp', EGRP);
    formData.append('dooos', DOOOS);
    formData.append('other3', other3);
    formData.append('itooe', ITOOE);
    formData.append('csicocs', CSICOCS);
    formData.append('ssticocs', SSTICOCS);
    formData.append('sstmufl', SSTMUFL);
    formData.append('other4', other4);
    formData.append('specialReserves', specialReserves);
    formData.append('currentExtraction', currentExtraction);
    formData.append('currentUse', currentUse);
    formData.append('other5', other5);
    formData.append('bateoty', BATEOTY);
    formData.append('token', token);

    $.ajax({
        url: '/insertOwnersEquityChange',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
                alert('数据添加成功');
            	resetOwnersEquityChange();
            }
        }
    });
}


//重置所有者权益表单
function resetOwnersEquityChange(){
	var div = $('.saveOwnersEquityChange').parents("#ownersEquityChange");
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".YEBATEOLY").val('');//上年年末余额
    div.find(".APC").val('');//会计政策变更
    div.find(".EEC").val('');//前期差错更正
    div.find(".BATBOTY").val('');//本年年初余额
    div.find(".AOIODITCY").val('');//本年增减变动金额
    div.find(".NP").val('');//净利润
    div.find(".GALDIIOE").val('');//直接计入所有者权益的利得和损失
    div.find(".NCIFVOAFSFA").val('');//可供出售金融资产公允值变动净额
    div.find(".TIOCIOORAIOIUUEM").val('');//权益法下被投资单位其他所有者权益变动的影响
    div.find(".ITRTOEI").val('');//与计入所有者权益项目相关的所得税影响
    div.find(".other1").val('');//其他
    div.find(".OIARC").val('');//所有者投入和减少资本
    div.find(".CIBO").val('');//所有者投入资本
    div.find(".OEIHI").val('');//其他权益工具持有者投入
    div.find(".TAOSPIITOE").val('');//股份支付计入所有者权益的金额
    div.find(".other2").val('');//其他
    div.find(".PD").val('');//利润分配
    div.find(".ESR").val('');//提取盈余公积
    div.find(".EGRP").val('');//提取一般风险准备
    div.find(".DOOOS").val('');//对所有者（或股东）的分配
    div.find(".other3").val('');//其他
    div.find(".ITOOE").val('');//所有者权益内部结转
    div.find(".CSICOCS").val('');//资本公积转增资本（或股本）
    div.find(".SSTICOCS").val('');//盈余公积转增资本（或股本）
    div.find(".SSTMUFL").val('');//盈余公积弥补亏损
    div.find(".other4").val('');//其他
    div.find(".specialReserves").val('');//专项储备
    div.find(".currentExtraction").val('');//本期提取
    div.find(".currentUse").val('');//本期使用
    div.find(".other5").val('');//其他
    div.find(".BATEOTY").val('');//本年年末余额
}




//编辑表单数值，触发修改合计表单中的值
//将所有需要计算的标签设置class=dataBinding
//将每组需要计算的标签设置相同的tag值
//将每组需要接收计算结果的id值等于tag的值
function calculation(){
    var inputs = $(".dataBinding");
    if(inputs.length > 0){
        for (var i = 0 ; i < inputs.length ; i++){
            //循环给所有需要数据绑定的标签绑定事件（表单值变化触发的事件）
            $(inputs[i]).bind('input propertychange', function() {
                var obj = $(this);
                var b = true;
                while(b){//定义循环处理相同的情况（合计是其他合计的基数情况）
                    var tagv = obj.attr('tag');//获取当前触发事件的对象的tag属性值
                    var elemtns = $('input[tag=' + tagv + ']')//找到其他tag值相同的标签
                    if(elemtns.length == 0){
                        b = false;
                    }
                    var str = 0.00;
                    for (var j = 0 ; j < elemtns.length ; j++){
                        var value = $(elemtns[j]).val();
                        //处理表单中的逗号
                        var reg = new RegExp('\\u002c' , 'g');
                        if(reg.test(value)){
                            value = value.replace(reg ,'');
                            $(elemtns[j]).val(value);
                        }

                        if(value != ""){
                            str += parseFloat(value);
                        }else{
                            str += 0.00;
                        }
                    }
                    $('#' + tagv).val(str.toFixed(2));
                    obj = $('#' + tagv);
                }
            });


        }
    }

}