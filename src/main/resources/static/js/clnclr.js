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


function getIndustry() {
    $.ajax({
        url: '/selectAllIndustry',
        type: 'POST',
        success: function (res) {
            if (res.b) {
                var list = res.result;
                var str = '';
                for (var i = 0; i < list.length; i++) {
                    if (i == 0) {
                        $('.industry').val(list[i].id);
                        str += '<option value="' + list[i].id + '" selected="selected">' + list[i].name + '</option>';
                    } else {
                        str += '<option value="' + list[i].id + '">' + list[i].name + '</option>';
                    }
                }
                $('.industry').append(str);
            }
        }
    });
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
        url: '/DataAnalysis/clnclr',
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
                var list = res.result;
                constructionCurve(list[0].date , list[0].value);
                constructionCurveSM(list[1].date , list[1].value);
            }
        }
    });
}



//构建曲线图
function constructionCurve(xAxis , series) {
    var title = {
        text: '流动负债/非流动负债比'
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

    $('#container').highcharts(json);
}

/**
 * 增长率
 * @param xAxis
 * @param series
 */
function constructionCurveSM(xAxis , series) {
    var title = {
        text: '增长率【流动负债/非流动负债比】'
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

    $('#containerSM').highcharts(json);
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