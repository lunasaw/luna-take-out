var everyPageDataCount = 7;
var usersPageIndex = 0;
var usersAllPage = 0;
$(document).ready(function () {


});

function getAdminList(loginName, pageIndex, everyPageDataCount, SynOrAsyn, url) {

}


function ADD_USER() {
    $("#USERS_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#USER_ADD_DIV_ID").attr("style", "display:block;");//隐藏div
}

function addUserCheck() {
    var loginName = $("#addLoginName").val().trim();
    var loginPwd = $("#addLoginPwd").val().trim();
    if (loginName == "") {
        $("#tishi").html("用户名不能为空");
        return;
    }
    if (loginName.length > 20) {
        $("#tishi").html("用户名最多20个字节");
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


    returnUserList();

}

function returnUserList() {
    $("#addLoginName").val("");
    $("#addLoginPwd").val("");


    $("#tishi").html("");
    $("#USERS_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#USER_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
//	$("#MEAL_IMG_DIV_ID").attr("style","display:none;");//隐藏div
}


function returnordersList() {
    $("#MEAL_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#orderInfoDivId").attr("style", "display:none;");//隐藏div
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






