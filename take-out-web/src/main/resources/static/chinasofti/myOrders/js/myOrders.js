var everyPageDataCount = 7;
var orderPageIndex = 0;
var orderAllPage = 0;
$(document).ready(function () {

    getOrderList(orderAllPage, everyPageDataCount, true, "order/api/myPageListByEntity");

});

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}

function getOrderList(pageStart, pageSize, SynOrAsyn, url) {
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
            orderAllPage = data.pages;
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
                    content = content + '<tr bgcolor="#FFFFFF"><td valign="center" align="center" width="30">' + list[i].id + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].orderTime + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].orderState + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].orderPrice + '</td>' +
                        '<td valign="center" align="center" width="30">' +
                        '<a href="" onclick="orders_info(\'' + list[i].id + '\'); return false;">详细信息</a></td>' +
                        '<td valign="center" align="center" width="30"><a href="" onclick="orders_handle(\'' + list[i].id + '\',\'3\'); return false;">收货</a></td>' +
                        '<tr bgcolor="#FFFFFF">';
                }
                $('#POST_LIST_TBODY_ID').append(content);
            } else {
                $('#POST_LIST_TBODY_ID').empty();
            }
        }
    });
}


function orders_info(oid) {


    $("#MEAL_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#orderInfoDivId").attr("style", "display:block;");//隐藏div

    getOrderDetail(oid);
}

function getOrderDetail(oid) {
    $.ajax({
        type: "GET",
        url: "order/api/get/" + oid,
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
                if (data != null) {
                    let orderMealVOListContent = '';
                    $('#orderInfoListDivId').empty();
                    let orderMealVOList = data.orderMealVOList;


                    for (let i in orderMealVOList) {
                        orderMealVOListContent = orderMealVOListContent + '<tr bgcolor="#FFFFFF">' +
                            '<td valign="center" align="center" width="30">' + orderMealVOList[i].seriesName + '</td>' +
                            '<td valign="center" align="center" width="30">' + orderMealVOList[i].mealName + '</td>' +
                            '<td valign="center" align="center" width="30">' + orderMealVOList[i].price + '</td>' +
                            '<td valign="center" align="center" width="30">' + orderMealVOList[i].mealCount + '</td>' +
                            '<td valign="center" align="center" width="30">' + orderMealVOList[i].mealPrice + '</td>' +
                            '</tr>';
                    }
                    $('#orderInfoListDivId').append(orderMealVOListContent);
                } else {
                    $('#orderInfoListDivId').empty();
                }
            }
        }
    })
}

function returnOrdersList() {
    $("#MEAL_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#orderInfoDivId").attr("style", "display:none;");//隐藏div
    $("#container").attr("style", "display:none;");//隐藏div

}

function orders_handle(oid, orderState) {
    let order = {
        id: oid,
        orderState: orderState
    }
    $.ajax({
        type: "PUT",
        url: "order/api/update",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(order),
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
            getOrderList(orderPageIndex, everyPageDataCount, true, "order/api/myPageListByEntity");
        }
    });
}


function GOTO_ORDER_NEXT_PAGE() {

    orderPageIndex = orderPageIndex + 1;

    getOrderList(orderPageIndex, everyPageDataCount, true, "order/api/myPageListByEntity");
}

function GOTO_ORDER_TAIL_PAGE() {


    getOrderList(orderAllPage, everyPageDataCount, true, "order/api/myPageListByEntity");
}

function GOTO_ORDER_PAGE() {
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
    if (jumpVal > orderAllPage) {
        $.MsgBox.Alert("消息", "页码超出上限");
        return;
    }


    getOrderList(jumpVal, everyPageDataCount, true, "order/api/myPageListByEntity");

}


function GOTO_ORDER_HOME_PAGE() {


    getOrderList(0, everyPageDataCount, true, "order/api/myPageListByEntity");
}

function GOTO_ORDER_PREVIOUS_PAGE() {

    orderPageIndex = orderPageIndex - 1;

    getOrderList(orderPageIndex, everyPageDataCount, true, "order/api/myPageListByEntity");
}

function searchByOrderOid() {


    getOrderList(0, everyPageDataCount, true, "order/api/myPageListByEntity");
}



