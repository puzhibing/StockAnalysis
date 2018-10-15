
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




    //页面跳转
    $(".dataManagement").click(function () {
        $(".iframe").attr("src" , "dataManagement.html");
    });
    $(".businessManagement").click(function () {
        $(".iframe").attr("src" , "businessManagement.html");
    });
    $(".financialData").click(function () {
        $(".iframe").attr("src" , "financialData.html");
    });

    $(".securitiesType").click(function () {
        $(".iframe").attr("src" , "securitiesType.html");
    });

    $(".stockExchange").click(function () {
        $(".iframe").attr("src" , "stockExchange.html");
    });

});