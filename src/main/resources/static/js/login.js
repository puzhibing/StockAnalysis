
$(document).ready(function () {

    $('.login').click(function () {
        login();
    });
});

//登录
function login(){
    let username = $('.username').val().trim();
    let password = $('.password').val().trim();
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
                let token = b.result;
                window.href='home.html?token=' + token;
            }else{
                alert(b.result);
            }
        }
    });
}