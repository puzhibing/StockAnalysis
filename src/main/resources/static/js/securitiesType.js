
var token = '';

$(document).ready(function () {
	
	//获取token
    token = getURLParameters('token');
	
    obtainAllData();

    $('.save').click(function () {
        insertStockType();
    });

    $('.reset').click(function () {
        resetData();
    });
})


//获取所有数据
function obtainAllData(){
    $.ajax({
        url: '/selectAllStockType',
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
    var str = '<tr><th>序号</th><th>证券类型名称</th><th>操作</th></tr>';
    for(var i = 0 ; i < list.length ; i++){
        str += '<tr id="' + list[i].id + '"><td>' + (i + 1) + '</td><td>' + list[i].name + '</td>' +
            '<td><button data="' + list[i].id + ';' + list[i].name + '" onclick="updateStockType(this)">编辑</button>' +
            '<button onclick="devareStockType(\'' + list[i].id + '\')" ">删除</button></td></tr>';
    }
    $('.table table').html(str);
}


//点击删除处理函数
function devareStockType(id){
	id = id.trim();
    $.ajax({
        url: '/devareStockType',
        type: 'POST',
        data: {
            id: id,
            token: token
        },
        success: function (res) {
            if(res.b){
                obtainAllData();
            }
        }
    });
}


//添加数据
function insertStockType(){
    var name = $('#name').val().trim();
    var id = $('#id').val().trim();
    if(name == ''){
        alert('无效的内容');
        return;
    }
    var url = '/updateStockType';
    if(id == ''){
        url = '/insertStockType'
    }

    $.ajax({
        url: url,
        type: 'POST',
        data: {
            id: id,
            name: name,
            token: token
        },
        success: function (res) {
            if(res.b){
                resetData();
                obtainAllData();
            }
        }
    });
}


//修改数据
function updateStockType(b){
    var v = $(b).attr('data');
    var arr = v.split(';');
    $('#id').val(arr[0]);
    $('#name').val(arr[1]);
}


//重置表单内容
function resetData(){
    $('#id').val('');
    $('#name').val('');
}