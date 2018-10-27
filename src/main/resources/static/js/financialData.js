
let token = "";

$(function () {

    $(".menu nav").click(function () {
        switchNav(this);
    });

    //绑定表单值发生变化处理函数
    $('.securitiesNumber').bind('input propertychange', function() {
        fuzzyAcquisition(this);
    });

    //失去焦点隐藏选择面板等操作
    $('.securitiesNumber').blur(function () {
        let obj = $(this);
        obj.siblings(".selectionPanel").hide();
        if(obj.val() == ""){
            $(".companyId").val('');
            obj.parents("tr").siblings("tr").find(".securitiesType").val('');
            obj.parents("tr").siblings("tr").find(".companyName").val('');
        }
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

    let id = $(nav).attr("class");
    $("#" + id).show();
}



//动态模糊筛选获取公司数据接口
function fuzzyAcquisition(v) {
    $(v).siblings(".selectionPanel").show();
    let value = $(v).val();
    $.ajax({
        url: "",
        type: "POST",
        data: {
            stockCode: value
        },
        success: function (res) {
            if(res.status){
                let str = '<ul>';
                for(var i = 0; i < 1; i++){
                    str += '<li id="' + '" code="' + '" type="' + '" name="' + '" onclick="selectionPanel(this)"><span>' + 123231 + '</span></li>';
                }
                $(".selectionPanel").html(str + '<ul/>');
            }
        }
    });
}


//选择面板值选中触发的事件
function selectionPanel(li){
    let th = $(li);
    let id = th.attr("id");
    let code = th.attr("code");
    let type = th.attr("type");
    let name = th.attr("name");

    $(".companyStockId").val(id);
    th.parents(".selectionPanel").siblings(".securitiesNumber").val(code);
    th.parents("tr").siblings("tr").find(".securitiesType").val(type);
    th.parents("tr").siblings("tr").find(".companyName").val(name);
    th.parent("ul").remove();
    th.parent("ul").parent("div").hide();
}


//保存流动资产数据
function saveCurrentAssets(){
    let div = $('.saveCurrentAssets').parents("#currentAssets");
    let id = div.find(".id").val();//数据id
    let companyStockId = $(".companyStockId").val();//企业证券id
    let dataTime = div.find(".dataTime").val();//数据日期
    let currencyUnit = div.find(".currencyUnit").val();//单位
    let moneyFunds = div.find(".moneyFunds").val();//货币资金
    let TFA = div.find(".TFA").val();//交易性金融资产
    let billReceivable = div.find(".billReceivable").val();//应收票据
    let accountsReceivable = div.find(".accountsReceivable").val();//应收账款
    let prepayments = div.find(".prepayments").val();//预付账款
    let interestReceivable = div.find(".interestReceivable").val();//应收利息
    let dividendReceivable = div.find(".dividendReceivable").val();//应收股利
    let otherReceivables = div.find(".otherReceivables").val();//其他应收款
    let stock = div.find(".stock").val();//存货
    let NCADWOY = div.find(".NCADWOY").val();//一年内到期的非流动资产
    let OCA = div.find(".OCA").val();//其他流动资产
    let TCA = div.find(".TCA").val();//流动资产合计

    let formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('moneyFunds', moneyFunds);
    formData.append('tfa', TFA);
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
            	resetCurrentAssets();
            }
        }
    });

}


//重置流动资产表单
function resetCurrentAssets(){
	let div = $('.saveCurrentAssets').parents("#currentAssets");
	div.find(".id").val("");//数据id
    $(".companyStockId").val("");//企业证券id
    div.find(".dataTime").val("");//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected' , 'selected');
    div.find(".moneyFunds").val("");//货币资金
    div.find(".TFA").val("");//交易性金融资产
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
    let div = $('.saveNonCurrentAssets').parents("#nonCurrentAssets");
    let id = div.find(".id").val();//数据id
    let companyStockId = $(".companyStockId").val();//企业证券id
    let dataTime = div.find(".dataTime").val();//数据日期
    let currencyUnit = div.find(".currencyUnit").val();//单位
    let AFSFA = div.find(".AFSFA").val();//可供出售金融资产
    let HAEI = div.find(".HAEI").val();//持有到期投资
    let LTR = div.find(".LTR").val();//长期应收款
    let LTEI = div.find(".LTEI").val();//长期股权投资
    let IRE = div.find(".IRE").val();//投资性房地产
    let fixedAssets = div.find(".fixedAssets").val();//固定资产
    let CAP = div.find(".CAP").val();//在建工程
    let engineerMaterial = div.find(".engineerMaterial").val();//工程物资
    let FAC = div.find(".FAC").val();//固定资产清理
    let PBA = div.find(".PBA").val();//生产性生物资产
    let gasolineAssets = div.find(".gasolineAssets").val();//汽油资产
    let intangibleAssets = div.find(".intangibleAssets").val();//无形资产
    let DE = div.find(".DE").val();//开发支出
    let goodwill = div.find(".goodwill").val();//商誉
    let LTPE = div.find(".LTPE").val();//长期待摊费用
    let DTA = div.find(".DTA").val();//递延所得税资产
    let ONCA = div.find(".ONCA").val();//其他非流动资产
    let TNCA = div.find(".TNCA").val();//非流动资产合计
    
    let formData = new FormData();
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
    formData.append('tnca', TNCA);
    
    $.ajax({
        url: '/insertNonCurrentAssets',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
            	resetNonCurrentAssets();
            }
        }
    });
}


//重置非流动资产表单
function resetNonCurrentAssets(){
	let div = $('.saveNonCurrentAssets').parents("#nonCurrentAssets");
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
    let div = $('.saveCurrentLiabilities').parents("#currentLiabilities");
    let id = div.find(".id").val();//数据id
    let companyStockId = $(".companyStockId").val();//企业证券id
    let dataTime = div.find(".dataTime").val();//数据日期
    let currencyUnit = div.find(".currencyUnit").val();//单位
    let STL = div.find(".STL").val();//短期借款
    let TFL = div.find(".TFL").val();//交易性金融负债
    let billsPayable = div.find(".billsPayable").val();//应付票据
    let accountsPayable = div.find(".accountsPayable").val();//应付账款
    let advancePayment = div.find(".advancePayment").val();//预收款项
    let payrollPayable = div.find(".payrollPayable").val();//应付职工薪酬
    let taxesPayable = div.find(".taxesPayable").val();//应交税费
    let interestPayable = div.find(".interestPayable").val();//应付利息
    let dividendPayable = div.find(".dividendPayable").val();//应付股利
    let otherPayables = div.find(".otherPayables").val();//其他应付款
    let NLDWOY = div.find(".NLDWOY").val();//一年内到期的非流动负债
    let OCL = div.find(".OCL").val();//其他流动负债
    let TCL = div.find(".TCL").val();//流动负债合计

    let formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('stl', STL);
    formData.append('tfl', TFL);
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

    $.ajax({
        url: '/insertCurrentLiabilities',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
            	resetCurrentLiabilities();
            }
        }
    });

}


//重置流动负债表单
function resetCurrentLiabilities(){
	let div = $('.saveCurrentLiabilities').parents("#currentLiabilities");
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".STL").val('');//短期借款
    div.find(".TFL").val('');//交易性金融负债
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
    let div = $('.saveNonCurrentLiabilities').parents("#nonCurrentLiabilities");
    let id = div.find(".id").val();//数据id
    let companyStockId = $(".companyStockId").val();//企业证券id
    let dataTime = div.find(".dataTime").val();//数据日期
    let currencyUnit = div.find(".currencyUnit").val();//单位
    let LTL = div.find(".LTL").val();//长期借款
    let bondsPayable = div.find(".bondsPayable").val();//应付债券
    let LTP = div.find(".LTP").val();//长期应付款
    let specialPayable = div.find(".specialPayable").val();//专项应付款
    let estimatedLiabilities = div.find(".estimatedLiabilities").val();//预计负债
    let DITL = div.find(".DITL").val();//递延所得税负债
    let ONCL = div.find(".ONCL").val();//其他非流动负债
    let TNCL = div.find(".TNCL").val();//非流动负债合计

    let formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('ltl', LTL);
    formData.append('bondsPayable', bondsPayable);
    formData.append('ltp', LTP);
    formData.append('specialPayable', specialPayable);
    formData.append('estimatedLiabilities', estimatedLiabilities);
    formData.append('ditl', DITL);
    formData.append('dncl', ONCL);
    formData.append('tncl', TNCL);

    $.ajax({
        url: '',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
            	resetNonCurrentLiabilities();
            }
        }
    });
}


//重置非流动负债表单
function resetNonCurrentLiabilities(){
	let div = $('.saveNonCurrentLiabilities').parents("#nonCurrentLiabilities");
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
    let div = $('.saveOwnersEquity').parents("#ownersEquity");
    let id = div.find(".id").val();//数据id
    let companyStockId = $(".companyStockId").val();//企业证券id
    let dataTime = div.find(".dataTime").val();//数据日期
    let currencyUnit = div.find(".currencyUnit").val();//单位
    let PIC = div.find(".PIC").val();//实收资本
    let capitalReserve = div.find(".capitalReserve").val();//资本公积
    let LTS = div.find(".LTS").val();//减：库存股
    let surplusReserve = div.find(".surplusReserve").val();//盈余公积
    let undistributedProfit = div.find(".undistributedProfit").val();//未分配利润
    let TOE = div.find(".TOE").val();//所有者权益合计

    let formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('pic', PIC);
    formData.append('capitalReserve', capitalReserve);
    formData.append('lts', LTS);
    formData.append('surplusReserve', surplusReserve);
    formData.append('undistributedProfit', undistributedProfit);
    formData.append('toe', TOE);

    $.ajax({
        url: '/insertOwnersEquity',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
            	resetOwnersEquity();
            }
        }
    });
}


//重置所有者权益表单
function resetOwnersEquity(){
	let div = $('.saveOwnersEquity').parents("#ownersEquity");
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".PIC").val('');//实收资本
    div.find(".capitalReserve").val('');//资本公积
    div.find(".LTS").val('');//减：库存股
    div.find(".surplusReserve").val('');//盈余公积
    div.find(".undistributedProfit").val('');//未分配利润
    div.find(".TOE").val('');//所有者权益合计
}




//保存利润
function saveProfit(){
    let div = $('.saveProfit').parents("#profit");
    let id = div.find(".id").val();//数据id
    let companyStockId = $(".companyStockId").val();//企业证券id
    let dataTime = div.find(".dataTime").val();//数据日期
    let currencyUnit = div.find(".currencyUnit").val();//单位
    let businessIncome = div.find(".businessIncome").val();//营业收入
    let operatingCost = div.find(".operatingCost").val();//营业成本
    let BTAA = div.find(".BTAA").val();//营业税金及附加
    let sellingExpenses = div.find(".sellingExpenses").val();//销售费用
    let managementCost = div.find(".managementCost").val();//管理费用
    let financialCost = div.find(".financialCost").val();//财务费用
    let AIL = div.find(".AIL").val();//资产减值损失
    let FVCI = div.find(".FVCI").val();//公允价值变动收益
    let IFI = div.find(".IFI").val();//投资收益
    let IIOJVAJV = div.find(".IIOJVAJV").val();//联营企业和合营企业投资收益
    let OII = div.find(".OII").val();//其他投资收益
    let operatingProfit = div.find(".operatingProfit").val();//营业利润
    let NOI = div.find(".NOI").val();//营业外收入
    let NOE = div.find(".NOE").val();//营业外支出
    let PALDOIA = div.find(".PALDOIA").val();//非流动资产处置损益
    let ONOE = div.find(".ONOE").val();//其他营业外支出
    let totalProfit = div.find(".totalProfit").val();//利润总额
    let ITE = div.find(".ITE").val();//所得税费用
    let netProfit = div.find(".netProfit").val();//净利润
    let EPS = div.find(".EPS").val();//每股收益
    let BEPS = div.find(".BEPS").val();//基本每股收益
    let DEPS = div.find(".DEPS").val();//稀释每股收益


    let formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('businessIncome', businessIncome);
    formData.append('operatingCost', operatingCost);
    formData.append('btaa', BTAA);
    formData.append('sellingExpenses', sellingExpenses);
    formData.append('managementCost', managementCost);
    formData.append('financialCost', financialCost);
    formData.append('ail', AIL);
    formData.append('fvci', FVCI);
    formData.append('ifi', IFI);
    formData.append('iiojvajv', IIOJVAJV);
    formData.append('oii', OII);
    formData.append('operatingProfit', operatingProfit);
    formData.append('noi', NOI);
    formData.append('noe', NOE);
    formData.append('paldoia', PALDOIA);
    formData.append('onoe', ONOE);
    formData.append('totalProfit', totalProfit);
    formData.append('ite', ITE);
    formData.append('netProfit', netProfit);
    formData.append('eps', EPS);
    formData.append('beps', BEPS);
    formData.append('deps', DEPS);

    $.ajax({
        url: '/insertProfit',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
            	resetProfit();
            }
        }
    });
}

//重置利润表单
function resetProfit(){
	let div = $('.saveProfit').parents("#profit");
    div.find(".id").val('');//数据id
    $(".companyStockId").val('');//企业证券id
    div.find(".dataTime").val('');//数据日期
    div.find(".currencyUnit").val("1");//单位
    div.find(".currencyUnit").children('option:first').attr('selected','selected');
    div.find(".businessIncome").val('');//营业收入
    div.find(".operatingCost").val('');//营业成本
    div.find(".BTAA").val('');//营业税金及附加
    div.find(".sellingExpenses").val('');//销售费用
    div.find(".managementCost").val('');//管理费用
    div.find(".financialCost").val('');//财务费用
    div.find(".AIL").val('');//资产减值损失
    div.find(".FVCI").val('');//公允价值变动收益
    div.find(".IFI").val('');//投资收益
    div.find(".IIOJVAJV").val('');//联营企业和合营企业投资收益
    div.find(".OII").val('');//其他投资收益
    div.find(".operatingProfit").val('');//营业利润
    div.find(".NOI").val('');//营业外收入
    div.find(".NOE").val('');//营业外支出
    div.find(".PALDOIA").val('');//非流动资产处置损益
    div.find(".ONOE").val('');//其他营业外支出
    div.find(".totalProfit").val('');//利润总额
    div.find(".ITE").val('');//所得税费用
    div.find(".netProfit").val('');//净利润
    div.find(".EPS").val('');//每股收益
    div.find(".BEPS").val('');//基本每股收益
    div.find(".DEPS").val('');//稀释每股收益
}





//保存现金流
function saveCashFlow(){
    let div = $('.saveCashFlow').parents("#cashFlow")
    let id = div.find(".id").val();//数据id
    let companyStockId = $(".companyStockId").val();//企业证券id
    let dataTime = div.find(".dataTime").val();//数据日期
    let currencyUnit = div.find(".currencyUnit").val();//单位
    let CRFSOGAS = div.find(".CRFSOGAS").val();//销售商品、提供劳务收到的现金
    let ROT = div.find(".ROT").val();//收到的税费返还
    let OCRTBAR = div.find(".OCRTBAR").val();//收到的其他与经营活动相关的现金
    let CIIOA = div.find(".CIIOA").val();//经营活动现金流入小计
    let CPFGAS = div.find(".CPFGAS").val();//购买商品、接受劳务支付的现金
    let POATOT = div.find(".POATOT").val();//支付的各项税费
    let POOCRTBA = div.find(".POOCRTBA").val();//支付其他与经营活动有关的现金
    let CFFOA = div.find(".CFFOA").val();//经营活动现金流出小计
    let NCFFOA = div.find(".NCFFOA").val();//经营活动产生的现金流净额
    let CRFIR = div.find(".CRFIR").val();//收回投资收到的现金
    let CRFII = div.find(".CRFII").val();//取得投资收益收到的现金
    let NCIDOFAIAAOAITPP = div.find(".NCIDOFAIAAOAITPP").val();//处置固定资产、无形资产和其他上期资产收回的现金净额
    let NCRFDOSAOBU = div.find(".NCRFDOSAOBU").val();//处置子公司及其他营业单位收到的现金净额
    let OCRTIAHBR = div.find(".OCRTIAHBR").val();//收到其他与投资活动相关的现金
    let CIOIA = div.find(".CIOIA").val();//投资活动现金流入小计
    let COFAIAAOCPFPI = div.find(".COFAIAAOCPFPI").val();//构建固定资产、无形资产和其他上期投资支付的现金
    let CPFI = div.find(".CPFI").val();//投资支付的现金
    let NCPBSAOBU = div.find(".NCPBSAOBU").val();//取得子公司及其他营业单位支付的现金净额
    let POOCRTIA = div.find(".POOCRTIA").val();//支付其他与投资活动有关的现金
    let CFOOIA = div.find(".CFOOIA").val();//投资活动现金流出小计
    let NCFFIA = div.find(".NCFFIA").val();//投资活动产生的现金流净额
    let CRFI = div.find(".CRFI").val();//吸收投资收到的现金
    let CRFB = div.find(".CRFB").val();//取得借款收到的现金
    let OCRTFAR = div.find(".OCRTFAR").val();//收到其他与筹资活动有关的现金
    let CIOFA = div.find(".CIOFA").val();//筹资活动现金流入小计
    let CFDR = div.find(".CFDR").val();//偿还债务支出的现金
    let CPFDPOIP = div.find(".CPFDPOIP").val();//分配股利、利润或偿付利息支付的现金
    let POOCRTFA = div.find(".POOCRTFA").val();//支付其他与筹资活动有关的现金
    let CFOOFA = div.find(".CFOOFA").val();//筹资活动现金流出小计
    let NCFGBFRA = div.find(".NCFGBFRA").val();//筹资活动产生的现金流量净额
    let NP = div.find(".NP").val();//净利润
    let AIP = div.find(".AIP").val();//资产减值准备
    let DOFAGADADOPBA = div.find(".DOFAGADADOPBA").val();//固定资产折旧、汽油资产拆耗、生产性生物资产折旧
    let AOIA = div.find(".AOIA").val();//无形资产摊销
    let AOLTPE = div.find(".AOLTPE").val();//长期待摊费用摊销
    let LOIFFAIAAOLTA = div.find(".LOIFFAIAAOLTA").val();//处置固定资产、无形资产和其他长期资产的损失（收益）
    let LOSOFA = div.find(".LOSOFA").val();//固定资产报废损失
    let LOFVC = div.find(".LOFVC").val();//公允值变动损失
    let FC = div.find(".FC").val();//财务费用
    let LL = div.find(".LL").val();//投资损失
    let DITAD = div.find(".DITAD").val();//递延所得税资产减少
    let IIIDITL = div.find(".IIIDITL").val();//递延所得税负债增加
    let LR = div.find(".LR").val();//存货的减少
    let DIORI = div.find(".DIORI").val();//经营性应收项目的减少
    let IIOPI = div.find(".IIOPI").val();//经营性应付项目的增加
    let Other = div.find(".Other").val();//其他
    let NCFFOAc = div.find(".NCFFOAc").val();//经营活动产生的现金流量净额
    let DTC = div.find(".DTC").val();//债务转为资本
    let SCBDWOY = div.find(".SCBDWOY").val();//一年内到期的可转换公司债券
    let FLOFA = div.find(".FLOFA").val();//融资租入固定资产
    let CATEOTP = div.find(".CATEOTP").val();//现金的期末余额
    let IBOC = div.find(".IBOC").val();//现金的期初余额
    let EBOCE = div.find(".EBOCE").val();//现金等价物的期末余额
    let IBOCE = div.find(".IBOCE").val();//现金等价物的期初余额
    let CACEATBOTY = div.find(".CACEATBOTY").val();//期初现金及现金等价物余额
    let BOCACEAEOT = div.find(".BOCACEAEOT").val();//期末现金及现金等价物余额
    let NIICACE = div.find(".NIICACE").val();//现金及现金等价物净增加额
    let TEOERFOCACE = div.find(".TEOERFOCACE").val();//汇率变动对现金及现金等价物的影响

    let formData = new FormData();
    formData.append('id', id);
    formData.append('companyStockId', companyStockId);
    formData.append('dataTime', dataTime);
    formData.append('currencyUnit', currencyUnit);
    formData.append('crfsogas', CRFSOGAS);
    formData.append('ort', ROT);
    formData.append('cortbar', OCRTBAR);
    formData.append('ciioa', CIIOA);
    formData.append('cpfgas', CPFGAS);
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
    formData.append('caceatboty', CACEATBOTY);
    formData.append('bocaceaeot', BOCACEAEOT);
    formData.append('niicace', NIICACE);
    formData.append('teoerfocace', TEOERFOCACE);


    $.ajax({
        url: '/insertCashFlow',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
            	resetCashFlow();
            }
        }
    });
}


//重置现金流表单
function resetCashFlow(){
	let div = $('.saveCashFlow').parents("#cashFlow")
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
    div.find(".CACEATBOTY").val('');//期初现金及现金等价物余额
    div.find(".BOCACEAEOT").val('');//期末现金及现金等价物余额
    div.find(".NIICACE").val('');//现金及现金等价物净增加额
    div.find(".TEOERFOCACE").val('');//汇率变动对现金及现金等价物的影响
}






//保存所有者权益变动
function saveOwnersEquityChange(){
    let div = $('.saveOwnersEquityChange').parents("#ownersEquityChange");
    let id = div.find(".id").val();//数据id
    let companyStockId = $(".companyStockId").val();//企业证券id
    let dataTime = div.find(".dataTime").val();//数据日期
    let currencyUnit = div.find(".currencyUnit").val();//单位
    let YEBATEOLY = div.find(".YEBATEOLY").val();//上年年末余额
    let APC = div.find(".APC").val();//会计政策变更
    let EEC = div.find(".EEC").val();//前期差错更正
    let BATBOTY = div.find(".BATBOTY").val();//本年年初余额
    let AOIODITCY = div.find(".AOIODITCY").val();//本年增减变动金额
    let NP = div.find(".NP").val();//净利润
    let GALDIIOE = div.find(".GALDIIOE").val();//直接计入所有者权益的利得和损失
    let NCIFVOAFSFA = div.find(".NCIFVOAFSFA").val();//可供出售金融资产公允值变动净额
    let TIOCIOORAIOIUUEM = div.find(".TIOCIOORAIOIUUEM").val();//权益法下被投资单位其他所有者权益变动的影响
    let ITRTOEI = div.find(".ITRTOEI").val();//与计入所有者权益项目相关的所得税影响
    let other1 = div.find(".other1").val();//其他
    let OIARC = div.find(".OIARC").val();//所有者投入和减少资本
    let CIBO = div.find(".CIBO").val();//所有者投入资本
    let TAOSPIITOE = div.find(".TAOSPIITOE").val();//股份支付计入所有者权益的金额
    let other2 = div.find(".other2").val();//其他
    let PD = div.find(".PD").val();//利润分配
    let ESR = div.find(".ESR").val();//提取盈余公积
    let DOOOS = div.find(".DOOOS").val();//对所有者（或股东）的分配
    let other3 = div.find(".other3").val();//其他
    let ITOOE = div.find(".ITOOE").val();//所有者权益内部结转
    let CSICOCS = div.find(".CSICOCS").val();//资本公积转增资本（或股本）
    let SSTICOCS = div.find(".SSTICOCS").val();//盈余公积转增资本（或股本）
    let SSTMUFL = div.find(".SSTMUFL").val();//盈余公积弥补亏损
    let other4 = div.find(".other4").val();//其他
    let BATEOTY = div.find(".BATEOTY").val();//本年年末余额

    let formData = new FormData();
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
    formData.append('taospiitoe', TAOSPIITOE);
    formData.append('other2', other2);
    formData.append('pd', PD);
    formData.append('esr', ESR);
    formData.append('dooos', DOOOS);
    formData.append('other3', other3);
    formData.append('itooe', ITOOE);
    formData.append('csicocs', CSICOCS);
    formData.append('ssticocs', SSTICOCS);
    formData.append('sstmufl', SSTMUFL);
    formData.append('other4', other4);
    formData.append('bateoty', BATEOTY);

    $.ajax({
        url: '/insertOwnersEquityChange',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function (res) {
            if(res.b){
            	resetOwnersEquityChange();
            }
        }
    });
}


//重置所有者权益表单
function resetOwnersEquityChange(){
	let div = $('.saveOwnersEquityChange').parents("#ownersEquityChange");
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
    div.find(".TAOSPIITOE").val('');//股份支付计入所有者权益的金额
    div.find(".other2").val('');//其他
    div.find(".PD").val('');//利润分配
    div.find(".ESR").val('');//提取盈余公积
    div.find(".DOOOS").val('');//对所有者（或股东）的分配
    div.find(".other3").val('');//其他
    div.find(".ITOOE").val('');//所有者权益内部结转
    div.find(".CSICOCS").val('');//资本公积转增资本（或股本）
    div.find(".SSTICOCS").val('');//盈余公积转增资本（或股本）
    div.find(".SSTMUFL").val('');//盈余公积弥补亏损
    div.find(".other4").val('');//其他
    div.find(".BATEOTY").val('');//本年年末余额
}