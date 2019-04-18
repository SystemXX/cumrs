function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


var Cumrs = {
    getUser: function () {
        $.ajax({
            type: "GET",
            url: "user/getSession",
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: function (resp) {
                Cumrs.user = resp;
            },
            error: function (error) {
                Cumrs.user = [];
            }
        });
    },

    formatDate: function (now) {
        now = new Date(now);
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var date = now.getDate();
        var hour = now.getHours();
        var minute = now.getMinutes();
        var second = now.getSeconds();
        return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
    }
}

var Area = [
    {"departmentName":"光电信息系","specialities":[{"speciality":"测控技术与仪器"},{"speciality":"光电信息科学与工程"}]},
    {"departmentName":"机电信息系","specialities":[{"speciality":"机械设计制造及其自动化"},{"speciality":"机械电子工程"},{"speciality":"高分子材料与工程"}]},
    {"departmentName":"电子信息系","specialities":[{"speciality":"电气工程及其自动化"},{"speciality":"自动化"},{"speciality":"通信工程"},{"speciality":"电子信息工程"}]},
    {"departmentName":"商学院","specialities":[{"speciality":"国际经济与贸易"},{"speciality":"国际商务"},{"speciality":"市场营销"},
            {"speciality":"人力资源管理"},{"speciality":"财务管理"},{"speciality":"会计学"}]},
    {"departmentName":"计算机信息与技术系","specialities":[{"speciality":"计算机科学与技术"},{"speciality":"软件工程"},{"speciality":"信息管理与信息系统"},
            {"speciality":"物联网工程"}]},
    {"departmentName":"建筑工程系","specialities":[{"speciality":"造价工程"},{"speciality":"土木工程"}]},
    {"departmentName":"人文社科系","specialities":[{"speciality":"汉语言文学"},{"speciality":"广告学"},{"speciality":"日语"}]}
]
