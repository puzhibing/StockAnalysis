$(function () {
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
        case 'stdtar':
            url = 'stdtar.html';
            break;
        default:
            break;
    }
    $('.content').load(url);//获取页面
}