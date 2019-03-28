$(function () {
    var width = $(document.body).outerWidth(true);
    var height = $(document.body).outerHeight(true);

    $('.home .content').css({
        'height':height - 71
    });

    //监听滚动条事件
    $(window).scroll(function () {
        if($(window).scrollTop() == 0){
            //到达顶部
            $('.head').removeAttr('style');
        }else{
            $('.head').css({
                'box-shadow':'0px 3px 10px rgba(128,128,128,0.3)' +
                    '                ,0px 3px 5px rgba(128,128,128,0.2)' +
                    '                ,0px 3px 3px rgba(128,128,128,0.1)'
            });
        }
    });


    $('.head div ul li').mouseenter(function () {
        $('.smallMenu').show();
        var clazz = $(this).attr('class');
        $('.' + clazz).css({
            'background-color': '#63989B'
        });
    });

    $('.head div ul li').mouseleave(function () {
        $('.smallMenu').hide();
        var clazz = $(this).attr('class');
        $('.' + clazz).removeAttr('style');
    });

    $('.smallMenu').mouseenter(function () {
        $(this).show();
    });

    $('.smallMenu').mouseleave(function () {
        $(this).hide();
    });


    $('.smallMenu div div').mouseenter(function () {
        var clazz = $(this).attr('class');
        $('.' + clazz).css({
            'background-color': '#63989B'
        });
    });

    $('.smallMenu div div').mouseleave(function () {
        var clazz = $(this).attr('class');
        $('.' + clazz).removeAttr('style');
    });

    $('.smallMenu div div ul li').click(function () {
        getPageHtml(this);
        $('.smallMenu').hide();
    });

});


//点击菜单获取对应页面中的节点
function getPageHtml(a) {
    var node = $(a).attr('node');
    var url = '';
    switch (node) {
        case 'assetLiabilityRatio':
            url = 'report/assetLiabilityRatio.html';
            break;
        case 'cancar':
            url = 'report/cancar.html';
            break;
        case 'clnclr':
            url = 'report/clnclr.html';
            break;
        case 'managementCapacity':
            url = 'report/managementCapacity.html';
            break;
        case 'aotsoba':
            url = 'report/aotsoba.html';
            break;
        case 'comprehensiveAnalysis':
            url = 'report/comprehensiveAnalysis.html';
            break;
        default:
            break;
    }
    $('.content').load(url);//获取页面
}