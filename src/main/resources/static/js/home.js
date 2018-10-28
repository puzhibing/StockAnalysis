
let token = '';

$(function () {

    //获取token
    token = getURLParameters('token');


    $(".dataManagement").click(function () {
        window.open("managementData.html?token=" + token);
    });
});