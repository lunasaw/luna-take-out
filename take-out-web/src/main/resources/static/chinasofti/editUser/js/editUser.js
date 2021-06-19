$(document).ready(function () {
    users_edit()

});

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}

function users_edit() {

    $.ajax({
        type: "GET",
        url: "user/api/sysUser",
        contentType: 'application/json;charset=UTF-8',
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
            if (result.success) {
                $("#editId").attr("value", data.id);
                $("#editTrueName").attr("value", data.realName);
                $("#editEmail").val(data.email);
                $("#editPhone").attr("value", data.phone);
                $("#editAddress").attr("value", data.address);
            }
        }
    })

}


function editUserCheck() {

    let id = $("#editId").val().trim();
    let trueName = $("#editTrueName").val().trim();
    let email = $("#editEmail").val().trim();
    let phone = $("#editPhone").val().trim();
    let address = $("#editAddress").val().trim();

    let editUser = {
        id: id,
        realName: trueName,
        email: email,
        phone: phone,
        address: address
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

    updateUser(editUser);
}

function updateUser(editUser) {
    $.ajax({
        type: "PUT",
        url: "user/api/update",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(editUser),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("消息", result.message);
            }
            if (data) {
                $.MsgBox.Alert("消息", "更新成功");
            }
        }
    })
}





