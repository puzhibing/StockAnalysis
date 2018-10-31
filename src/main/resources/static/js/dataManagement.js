
let token = '';

$(document).ready(function () {


    //绑定表单值发生变化处理函数
    $('.securitiesNumber').bind('input propertychange', function() {
        fuzzyAcquisition(this);
    });

    //失去焦点隐藏选择面板等操作
    $('.securitiesNumber').blur(function () {
        let obj = $(this);
        obj.siblings(".selectionPanel").hide();
        if(obj.val() == ""){
            $(".companyId").val('');
            obj.parents("tr").siblings("tr").find(".securitiesType").val('');
            obj.parents("tr").siblings("tr").find(".companyName").val('');
        }
    });



});




//动态模糊筛选获取公司数据接口
function fuzzyAcquisition(v) {
    $(v).siblings(".selectionPanel").show();
    let value = $(v).val();
    $.ajax({
        url: "/fuzzyQueryCompany",
        type: "POST",
        data: {
        	value: value
        },
        success: function (res) {
            $(v).siblings(".selectionPanel").html('');
            if(res.b){
                let list = res.result;
                let str = '<ul>';
                for(var i = 0; i < list.length; i++){
                    str += '<li id="' + list[i].id +  '" name="' + list[i].name + '" onclick="selectionPanel(this)"><span>' + list[i].chName + '</span></li>';
                }
                $(v).siblings(".selectionPanel").html(str + '<ul/>');
            }
        }
    });
}


//选择面板值选中触发的事件
function selectionPanel(li){
    li = $(li);
    let id = li.attr("id");
    let name = li.attr("name");

    $(".companyStockId").val(id);
    li.parents(".selectionPanel").siblings(".securitiesNumber").val(name);
    li.parents(".selectionPanel").siblings(".companyId").val(id);
    th.parent("ul").parent("div").hide();
}