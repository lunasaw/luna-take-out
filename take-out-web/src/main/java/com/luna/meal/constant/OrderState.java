package com.luna.meal.constant;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author luna
 * 2021/6/19
 */
public enum OrderState {

    /** 确认收货 */
    CONFIRM_RECEIPT("已确认收货", 3),

    /** 作废 */
    CANCELLATION("已取消", 2),

    /** 已处理 */
    DISPOSE("已开始送餐", 1),

    /** 未处理 */
    NORMAL("未开始送餐", 0);

    private String  orderState;

    private Integer orderType;

    OrderState(String orderState, Integer orderType) {
        this.orderState = orderState;
        this.orderType = orderType;
    }

    public static String getState(Integer orderType) {
        return Arrays.stream(OrderState.values()).filter(type -> Objects.equals(type.getOrderType(), orderType))
            .findFirst().get()
            .getOrderState();
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
