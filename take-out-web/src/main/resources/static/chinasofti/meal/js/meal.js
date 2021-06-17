var everyPageDataCount = 7;
var mealPageIndex = 0;
var mealAllPage = 0;
$(document).ready(function () {


});

function getMealList(mealName, seriesId, pageIndex, everyPageDataCount, SynOrAsyn, url) {

}


function addShoppingCart(mealId) {

    $.MsgBox.Alert("消息", "已加入购物车");

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

function searchByMealName() {

}




