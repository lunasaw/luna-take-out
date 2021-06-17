var everyPageDataCount = 7;
var usersPageIndex = 0;
var usersAllPage = 0;
$(document).ready(function () {


});

function getUsersList(loginName, pageIndex, everyPageDataCount, SynOrAsyn, url) {

}


function ADD_USER() {
    $("#USERS_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#USER_ADD_DIV_ID").attr("style", "display:block;");//隐藏div
}

function addUserCheck() {
    var loginName = $("#addLoginName").val().trim();
    var loginPwd = $("#addLoginPwd").val().trim();
    var trueName = $("#addTrueName").val().trim();
    var email = $("#addEmail").val().trim();
    var phone = $("#addPhone").val().trim();
    var address = $("#addAddress").val().trim();
    if (loginName == "") {
        $("#tishi").html("用户名不能为空");
        return;
    }
    if (loginName.length > 20) {
        $("#tishi").html("菜名最多20个字符");
        return;
    }

    if (loginPwd == "") {
        $("#tishi").html("密码不能为空");
        return;
    }
    if (loginPwd.length > 6) {
        $("#tishi").html("密码最多6位");
        return;
    }

    if (trueName == "") {
        $("#tishi").html("真实姓名不能为空");
        return;
    }
    if (trueName.length > 20) {
        $("#tishi").html("真实姓名做多20个字节");
        return;
    }

    if (email.trim() == "") {
        $("#tishi").html("电子邮箱不能为空");
        return;
    }

    var str = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!str.test(email)) {
        $("#tishi").html("电子邮箱格式不正确");
        return;
    }

    if (phone.trim() == "") {
        $("#tishi").html("手机号码不能为空");
        return;
    }

    var str = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
    if (!str.test(phone)) {
        $("#tishi").html("手机号码格式不正确");
        return;
    }

    if (address.trim() == "") {
        $("#tishi").html("送餐地址不能为空");
        return;
    }
    if (trueName.length > 20) {
        $("#tishi").html("送餐地址做多50个字节");
        return;
    }

    returnUserList();

}

function returnUserList() {
    $("#addLoginName").val("");
    $("#addLoginPwd").val("");
    $("#addTrueName").val("");
    $("#addEmail").val("");
    $("#addPhone").val("");
    $("#addAddress").val("");


    $("#editLoginName").val("");
    $("#editLoginPwd").val("");
    $("#editTrueName").val("");
    $("#editEmail").val("");
    $("#editPhone").val("");
    $("#editAddress").val("");
    $("#editLoginPwdHidder").val("");
    $("#editId").val("");


    $("#tishi1").html("");
    $("#tishi").html("");
    $("#USERS_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#USER_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#USER_EDIT_DIV_ID").attr("style", "display:none;");//隐藏div
//	$("#MEAL_IMG_DIV_ID").attr("style","display:none;");//隐藏div
}

function users_edit(id) {
    $("#USERS_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#USER_EDIT_DIV_ID").attr("style", "display:block;");//隐藏div

}

function returnordersList() {
    $("#MEAL_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#orderInfoDivId").attr("style", "display:none;");//隐藏div
}

function edtiUserCheck() {
    var id = $("#editId").val();
    var loginName = $("#editLoginName").val().trim();
    var loginPwd = $("#editLoginPwd").val().trim();
    var trueName = $("#editTrueName").val().trim();
    var email = $("#editEmail").val().trim();
    var phone = $("#editPhone").val().trim();
    var address = $("#editAddress").val().trim();
    if (loginName == "") {
        $("#tishi1").html("用户名不能为空");
        return;
    }
    if (loginName.length > 20) {
        $("#tishi1").html("菜名最多20个字符");
        return;
    }

    if (loginPwd.length > 6) {
        $("#tishi1").html("密码最多6位");
        return;
    }

    if (trueName == "") {
        $("#tishi1").html("真实姓名不能为空");
        return;
    }
    if (trueName.length > 20) {
        $("#tishi1").html("真实姓名做多20个字节");
        return;
    }

    if (email.trim() == "") {
        $("#tishi1").html("电子邮箱不能为空");
        return;
    }

    var str = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!str.test(email)) {
        $("#tishi1").html("电子邮箱格式不正确");
        return;
    }

    if (phone.trim() == "") {
        $("#tishi1").html("手机号码不能为空");
        return;
    }

    var str = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
    if (!str.test(phone)) {
        $("#tishi1").html("手机号码格式不正确");
        return;
    }

    if (address.trim() == "") {
        $("#tishi1").html("送餐地址不能为空");
        return;
    }
    if (trueName.length > 20) {
        $("#tishi1").html("送餐地址做多50个字节");
        return;
    }
    if (loginPwd == "") {
        loginPwd = $("#editLoginPwdHidder").val();
    }

    returnUserList();

}

function users_delete(id) {

}

function GOTO_USERS_NEXT_PAGE() {


}

function GOTO_USERS_TAIL_PAGE() {

}

function GOTO_USERS_PAGE() {
    var jumpVal = $("#JUMP_INPUT_ID").val().trim();
    if (jumpVal == "") {
        $.MsgBox.Alert("消息", "跳转页不能为空");
        return;
    }
    if (!(/^[0-9]+$/.test(jumpVal))) {
        $.MsgBox.Alert("消息", "页码必须为数字");
        return;
    }
    if (jumpVal <= 0) {
        $.MsgBox.Alert("消息", "页码必须大于等于1");
        return;
    }
    if (jumpVal > usersAllPage) {
        $.MsgBox.Alert("消息", "页码超出上限");
        return;
    }

}


function GOTO_USERS_HOME_PAGE() {

}

function GOTO_USERS_PREVIOUS_PAGE() {


}

function searchByUserName() {

}






