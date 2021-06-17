var everyPageDataCount = 7;
var mealPageIndex = 0;
var mealAllPage = 0;
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


});

function getMealList(oid, orderState, pageIndex, everyPageDataCount, SynOrAsyn, url) {

}


function orders_info(oid) {


    $("#MEAL_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#orderInfoDivId").attr("style", "display:block;");//隐藏div

}


function returnordersList() {
    $("#MEAL_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#orderInfoDivId").attr("style", "display:none;");//隐藏div
}

function orders_handle(oid, orderState) {

}

function GOTO_MEAL_NEXT_PAGE() {


}

function GOTO_MEAL_TAIL_PAGE() {

}

function GOTO_MEAL_PAGE() {
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
    if (jumpVal > mealAllPage) {
        $.MsgBox.Alert("消息", "页码超出上限");
        return;
    }

}


function GOTO_MEAL_HOME_PAGE() {

}

function GOTO_MEAL_PREVIOUS_PAGE() {


}

function searchByOrderOid() {

}






