var companyId = '';

$(document).ready(function() {
    getStockType();
    getIndustry();

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
    var industry = $('.industry').val();
    var stockType = $('.stockType').val();
    $.ajax({
        url: '/DataAnalysis/clnclr',
        type: 'POST',
        data: {
            startTime: start,
            endTime: end,
            industryId: industry,
            stockTypeId: stockType,
            companyId: companyId
        },
        success: function (res) {
            if(res.b){
                var list = res.result;
                constructionCurve(list.date , list.value);
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
