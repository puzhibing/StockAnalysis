var companyId = '';
var industryId = '';
$(document).ready(function() {
    getStockType();
    var width = $(document.body).outerWidth(true);
    var height = $(document.body).outerHeight(true);

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

    $('.selectionPanelIndustry .table').css({
        'left': (width - 500) / 2,
    });

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
    $.ajax({
        url: '/DataAnalysis/assetLiabilityRatio',
        type: 'POST',
        data: {
            startTime: start,
            endTime: end,
            industryId: industryId,
            stockTypeId: stockType,
            companyId: companyId
        },
        success: function (res) {
            if(res.b){
                initDiv();
                var list = res.result;
                currentRatio(list[0].date , list[0].value);
                quickRatio(list[1].date , list[1].value);
                // assetsAndLiabilities(list[2].date , list[2].value);
                // constructionCurve2(list[3].date , list[3].value);
                assetsAndLiabilities(list[4].date , list[4].value);
            }
        }
    });
}


function initDiv() {
    $('.stdtar-curve').css({
        'border': '1px solid #DFDFDF'
    });
    $('#currentRatio').siblings('span').text('短期偿债能力分析');
    $('#container1').siblings('span').text('长期偿债能力分析');
    $('#assetsAndLiabilities').siblings('span').text('综合分析');
    $('.stdtar-curve').children('span').css({
        'display': 'block',
        'height': '30px',
        'width': '100%',
        'background-color': '#F8F8F8'
    });

}


//构建曲线图

//流动比率
function currentRatio(xAxis , series) {
    var title = {
        text: '流动比率（流动资产 / 流动负债）',
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


//速动比率
function quickRatio(xAxis , series) {
    var title = {
        text: '速动比率（流动资产合计 - 存货 - 应收账款 - 预付账款） / 流动负债）',
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


function assetsAndLiabilities1(xAxis , series) {
    var title = {
        text: '',
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

    $('#container2').highcharts(json);
}



function assetsAndLiabilities(xAxis , series) {
    var title = {
        text: '资产负债率（资产总额 / 负债总额）',
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