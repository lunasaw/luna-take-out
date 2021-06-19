var everyPageDataCount = 7;
var cartPageIndex = 0;
var cartAllPage = 0;
$(document).ready(function () {

    getMealCartList(cartAllPage, everyPageDataCount, true, "cart/api/pageListByEntity");
});

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}

function getMealCartList(pageStart, pageSize, SynOrAsyn, url) {
    $.ajax({
        url: url + "/" + pageStart + "/" + pageSize, // url where to submit the request
        type: "GET", // type of action POST || GET
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
            cartAllPage = data.pages;
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
                $('#POST_LIST_TBODY_ID').empty();
                for (let i in list) {
                    content = content + '<tr bgcolor="#FFFFFF">' +
                        '<td valign="center" align="center" width="30">' + list[i].id + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].seriesName + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].mealName + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].price + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].mealCount + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].mealPrice + '</td>' +
                        '<td valign="center" align="center" width="30"><a href="" onclick="DELETE_CART(\'' + list[i].id + '\'); return false;">删除</a>' +
                        '</td>' +
                        '</tr>';
                }
                $('#POST_LIST_TBODY_ID').append(content);
            } else {
                $('#POST_LIST_TBODY_ID').empty();
            }
        }
    });
}


function DELETE_CART(cartId) {
    // 发送请求
    $.ajax({
        url: "cart/api/delete/" + cartId, // url where to submit the request
        type: "DELETE", // type of action POST || GET
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("消息", "出错了，请于管理员联系");
                return;
            }
            $.MsgBox.Alert("消息", "删除成功");
            getMealCartList(cartPageIndex, everyPageDataCount, true, "cart/api/pageListByEntity");
        }
    });
}


function ADD_CARTS() {
    // 发送请求
    $.ajax({
        url: "order/api/createOrder", // url where to submit the request
        type: "POST", // type of action POST || GET
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("消息", "出错了，请于管理员联系");
                return;
            }
            $.MsgBox.Alert("消息", "订单已生成");
            getMealCartList(cartPageIndex, everyPageDataCount, true, "cart/api/pageListByEntity");
        }
    });
}

function GOTO_CART_NEXT_PAGE() {
    cartPageIndex = cartPageIndex + 1;
    getMealCartList(cartPageIndex, everyPageDataCount, true, "cart/api/pageListByEntity");
}

function GOTO_CART_TAIL_PAGE() {
    getMealCartList(cartAllPage, everyPageDataCount, true, "cart/api/pageListByEntity");
}

function GOTO_CART_PAGE() {
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
    if (jumpVal > cartAllPage) {
        $.MsgBox.Alert("消息", "页码超出上限");
        return;
    }

    getMealCartList(jumpVal, everyPageDataCount, true, "cart/api/pageListByEntity");

}


function GOTO_CART_HOME_PAGE() {

    getMealCartList(0, everyPageDataCount, true, "cart/api/pageListByEntity");
}

function GOTO_CART_PREVIOUS_PAGE() {
    cartPageIndex = cartPageIndex - 1;
    getMealCartList(cartPageIndex, everyPageDataCount, true, "cart/api/pageListByEntity");
}

function searchByOrderOid() {
    getMealCartList(0, everyPageDataCount, true, "cart/api/pageListByEntity");
}




