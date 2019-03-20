var token = '';
var parentId = '0';
var id = '';
var currentDom = '';//当前选中的dom
$(document).ready(function () {

    // 获取token
    token = getURLParameters('token');

    getData('.table' , "0");

    $('.save').click(function () {
        saveIndustry();
    });

    // $('.reset').click(function () {
    //     resetData();
    // });
});


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
                        '       <span>' +
                        '           <i class="fa fa-trash" aria-hidden="true" onclick="deleteData(' + list[i].id + ')"></i>' +
                        '           <i class="fa fa-pencil-square" aria-hidden="true"></i>' +
                        '       </span>' +
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
    parentId = id;
    currentDom = div;
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
        parentId = '0';
        currentDom = '';
    }
}


/**
 * 保存数据
 */
function saveIndustry(){
    var url = '';
    var name = $('#name').val();
    var code = $('#code').val();
    if('' == id){
        url = '/updateIndustry';
    }else{
        url = '/insertIndustry';
    }
    $.ajax({
        url: url,
        type: 'POST',
        data: {
            id: id,
            parentId: parentId,
            code: code,
            name: name
        },
        success: function (res) {
            if(res.b){
                alert('保存成功');
                var li = $(currentDom).parent('li').parent('ul').parent('li');
                var parentObj = '';
                if(li.length == 0){
                    parentObj = '.table';
                }else{
                    parentObj = li;
                }
                getData(parentObj , parentId);
                $(currentDom).parent('li').parent('ul').remove();
            }
        }
    });
}


/**
 * 删除数据
 * @param id
 */
function deleteData(id) {
    $.ajax({
        url: '/deleteIndustry',
        type: 'POST',
        data: {
            id: id,
            token: token
        },
        success: function (res) {
            if(res.b){
                alert('删除成功');
            }
        }
    });
}