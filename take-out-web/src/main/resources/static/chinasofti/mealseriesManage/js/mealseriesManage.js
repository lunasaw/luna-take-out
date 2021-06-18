var everyPageDataCount = 7;
var mealSeriesPageIndex = 1;
var mealSeriesAllPage = 0;
$(document).ready(function () {

    let searchNameVal = $("#INPUT_MEAL_SERIES_NAME").val().trim();

    getMealSeriesList(searchNameVal, 1, everyPageDataCount, true, "mealSeries/api/pageListByEntity");


});

function getMealSeriesList(seriesName, pageStart, pageSize, SynOrAsyn, url) {
    let mealSeries = {
        seriesName: seriesName,
    }

    $.ajax({
        url: url + "/" + pageStart + "/" + pageSize, // url where to submit the request
        type: "GET", // type of action POST || GET
        data: mealSeries,
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
            mealSeriesAllPage = data.pages;
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
                $('.meal-series-data').empty();
                for (let i in list) {
                    content = content + '<tr bgcolor="#FFFFFF"><td valign="center" align="center" width="30">' + list[i].sort + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].seriesName + '</td>' +
                        '<td valign="center" align="center" width="30"><a href="" onclick="mealSeriesEdit(\'' + list[i].id + '\',\'' + list[i].sort + '\',\'' + list[i].seriesName + '\'); return false;">编辑</a> </td>' +
                        '<td valign="center" align="center" width="30"><a href="" onclick="deleteMealSeries(\'' + list[i].id + '\'); return false;">删除</a> </td></tr>';
                }
                $('.meal-series-data').append(content);
            } else {
                $('.meal-series-data').empty();
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

function addMealSeriesCheck() {
    let mealSeriesName = $("#ADD_MEAL_SERIES").val().trim();
    let sort = $("#EDIT_MEAL_SERIES_SORT").val().trim();
    let mealSeries = {
        seriesName: mealSeriesName,
        sort: sort
    }
    if (mealSeriesName == "") {
        $("#tishi").html("菜系名称不能为空");
        return;
    }
    if (mealSeriesName.length > 10) {
        $("#tishi").html("菜系名称最多10个字符");
        return;
    }

    addMealSeries(mealSeries);
}

function addMealSeries(mealSeries) {
    $.ajax({
        type: "POST",
        url: "mealSeries/api/insert",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(mealSeries),
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
            $.MsgBox.Alert("消息", "新增成功");
            returnMealSeriesList();
        }
    });
}


function mealSeriesEdit(seriesId, sort, seriesName) {

    $("#MEAL_SERIES_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEAL_SERIES_EDIT_DIV_ID").attr("style", "display:block;");//隐藏div

    $("#EDIT_MEAL_SERIES_HIDDE").val(seriesId);
    $("#EDIT_MEAL_SERIES_SORT").val(sort);
    $("#EDIT_MEAL_SERIES").val(seriesName);
}

function editMealSeriesCheck() {
    let seriesName = $("#EDIT_MEAL_SERIES").val()
    let seriesId = $("#EDIT_MEAL_SERIES_HIDDE").val();
    let sort = $("#EDIT_MEAL_SERIES_SORT").val();

    let mealSeries = {
        id: seriesId,
        sort: sort,
        seriesName: seriesName
    }

    if (seriesName == "") {
        $("#tishi1").html("菜系名称不能为空");
        return;
    }
    if (seriesName.length > 10) {
        $("#tishi1").html("菜系名称最多10个字符");
        return;
    }

    update(mealSeries);
}

function update(mealSeries) {
    $.ajax({
        type: "PUT",
        url: "mealSeries/api/update",
        async: true,
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(mealSeries),
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
                $("#MEAL_SERIES_EDIT_DIV_ID").attr("style", "display:none;");//隐藏div
                $("#MEAL_SERIES_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
                let searchNameVal = $("#INPUT_MEAL_SERIES_NAME").val().trim();
                getMealSeriesList(searchNameVal, 0, everyPageDataCount, true, "mealSeries/api/pageListByEntity");
            } else {
                $.MsgBox.Alert("消息", "修改失败");
            }
        }
    });
}


function deleteMealSeries(seriesId) {
    // 发送请求
    $.ajax({
        url: "mealSeries/api/delete/" + seriesId, // url where to submit the request
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
            if (data) {
                $.MsgBox.Alert("消息", "删除成功");
                let searchNameVal = $("#INPUT_MEAL_SERIES_NAME").val().trim();
                getMealSeriesList(searchNameVal, mealSeriesPageIndex, everyPageDataCount, true, "mealSeries/api/pageListByEntity");
            } else {
                $.MsgBox.Alert("消息", "请重试");
            }
        }
    });
    // window.location = "${ctx }/user/removeUser?ids=" + ids.get();
}

function returnMealSeriesList() {
    $("#ADD_MEAL_SERIES").val("");
    $("#EDIT_MEAL_SERIES").val("");
    $("#EDIT_MEAL_SERIES_HIDDE").val("");

    $("#tishi1").html("");
    $("#tishi").html("");
    $("#MEAL_SERIES_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#MEAL_SERIES_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEAL_SERIES_EDIT_DIV_ID").attr("style", "display:none;");//隐藏div
}

function ADD_MEAL_SERIES() {
    $("#MEAL_SERIES_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEAL_SERIES_ADD_DIV_ID").attr("style", "display:block;");//隐藏div
}


function GOTO_MEAL_SERIES_NEXT_PAGE() {

    let searchNameVal = $("#INPUT_MEAL_SERIES_NAME").val().trim();
    mealSeriesPageIndex = mealSeriesPageIndex + 1;
    getMealSeriesList(searchNameVal, mealSeriesPageIndex, everyPageDataCount, true, "mealSeries/api/pageListByEntity");

}

function GOTO_MEAL_SERIES_TAIL_PAGE() {
    let searchNameVal = $("#INPUT_MEAL_SERIES_NAME").val().trim();
    getMealSeriesList(searchNameVal, mealSeriesAllPage, everyPageDataCount, true, "mealSeries/api/pageListByEntity");

}

function GOTO_MEAL_SERIES_PAGE() {
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
    if (jumpVal > mealSeriesAllPage) {
        $.MsgBox.Alert("消息", "页码超出上限");
        return;
    }
    let searchNameVal = $("#INPUT_MEAL_SERIES_NAME").val().trim();
    getMealSeriesList(searchNameVal, jumpVal, everyPageDataCount, true, "mealSeries/api/pageListByEntity");

}


function GOTO_MEAL_SERIES_HOME_PAGE() {
    let searchNameVal = $("#INPUT_MEAL_SERIES_NAME").val().trim();
    getMealSeriesList(searchNameVal, 0, everyPageDataCount, true, "mealSeries/api/pageListByEntity");

}

function GOTO_MEAL_SERIES_PREVIOUS_PAGE() {
    let searchNameVal = $("#INPUT_MEAL_SERIES_NAME").val().trim();
    mealSeriesPageIndex = mealSeriesPageIndex - 1;
    getMealSeriesList(searchNameVal, mealSeriesPageIndex, everyPageDataCount, true, "mealSeries/api/pageListByEntity");


}

function searchBySeriesName() {
    let searchNameVal = $("#INPUT_MEAL_SERIES_NAME").val().trim();
    getMealSeriesList(searchNameVal, 0, everyPageDataCount, true, "mealSeries/api/pageListByEntity");

}








