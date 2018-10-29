
let url = "http://localhost:8888";

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
    return null;
}


//将long类型的时间转换成日期
function longToDate(long){
    return new Date(long);
}



//解析url获取参数
function getURLParameters(key){
    let value = '';
    let url = window.location.href.trim();
    let arr = url.split('?');
    if(arr[1].indexOf('&') != 0){
        let parameters = arr[1].split('&');
        for(let i = 0 ; i < parameters.length ; i++){
            let v = parameters[i].split('=');
            if(v[0].trim() == key.trim()){
                value = v[1].trim();
                break;
            }
        }
    }
    return value;
}