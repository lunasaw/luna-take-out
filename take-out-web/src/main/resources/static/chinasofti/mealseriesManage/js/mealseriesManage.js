var everyPageDataCount = 7;
var mealseriesPageIndex = 0;
var mealseriesAllPage = 0;
$(document).ready(function () {
//	KindEditor.options.cssData = 'body {font-family:微软雅黑;}',
//	editor = KindEditor.create('textarea[id="POST_ADD_DES"]', {
//		allowUpload : true,
//	    uploadJson: '/postbar/postController/kindEditorImgInput',
//	    allowFileManager: false,
//	    width:'900px',
//	    height: '300px',
//	    items: [ 'fullscreen','|','undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste',
//	            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
//	            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
//	            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
//	            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
//	             'table', 'hr', 'emoticons', ]
//	});


    //var searchNameVal=$("#SEARCH_MEALSERIES_NAME_HIDDEN").val().trim();
    //getMealseriesList(searchNameVal,0,everyPageDataCount,true,"/order/mealseriesManageController/getMealseriesList");

});

function getMealseriesList(seriesName, pageIndex, everyPageDataCount, SynOrAsyn, url) {

}

function addMealseriesCheck() {
    var mealseriesName = $("#ADD_MEALSERIES").val().trim();

    if (mealseriesName == "") {
        $("#tishi").html("菜系名称不能为空");
        return;
    }
    if (mealseriesName.length > 10) {
        $("#tishi").html("菜系名称最多10个字符");
        return;
    }


    returnMealseriesList();


}


function mealseries_edit(seriesId) {


    $("#MEALSERIES_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEALSERIES_EDIT_DIV_ID").attr("style", "display:block;");//隐藏div


}

function editMealseriesCheck() {
    var seriesName = $("#EDIT_MEALSERIES").val()
    var seriesId = $("#EDIT_MEALSERIES_HIDDE").val();
    if (seriesName == "") {
        $("#tishi1").html("菜系名称不能为空");
        return;
    }
    if (seriesName.length > 10) {
        $("#tishi1").html("菜系名称最多10个字符");
        return;
    }

    returnMealseriesList();


}

function DELETE_MEALSERIES(seriesId) {


}

function returnMealseriesList() {
    $("#ADD_MEALSERIES").val("");
    $("#EDIT_MEALSERIES").val("");
    $("#EDIT_MEALSERIES_HIDDE").val("");

    $("#tishi1").html("");
    $("#tishi").html("");
    $("#MEALSERIES_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#MEALSERIES_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEALSERIES_EDIT_DIV_ID").attr("style", "display:none;");//隐藏div
}

function ADD_MEALSERIES() {
    $("#MEALSERIES_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#MEALSERIES_ADD_DIV_ID").attr("style", "display:block;");//隐藏div
}


function GOTO_MEALSERIES_NEXT_PAGE() {


}

function GOTO_MEALSERIES_TAIL_PAGE() {

}

function GOTO_MEALSERIES_PAGE() {
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
    if (jumpVal > mealseriesAllPage) {
        $.MsgBox.Alert("消息", "页码超出上限");
        return;
    }

}


function GOTO_MEALSERIES_HOME_PAGE() {

}

function GOTO_MEALSERIES_PREVIOUS_PAGE() {


}

function searchBySeriesName() {

}






