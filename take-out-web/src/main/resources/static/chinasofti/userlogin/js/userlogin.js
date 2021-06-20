var uk = "_uk_", pk = "_pk_", rk = "_rk_";
$(function () {
    layui.use("layer");
    $("#loginName").focus();
    $("#loginName").val($.localStorage.get(uk));
    $("#loginPwd").val($.localStorage.get(pk));
    if ($.localStorage.get(rk)) {
        $("#rememberPwd").attr("checked", true);
    }
});

function goAdmin() {
    window.location.replace("adminLogin.html");
}

function subLogin() {
    let $msg = $("#message"), $usrname = $("#loginName"), $pwd = $("#loginPwd"), $rememberPwd = $("#rememberPwd");
    let usrname = $usrname.val() || "";

    if (usrname.trim().length == 0) {
        $("#tishi").html("用户名不能为空");
        return;
    }
    let pwd = $pwd.val() || "";
    if (pwd.trim().length == 0) {
        $("#tishi").html("密码不能为空!");
        return;
    }
    if ($rememberPwd.is(':checked')) {
        $.localStorage.set(uk, usrname);
        $.localStorage.set(pk, pwd);
        $.localStorage.set(rk, true);
        rememberPwd = "no";
    } else {
        $.localStorage.remove(uk);
        $.localStorage.remove(pk);
        $.localStorage.remove(rk);
        rememberPwd = "off";
    }

    let userReq = {
        username: usrname,
        password: pwd,
        rememberPwd: rememberPwd
    }
    login(userReq);
}

function login(userReq) {
    $.ajax({
        type: "POST",
        url: "user/api/login",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(userReq),
        // data: serializeFormData($('.form-sign')),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("登陆失败", result.message);
                return;
            }
            window.location.replace("main.html?menuUserName=" + data.username.trim() + "&admin=" + data.admin);
        }
    });
}

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}

if (window != top) {
    top.location.href = location.href;
}


