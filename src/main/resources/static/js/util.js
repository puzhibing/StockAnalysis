
var url = "http://localhost:8888";

//将字符串日期转换为日期对象1900-01-01 00:00:00
function StringToDate(strDate){
    strDate = $.trim(strDate);
    if(null != strDate && strDate.length > 0){
        var date;
        if(strDate.indexOf(" ") != -1){
            var d = strDate.substring(0 , strDate.indexOf(" ")).split("-");
            var m = strDate.substring(strDate.indexOf(" ") + 1 , strDate.length).split(":");
            date = new Date(parseInt(d[0]) , parseInt(d[1]) , parseInt(d[2]) , parseInt(m[0]) , parseInt(m[1]) , parseInt(m[2]));
        }else{
            var d = strDate.split("-");
            date = new Date(parseInt(d[0]) , parseInt(d[1]) , parseInt(d[2]));
        }
        return date;
    }
    return new Date();
}


//将long类型的时间转换成日期
function longToDate(long){
    var date = new Date(long);
    var str = date.getFullYear() + '-' ;
        if(date.getMonth() < 10){
            str += '0' + (date.getMonth() + 1) + '-';
        }else{
            str += (date.getMonth() + 1) + '-';
        }

    if(date.getDate() < 10){
        str += '0' + date.getDate();
    }else{
        str += date.getDate();
    }
    return str;
}



//解析url获取参数
function getURLParameters(key){
    var value = '';
    var url = window.location.href.trim();
    var arr = url.split('?');
    if(arr[1].indexOf('&') != 0){
        var parameters = arr[1].split('&');
        for(var i = 0 ; i < parameters.length ; i++){
            var v = parameters[i].split('=');
            if(v[0].trim() == key.trim()){
                value = v[1].trim();
                break;
            }
        }
    }
    return value;
}



//定义处理提示效果
function processing(){
    var str = '<div class="processing" style="position:fixed;top: 0px;left: 0px;z-index: 900;width: 100vw;height: 100vh;background-color:rgba(140,140,140,0.1);">' +
        '<i class="fa fa-spinner fa-pulse"></i>'+
        '</div>';

    $('body').append(str);

    var width = $(document.body).outerWidth(true);
    var height = $(document.body).outerHeight(true);
    $('.processing i').css({
        'font-size': '36px',
        'color': '#547576',
        'position': 'absolute',
        'z-index': '1000',
        'top': '200px',
        'left': (width - 20) / 2,
    });
}


//关闭处理提示效果
function closeProcessing(){
    $('.processing').remove();
}