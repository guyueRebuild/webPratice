/**
 * Created by XieZhiHao on 2016/12/1.
 */
//获取当前日期时间
function getCurrentDateTime() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    return year + "-" + formatNum(month) + "-" + formatNum(day) + " " + formatNum(hours) + ":" + formatNum(minutes) + ":" + formatNum(seconds);
}

//获取当前日期
function getCurrentDate() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return year + "-" + formatNum(month) + "-" + formatNum(day);
}

//格式化小于10的数字
function formatNum(n) {
    if(n >= 0 && n <= 9) {
        return "0" + n;
    }else{
        return n;
    }
}