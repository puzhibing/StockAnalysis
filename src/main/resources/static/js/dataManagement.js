
let token = '';

$(document).ready(function () {


    //绑定表单值发生变化处理函数
    $('.securitiesNumber').bind('input propertychange', function() {
        fuzzyAcquisition(this);
    });

    $(document).click(function () {
        let se = $('.text .selectionPanel');
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

    $('.text .selectionPanel').bind('click',function(e){
        stopPropagation(e);
    });


    $('.search').bind('click',function () {
        selectCompanyInfoById();
    })

});




//动态模糊筛选获取公司数据接口
function fuzzyAcquisition(v) {
    $(v).siblings(".selectionPanel").show();
    let value = $(v).val();
    $.ajax({
        url: "/fuzzyQueryCompany",
        type: "POST",
        data: {
        	value: value
        },
        success: function (res) {
            $(v).siblings(".selectionPanel").html('');
            if(res.b){
                let list = res.result;
                let str = '<ul>';
                for(var i = 0; i < list.length; i++){
                    str += '<li id="' + list[i].id +  '" name="' + list[i].chName + '" onclick="selectionPanel(this)"><span>' + list[i].chName + '</span></li>';
                }
                str += '</ul>';
                $(v).siblings(".selectionPanel").html(str);
            }
        }
    });
}


//选择面板值选中触发的事件
function selectionPanel(li){
    li = $(li);
    li.parents(".selectionPanel").show();
    let id = li.attr("id");
    let name = li.attr("name");

    $(".companyStockId").val(id);
    li.parents(".selectionPanel").siblings(".securitiesNumber").val(name);
    li.parents(".selectionPanel").siblings(".companyId").val(id);
    li.parents(".selectionPanel").hide();
}




//搜索企业数据
function selectCompanyInfoById(){
    let id = $('.companyId').val().trim();
    if(id == ''){
        alert('请录入有效的搜索条件');
        return;
    }
    $.ajax({
        url: '/selectCompanyInfoById',
        type: 'POST',
        data: {
            id: id
        },
        success: function (res) {
            $(".enterpriseInfo .companyStocks table").html('');
            $('.enterpriseInfo .company').html('');
            if(res.b){
                let data = res.result;
                let com = '<span>企业名称：' + data.chName + '</span><span>中文简称：' + data.chShortName + '</span><span>英文名称：' + data.enName + '</span><span>英文简称：' + data.enShortName + '</span><span>网址：' + data.url + '</span>';
                $('.enterpriseInfo .company').html(com);

                let str = "<tr><th>证券名称</th><th>证券编号</th><th>上市时间</th><th>上市地址</th></tr>";
                for(var i in data.companyStocks){
                    str += '<tr id="' + data.companyStocks[i].id + '" onclick="selectAllSecuritiesDataById(this)">' +
                        '<td>' + data.companyStocks[i].stockTypeId[0].name + '</td>' +
                        '<td>' + data.companyStocks[i].stockCode + '</td>' +
                        '<td>' + data.companyStocks[i].listingTime + '</td>' +
                        '<td>' + data.companyStocks[i].stockExchangeId[0].name + '</td>' +
                        '</tr>';
                }
                $(".enterpriseInfo .companyStocks table").html(str);
            }
        }
    });
}



//查询所有证券数据
function selectAllSecuritiesDataById(td){
    $('.currentAssets .data table').html('');
    $('.nonCurrentAssets .data table').html('');
    $('.currentLiabilities .data table').html('');
    $('.nonCurrentLiabilities .data table').html('');
    $('.ownersEquity .data table').html('');
    $('.ownersEquityChange .data table').html('');
    $('.profit .data table').html();
    $('.cashFlow .data table').html();
    $('.cashFlowStatement .data table').html();

    td = $(td);
    let id = td.attr('id');
    if(id == ''){
        alert('数据异常');
        return;
    }
    $.ajax({
        url: '/selectAllSecuritiesDataById',
        type: 'POST',
        data: {
            id: id
        },
        success: function (res) {
            if(res.b){
                analysisResult(res.result);
            }
        }
    });
}



//解析所有证券数据结果
function analysisResult(data){
    let currentAssets = JSON.parse(data.currentAssets);
    let nonCurrentAssets = JSON.parse(data.nonCurrentAssets);
    let currentLiabilities = JSON.parse(data.currentLiabilities);
    let nonCurrentLiabilities = JSON.parse(data.nonCurrentLiabilities);
    let ownersEquity = data.ownersEquity;
    let ownersEquityChange = data.ownersEquityChange;
    let profit = data.profit;
    let cashFlow = data.cashFlow;
    let cashFlowStatement = data.cashFlowStatement;

    let str1 = '<tr>' +
        '<th>序号</th><th>数据日期</th><th>货币资金</th><th>拆出资金</th><th>交易性金融资产</th><th>衍生金融资产</th><th>买入返售金融资产</th><th>应收票据</th><th>应收账款</th><th>预付账款</th><th>应收利息</th>' +
        '<th>应收股利</th><th>其他应收款</th><th>存货</th><th>一年内到期的非流动资产</th><th>其他流动资产</th><th>流动资产合计</th>' +
        '</tr>';
    for(var i = 0 ; i < currentAssets.length ; i++){
        str1 += '<tr>' +
            '<td>' + (i + 1) + '</td><td>' + currentAssets[i].dataTime + '</td><td>' + currentAssets[i].moneyFunds + '</td><td>' + currentAssets[i].wof + '</td><td>' + currentAssets[i].tfa + '</td><td>' + currentAssets[i].dfa + '</td><td>' + currentAssets[i].bbrfa + '</td>' +
            '<td>' + currentAssets[i].billReceivable + '</td><td>' + currentAssets[i].accountsReceivable + '</td><td>' + currentAssets[i].prepayments + '</td><td>' + currentAssets[i].interestReceivable + '</td><td>' + currentAssets[i].dividendReceivable + '</td>' +
            '<td>' + currentAssets[i].otherReceivables + '</td><td>' + currentAssets[i].stock + '</td><td>' + currentAssets[i].ncadwoy + '</td><td>' + currentAssets[i].oca + '</td><td>' + currentAssets[i].tca + '</td>' +
            '</tr>';
    }

    let str2 = '<tr>' +
        '<th>序号</th><th>数据日期</th><th>可供出售金融资产</th><th>持有到期投资</th><th>长期应收款</th><th>长期股权投资</th><th>投资性房地产</th><th>固定资产</th>' +
        '<th>在建工程</th><th>工程物资</th><th>固定资产清理</th><th>生产性生物资产</th><th>汽油资产</th><th>无形资产</th><th>开发支出</th><th>商誉</th><th>长期待摊费用</th>' +
        '<th>递延所得税资产</th><th>其他非流动资产</th><th>非流动资产合计</th>' +
        '</tr>';
    for(var i = 0 ; i < nonCurrentAssets.length ; i++){
        str2 += '<tr>' +
            '<td>' + (i + 1) + '</td><td>' + nonCurrentAssets[i].dataTime + '</td><td>' + nonCurrentAssets[i].afsfa + '</td><td>' + nonCurrentAssets[i].haei + '</td><td>' + nonCurrentAssets[i].ltr + '</td>' +
            '<td>' + nonCurrentAssets[i].ltbi + '</td><td>' + nonCurrentAssets[i].ire + '</td><td>' + nonCurrentAssets[i].fixedAssets + '</td><td>' + nonCurrentAssets[i].cap + '</td>' +
            '<td>' + nonCurrentAssets[i].engineerMaterial + '</td><td>' + nonCurrentAssets[i].fac + '</td><td>' + nonCurrentAssets[i].pba + '</td><td>' + nonCurrentAssets[i].gasolineAssets + '</td>' +
            '<td>' + nonCurrentAssets[i].intangibleAssets + '</td><td>' + nonCurrentAssets[i].de + '</td><td>' + nonCurrentAssets[i].goodwill + '</td><td>' + nonCurrentAssets[i].ltpe + '</td>' +
            '<td>' + nonCurrentAssets[i].dta + '</td><td>' + nonCurrentAssets[i].onca + '</td><td>' + nonCurrentAssets[i].tnca + '</td>' +
            '</tr>';
    }

    let str3 = '<tr>' +
        '<th>序号</th><th>数据日期</th><th>短期借款</th><th>拆入资金</th><th>交易性金融负债</th><th>衍生金融负债</th><th>卖出回购金融资产款</th><th>应付票据</th><th>应付账款</th><th>预收款项</th><th>应付职工薪酬</th>' +
        '<th>应交税费</th><th>应付利息</th><th>应付股利</th><th>其他应付款</th><th>一年内到期的非流动负债</th><th>其他流动负债</th><th>流动负债合计</th>' +
        '</tr>';
    for(var i = 0 ; i < currentLiabilities.length ; i++){
        str3 += '<tr>' +
            '<td>' + (i + 1) + '</td><td>' + currentLiabilities[i].dataTime + '</td><td>' + currentLiabilities[i].stl + '</td><td>' + currentLiabilities[i].uf + '</td><td>' + currentLiabilities[i].tfl + '</td><td>' + currentLiabilities[i].dfl + '</td><td>' + currentLiabilities[i].srfa + '</td>' +
            '<td>' + currentLiabilities[i].billsPayable + '</td><td>' + currentLiabilities[i].accountsPayable + '</td><td>' + currentLiabilities[i].advancePayment + '</td><td>' + currentLiabilities[i].payrollPayable + '</td><td>' + currentLiabilities[i].taxesPayable + '</td>' +
            '<td>' + currentLiabilities[i].interestPayable + '</td><td>' + currentLiabilities[i].dividendPayable + '</td><td>' + currentLiabilities[i].otherPayables + '</td><td>' + currentLiabilities[i].nldwoy + '</td>' +
            '<td>' + currentLiabilities[i].ocl + '</td><td>' + currentLiabilities[i].tcl + '</td>' +
            '</tr>';
    }

    let str4 = '<tr>' +
        '<th>序号</th><th>数据日期</th><th>长期借款</th><th>应付债券</th><th>长期应付款</th><th>专项应付款</th><th>预计负债</th><th>递延所得税负债</th>' +
        '<th>其他非流动负债</th><th>非流动负债合计</th>' +
        '</tr>';
    for(var i = 0 ; i < nonCurrentLiabilities.length ; i++){
        str4 += '<tr>' +
            '<td>' + (i + 1) + '</td><td>' + nonCurrentLiabilities[i].dataTime + '</td><td>' + nonCurrentLiabilities[i].ltl + '</td><td>' + nonCurrentLiabilities[i].bondsPayable + '</td><td>' + nonCurrentLiabilities[i].ltp + '</td>' +
            '<td>' + nonCurrentLiabilities[i].specialPayable + '</td><td>' + nonCurrentLiabilities[i].estimatedLiabilities + '</td><td>' + nonCurrentLiabilities[i].ditl + '</td><td>' + nonCurrentLiabilities[i].dncl + '</td>' +
            '<td>' + nonCurrentLiabilities[i].tncl + '</td>' +
            '</tr>';
    }

    let str5 = '<tr>' +
        '<th>序号</th><th>数据日期</th><th>实收资本</th><th>资本公积</th><th>减：库存股</th><th>盈余公积</th><th>一般风险准备</th><th>未分配利润</th><th>所有者权益合计</th>' +
        '</tr>';
    for(var i = 0 ; i < ownersEquity.length ; i++){
        str5 += '<tr>' +
            '<td>' + (i + 1) + '</td><td>' + ownersEquity[i].dataTime + '</td><td>' + ownersEquity[i].pic + '</td><td>' + ownersEquity[i].capitalReserve + '</td><td>' + ownersEquity[i].lts + '</td>' +
            '<td>' + ownersEquity[i].surplusReserve + '</td><td>' + ownersEquity[i].grp + '</td><td>' + ownersEquity[i].undistributedProfit + '</td><td>' + ownersEquity[i].toe + '</td>' +
            '</tr>';
    }

    let str6 = '<tr>' +
        '<th>序号</th><th>数据日期</th><th>营业收入</th><th>营业成本</th><th>营业税金及附加</th><th>销售费用</th><th>管理费用</th><th>财务费用</th>' +
        '<th>资产减值损失</th><th>公允价值变动收益</th><th>资产处置收益</th><th>投资收益</th><th>联营企业和合营企业投资收益</th><th>其他投资收益</th><th>营业利润</th><th>营业外收入</th>' +
        '<th>营业外支出</th><th>非流动资产处置损益</th><th>其他营业外支出</th><th>利润总额</th><th>所得税费用</th><th>净利润</th><th>每股收益</th><th>基本每股收益</th>' +
        '<th>稀释每股收益</th>' +
        '</tr>';
    for(var i = 0 ; i < profit.length ; i++){
        str6 += '<tr>' +
            '<td>' + (i + 1) + '</td><td>' + profit[i].dataTime + '</td><td>' + profit[i].businessIncome + '</td><td>' + profit[i].operatingCost + '</td><td>' + [i].btaa + '</td>' +
            '<td>' + profit[i].sellingExpenses + '</td><td>' + profit[i].managementCost + '</td><td>' + profit[i].financialCost + '</td><td>' + profit[i].ail + '</td>' +
            '<td>' + profit[i].fvci + '</td><td>' + profit[i].adi + '</td><td>' + profit[i].ifi + '</td><td>' + profit[i].iiojvajv + '</td><td>' + profit[i].oii + '</td>' +
            '<td>' + profit[i].operatingProfit + '</td><td>' + profit[i].noi + '</td><td>' + profit[i].noe + '</td><td>' + profit[i].paldoia + '</td>' +
            '<td>' + profit[i].onoe + '</td><td>' + profit[i].totalProfit + '</td><td>' + profit[i].ite + '</td><td>' + profit[i].netProfit + '</td>' +
            '<td>' + profit[i].eps + '</td><td>' + profit[i].beps + '</td><td>' + profit[i].deps + '</td>' +
            '</tr>';
    }

    let str7 = '<tr>' +
        '<th>序号</th><th>数据日期</th><th>销售商品、提供劳务收到的现金</th><th>收到的税费返还</th><th>收到的其他与经营活动相关的现金</th><th>经营活动现金流入小计</th><th>购买商品、接受劳务支付的现金</th><th>支付的各项税费</th>' +
        '<th>支付其他与经营活动有关的现金</th><th>经营活动现金流出小计</th><th>经营活动产生的现金流净额</th><th>收回投资收到的现金</th><th>取得投资收益收到的现金</th><th>处置固定资产、无形资产和其他上期资产收回的现金净额</th><th>处置子公司及其他营业单位收到的现金净额</th><th>收到其他与投资活动相关的现金</th>' +
        '<th>投资活动现金流入小计</th><th>构建固定资产、无形资产和其他上期投资支付的现金</th><th>投资支付的现金</th><th>取得子公司及其他营业单位支付的现金净额</th><th>支付其他与投资活动有关的现金</th><th>投资活动现金流出小计</th><th>投资活动产生的现金流净额</th><th>吸收投资收到的现金</th>' +
        '<th>取得借款收到的现金</th><th>收到其他与筹资活动有关的现金</th><th>筹资活动现金流入小计</th><th>偿还债务支出的现金</th><th>分配股利、利润或偿付利息支付的现金</th><th>支付其他与筹资活动有关的现金</th><th>筹资活动现金流出小计</th><th>筹资活动产生的现金流量净额</th>' +
        '<th>汇率变动对现金及现金等价物的影响</th><th>现金及现金等价物净增加额</th><th>期初现金及现金等价物余额</th><th>期末现金及现金等价物余额</th>' +
        '</tr>';
    for(var i = 0 ; i < cashFlow.length ; i++){
        str7 += '<tr>' +
            '<td>' + (i + 1) + '</td><td>' + cashFlow[i].dataTime + '</td><td>' + cashFlow[i].crfsogas + '</td><td>' + cashFlow[i].ort + '</td><td>' + cashFlow[i].cortbar + '</td>' +
            '<td>' + cashFlow[i].ciioa + '</td><td>' + cashFlow[i].cpfgas + '</td><td>' + cashFlow[i].poatot + '</td><td>' + cashFlow[i].poocrtba + '</td>'+
            '<td>' + cashFlow[i].cffoa + '</td><td>' + cashFlow[i].ncffoa + '</td><td>' + cashFlow[i].crfir + '</td><td>' + cashFlow[i].crfii + '</td>' +
            '<td>' + cashFlow[i].ncidofaiaaoaitpp + '</td><td>' + cashFlow[i].ncrfdosaobu + '</td><td>' + cashFlow[i].ocrtiahbr + '</td><td>' + cashFlow[i].cioia + '</td>' +
            '<td>' + cashFlow[i].cofaiaaocpfpi + '</td><td>' + cashFlow[i].cpfi + '</td><td>' + cashFlow[i].ncpbsaobu + '</td><td>' + cashFlow[i].poocrtia + '</td>' +
            '<td>' + cashFlow[i].cfooia + '</td><td>' + cashFlow[i].ncffia + '</td><td>' + cashFlow[i].crfi + '</td><td>' + cashFlow[i].crfb + '</td>' +
            '<td>' + cashFlow[i].ocrtfar + '</td><td>' + cashFlow[i].ciofa + '</td><td>' + cashFlow[i].cfdr + '</td><td>' + cashFlow[i].cpfdpoip + '</td>' +
            '<td>' + cashFlow[i].poocrtfa + '</td><td>' + cashFlow[i].cfoofa + '</td><td>' + cashFlow[i].ncfgbfra + '</td><td>' + cashFlow[i].teoerfocace + '</td>' +
            '<td>' + cashFlow[i].niicace + '</td><td>' + cashFlow[i].caceatboty + '</td><td>' + cashFlow[i].bocaceaeot + '</td>' +
            '</tr>';
    }

    let str8 = '<tr>' +
        '<th>序号</th><th>数据日期</th><th>上年年末余额</th><th>会计政策变更</th><th>前期差错更正</th><th>本年年初余额</th><th>本年增减变动金额</th><th>净利润</th>' +
        '<th>直接计入所有者权益的利得和损失</th><th>可供出售金融资产公允值变动净额</th><th>权益法下被投资单位其他所有者权益变动的影响</th><th>与计入所有者权益项目相关的所得税影响</th><th>其他</th><th>所有者投入和减少资本</th><th>所有者投入资本</th><th>股份支付计入所有者权益的金额</th>' +
        '<th>其他</th><th>利润分配</th><th>提取盈余公积</th><th>对所有者（或股东）的分配</th><th>其他</th><th>所有者权益内部结转</th><th>资本公积转增资本（或股本）</th><th>盈余公积转增资本（或股本）</th>' +
        '<th>盈余公积弥补亏损</th><th>其他</th><th>本年年末余额</th>' +
        '</tr>';
    for(var i = 0 ; i < ownersEquityChange.length ; i++){
        str8 += '<tr>' +
            '<td>' + (i + 1) + '</td><td>' + ownersEquityChange[i].dataTime + '</td><td>' + ownersEquityChange[i].yebateoly + '</td><td>' + ownersEquityChange[i].apc + '</td><td>' + ownersEquityChange[i].eec + '</td>' +
            '<td>' + ownersEquityChange[i].batboty + '</td><td>' + ownersEquityChange[i].aoioditcy + '</td><td>' + ownersEquityChange[i].np + '</td><td>' + ownersEquityChange[i].galdiioe + '</td>'+
            '<td>' + ownersEquityChange[i].ncifvoafsfa + '</td><td>' + ownersEquityChange[i].tiociooraioiuuem + '</td><td>' + ownersEquityChange[i].itrtoei + '</td><td>' + ownersEquityChange[i].other1 + '</td>'+
            '<td>' + ownersEquityChange[i].oiarc + '</td><td>' + ownersEquityChange[i].cibo + '</td><td>' + ownersEquityChange[i].taospiitoe + '</td><td>' + ownersEquityChange[i].other2 + '</td>'+
            '<td>' + ownersEquityChange[i].pd + '</td><td>' + ownersEquityChange[i].esr + '</td><td>' + ownersEquityChange[i].dooos + '</td><td>' + ownersEquityChange[i].other3 + '</td>'+
            '<td>' + ownersEquityChange[i].itooe + '</td><td>' + ownersEquityChange[i].csicocs + '</td><td>' + ownersEquityChange[i].ssticocs + '</td><td>' + ownersEquityChange[i].sstmufl + '</td>'+
            '<td>' + ownersEquityChange[i].other4 + '</td><td>' + ownersEquityChange[i].bateoty + '</td>'+
            '</tr>';
    }


    let str9 = '<tr>' +
        '<th>序号</th><th>数据日期</th><th>净利润</th><th>资产减值准备</th><th>固定资产折旧、汽油资产拆耗、生产性生物资产折旧</th><th>无形资产摊销</th><th>长期待摊费用摊销</th><th>处置固定资产、无形资产和其他长期资产的损失（收益）</th>' +
        '<th>固定资产报废损失</th><th>公允值变动损失</th><th>财务费用</th><th>投资损失</th><th>递延所得税资产减少</th><th>递延所得税负债增加</th><th>存货的减少</th><th>经营性应收项目的减少</th><th>经营性应付项目的增加</th><th>其他</th>' +
        '<th>经营活动产生的现金流量净额</th><th>债务转为资本</th><th>一年内到期的可转换公司债券</th><th>融资租入固定资产</th><th>现金的期末余额</th><th>减：现金的期初余额</th><th>加：现金等价物的期末余额</th><th>减：现金等价物的期初余额</th>' +
        '<th>现金及现金等价物净增加额</th>' +
        '</tr>';
    for(var i = 0 ; i < cashFlowStatement.length ; i++){
        str9 += '<tr>' +
            '<td>' + (i + 1) + '</td><td>' + cashFlowStatement[i].dataTime + '</td><td>' + cashFlowStatement[i].np + '</td>' +
            '<td>' + cashFlowStatement[i].aip + '</td><td>' + cashFlowStatement[i].dofagadadopba + '</td><td>' + cashFlowStatement[i].aoia + '</td><td>' + cashFlowStatement[i].aoltpe + '</td>' +
            '<td>' + cashFlowStatement[i].loiffaiaaolta + '</td><td>' + cashFlowStatement[i].losofa + '</td><td>' + cashFlowStatement[i].lofvc + '</td><td>' + cashFlowStatement[i].fc + '</td>' +
            '<td>' + cashFlowStatement[i].ll + '</td><td>' + cashFlowStatement[i].ditad + '</td><td>' + cashFlowStatement[i].iiiditl + '</td><td>' + cashFlowStatement[i].lr + '</td>' +
            '<td>' + cashFlowStatement[i].diori + '</td><td>' + cashFlowStatement[i].iiopi + '</td><td>' + cashFlowStatement[i].other + '</td><td>' + cashFlowStatement[i].ncffoac + '</td>' +
            '<td>' + cashFlowStatement[i].dtc + '</td><td>' + cashFlowStatement[i].scbdwoy + '</td><td>' + cashFlowStatement[i].flofa + '</td><td>' + cashFlowStatement[i].cateotp + '</td>' +
            '<td>' + cashFlowStatement[i].iboc + '</td><td>' + cashFlowStatement[i].eboce + '</td><td>' + cashFlowStatement[i].iboce + '</td><td>' + cashFlowStatement[i].niicace + '</td>' +
            '</tr>';
    }



    $('.currentAssets .data table').html(str1);
    $('.nonCurrentAssets .data table').html(str2);
    $('.currentLiabilities .data table').html(str3);
    $('.nonCurrentLiabilities .data table').html(str4);
    $('.ownersEquity .data table').html(str5);
    $('.profit .data table').html(str6);
    $('.cashFlow .data table').html(str7);
    $('.ownersEquityChange .data table').html(str8);
    $('.cashFlowStatement .data table').html(str9);
}