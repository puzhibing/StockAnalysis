let chName , chShortName , enName , enShortName , registerTime , url , companyId , token , tr ,
    formData , u , stockType , stockCode , listingTime , stockExchange , companyStockId , result;


//初始化
$(document).ready(function () {

    initStyle();

    $(".save").click(function () {
        saveData();
    });

    $(".reset").click(function () {
        resetData();
    });

    $(".saveCompanyStock").click(function () {
        saveCompanyStock();
    });

});


//初始化样式
function initStyle(){
    $(".other button").attr("disabled","disabled");
    $(".other input").attr("disabled","disabled")
    $(".other button").css({
        "cursor":"not-allowed"
    });
}


//保存基础数据
function saveData(){
    chName = $(".chName").val();
    chShortName = $(".chShortName").val();
    enName = $(".enName").val();
    enShortName = $(".enShortName").val();
    registerTime = StringToDate($(".registerTime").val()).toString();
    url = $(".url").val();
    companyId = $(".companyId").val();
    token = $(".token").val();

    formData =  new FormData();
    formData.append("id", companyId);
    formData.append("chName", chName);
    formData.append("chShortName", chShortName);
    formData.append("enName", enName);
    formData.append("enShortName", enShortName);
    formData.append("registerTime", registerTime);
    formData.append("url", url);
    formData.append("token", token);

    if("" == companyId){
        u = "http://192.168.3.155:8888/insertCompany";
    }else{
        u = "http://192.168.3.155:8888/updateCompany";
    }
    $.ajax({
        url: u,
        type: "POST",
        date: formData,
        cache: false,
        processData: false,
        contentType: false,
        success: function (data) {
            if(data.b){
                alert("保存成功");
                resetData();
                $(".other button").removeAttr("disabled");
                $(".other input").removeAttr("disabled");
                $(".other button").css({
                    "cursor":"pointer"
                });
            }
        }
    });
}



//重置基础数据表单
function resetData(){
    $(".data div table tr td input").val("");
    $(".companyId").val("");
    $(".token").val("");
}




//保存公司证券数据
function saveCompanyStock(){
    companyStockId = $(".companyStockId").val();
    companyId = $(".companyId").val();
    stockType = $(".stockType").val();
    stockCode = $(".stockCode").val();
    listingTime = $(".listingTime").val();
    stockExchange = $(".stockExchange").val();

    formData = new FormData();
    formData.append("id", companyStockId);
    formData.append("companyId", companyId);
    formData.append("stockCode", stockCode);
    formData.append("stockTypeId", stockType);
    formData.append("listingTime", listingTime);
    formData.append("stockExchangeId", stockExchange);
    if("" == companyStockId){
        u = "/insertCompanyStock";
    }else{
        u = "/updateCompanyStock";
    }
    $.ajax({
        url: u,
        type: "POST",
        data: formData,
        cache: false,
        processData: false,
        contentType: false,
        success: function (data) {
            if(data.b){
                alert("保存成功");
                $(".other div table tr td input").val("");
                selectCompanyStock();
            }

        }
    });

}



//查询公司证券数据
function selectCompanyStock(){
    companyId = $(".companyId").val();
    $.ajax({
        url: "/selectCompanyStockByCompanyId",
        type: "POST",
        data: {
            companyId: companyId
        },
        success: function (data) {
            if(data.b){
                result = data.result;
                JSON.stringify(result);
                var str = "";
                for(var i in result){
                    str += '<tr id="' + result[i].id + '"><td>' + result[i].stockTypeId[0].name + '</td><td>' + result[i].stockCode + '</td><td>' + result[i].listingTime + '</td>' +
                        '<td>' + result[i].stockExchangeId[0].name + '</td><td><button class="edit">编辑</button><button class="del">删除</button></td></tr>';
                }
                $(".other>table").append(str);
            }
        }
    });
}



//删除公司证券数据
function removeRow(obj){
    $(obj).parent("td").parent("tr").remove();
}

