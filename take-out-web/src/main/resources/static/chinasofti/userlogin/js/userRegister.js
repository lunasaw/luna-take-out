function addUserCheck() {
    let loginName = $("#addLoginName").val().trim();
    let loginPwd = $("#addLoginPwd").val().trim();
    let trueName = $("#addTrueName").val().trim();
    let email = $("#addEmail").val().trim();
    let phone = $("#addPhone").val().trim();
    let address = $("#addAddress").val().trim();

    let registerUser = {
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

    let str = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!str.test(email)) {
        $("#tishi").html("电子邮箱格式不正确");
        return;
    }

    if (phone.trim() == "") {
        $("#tishi").html("手机号码不能为空");
        return;
    }

    str = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
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
    register(registerUser);
}

function register(user) {
    $.ajax({
        type: "POST",
        url: "user/api/register",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(user),
        // data: serializeFormData($('.form-sign')),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("注册失败", result.message);
            }
            if (data) {
                $.MsgBox.Alert("消息", "注册成功请关闭本窗口进行登录！");
            }else {
                $.MsgBox.Alert("消息", "注册失败，请重新注册！");
            }
        }
    });
}

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}