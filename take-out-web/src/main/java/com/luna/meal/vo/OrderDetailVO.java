package com.luna.meal.vo;

import com.luna.meal.entity.Meal;
import com.luna.meal.entity.User;

import java.util.List;

/**
 * @author luna
 * 2021/6/19
 */
public class OrderDetailVO {

    private UserDetailVO      userDetailVO;

    private List<OrderMealVO> orderMealVOList;

    public UserDetailVO getUserDetailVO() {
        return userDetailVO;
    }

    public void setUserDetailVO(UserDetailVO userDetailVO) {
        this.userDetailVO = userDetailVO;
    }

    public List<OrderMealVO> getOrderMealVOList() {
        return orderMealVOList;
    }

    public void setOrderMealVOList(List<OrderMealVO> orderMealVOList) {
        this.orderMealVOList = orderMealVOList;
    }
}
