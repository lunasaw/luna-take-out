var everyPageDataCount = 7;
var usersPageIndex = 0;
var usersAllPage = 0;
$(document).ready(function () {
    let searchNameVal = $("#SEARCH_USER_NAME").val().trim();

    getUsersList(searchNameVal, 1, everyPageDataCount, true, "user/api/pageListByEntity");

});

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}

function getUsersList(loginName, pageStart, pageSize, SynOrAsyn, url) {
    let userInfo = {
        username: loginName,
        admin: 0,
    }
    console.log(userInfo)
    $.ajax({
        url: url + "/" + pageStart + "/" + pageSize, // url where to submit the request
        type: "GET", // type of action POST || GET
        data: userInfo,
        sync: SynOrAsyn,
        success: function (result) {
            // console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                console.log(error)
                // alert(JSON.stringify(error));
                $.MsgBox.Alert("消息", "出错了，请于管理员联系");
                return;
            }

            console.log(data);
            if (data == null) {
                return;
            }

            // 当前页面开始记录数目
            $("#data_count_start").text(data.startRow);
            // 当前页面结束记录数
            $("#data_count_end").text(data.endRow);
            // 总页数
            $("#page_count").text(data.pages);
            postAllPage = data.pages;
            // 总计
            $("#data_count").text(data.total);
            // 当前页数
            $("#page_num").text(data.pageNum);
            if (data.isFirstPage == true) {
                $("#page_pre_btn").attr("disabled", "disabled")
            } else {
                $("#page_pre_btn").removeAttr("disabled");
            }

            if (data.isLastPage == true) {
                $("#page_next_btn").attr("disabled", "disabled")
            } else {
                $("#page_next_btn").removeAttr("disabled");
            }


            // 渲染页面
            let list = data.list;
            if (list.length > 0) {
                let content = '';
                $('.user-data').empty();
                for (let i in list) {
                    content = content + '<tr bgcolor="#FFFFFF"><td valign="center" align="center" width="30">' + list[i].username + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].realName + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].email + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].phone + '</td>' +
                        '<td valign="center" align="center" width="120">' + list[i].address + '</td>' +
                        '<td valign="center" align="center" width="30"><a href="" onclick="usersEdit(' +
                        '\'' + list[i].id + '\',\'' + list[i].username + '\',\'' + list[i].password + '\',\'' + list[i].realName + '\',\'' + list[i].email + '\',\'' + list[i].phone + '\',\'' + list[i].address + '\');return false;">编辑</a>' +
                        '           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="" onclick="usersDelete(' + list[i].id + '); return false;">删除</a> </td></tr>'
                }
                $('.user-data').append(content);
            } else {
                $('.user-data').empty();
            }
        }
    });
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


    let addUser = {
        username: loginName,
        password: loginPwd,
        realName: trueName,
        email: email,
        phone: phone,
        address: address
    }

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

    addUsers(addUser);

}

function addUsers(addUser) {
    $.ajax({
        type: "POST",
        url: "user/api/insert",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(addUser),
        // data: serializeFormData($('.form-sign')),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("消息", result.message);
            }
            $.MsgBox.Alert("消息", "注册成功");
            returnUserList();
        }
    });
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
    getUsersList("", 1, everyPageDataCount, true, "user/api/pageListByEntity");
}

function users_edit(id) {
    $("#USERS_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#USER_EDIT_DIV_ID").attr("style", "display:block;");//隐藏div
}

function users_edit(id, username, password, realName, email, phone, address) {
    $("#USERS_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#USER_EDIT_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#editId").val(id);
    $("#editLoginName").val(username);
    $("#editLoginPwd").val(password);
    $("#editTrueName").val(realName);
    $("#editEmail").val(email);
    $("#editPhone").val(phone);
    $("#editAddress").val(address);
}


function returnordersList() {
    $("#MEAL_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#orderInfoDivId").attr("style", "display:none;");//隐藏div
}

function edtiUserCheck() {
    let id = $("#editId").val();
    let loginName = $("#editLoginName").val().trim();
    let loginPwd = $("#editLoginPwd").val().trim();
    let trueName = $("#editTrueName").val().trim();
    let email = $("#editEmail").val().trim();
    let phone = $("#editPhone").val().trim();
    let address = $("#editAddress").val().trim();

    let updateUser = {
        id: id,
        username: loginName,
        password: loginPwd,
        realName: trueName,
        email: email,
        phone: phone,
        address: address
    }
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

    let str = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!str.test(email)) {
        $("#tishi1").html("电子邮箱格式不正确");
        return;
    }

    if (phone.trim() == "") {
        $("#tishi1").html("手机号码不能为空");
        return;
    }

    str = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
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

    update(updateUser);
}

function update(userInfo) {
    $.ajax({
        type: "PUT",
        url: "user/api/update",
        async: true,
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(userInfo),
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, text) {
            $.MsgBox.Alert("消息", "出错了，请于管理员联系");
        },
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                console.log(error)
                // alert(JSON.stringify(error));
                $.MsgBox.Alert("消息", "出错了，请于管理员联系");
                return;
            }
            if (data) {
                $.MsgBox.Alert("消息", "修改成功");
                $("#POST_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
                $("#POST_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
                let searchNameVal = $("#SEARCH_USER_NAME").val().trim();
                getUsersList(searchNameVal, 0, everyPageDataCount, true, "user/api/pageListByEntity");
            } else {
                $.MsgBox.Alert("消息", "修改失败");
            }
        }
    });
}

function users_delete(id) {
    deleteUser(id)
}

function deleteUser(id) {
    // 发送请求
    $.ajax({
        url: "user/api/delete" + id, // url where to submit the request
        type: "DELETE", // type of action POST || GET
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                console.log(error)
                alert(JSON.stringify(error));
                return;
            }
            console.log(data);

            $.MsgBox.Alert("消息", "删除成功");
            let searchNameVal = $("#SEARCH_USER_NAME").val().trim();
            getUsersList(searchNameVal, usersPageIndex, everyPageDataCount, true, "user/api/pageListByEntity");
        }
    });
    // window.location = "${ctx }/user/removeUser?ids=" + ids.get();
}

function GOTO_USERS_NEXT_PAGE() {
    let searchNameVal = $("#SEARCH_USER_NAME").val().trim();
    getUsersList(searchNameVal, 0, everyPageDataCount, true, "user/api/pageListByEntity");
}

function GOTO_USERS_TAIL_PAGE() {
    let searchNameVal = $("#SEARCH_USER_NAME").val().trim();
    getUsersList(searchNameVal, postAllPage, everyPageDataCount, true, "user/api/pageListByEntity");

}

function GOTO_USERS_PAGE() {
    let jumpVal = $("#JUMP_INPUT_ID").val().trim();
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
    let searchNameVal = $("#SEARCH_USER_NAME").val().trim();
    getUsersList(searchNameVal, jumpVal, everyPageDataCount, true, "user/api/pageListByEntity");

}


function GOTO_USERS_HOME_PAGE() {
    let searchNameVal = $("#SEARCH_USER_NAME").val().trim();
    getUsersList(searchNameVal, 0, everyPageDataCount, true, "user/api/pageListByEntity");
}

function GOTO_USERS_PREVIOUS_PAGE() {
    let searchNameVal = $("#SEARCH_USER_NAME").val().trim();
    usersPageIndex = usersPageIndex - 1;
    getUsersList(searchNameVal, usersPageIndex, everyPageDataCount, true, "user/api/pageListByEntity");
}

function searchByUserName() {
    let searchNameVal = $("#SEARCH_USER_NAME").val().trim();
    getUsersList(searchNameVal, 0, everyPageDataCount, true, "user/api/pageListByEntity");
}






