var token = '';

$(document).ready(function () {
	
	//获取token
    token = getURLParameters('token');
	
    selectAllStockExchange();

    $('.save').click(function () {
        saveStockExchange();
    });

    $('.reset').click(function () {
        resetData();
    });
});



//获取所有数据
function selectAllStockExchange(){
    $.ajax({
        url: '/selectAllStockExchange',
        type: 'POST',
        data: {},
        success: function (res) {
            if(res.b){
                var list = res.result;
                analysisResult(list);
            }
        }
    });
}


//解析处理结果
function analysisResult(list){
	list = JSON.parse(list);
    $('.table table').html('');
    var str = '<tr><th>序号</th><th>交易所名称</th><th>交易所网址</th><th>操作</th></tr>';
    for(var i = 0 ; i < list.length ; i++){
        str += '<tr id="' + list[i].id + '"><td>' + (i + 1) + '</td><td>' + list[i].name + '<td>' + list[i].url + '</td>' +
            '<td><button data="' + list[i].id + ';' + list[i].name + ';' + list[i].url +  '" onclick="updateStockExchange(this)">编辑</button>' +
            '<button onclick="devareStockExchange(\'' + list[i].id + '\')">删除</button></td></tr>';
    }
    $('.table table').html(str);
}


//删除数据
function devareStockExchange(id){
	id = id.trim();
    $.ajax({
        url: '/devareStockExchange',
        type: 'POST',
        data: {
            id: id,
            token: token
        },
        success: function (res) {
            if(res.b){
                selectAllStockExchange();
            }
        }
    });
}



//添加或修改数据
function saveStockExchange(){
    var id = $('#id').val().trim();
    var name = $('#name').val().trim();
    var address = $('#url').val().trim();
    if(name == ''){
        alert('无效内容');
        return;
    }
    var url = '/updateStockExchange';
    if(id == ''){
        url = '/insertStockExchange';
    }
    $.ajax({
        url: url,
        type: 'POST',
        data: {
            id: id,
            name: name,
            url: address,
            token: token
        },
        success: function (res) {
            if(res.b){
                resetData();
                selectAllStockExchange();
            }
        }
    });

}


//修改数据
function updateStockExchange(b){
    var v = $(b).attr('data');
    var arr = v.split(';');
    $('#id').val(arr[0]);
    $('#name').val(arr[1]);
    $('#url').val(arr[2]);
}


//重置表单内容
function resetData(){
    $('#id').val('');
    $('#name').val('');
    $('#url').val('');
}