
let token = '';
let height;
let width;

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

});