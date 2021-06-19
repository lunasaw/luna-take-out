var everyPageDataCount = 7;
var mealPageIndex = 0;
var mealAllPage = 0;
$(document).ready(function () {

    getMealSeries();
    let mealName = $("#SEARCH_MEAL_NAME").val().trim();
    let seriesId = $("#SEARCH_SERIES_ID").val();
    getMealList(mealName, seriesId, mealPageIndex, everyPageDataCount, true, "meal/api/pageListByEntity");
});

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}

function getMealList(mealName, seriesId, pageStart, pageSize, SynOrAsyn, url) {
    let meal = {
        seriesId: seriesId,
        mealName: mealName,
    }
    console.log(meal);
    $.ajax({
        url: url + "/" + pageStart + "/" + pageSize, // url where to submit the request
        type: "GET", // type of action POST || GET
        data: meal,
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
            mealAllPage = data.pages;
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

            let selectConetnt = '<select id="my10"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option></select>'

            // 渲染页面
            let list = data.list;
            if (list.length > 0) {
                let content = '';
                $('.meal-list-data').empty();
                for (let i in list) {
                    content = content + '<div id="example3_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4" ><div class="form-inline col-sm-12"><div>' +
                        '<img src="' + list[i].image + '" style="whith:80px;height:80px"></div>' +
                        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div>' +
                        '<table>' +
                        '<tbody>' +
                        '<tr><td>菜名：' + list[i].mealName + '</td></tr>' +
                        '<tr><td>价格：' + list[i].mealPrice + '</td></tr>' +
                        '<tr><td>摘要：' + list[i].summarize + '</td></tr>' +
                        '<tr><td>数量：' + selectConetnt + '</td>' +
                        '<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-info"onclick="addShoppingCart(\'' + list[i].id + '\')">加入购物车</button></td></tr>' +
                        '</tbody></table>' +
                        '</div></div><div class="col-sm-12">简介：' + list[i].description + '</div></div>' +
                        '<hr>'
                }
                $('.meal-list-data').append(content);
            } else {
                $('.meal-list-data').empty();
            }
        }
    });
}

function getMealSeries() {
    $.ajax({
        type: "GET",
        url: "mealSeries/api/list",
        async: true,
        contentType: 'application/json;charset=UTF-8',
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

            // 渲染页面
            let list = data;
            if (list.length > 0) {
                let content = '<option value="">所有菜系</option>';
                $('#SEARCH_SERIES_ID').empty();
                for (let i in list) {
                    content = content + '<option value="' + list[i].id + '">' + list[i].seriesName + '</option>'
                }
                $('#SEARCH_SERIES_ID').append(content);
            } else {
                $('#SEARCH_SERIES_ID').empty();
            }
        }
    });
}

function addShoppingCart(mealId) {
    let mealSize = $("#my10").val().trim();

    let cart = {
        count: mealSize,
        mealId: mealId
    }
    $.ajax({
        type: "POST",
        url: "cart/api/insert",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(cart),
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
            $.MsgBox.Alert("消息", "已加入购物车");
        }
    });
}

function GOTO_MEAL_NEXT_PAGE() {
    let searchNameVal = $("#SEARCH_MEAL_NAME").val().trim();
    mealPageIndex = mealPageIndex + 1;
    let seriesId = $("#SEARCH_SERIES_ID").val();
    getMealList(searchNameVal, seriesId, mealPageIndex, everyPageDataCount, true, "meal/api/pageListByEntity");
}

function GOTO_MEAL_TAIL_PAGE() {
    let searchNameVal = $("#SEARCH_MEAL_NAME").val().trim();
    let seriesId = $("#SEARCH_SERIES_ID").val();
    getMealList(searchNameVal, seriesId, mealAllPage, everyPageDataCount, true, "meal/api/pageListByEntity");
}

function GOTO_MEAL_PAGE() {
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
    if (jumpVal > mealAllPage) {
        $.MsgBox.Alert("消息", "页码超出上限");
        return;
    }
    let searchNameVal = $("#SEARCH_MEAL_NAME").val().trim();
    let seriesId = $("#SEARCH_SERIES_ID").val();
    getMealList(searchNameVal, seriesId, jumpVal, everyPageDataCount, true, "meal/api/pageListByEntity");

}

function GOTO_MEAL_HOME_PAGE() {
    let searchNameVal = $("#SEARCH_MEAL_NAME").val().trim();
    let seriesId = $("#SEARCH_SERIES_ID").val();
    getMealList(searchNameVal, seriesId, 0, everyPageDataCount, true, "meal/api/pageListByEntity");
}

function GOTO_MEAL_PREVIOUS_PAGE() {
    let searchNameVal = $("#SEARCH_MEAL_NAME").val().trim();
    mealPageIndex = mealPageIndex - 1;
    let seriesId = $("#SEARCH_SERIES_ID").val();
    getMealList(searchNameVal, seriesId, mealPageIndex, everyPageDataCount, true, "meal/api/pageListByEntity");
}

function searchByMealName() {
    let searchNameVal = $("#SEARCH_MEAL_NAME").val().trim();
    let seriesId = $("#SEARCH_SERIES_ID").val();
    getMealList(searchNameVal, seriesId, 0, everyPageDataCount, true, "meal/api/pageListByEntity");
}



