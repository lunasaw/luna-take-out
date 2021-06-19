function adminSubPassword() {
    var newPassword = $("#newPassword").val();
    var newPasswordCon = $("#newPasswordCon").val()
    var oldPassword = $("#oldPassword").val()


    if (typeof (newPassword) == 'undefined' || newPassword.trim() == "") {
        $("#tishi").html("新密码不能为空");
        return;
    }

    if (typeof (newPasswordCon) == 'undefined' || newPasswordCon.trim() == "") {
        $("#tishi").html("确认密码不能为空");
        return;
    }
    if (newPassword.trim() != newPasswordCon.trim()) {
        $("#tishi").html("新密码与确认密码必须保持一致");
        return;
    }

    if (typeof (oldPassword) == 'undefined' || oldPassword.trim() == "") {
        $("#tishi").html("当前密码不能为空");
        return;
    }

    let password = {
        newPassword: newPassword,
        oldPassword: oldPassword
    }
    editPassword("adminlogin.html", password);
}

function userSubPassword() {

    let newPassword = $("#newPassword").val();
    let newPasswordCon = $("#newPasswordCon").val()
    let oldPassword = $("#oldPassword").val()


    if (typeof (newPassword) == 'undefined' || newPassword.trim() == "") {
        $("#tishi").html("新密码不能为空");
        return;
    }

    if (typeof (newPasswordCon) == 'undefined' || newPasswordCon.trim() == "") {
        $("#tishi").html("确认密码不能为空");
        return;
    }
    if (newPassword.trim() != newPasswordCon.trim()) {
        $("#tishi").html("新密码与确认密码必须保持一致");
        return;
    }

    if (typeof (oldPassword) == 'undefined' || oldPassword.trim() == "") {
        $("#tishi").html("当前密码不能为空");
        return;
    }

    let password = {
        newPassword: newPassword,
        oldPassword: oldPassword
    }
    editPassword("userlogin.html", password);


}

function editPassword(page, editPassword) {
    $.ajax({
        type: "POST",
        url: "user/api/editPassword",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(editPassword),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
            }
            if (data) {
                $.MsgBox.Alert("消息", "密码修改成功，请重新登录！");
                window.parent.location.replace(page);
            } else {
                $.MsgBox.Alert("消息", "密码修改失败，请重新注册！");
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