var companyId = '';
var industryId = '';
var jsonObj = {};
$(document).ready(function() {

    var width = $(document.body).outerWidth(true);
    var height = $(document.body).outerHeight(true);

    $('.selectionPanelIndustry .table').css({
        'left': (width - 500) / 2,
    });

    $(document).click(function () {
        var se = $('.stdtar-queryConditions .selectionPanel');
        if(se.css('display') == 'block'){
            se.hide();
        }
        if('' == companyId){
            $('.enterprise').val('');
        }
    });

    //绑定表单值发生变化处理函数
    $('.enterprise').bind('input propertychange', function() {
        fuzzyAcquisition(this);
    });

    $('.submit').click(function () {
        var start = $('.star-time').val();
        var end = $('.end-time').val();
        var stockType = $('.stockType').val();
        if('' == start){
            $('.star-time').css({
                'border':'1px solid #CA4747'
            })
            return;
        }

        if('' == end){
            $('.end-time').css({
                'border':'1px solid #CA4747'
            })
            return;
        }

        if('' == stockType){
            $('.stockType').css({
                'border':'1px solid #CA4747'
            })
            return;
        }
        getData();
    });


    $('.stdtar-home .stdtar-queryConditions .industry').click(function () {
        $('.selectionPanelIndustry .table .condition').html('');
        getDataLi('.selectionPanelIndustry .table .condition' , "0");
        $('.selectionPanelIndustry').show();
    });

    $('.selectionPanelIndustry .table .title i').click(function () {
        $('.selectionPanelIndustry').hide();
    });

    $('.selectionPanelIndustry .table .foot button').click(function () {
        doubleClickLi();
    });

    //放大点击事件
    $('.amplification').click(function () {
        var text = $(this).siblings('span').text();
        $('.window .content span').html(text);

        $('.window').css({
            'width':'100%',
            'height':'100%'
        });
        $('.window .content').css({
            'width':'90%',
            'height':'80%'
        });
        $('.window .content .containerBig').css({
            'width':'100%',
            'height':'46%'
        });

        var datas = $(this).attr('data').split(';');
        $('#containerBig').highcharts(eval('jsonObj.' + datas[0]));
        $('#containerBigSM').highcharts(eval('jsonObj.' + datas[1]));
        var w = $('.window .content .containerBig').width();
        //创建循环定时任务
        var ref = setInterval(function(){
            var _w = $('.window .content .containerBig').width();
            if(w != _w){
                var chart = $('#containerBig').highcharts();
                chart.reflow();
                var chartSM = $('#containerBigSM').highcharts();
                chartSM.reflow();
                w = _w;
            }else{
                clearInterval(ref);
            }
        },100);

    });


    //缩小点击事件
    $('.narrow').click(function () {
        $('.window .content .containerBig').html('');
        $('.window .content span').html('');

        $('.window').removeAttr('style');
        $('.window .content').removeAttr('style');
        $('.window .content .containerBig').removeAttr('style');
    });

    $('.stdtar-home .stdtar-curve>div').mouseenter(function () {
        $(this).find('.tool').css({
           'color':'#727272'
        });
    });
    $('.stdtar-home .stdtar-curve>div').mouseleave(function () {
        $(this).find('.tool').removeAttr('style');
    });
    $('.window .content').mouseenter(function () {
        $(this).find('.tool').css({
            'color':'#727272'
        });
    });
    $('.window .content').mouseleave(function () {
        $(this).find('.tool').removeAttr('style');
    });


    getStockType();

});


//获取焦点还原样式
function revertStyle(){

}


function getStockType() {
    $.ajax({
        url: '/selectAllStockType',
        type:'POST',
        success: function (res) {
            if(res.b){
                var list = JSON.parse(res.result);
                var str = '';
                for(var i = 0 ; i < list.length ; i++){
                    if(i == 0){
                        $('.stockType').val(list[i].id);
                        str += '<option value="' + list[i].id + '" selected="selected">' + list[i].name + '</option>';
                    }else{
                        str += '<option value="' + list[i].id + '">' + list[i].name + '</option>';
                    }
                }
                $('.stockType').append(str);
            }
        }
    });
}


function fuzzyAcquisition(v) {
    $(v).siblings(".selectionPanel").show();
    var value = $(v).val();
    if(value.length < 5){
        return;
    }

    $.ajax({
        url: "/fuzzyQueryCompany",
        type: "POST",
        data: {
            value: value
        },
        success: function (res) {
            $(v).siblings(".selectionPanel").html('');
            if(res.b){
                var list = res.result;
                var str = '<ul>';
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
    var name = li.attr("name");

    companyId = li.attr("id");
    li.parents(".selectionPanel").siblings(".enterprise").val(name);
    li.parents(".selectionPanel").hide();
}


function getData() {
    var start = $('.star-time').val();
    var end = $('.end-time').val();
    var stockType = $('.stockType').val();
    var sm = $('.sm').val();
    $.ajax({
        url: '/DataAnalysis/comprehensiveAnalysis',
        type: 'POST',
        data: {
            startTime: start,
            endTime: end,
            industryId: industryId,
            stockTypeId: stockType,
            companyId: companyId,
            sm: sm
        },
        success: function (res) {
            if(res.b){
                var map = res.result;
                directRevenueShare(map.directRevenueShare.date , map.directRevenueShare.value);
                directRevenueShareSM(map.directRevenueShareSM.date , map.directRevenueShareSM.value);
                mainBusinessCashFlowRatio(map.mainBusinessCashFlowRatio.date , map.mainBusinessCashFlowRatio.value);
                mainBusinessCashFlowRatioSM(map.mainBusinessCashFlowRatioSM.date , map.mainBusinessCashFlowRatioSM.value);
                currentRatio(map.currentRatio.date , map.currentRatio.value);
                currentRatioSM(map.currentRatioSM.date , map.currentRatioSM.value);
                quickRatio(map.quickRatio.date , map.quickRatio.value);
                quickRatioSM(map.quickRatioSM.date , map.quickRatioSM.value);
                assetsAndLiabilities(map.assetsAndLiabilities.date , map.assetsAndLiabilities.value);
                assetsAndLiabilitiesSM(map.assetsAndLiabilitiesSM.date , map.assetsAndLiabilitiesSM.value);
            }
        }
    });
}



//构建曲线图

//主营业务收入占比
function directRevenueShare(xAxis , series) {
    var title = {
        text: '主营业务收入占比【营业收入净额 / 综合收益总额】',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '比例'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    jsonObj.directRevenueShare = json;

    $('#directRevenueShare').highcharts(json);
}

/**
 * 主营业务收入占比增长率
 * @param xAxis
 * @param series
 */
function directRevenueShareSM(xAxis , series) {
    var title = {
        text: '流动比率增长率【(本期 - 上期) / 上期】',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '增长率'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    jsonObj.directRevenueShareSM = json;

    $('#directRevenueShareSM').highcharts(json);
}



//主营业务现金流占比
function mainBusinessCashFlowRatio(xAxis , series) {
    var title = {
        text: '主营业务现金流占比',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '比例'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    jsonObj.mainBusinessCashFlowRatio = json;

    $('#mainBusinessCashFlowRatio').highcharts(json);
}

//主营业务现金流占比增长率
function mainBusinessCashFlowRatioSM(xAxis , series) {
    var title = {
        text: '主营业务现金流占比增长率【(本期 - 上期) / 上期】',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '增长率'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    jsonObj.mainBusinessCashFlowRatioSM = json;

    $('#mainBusinessCashFlowRatioSM').highcharts(json);
}


//流动比率
function currentRatio(xAxis , series) {
    var title = {
        text: '流动比率【流动资产 / 流动负债】',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '比例'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    $('#currentRatio').highcharts(json);
}

/**
 * 流动比率增长率
 * @param xAxis
 * @param series
 */
function currentRatioSM(xAxis , series) {
    var title = {
        text: '流动比率增长率【(本期 - 上期) / 上期】',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '增长率'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    $('#currentRatioSM').highcharts(json);
}



//速动比率
function quickRatio(xAxis , series) {
    var title = {
        text: '速动比率【(流动资产合计 - 存货 - 应收账款 - 预付账款) / 流动负债】',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '比例'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    $('#quickRatio').highcharts(json);
}

//速动比率增长率
function quickRatioSM(xAxis , series) {
    var title = {
        text: '速动比率增长率【(本期 - 上期) / 上期】',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '增长率'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    $('#quickRatioSM').highcharts(json);
}

function assetsAndLiabilities(xAxis , series) {
    var title = {
        text: '资产负债率【资产总额 / 负债总额】',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '比例'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    $('#assetsAndLiabilities').highcharts(json);
}


function assetsAndLiabilitiesSM(xAxis , series) {
    var title = {
        text: '资产负债率增长率【(本期 - 上期) / 上期】',
        style: {
            'font-size':'14px',
            'color':'#757575'
        }
    };
    var xAxis = {
        categories: xAxis
    };
    var yAxis = {
        title: {
            text: '比例'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };

    var series =  series;

    var credits = {
        enabled: false
    };

    var json = {};

    json.title = title;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.series = series;
    json.credits = credits;

    $('#assetsAndLiabilitiesSM').highcharts(json);
}





/**
 * 获取节点数据
 * @param parentId
 */
function getDataLi(parentObj , parentId){
    $.ajax({
        url: '/selectDataByParentId',
        type: 'POST',
        data: {
            parentId: parentId
        },
        success: function (res) {
            if(res.b){
                var list = res.result;
                var str = '<ul>';
                for (var i = 0 ; i < list.length ; i++){
                    str += '<li id="' + list[i].id + '">' +
                        '   <div onclick="initLiEvent(this)">' +
                        '       <i class="fa fa-plus-circle" aria-hidden="true"></i>' +
                        '       <span>' + list[i].name + '</span>' +
                        '   </div>' +
                        '</li>';
                }
                str += '</ul>';
                $(parentObj).append(str);
            }
        }
    });
}


/**
 * 初始化li点击事件
 */
function initLiEvent(div) {
    var li = $(div).parent('li');
    var id = li.attr('id');
    industryId = id;
    var clazz = $(div).children('i').attr('class');
    if(clazz == 'fa fa-plus-circle'){
        $(div).children('i').attr('class' , 'fa fa-minus-circle');
        $(div).css({
            'color':'#547576'
        })
        li.siblings('li').children('div').removeAttr('style');
        li.siblings('li').children('ul').remove();
        li.siblings('li').children('div').children('i').attr('class' , 'fa fa-plus-circle');
        getDataLi(li , id);
    }else{
        $(div).siblings('ul').remove();
        $(div).children('i').attr('class' , 'fa fa-plus-circle');
        $(div).removeAttr('style');
    }
}

//点击选择处理函数
function doubleClickLi(){
    if('' == industryId){
        alert('请先选择有效项');
        return;
    }
    var name = $('#' + industryId).children('div').children('span').text();
    $('.industry').val(name);
    $('.selectionPanelIndustry').hide();
}