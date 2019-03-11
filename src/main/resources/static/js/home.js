
var token = '';

$(function () {

    //获取token
    token = getURLParameters('token');


    $(".dataManagement").click(function () {
        window.open("managementData.html?token=" + token);
    });

    $(".dataAnalysis").click(function () {
        window.open("dataAnalysis.html?token=" + token);
    });
});