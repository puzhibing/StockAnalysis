
let token = '';

//初始化
$(document).ready(function () {

	
    //获取token
    token = getURLParameters('token');

    initStyle();

    getURLParameters('token');

     findAllCompany();

    selectAllStockType('1');

    selectAllStockExchange('1');

    selectAllIndustry();

    //绑定表单值发生变化处理函数
    $('.securitiesNumber').bind('input propertychange', function() {
        fuzzyAcquisition(this);
    });

    $(document).click(function () {
        let se = $('.text .selectionPanel');
        if(se.css('display') == 'block'){
            se.hide();
        }
    });

    $('.search').bind('click',function () {
        selectCompanyInfoById();
    })


    $(".save").click(function () {
        saveData();
    });

    $(".reset").click(function () {
        resetData();
    });

    $(".saveCompanyStock").click(function () {
        saveCompanyStock();
    });

    $('.importBu').click(function () {
    	selectAllStockExchange('2');
    	selectAllStockType('2');
        $('.importPal').show();
    });

    $('.importPal .import .title i').click(function () {
        $('.importPal').hide();
    });

    $('.importData').click(function () {
        importData();
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

    $('.importPal .import').css({
        'width': '325px',
        'height': '225px',
        'top': (height - 325) / 2,
        'left': (width - 225) / 2,
    });
}


//获取所有行业数据
function selectAllIndustry(){
    $.ajax({
        url: '/selectAllIndustry',
        type: 'POST',
        success: function (res){
            $('.industry').html('<span>所属行业：</span>');
            var str = '<span>所属行业：</span><select><option value=""></option>';
            if(res.b){
                var data = res.result;
                for(var i = 0 ; i < data.length ; i++){
                    str += '<option value="' + data[i].code + '">' + data[i].name + '</option>';
                }
                str += '</select>';
                $('.industry').html(str);
            }
        }
    });
}


//获取所有证券类型数据
function selectAllStockType(hj){
    $.ajax({
        url: '/selectAllStockType',
        type: 'POST',
        data: {},
        success: function (res) {
            $('.stockType').html('');
            $('.importPal .type').html('');
            let str = '';
            if(res.b){
                let types = JSON.parse(res.result);
                if(hj == '1'){
                	for (let i = 0 ; i < types.length ; i++){
                        str += '<option value="' + types[i].id + '">' + types[i].name + '</option>';
                    }
                    $('.stockType').html(str);
                }else if(hj == '2'){
                	for (let i = 0 ; i < types.length ; i++){
                        str += '<option value="' + types[i].id + ';' + types[i].name + '">' + types[i].name + '</option>';
                    }
                    $('.importPal .type').html(str);
                }
                
            }
        }
    });
}



//获取所有交易所数据
function selectAllStockExchange(hj){
    $.ajax({
        url: '/selectAllStockExchange',
        type: 'POST',
        data: {},
        success: function (res) {
            $('.stockExchange').html('');
            $('.importPal .add').html('');
            let str = '';
            if(res.b){
                let types = JSON.parse(res.result);
                if(hj == '1'){
                	for (let i = 0 ; i < types.length ; i++){
                        str += '<option value="' + types[i].id + '">' + types[i].name + '</option>';
                    }
                    $('.stockExchange').html(str);
                }else if(hj == '2'){
                	for (let i = 0 ; i < types.length ; i++){
                        str += '<option value="' + types[i].id + ';' + types[i].name + '">' + types[i].name + '</option>';
                    }
                	$('.importPal .add').html(str);
                }
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
    let industry = $(".industry select").val();

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
    formData.append("industry", industry);
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
                let industry = company.industry;
                $('.industry select option').removeAttr('selected');
                $('.industry').find('option[value="' + industry + '"]').attr('selected',true);

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
                let str = '<tr><th>序号</th><th>企业名称</th><th>网址</th><th>操作</th></tr>';
                for (let i = 0 ; i < result.length ; i++){
                    str += '<tr id="' + result[i].id + '" onclick="selected(this)" data="' + result[i].id + ';' + result[i].chName + ';' + result[i].chShortName + ';' + result[i].enName + ';' + result[i].enShortName + ';' + result[i].registerTime + ';' + result[i].url + ';' + result[i].industry + '">' +
                        '<td>' + (i + 1) + '</td><td>' + result[i].chName + '</td></td><td>' + result[i].url + '</td><td><button onclick="deleteCompany(\'' + result[i].id + '\')">删除</button></td></tr>';
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
        'background-color':'#ADCED0'
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
    let industry = arr[7];
    $('.industry select option').removeAttr('selected');
    $('.industry').find('option[value="' + industry + '"]').attr('selected',true);


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
                        '<td><button class="edit" onclick="updateCompanyStock(this)" data="' + result[i].id + ';' + result[i].stockTypeId[0].id + ';' + result[i].stockCode + ';' + result[i].listingTime + ';' + result[i].stockExchangeId[0].id + '">编辑</button>' +
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
    $(".listingTime").val(arr[3].substring(0,arr[3].indexOf('T')));
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



//导入数据
function importData(){
    let add = $('.importPal .add').val();
    let type = $('.importPal .type').val();
    add = add.split(';');
    type = type.split(';');
    let t = '';
    switch(type[1]){
        case 'A 股':
            t = 'A';
            break;
        case 'B 股':
            t = 'B';
            break;

    }

    $.ajax({
        url: "/crawlingCompany",
        type: 'POST',
        data: {
            type: t,
            stockTypeId: type[0],
            stockExchangeName: add[1],
            stockExchangeId: add[0],
            token: token
        },
        beforeSend: function(){
            processing();//添加加载效果
        },
        success: function (res) {
            if(res.b){
                alert("导入数据成功...\r\n" + res.result);
            }else{
                alert("数据导入发生异常...\r\n" + res.result);
            }
        },
        complete: function () {
            closeProcessing();//关闭特效
        }
    });
}



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
                    str += '<li id="' + list[i].id +  '" name="' + list[i].chName + '" onclick="selectionPanel(this)"><span>' + list[i].chName + '</span></li>';
                }
                str += '</ul>';
                $(v).siblings(".selectionPanel").html(str);
            }
        }
    });
}


//选择面板值选中触发的事件
function selectionPanel(li){
    li = $(li);
    li.parents(".selectionPanel").show();
    let id = li.attr("id");
    let name = li.attr("name");

    $(".companyStockId").val(id);
    li.parents(".selectionPanel").siblings(".securitiesNumber").val(name);
    li.parents(".selectionPanel").siblings(".id").val(id);
    li.parents(".selectionPanel").hide();
}




//搜索企业数据
function selectCompanyInfoById(){
    $(".enterpriseInfo .companyStocks table").html('');
    $('.enterpriseInfo .company').html('');
    let id = $('.id').val().trim();
    if(id == ''){
        alert('请录入有效的搜索条件');
        return;
    }
    $.ajax({
        url: '/selectCompanyInfoById',
        type: 'POST',
        data: {
            id: id
        },
        success: function (res) {

            if(res.b){
                let result = res.result;
                let str = '<tr><th>序号</th><th>企业名称</th><th>网址</th><th>操作</th></tr>' +
                    '<tr id="' + result.id + '" onclick="selected(this)" data="' + result.id + ';' + result.chName + ';' + result.chShortName + ';' + result.enName + ';' + result.enShortName + ';' + result.registerTime + ';' + result.url + ';' + result.industry + '">' +
                    '<td>1</td><td>' + result.chName + '</td></td><td>' + result.url + '</td><td><button onclick="deleteCompany(\'' + result.id + '\')">删除</button></td></tr>';;

                $('.CompanyData table').html(str);
                $('.CompanyData .text .securitiesNumber').val('');
                $('.CompanyData .text .id').val('');
            }
        }
    });
}