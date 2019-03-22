
var token = '';
var height;
var width;

$(function () {
    height = $(document.body).outerHeight(true);
    width = $(document.body).outerWidth(true);

    //初始化页面宽度和高度
    $(".con").css({
        "height": height - 42,
        "width" : width - 221
    });

    $(".con iframe").css({
        "height": height - 42,
        "width" : width - 221
    });

    $(".menu").css({
        "height": height - 42,
    });


    //获取token
    token = getURLParameters('token');

    elementsclick();

    $('.toolInp').click(function () {
        if($('.tool').is(':hidden')){
            $('.tool').show();
        }else{
            $('.tool').hide();
        }
    });


    document.onclick = function(e){
        e = e || window.event;
        var dom =  e.srcElement|| e.target;
        if(dom.className !="toolInp"){
            if(dom.className !='tool' ){
                $('.tool').hide();
            }
        }
    };

    $('.head .tool li').click(function () {
        mintools(this);
    });



    $('.calculator .title .closeDiv').mouseenter(function(){
        $(this).css({
            'background-color':'#E81123'
        })
        $(this).children('i').css({
            'color':'#FFFFFF'
        });
    });

    $('.calculator .title .closeDiv').mouseleave(function(){
        $(this).removeAttr('style');
        $(this).children('i').removeAttr('style');
    });

    $('.calculator .title .closeDiv').click(function(){
        $('.calculator').hide();
        $('.calculator').removeAttr('style');
        $('.calculator input[type="number"]').val('');
        $('.calculator p').text(0);
        $($('.calculator select[class="computation"]').children('option')[0]).attr('selected','selected');
        $('.calculator select[class="computation"]').val('+');
    });

    $('.calculator input[type="number"]').bind('input propertychange',function () {
        calculate();
    });

    $('.calculator select[class="computation"]').bind('input propertychange',function () {
        calculate();
    });

    mobileCalculator();
});


function elementsclick(){
    //页面跳转
    $(".dataManagement").click(function () {
        $(".iframe").attr("src" , "dataManagement.html?token=" + token);
    });
    $(".businessManagement").click(function () {
        $(".iframe").attr("src" , "businessManagement.html?token=" + token);
    });
    $(".financialData").click(function () {
        $(".iframe").attr("src" , "financialData.html?token=" + token);
    });

    $(".securitiesType").click(function () {
        $(".iframe").attr("src" , "securitiesType.html?token=" + token);
    });

    $(".stockExchange").click(function () {
        $(".iframe").attr("src" , "stockExchange.html?token=" + token);
    });

    $(".industry").click(function () {
        $(".iframe").attr("src" , "industry.html?token=" + token);
    });

    $('.diaplayTitle').click(function () {
        diaplayTitle();
    });
}


function diaplayTitle(){
    var clazz = $('.menu .title i').attr('class');
    var str = '';
    $('.menu').remove();
    if('fa fa-eject fa-rotate-270' == clazz){
        str += '<div class="menu">\n' +
            '                <div class="title" title="展开">\n' +
            '                    <div class="diaplayTitle">\n' +
            '                        <i class="fa fa-eject fa-rotate-90"></i>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                <div class="cd">\n' +
            '                    <ul>\n' +
            '                        <li class="dataManagement" title="数据管理"><i class="fa fa-pie-chart"></i></li>\n' +
            '                        <li class="businessManagement" title="企业管理"><i class="fa fa-address-card-o"></i></li>\n' +
            '                        <li class="financialData" title="财务数据"><i class="fa fa-bar-chart-o"></i></li>\n' +
            '                        <li class="securitiesType" title="证券类型"><i class="fa fa-code-fork"></i></li>\n' +
            '                        <li class="stockExchange" title="证券交易所"><i class="fa fa-btc"></i></li>\n' +
            '                        <li class="industry" title="行业管理"><i class="fa fa-bookmark-o"></i></li>\n' +
            '                        <li title="全局设置"><i class="fa fa-cogs"></i></li>\n' +
            '                    </ul>\n' +
            '                </div>\n' +
            '            </div>';
        $('.content').prepend(str);

        $('.menu').css({
            'width':'30px'
        });
        $('.menu .cd i').css({
            'margin-top':'17px',
            'margin-left':'5px'
        });
        $(".con iframe").css({
            "height": height - 42,
            "width" : width - 30
        });

        elementsclick();
    }else{
        str += '<div class="menu">\n' +
            '                <div class="title">\n' +
            '                    <div class="bt">\n' +
            '\n' +
            '                    </div>\n' +
            '                    <div class="diaplayTitle">\n' +
            '                        <i class="fa fa-eject fa-rotate-270"></i>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                <div class="cd">\n' +
            '                    <ul>\n' +
            '                        <li class="dataManagement"><i class="fa fa-pie-chart"></i><span>数据管理</span></li>\n' +
            '                        <li class="businessManagement"><i class="fa fa-address-card-o"></i><span>企业管理</span></li>\n' +
            '                        <li class="financialData"><i class="fa fa-bar-chart-o"></i><span>财务数据</span></li>\n' +
            '                        <li class="securitiesType"><i class="fa fa-code-fork"></i><span>证券类型</span></li>\n' +
            '                        <li class="stockExchange"><i class="fa fa-btc"></i><span>证券交易所</span></li>\n' +
            '                        <li class="industry"><i class="fa fa-btc"></i><span>行业管理</span></li>\n' +
            '                        <li><i class="fa fa-cogs"></i><span>全局设置</span></li>\n' +
            '                    </ul>\n' +
            '                </div>\n' +
            '            </div>';
        $('.content').prepend(str);
        $(".con iframe").css({
            "height": height - 42,
            "width" : width - 221
        });
        
        elementsclick();
    }
}



function mintools(e) {
    var id = $(e).attr('id');
    switch (id) {
        case 'calculator':
            $('.' + id).show();
            break;
        default:
            break;
    }
}


//计算器计算函数
function calculate() {
    var n1 = $('.num1').val();
    var n2 = $('.num2').val();
    var c = $('.computation').val();
    var r = 0;
    n1 = ('' == n1 ? 0 : n1);
    n2 = ('' == n2 ? 0 : n2);
    switch (c) {
        case '+':
            r = parseFloat(n1) + parseFloat(n2);
            break;
        case '-':
            r = parseFloat(n1) - parseFloat(n2);
            break;
        case '*':
            r = parseFloat(n1) * parseFloat(n2);
            break;
        case '/':
            if(0 == n2){
                r = 0;
                alert('被除数不能为0');
            }else{
                r = parseFloat(n1) / parseFloat(n2);
            }
            break;
        default:
            break;
    }

    $('.result').text(r);
}

//移动计算器div函数
function mobileCalculator(){
    var $x = 0;
    var $y = 0;
    var x_ = 0;
    var y_ = 0;
    var x = 0;
    var y = 0;
    $('.calculator .title').mousedown(function (e) {
        $x = $('.calculator').offset().left;
        $y = $('.calculator').offset().top;

        var _x = e.originalEvent.x || e.originalEvent.layerX || 0;
        var _y = e.originalEvent.y || e.originalEvent.layerY || 0;

        x_ = _x - $x;
        y_ = _y - $y;

        $('.calculator .title').mousemove(function (ev) {
            x = ev.originalEvent.x || ev.originalEvent.layerX || 0;
            y = ev.originalEvent.y || ev.originalEvent.layerY || 0;

            $('.calculator').css({
                'top': y - y_,
                'left': x - x_
            });
        });
    });


    $('.calculator .title').mouseup(function (e) {
        $('.calculator .title').unbind('mousemove');
    });
}