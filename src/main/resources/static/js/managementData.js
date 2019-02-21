
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