
let token = '';

//初始化
$(document).ready(function () {
	
    //获取token
    token = getURLParameters('token');

    initStyle();

    getURLParameters('token');

     findAllCompany();

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
    $(".other input").attr("disabled","disabled");
    $(".other select").attr("disabled","disabled")
    $(".other button").css({
        "cursor":"not-allowed"
    });

    let width = $(document.body).outerWidth(true);
    let height = $(document.body).outerHeight(true);

    $('.content .CompanyData').css({
        'max-width':width-785,
        'max-height':height-80
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
            let str = '';
            if(res.b){
                let types = JSON.parse(res.result);
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
            let str = '';
            if(res.b){
                let types = JSON.parse(res.result);
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

    if(chName == ''){
        alert('请填写正确的数据');
        return;
    }

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
        data: formData,
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
                $(".other select").removeAttr("disabled");
                $(".other button").css({
                    "cursor":"pointer"
                });
                findAllCompany();
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
    let listingTime = StringToDate($(".listingTime").val()).toString();
    let stockExchange = $(".stockExchange").val();

    let formData = new FormData();
    formData.append("id", companyStockId);
    formData.append("companyId", companyId);
    formData.append("stockCode", stockCode);
    formData.append("stockTypeId", stockType);
    formData.append("listingTime", listingTime);
    formData.append("stockExchangeId", stockExchange);
    formData.append("token", token);
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



//获取所有企业数据
function findAllCompany(){
    $('.CompanyData table').html('');
    $.ajax({
        url: '/selectAllCompany',
        type: 'POST',
        data: {},
        success: function (res) {
            if(res.b){
                let result = res.result;
                let str = '<tr><th>序号</th><th>企业名称</th><th>中文简称</th><th>英文名称</th><th>英文简称</th><th>注册日期</th><th>网址</th><th>操作</th></tr>';
                for (let i = 0 ; i < result.length ; i++){
                    str += '<tr id="' + result[i].id + '" onclick="selected(this)" data="' + result[i].id + ';' + result[i].chName + ';' + result[i].chShortName + ';' + result[i].enName + ';' + result[i].enShortName + ';' + result[i].registerTime + ';' + result[i].url + ';' + '">' +
                        '<td>' + (i + 1) + '</td><td>' + result[i].chName + '</td><td>' + result[i].chShortName + '</td><td>' + result[i].enName + '</td>' +
                        '<td>' + result[i].enShortName + '</td><td>' + new Date(result[i].registerTime) + '</td><td>' + result[i].url + '</td><td><button onclick="deleteCompany(\'' + result[i].id + '\')">删除</button></td></tr>';
                }
                $('.CompanyData table').html(str);
            }
        }
    });
}


//选中表格中的数据处理函数
function selected(tr){
    tr = $(tr);
    tr.css({
        'background-color':'#E5E5E5'
    });
    tr.siblings('tr').css({
        'background-color':'#FFFFFF'
    });
    let data = tr.attr('data');
    let arr = data.split(';');

    $(".companyId").val(arr[0]);
    $(".chName").val(arr[1]);
    $(".chShortName").val(arr[2]);
    $(".enName").val(arr[3]);
    $(".enShortName").val(arr[4]);
    $(".registerTime").val(longToDate(arr[5]));
    $(".url").val(arr[6]);

    $(".other button").removeAttr("disabled");
    $(".other input").removeAttr("disabled");
    $(".other select").removeAttr("disabled");
    $(".other button").css({
        "cursor":"pointer"
    });

    selectCompanyStock();

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
                var str = "<tr><th>证券名称</th><th>证券编号</th><th>上市时间</th><th>上市地址</th><th>操作</th></tr>";
                for(var i in result){
                    str += '<tr id="' + result[i].id + '">' +
                        '<td>' + result[i].stockTypeId[0].name + '</td>' +
                        '<td>' + result[i].stockCode + '</td>' +
                        '<td>' + longToDate(result[i].listingTime) + '</td>' +
                        '<td>' + result[i].stockExchangeId[0].name + '</td>' +
                        '<td><button class="edit" onclick="updateCompanyStock(this)" data="' + result[i].id + '' + result[i].stockTypeId[0].id + ';' + result[i].stockCode + ';' + result[i].listingTime + ';' + result[i].stockExchangeId[0].id + '">编辑</button>' +
                        '<button class="del" onclick="removeRow(\'' + result[i].id + '\')">删除</button></td>' +
                        '</tr>';
                }
                $(".other>table").html(str);
            }
        }
    });
}


//编辑证券数据
function updateCompanyStock(bj){
    var data = $(bj).attr('data');
    var arr = data.split(';');

    $(".companyStockId").val(arr[0]);
    $(".stockCode").val(arr[2]);
    $(".listingTime").val(arr[3]);
    $('.stockType').val(arr[1]);
    $('.stockType option[value=\'' + arr[1] + '\']').attr('selected','selected');
    $('.stockExchange').val(arr[4]);
    $('.stockExchange option[value=\'' + arr[4] + '\']').attr('selected','selected');
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
                alert('删除成功');
                selectCompanyStock();
            }
        }
    });
}



function deleteCompany(id){
    id = id.trim();
    $.ajax({
        url: '/deleteCompany',
        type: 'POST',
        data: {
            id: id,
            token: token
        },
        success: function (res) {
            if(res.b){
                alert('删除成功');
                findAllCompany();
            }
        }
    });
}

