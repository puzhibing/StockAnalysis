
$(document).ready(function () {

    $('.formCeontent .login').click(function () {
        login();
    });

    $('.registerPal .register').click(function () {
        register();
    });

    $('.formCeontent .register').click(function () {
        switchRegister();
    });

    $('.registerPal .login').click(function () {
        switchLogin();
    });


});



//登录
function login(){
    var username = $('.formCeontent .username').val().trim();
    var password = $('.formCeontent .password').val().trim();
    if(username == ''){
        alert('用户名无效');
        return;
    }
    if(password == ''){
        alert('密码无效');
        return;
    }

    $.ajax({
        url: '/verifyLogin',
        type: 'POST',
        data: {
            username: username,
            password: password
        },
        success: function (res) {
            if(res.b){
                var token = res.result;
                window.location.href = 'home.html?token=' + token;
            }else{
                alert(res.result);
            }
        }
    });
}


//切换到注册页面
function switchRegister(){
    $('.formCeontent .username').val('');
    $('.formCeontent .password').val('');
    $('.registerPal .username').val('');
    $('.registerPal .password').val('');
    $('.registerPal .phone').val('');

    $('.formCeontent').hide();
    $('.registerPal').show();
    $('.title span').text('用户注册');
}


//切换到登录
function switchLogin() {
    $('.formCeontent .username').val('');
    $('.formCeontent .password').val('');
    $('.registerPal .username').val('');
    $('.registerPal .password').val('');
    $('.registerPal .phone').val('');

    $('.formCeontent').show();
    $('.registerPal').hide();
    $('.title span').text('用户登录');
}


//注册
function register(){
    var username = $('.registerPal .username').val().trim();
    var password = $('.registerPal .password').val().trim();
    var phone = $('.registerPal .phone').val().trim();

    if(username == ''){
        alert('用户名不能为空');
    }
    if(password == ''){
        alert('密码不能为空');
    }
    if(phone == ''){
        alert('手机号码不能为空');
    }
}