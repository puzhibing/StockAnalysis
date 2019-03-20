$(function () {
    var width = $(document.body).outerWidth(true);
    var height = $(document.body).outerHeight(true);

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

    $('.home .content').css({
        'height':height - 51
    });
});


//点击菜单获取对应页面中的节点
function getPageHtml(a) {
    var node = $(a).attr('node');
    var url = '';
    switch (node) {
        case 'assetLiabilityRatio':
            url = 'assetLiabilityRatio.html';
            break;
        case 'cancar':
            url = 'cancar.html';
            break;
        case 'clnclr':
            url = 'clnclr.html';
            break;
        case 'managementCapacity':
            url = 'managementCapacity.html';
            break;
        default:
            break;
    }
    $('.content').load(url);//获取页面
}