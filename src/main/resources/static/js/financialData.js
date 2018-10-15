
$(function () {



    $(".menu nav").click(function () {
        switchNav(this);
    });
});

function switchNav(nav){
    $(".data div").hide();
    $(nav).siblings().css({
        "background-color":"#ECECEC",
        "border-bottom" : "1px solid #BCBCBC",
        "color":"#2B2B2B"
    });
    $(nav).css({
        "background-color":"#FFFFFF",
        "border-bottom" : "1px solid #FFFFFF",
        "color":"#75A2A5"
    });

    let id = $(nav).attr("class");
    $("#" + id).show();
}