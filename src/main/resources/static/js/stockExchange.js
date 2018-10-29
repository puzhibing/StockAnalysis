let token = '';

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
                let list = res.result;
                analysisResult(list);
            }
        }
    });
}


//解析处理结果
function analysisResult(list){
	list = JSON.parse(list);
    $('.table table').html('');
    let str = '<tr><th>序号</th><th>交易所名称</th><th>交易所网址</th><th>操作</th></tr>';
    for(let i = 0 ; i < list.length ; i++){
        str += '<tr id="' + list[i].id + '"><td>' + (i + 1) + '</td><td>' + list[i].name + '<td>' + list[i].url + '</td>' +
            '<td><button data="' + list[i].id + ';' + list[i].name + ';' + list[i].url +  '" onclick="updateStockExchange(this)">编辑</button>' +
            '<button onclick="deleteStockExchange(\'' + list[i].id + '\')">删除</button></td></tr>';
    }
    $('.table table').html(str);
}


//删除数据
function deleteStockExchange(id){
	id = id.trim();
    $.ajax({
        url: '/deleteStockExchange',
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
    let id = $('#id').val().trim();
    let name = $('#name').val().trim();
    let address = $('#url').val().trim();
    if(name == ''){
        alert('无效内容');
        return;
    }
    let url = '/updateStockExchange';
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
    let v = $(b).attr('data');
    let arr = v.split(';');
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