
var token = '';
var industryId = '';

//初始化
$(document).ready(function () {

	
    //获取token
    token = getURLParameters('token');

    initStyle();

    getURLParameters('token');

     findAllCompany();

    selectAllStockType('1');

    selectAllStockExchange('1');


    //绑定表单值发生变化处理函数
    $('.securitiesNumber').bind('input propertychange', function() {
        fuzzyAcquisition(this);
    });

    $(document).click(function () {
        var se = $('.text .selectionPanel');
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
    
    $('.content .data .industry').click(function () {
        $('.selectionPanelIndustry .table .condition').html('');
        getData('.selectionPanelIndustry .table .condition' , "0");
        $('.selectionPanelIndustry').show();
    });

    $('.selectionPanelIndustry .table .title i').click(function () {
        $('.selectionPanelIndustry').hide();
    });

    $('.selectionPanelIndustry .table .foot button').click(function () {
        doubleClickLi();
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

    var width = $(document.body).outerWidth(true);
    var height = $(document.body).outerHeight(true);

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

    $('.selectionPanelIndustry .table').css({
        'left': (width - 500) / 2,
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
            var str = '';
            if(res.b){
                var types = JSON.parse(res.result);
                if(hj == '1'){
                	for (var i = 0 ; i < types.length ; i++){
                        str += '<option value="' + types[i].id + '">' + types[i].name + '</option>';
                    }
                    $('.stockType').html(str);
                }else if(hj == '2'){
                	for (var i = 0 ; i < types.length ; i++){
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
            var str = '';
            if(res.b){
                var types = JSON.parse(res.result);
                if(hj == '1'){
                	for (var i = 0 ; i < types.length ; i++){
                        str += '<option value="' + types[i].id + '">' + types[i].name + '</option>';
                    }
                    $('.stockExchange').html(str);
                }else if(hj == '2'){
                	for (var i = 0 ; i < types.length ; i++){
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
    var companyId = $(".companyId").val();
    var chName = $(".chName").val();
    var chShortName = $(".chShortName").val();
    var enName = $(".enName").val();
    var enShortName = $(".enShortName").val();
    var registerTime = StringToDate($(".registerTime").val()).toString();
    var url = $(".url").val();

    if(chName == ''){
        alert('请填写正确的数据');
        return;
    }

    var formData =  new FormData();
    formData.append("id", companyId);
    formData.append("chName", chName);
    formData.append("chShortName", chShortName);
    formData.append("enName", enName);
    formData.append("enShortName", enShortName);
    formData.append("registerTime", registerTime);
    formData.append("url", url);
    formData.append("industry", industryId);
    formData.append("token", token);

    var u = "/updateCompany";
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
                var company = JSON.parse(data.result);
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
    industryId = '';
    $(".companyId").val("");
}




//保存公司证券数据
function saveCompanyStock(){
    var companyStockId = $(".companyStockId").val();
    var companyId = $(".companyId").val();
    var stockType = $(".stockType").val();
    var stockCode = $(".stockCode").val();
    var listingTime = StringToDate($(".listingTime").val()).toString();
    var stockExchange = $(".stockExchange").val();

    var formData = new FormData();
    formData.append("id", companyStockId);
    formData.append("companyId", companyId);
    formData.append("stockCode", stockCode);
    formData.append("stockTypeId", stockType);
    formData.append("listingTime", listingTime);
    formData.append("stockExchangeId", stockExchange);
    formData.append("token", token);
    var u;
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
                var result = res.result;
                var str = '<tr><th>序号</th><th>企业名称</th><th>网址</th><th>操作</th></tr>';
                for (var i = 0 ; i < result.length ; i++){
                    str += '<tr id="' + result[i].id + '" onclick="selected(this)" data="' + result[i].id + ';' + result[i].chName + ';' + result[i].chShortName + ';' + result[i].enName + ';' + result[i].enShortName + ';' + result[i].registerTime + ';' + result[i].url;
                    if(null != result[i].industry && result[i].industry.length != 0){
                        str += ';' + result[i].industry.id + ';' + result[i].industry.name + '">';
                    }else{
                        str += ';;">';
                    }
                    str += '<td>' + (i + 1) + '</td><td>' + result[i].chName + '</td></td><td>' + result[i].url + '</td><td><button onclick="devareCompany(\'' + result[i].id + '\')">删除</button></td></tr>';
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
    var data = tr.attr('data');
    var arr = data.split(';');

    $(".companyId").val(arr[0]);
    $(".chName").val(arr[1]);
    $(".chShortName").val(arr[2]);
    $(".enName").val(arr[3]);
    $(".enShortName").val(arr[4]);
    $(".registerTime").val(longToDate(arr[5]));
    $(".url").val(arr[6]);
    $(".industry").val(arr[8]);
    industryId = arr[7];



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
    var companyId = $(".companyId").val();
    $.ajax({
        url: "/selectCompanyStockByCompanyId",
        type: "POST",
        data: {
            companyId: companyId
        },
        success: function (data) {
            $(".other>table").html('');
            if(data.b){
                var result = data.result;
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
        url: '/devareCompanyStock',
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



function devareCompany(id){
    id = id.trim();
    $.ajax({
        url: '/devareCompany',
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
    var add = $('.importPal .add').val();
    var type = $('.importPal .type').val();
    add = add.split(';');
    type = type.split(';');
    var t = '';
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
    var value = $(v).val();
    if(value.length < 5){
        return;
    }

    $.ajax({
        url: "/fuzzyQueryCompany",
        type: "POST",
        data: {
            value: value
        },
        success: function (res) {
            $(v).siblings(".selectionPanel").html('');
            if(res.b){
                var list = res.result;
                var str = '<ul>';
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
    var id = li.attr("id");
    var name = li.attr("name");

    $(".companyStockId").val(id);
    li.parents(".selectionPanel").siblings(".securitiesNumber").val(name);
    li.parents(".selectionPanel").siblings(".id").val(id);
    li.parents(".selectionPanel").hide();
}




//搜索企业数据
function selectCompanyInfoById(){
    $(".enterpriseInfo .companyStocks table").html('');
    $('.enterpriseInfo .company').html('');
    var id = $('.id').val().trim();
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
                var result = res.result;
                var str = '<tr><th>序号</th><th>企业名称</th><th>网址</th><th>操作</th></tr>' +
                    '<tr id="' + result.id + '" onclick="selected(this)" data="' + result.id + ';' + result.chName + ';' + result.chShortName + ';' + result.enName + ';' + result.enShortName + ';' + result.registerTime + ';' + result.url;
                    if(null != result.industry && result.industry.length != 0){
                        str += ';' + result.industry.id + ';' + result.industry.name + '">';
                    }else{
                        str += ';;">';
                    }
                    str += '<td>1</td><td>' + result.chName + '</td></td><td>' + result.url + '</td><td><button onclick="devareCompany(\'' + result.id + '\')">删除</button></td></tr>';;

                $('.CompanyData table').html(str);
                $('.CompanyData .text .securitiesNumber').val('');
                $('.CompanyData .text .id').val('');
            }
        }
    });
}




/**
 * 获取节点数据
 * @param parentId
 */
function getData(parentObj , parentId){
    $.ajax({
        url: '/selectDataByParentId',
        type: 'POST',
        data: {
            parentId: parentId
        },
        success: function (res) {
            if(res.b){
                var list = res.result;
                var str = '<ul>';
                for (var i = 0 ; i < list.length ; i++){
                    str += '<li id="' + list[i].id + '">' +
                        '   <div onclick="initLiEvent(this)">' +
                        '       <i class="fa fa-plus-circle" aria-hidden="true"></i>' +
                        '       <span>' + list[i].name + '</span>' +
                        '   </div>' +
                        '</li>';
                }
                str += '</ul>';
                $(parentObj).append(str);
            }
        }
    });
}


/**
 * 初始化li点击事件
 */
function initLiEvent(div) {
    var li = $(div).parent('li');
    var id = li.attr('id');
    industryId = id;
    var clazz = $(div).children('i').attr('class');
    if(clazz == 'fa fa-plus-circle'){
        $(div).children('i').attr('class' , 'fa fa-minus-circle');
        $(div).css({
            'color':'#547576'
        })
        li.siblings('li').children('div').removeAttr('style');
        li.siblings('li').children('ul').remove();
        li.siblings('li').children('div').children('i').attr('class' , 'fa fa-plus-circle');
        getData(li , id);
    }else{
        $(div).siblings('ul').remove();
        $(div).children('i').attr('class' , 'fa fa-plus-circle');
        $(div).removeAttr('style');
    }
}

//点击选择处理函数
function doubleClickLi(){
    if('' == industryId){
        alert('请先选择有效项');
        return;
    }
    var name = $('#' + industryId).children('div').children('span').text();
    $('.industry').val(name);
    $('.selectionPanelIndustry').hide();
}