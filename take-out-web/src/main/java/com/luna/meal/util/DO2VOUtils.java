package com.luna.meal.util;

import com.luna.common.date.DateUtil;
import com.luna.meal.constant.OrderState;
import com.luna.meal.entity.*;
import com.luna.meal.vo.*;

import java.math.BigDecimal;

/**
 * @author luna
 * 2021/6/18
 */
public class DO2VOUtils {

    public static MealVO MealDO2MealVO(Meal meal, String seriesName) {
        if (meal == null) {
            return null;
        }
        MealVO mealVO = new MealVO();
        mealVO.setId(meal.getId());
        mealVO.setSeriesName(seriesName);
        mealVO.setMealName(meal.getMealName());
        mealVO.setSummarize(meal.getSummarize());
        mealVO.setDescription(meal.getDescription());
        mealVO.setMealPrice(meal.getMealPrice());
        mealVO.setImage(meal.getImage());
        mealVO.setCreateTime(DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, meal.getCreateTime()));
        mealVO.setModifiedTime(DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, meal.getModifiedTime()));
        mealVO.setVersion(meal.getVersion());
        return mealVO;
    }

    public static OrderVO orderDO2OrderVO(Order order) {
        if (order == null) {
            return null;
        }
        OrderVO orderVO = new OrderVO();
        orderVO.setId(order.getId());
        orderVO.setUserId(order.getUserId());
        orderVO.setOrderTime(order.getOrderTime() != null
            ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, order.getOrderTime()) : "");
        orderVO.setOrderState(OrderState.getState(order.getOrderState()));
        orderVO.setOrderPrice(order.getOrderPrice().toString());
        orderVO.setCreateTime(DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, order.getCreateTime()));
        orderVO.setModifiedTime(DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, order.getModifiedTime()));
        orderVO.setVersion(order.getVersion());
        return orderVO;
    }

    public static UserDetailVO userDO2UserDetailVO(User user) {
        if (user == null) {
            return null;
        }

        UserDetailVO userDetailVO = new UserDetailVO();
        userDetailVO.setId(user.getId());
        userDetailVO.setUsername(user.getUsername());
        userDetailVO.setPassword(user.getPassword());
        userDetailVO.setRealName(user.getRealName());
        userDetailVO.setEmail(user.getEmail());
        userDetailVO.setPhone(user.getPhone());
        userDetailVO.setAddress(user.getAddress());
        userDetailVO.setFacePath(user.getFacePath());
        userDetailVO.setFaceData(user.getFaceData());
        userDetailVO.setCreateTime(user.getCreateTime());
        userDetailVO.setModifiedTime(user.getModifiedTime());
        userDetailVO.setVersion(user.getVersion());
        userDetailVO.setAdmin(user.getAdmin());
        return userDetailVO;
    }

    public static OrderMealVO OrderMealDO2OrderMealVO(OrderMeal orderMeal, String mealName, String seriesName,
        Double price) {
        if (orderMeal == null) {
            return null;
        }

        OrderMealVO orderMealVO = new OrderMealVO();
        orderMealVO.setId(orderMeal.getId());
        orderMealVO.setOrderId(orderMeal.getOrderId());
        orderMealVO.setMealName(mealName);
        orderMealVO.setSeriesName(seriesName);
        orderMealVO.setMealPrice(orderMeal.getMealPrice().toString());
        orderMealVO.setPrice(price.toString());
        orderMealVO.setMealCount(orderMeal.getMealCount());
        orderMealVO.setCreateTime(DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, orderMeal.getCreateTime()));
        orderMealVO.setModifiedTime(DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, orderMeal.getModifiedTime()));
        orderMealVO.setVersion(orderMeal.getVersion());
        return orderMealVO;

    }

    public static CartMealVO cartDO2CartMealVO(Cart cart, String mealName, String seriesName, Double price) {
        if (cart == null) {
            return null;
        }

        CartMealVO cartMealVO = new CartMealVO();
        cartMealVO.setId(cart.getId());
        cartMealVO.setMealName(mealName);
        cartMealVO.setSeriesName(seriesName);
        BigDecimal priceBigDecimal = new BigDecimal(Double.toString(price));
        cartMealVO.setPrice(priceBigDecimal.toString());
        BigDecimal decimal = priceBigDecimal.multiply(BigDecimal.valueOf(cart.getCount()));
        decimal.setScale(2, BigDecimal.ROUND_DOWN);
        cartMealVO.setMealPrice(decimal.toString());
        cartMealVO.setMealCount(cart.getCount());
        return cartMealVO;
    }
}
