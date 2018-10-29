
let token = '';
//初始化
$(document).ready(function () {

    initStyle();

    getURLParameters('token');

    selectAllStockType();

    selectAllStockExchange();

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


//获取所有证券类型数据
function selectAllStockType(){
    $.ajax({
        url: '/selectAllStockType',
        type: 'POST',
        data: {},
        success: function (res) {
            $('.stockType').html('');
            let str = '<option value=""></option>';
            if(res.b){
                let types = res.result;
                for (let i = 0 ; i < types.length ; i++){
                    str += '<option value="' + types[i].id + '">' + types[i].name + '</option>';
                }
                $('.stockType').html(str);
            }
        }
    });
}



//获取所有交易所数据
function selectAllStockExchange(){
    $.ajax({
        url: '/selectAllStockExchange',
        type: 'POST',
        data: {},
        success: function (res) {
            $('.stockExchange').html('');
            let str = '<option value=""></option>';
            if(res.b){
                let types = res.result;
                for (let i = 0 ; i < types.length ; i++){
                    str += '<option value="' + types[i].id + '">' + types[i].name + '</option>';
                }
                $('.stockExchange').html(str);
            }
        }
    });
}


//保存基础数据
function saveData(){
    let companyId = $(".companyId").val();
    let chName = $(".chName").val();
    let chShortName = $(".chShortName").val();
    let enName = $(".enName").val();
    let enShortName = $(".enShortName").val();
    let registerTime = StringToDate($(".registerTime").val()).toString();
    let url = $(".url").val();

    let formData =  new FormData();
    formData.append("id", companyId);
    formData.append("chName", chName);
    formData.append("chShortName", chShortName);
    formData.append("enName", enName);
    formData.append("enShortName", enShortName);
    formData.append("registerTime", registerTime);
    formData.append("url", url);
    formData.append("token", token);

    let u = "/updateCompany";
    if("" == companyId){
        u = "/insertCompany";
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
                let company = JSON.parse(data.result);
                $(".companyId").val(company.id);
                $(".chName").val(company.chName);
                $(".chShortName").val(company.chShortName);
                $(".enName").val(company.enName);
                $(".enShortName").val(company.enShortName);
                $(".registerTime").val(longToDate(company.registerTime));
                $(".url").val(company.url);

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
}




//保存公司证券数据
function saveCompanyStock(){
    let companyStockId = $(".companyStockId").val();
    let companyId = $(".companyId").val();
    let stockType = $(".stockType").val();
    let stockCode = $(".stockCode").val();
    let listingTime = $(".listingTime").val();
    let stockExchange = $(".stockExchange").val();

    let formData = new FormData();
    formData.append("id", companyStockId);
    formData.append("companyId", companyId);
    formData.append("stockCode", stockCode);
    formData.append("stockTypeId", stockType);
    formData.append("listingTime", listingTime);
    formData.append("stockExchangeId", stockExchange);
    let u;
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
                $('.stockType').val('');
                $('.stockType option:first').attr('selected','selected');
                $('.stockExchange').val('');
                $('.stockExchange option:first').attr('selected','selected');
                selectCompanyStock();
            }

        }
    });

}



//查询公司证券数据
function selectCompanyStock(){
    let companyId = $(".companyId").val();
    $.ajax({
        url: "/selectCompanyStockByCompanyId",
        type: "POST",
        data: {
            companyId: companyId
        },
        success: function (data) {
            $(".other>table").html('');
            if(data.b){
                let result = data.result;
                JSON.stringify(result);
                var str = "<tr><th>证券名称</th><th>证券编号</th><th>上市时间</th><th>上市地址</th><th>操作</th></tr>";
                for(var i in result){
                    str += '<tr id="' + result[i].id + '">' +
                        '<td>' + result[i].stockTypeId[0].name + '</td>' +
                        '<td>' + result[i].stockCode + '</td>' +
                        '<td>' + result[i].listingTime + '</td>' +
                        '<td>' + result[i].stockExchangeId[0].name + '</td>' +
                        '<td><button class="edit" onclick="updateCompanyStock(this)">编辑</button>' +
                        '<button class="del" onclick="removeRow(\'' + result[i].id + '\')">删除</button></td>' +
                        '</tr>';
                }
                $(".other>table").html(str);
            }
        }
    });
}


//编辑证券数据
function updateCompanyStock(){

}


//删除公司证券数据
function removeRow(id){
    id = id.trim();
    $.ajax({
        url: '/deleteCompanyStock',
        type: 'POST',
        data: {
            id: id,
            token: token
        },
        success: function (res) {
            if(res.b){

            }
        }
    });
}

