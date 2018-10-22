
$(function () {

    $(".menu nav").click(function () {
        switchNav(this);
    });

    //绑定表单值发生变化处理函数
    $('.securitiesNumber').bind('input propertychange', function() {
        fuzzyAcquisition(this);
    });

    //失去焦点隐藏选择面板等操作
    $('.securitiesNumber').blur(function () {
        var obj = $(this);
        obj.siblings(".selectionPanel").hide();
        if(obj.val() == ""){
            $(".companyId").val('');
            obj.parents("tr").siblings("tr").find(".securitiesType").val('');
            obj.parents("tr").siblings("tr").find(".companyName").val('');
        }
    });
});

//定义切换窗口的效果
function switchNav(nav){
    $(".dataType").hide();
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



//动态模糊筛选获取公司数据接口
function fuzzyAcquisition(v) {
    $(v).siblings(".selectionPanel").show();
    let value = $(v).val();
    $.ajax({
        url: "",
        type: "POST",
        data: {
            stockCode: value
        },
        success: function (res) {
            if(res.status){
                let str = '<ul>';
                for(var i = 0; i < 1; i++){
                    str += '<li id="' + '" code="' + '" type="' + '" name="' + '" onclick="selectionPanel(this)"><span>' + 123231 + '</span></li>';
                }
                $(".selectionPanel").html(str + '<ul/>');
            }
        }
    });
}


//选择面板值选中触发的事件
function selectionPanel(li){
    var th = $(li);
    var id = th.attr("id");
    var code = th.attr("code");
    var type = th.attr("type");
    var name = th.attr("name");

    $(".companyId").val(id);
    th.parents(".selectionPanel").siblings(".securitiesNumber").val(code);
    th.parents("tr").siblings("tr").find(".securitiesType").val(type);
    th.parents("tr").siblings("tr").find(".companyName").val(name);
    th.parent("ul").remove();
}