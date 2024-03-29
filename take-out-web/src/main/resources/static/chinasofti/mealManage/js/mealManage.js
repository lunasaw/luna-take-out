var everyPageDataCount = 7;
var mealPageIndex = 1;
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
                        '<td valign="center" align="center" width="30">' + list[i].summarize + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].mealPrice + '</td>' +
                        '<td valign="center" align="center" width="30"><a href="" onclick="meal_edit(\'' + list[i].id + '\'); return false;">编辑</a> </td>' +
                        '<td valign="center" align="center" width="30"><a href="" onclick="meal_edit_img(\'' + list[i].id + '\',\'' + list[i].image + '\'); return false;">上传</a> </td>' +
                        '<td valign="center" align="center" width="30"><a href="" onclick="DELETE_MEAL(\'' + list[i].id + '\'); return false;">删除</a> </td>' +
                        '</tr>'
                }
                $('#POST_LIST_TBODY_ID').append(content);
            } else {
                $('#POST_LIST_TBODY_ID').empty();
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
                $('#ADD_MEAL_SERIES_NAME').empty();
                $('#EDIT_MEAL_SERIES_NAME').empty();
                $('#SEARCH_SERIES_ID').empty();
                for (let i in list) {
                    content = content + '<option value="' + list[i].id + '">' + list[i].seriesName + '</option>'
                }
                $('#ADD_MEAL_SERIES_NAME').append(content);
                $('#EDIT_MEAL_SERIES_NAME').append(content);
                $('#SEARCH_SERIES_ID').append(content);
            } else {
                $('#ADD_MEAL_SERIES_NAME').empty();
                $('#EDIT_MEAL_SERIES_NAME').empty();
                $('#SEARCH_SERIES_ID').empty();
            }
        }
    });
}

function addMealCheck() {
    let mealName = $("#ADD_MEAL_NAME").val().trim();
    let mealSummarize = $("#ADD_MEAL_SUMMARIZE").val().trim();
    let mealDescription = $("#ADD_MEAL_DESCRIPTION").val().trim();
    let mealPrice = $("#ADD_MEAL_PRICE").val().trim();
    let mealSeriesId = $("#ADD_MEAL_SERIES_NAME").val();

    let meal = {
        mealName: mealName,
        summarize: mealSummarize,
        description: mealDescription,
        mealPrice: mealPrice,
        seriesId: mealSeriesId,
    }

    if (mealName == "") {
        $("#tishi").html("菜名不能为空");
        return;
    }
    if (mealName.length > 20) {
        $("#tishi").html("菜名最多20个字符");
        return;
    }

    if (mealSummarize == "") {
        $("#tishi").html("摘要不能为空");
        return;
    }
    if (mealSummarize.length > 250) {
        $("#tishi").html("摘要最多250个字符");
        return;
    }

    if (mealDescription == "") {
        $("#tishi").html("介绍不能为空");
        return;
    }
    if (mealDescription.length > 250) {
        $("#tishi").html("介绍最多250个字符");
        return;
    }
    if (mealPrice.trim() == "") {
        $("#tishi").html("价钱不能为空");
        return;
    }

    let str = /^(([1-9]\d{0,8})|0)(\.\d{2})?$/;
    if (!str.test(mealPrice)) {
        $("#tishi").html("整数(8位)或保留小数点后两位如：88.10");
        return;
    }

    addMeal(meal);

}

function addMeal(meal) {
    $.ajax({
        type: "POST",
        url: "meal/api/insert",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(meal),
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
            $.MsgBox.Alert("消息", "添加成功");
            returnMealList();
        }
    });
}


function meal_edit_img(mealId, image) {

    $("#MEAL_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEAL_IMG_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#mealIdHidd").attr("value", mealId);
    $("#finalImg").attr("src", image);
}

function meal_edit(mealId) {

    $("#MEAL_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEAL_EDIT_DIV_ID").attr("style", "display:block;");//隐藏div

    getMealDetail(mealId);
}

function updateMeal(meal) {
    $.ajax({
        type: "PUT",
        url: "meal/api/update",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(meal),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("消息", result.message);
            }
            $.MsgBox.Alert("消息", "更新成功");
            let searchNameVal = $("#SEARCH_MEAL_NAME").val().trim();
            let seriesId = $("#SEARCH_SERIES_ID").val();
            returnMealList();
            getMealList(searchNameVal, seriesId, mealPageIndex, everyPageDataCount, true, "meal/api/pageListByEntity");
        }
    })
}

function getMealDetail(mealId) {
    $.ajax({
        type: "GET",
        url: "meal/api/get/" + mealId,
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
                $("#EDIT_MEAL_HIDDE").attr("value", data.id);
                $("#EDIT_MEAL_NAME").attr("value", data.mealName);
                $("#EDIT_MEAL_SERIES_NAME").val(data.seriesId);
                $("#EDIT_MEAL_SUMMARIZE").attr("value", data.summarize);
                $("#EDIT_MEAL_DESCRIPTION").attr("value", data.description);
                $("#EDIT_MEAL_PRICE").attr("value", data.mealPrice);
            }
        }
    })
}

function editMealCheck() {

    let mealId = $("#EDIT_MEAL_HIDDE").val();
    let mealName = $("#EDIT_MEAL_NAME").val().trim();
    let mealSummarize = $("#EDIT_MEAL_SUMMARIZE").val().trim();
    let mealDescription = $("#EDIT_MEAL_DESCRIPTION").val().trim();
    let mealPrice = $("#EDIT_MEAL_PRICE").val().trim();
    let mealSeriesId = $("#EDIT_MEAL_SERIES_NAME").val().trim();


    let meal = {
        id: mealId,
        mealName: mealName,
        summarize: mealSummarize,
        description: mealDescription,
        mealPrice: mealPrice,
        seriesId: mealSeriesId,
    }

    if (mealName == "") {
        $("#tishi").html("菜名不能为空");
        return;
    }
    if (mealName.length > 20) {
        $("#tishi").html("菜名最多20个字符");
        return;
    }

    if (mealSummarize == "") {
        $("#tishi").html("摘要不能为空");
        return;
    }
    if (mealSummarize.length > 250) {
        $("#tishi").html("摘要最多250个字符");
        return;
    }

    if (mealDescription == "") {
        $("#tishi").html("介绍不能为空");
        return;
    }
    if (mealDescription.length > 250) {
        $("#tishi").html("介绍最多250个字符");
        return;
    }
    if (mealPrice.trim() == "") {
        $("#tishi").html("价钱不能为空");
        return;
    }

    var str = /^(([1-9]\d{0,8})|0)(\.\d{2})?$/;
    if (!str.test(mealPrice)) {
        $("#tishi").html("整数(8位)或保留小数点后两位如：88.10");
        return;
    }

    updateMeal(meal);


}

function DELETE_MEAL(mealId) {
    $.ajax({
        type: "DELETE",
        url: "meal/api/delete/" + mealId,
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
            $.MsgBox.Alert("消息", "删除成功");
            let searchNameVal = $("#SEARCH_MEAL_NAME").val().trim();
            let seriesId = $("#SEARCH_SERIES_ID").val();
            getMealList(searchNameVal, seriesId, mealPageIndex, everyPageDataCount, true, "meal/api/pageListByEntity");
        }
    })
}

function returnMealList() {
    $("#ADD_MEAL_NAME").val("");
    $("#ADD_MEAL_SUMMARIZE").val("");
    $("#ADD_MEAL_DESCRIPTION").val("");
    $("#ADD_MEAL_PRICE").val("");
    $("#ADD_MEALSERIES_NAME").val("");


    $("#EDI_MEAL_NAME").val("");
    $("#EDI_MEAL_SUMMARIZE").val("");
    $("#EDI_MEAL_DESCRIPTION").val("");
    $("#EDI_MEAL_PRICE").val("");
    $("#EDI_MEALSERIES_NAME").val("");

    $("#mealIdHidd").val("");
    $("#EDIT_MEAL_HIDDE").val("");

    $("#tishi1").html("");
    $("#tishi").html("");
    $("#MEAL_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#MEAL_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEAL_EDIT_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEAL_IMG_DIV_ID").attr("style", "display:none;");//隐藏div
}

function ADD_MEAL() {
    $("#MEAL_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEAL_ADD_DIV_ID").attr("style", "display:block;");//隐藏div
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


(window.onresize = function () {
    var win_height = $(window).height();
    var win_width = $(window).width();
    if (win_width <= 768) {
        $(".tailoring-content").css({
            "top": (win_height - $(".tailoring-content").outerHeight()) / 2,
            "left": 0
        });
    } else {
        $(".tailoring-content").css({
            "top": (win_height - $(".tailoring-content").outerHeight()) / 2,
            "left": (win_width - $(".tailoring-content").outerWidth()) / 2
        });
    }
})();

//弹出图片裁剪框
$("#replaceImg").on("click", function () {
    $(".tailoring-container").toggle();
});

//图像上传
function selectImg(file) {
    if (!file.files || !file.files[0]) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function (evt) {
        var replaceSrc = evt.target.result;
        //更换cropper的图片
        $('#tailoringImg').cropper('replace', replaceSrc, false);//默认false，适应高度，不失真
    }
    reader.readAsDataURL(file.files[0]);
}

//cropper图片裁剪
$('#tailoringImg').cropper({
    aspectRatio: 1 / 1,//默认比例
    preview: '.previewImg',//预览视图
    guides: false,  //裁剪框的虚线(九宫格)
    autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
    movable: false, //是否允许移动图片
    dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
    movable: true,  //是否允许移动剪裁框
    resizable: true,  //是否允许改变裁剪框的大小
    zoomable: false,  //是否允许缩放图片大小
    mouseWheelZoom: false,  //是否允许通过鼠标滚轮来缩放图片
    touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
    rotatable: true,  //是否允许旋转图片
    crop: function (e) {
        // 输出结果数据裁剪图像。
    }
});
//旋转
$(".cropper-rotate-btn").on("click", function () {
    $('#tailoringImg').cropper("rotate", 45);
});
//复位
$(".cropper-reset-btn").on("click", function () {
    $('#tailoringImg').cropper("reset");
});
//换向
var flagX = true;
$(".cropper-scaleX-btn").on("click", function () {
    if (flagX) {
        $('#tailoringImg').cropper("scaleX", -1);
        flagX = false;
    } else {
        $('#tailoringImg').cropper("scaleX", 1);
        flagX = true;
    }
    flagX != flagX;
});

//裁剪后的处理
$("#sureCut").on("click", function () {
    if ($("#tailoringImg").attr("src") == null) {
        file = "";
        return false;
    } else {
        var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
        var base64url = cas.toDataURL('image/jpg'); //转换为base64地址形式

        $("#finalImg").prop("src", base64url);//显示为图片的形式

        base64url = base64url.substring(22);

        file = base64url;

        //关闭裁剪框
        closeTailor();
    }
});

//关闭裁剪框
function closeTailor() {
    $(".tailoring-container").toggle();
}

function subphoto() {
    let mealId = $("#mealIdHidd").val().trim();
    if (file == "") {
        $.MsgBox.Alert("消息", "请选择头像！");
        return;
    }
    if (file.length > 2000000) {
        $.MsgBox.Alert("消息", "剪裁区域的图片过大，上传头像大小不能超过2M！现在大小约为：" + Math.floor((file.length / 1000000)) + "M");
        return;
    }

    uploadFile(mealId);
}

function uploadFile(mealId) {
    let formData = new FormData();
    let file = $('#chooseImg')[0].files[0];
    formData.append('file', file);
    $.ajax(
        {
            type: "POST",
            url: "img/api/upload/meal/" + mealId,
            data: formData,
            contentType: false, //禁止设置请求类型
            processData: false, //禁止jquery对data数据的处理,默认会处理，禁止的原因是,FormData已经帮我们做了处理
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
                if (result.success) {
                    $.MsgBox.Alert("消息", "上传成功");
                    let searchNameVal = $("#SEARCH_MEAL_NAME").val().trim();
                    let seriesId = $("#SEARCH_SERIES_ID").val();
                    returnMealList();
                    getMealList(searchNameVal, seriesId, mealPageIndex, everyPageDataCount, true, "meal/api/pageListByEntity");

                } else {
                    $.MsgBox.Alert("消息", "上传失败，请重试");
                }
            }
        }
    );
}


