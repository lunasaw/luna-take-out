package com.luna.meal.service.impl;

import com.luna.common.dto.constant.ResultCode;
import com.luna.meal.constant.UserConstant;
import com.luna.meal.entity.Meal;
import com.luna.meal.entity.OrderMeal;
import com.luna.meal.entity.User;
import com.luna.meal.exception.UserException;
import com.luna.meal.mapper.*;
import com.luna.meal.service.OrderService;
import com.luna.meal.entity.Order;
import com.luna.meal.tools.UserTools;
import com.luna.meal.util.DO2VOUtils;
import com.luna.meal.vo.OrderDetailVO;
import com.luna.meal.vo.OrderMealVO;
import com.luna.meal.vo.OrderVO;
import com.luna.meal.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:21:22
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper      orderMapper;

    @Autowired
    private OrderMealMapper  orderMealMapper;

    @Autowired
    private UserMapper       userMapper;

    @Autowired
    private MealSeriesMapper mealSeriesMapper;

    @Autowired
    private MealMapper       mealMapper;

    @Autowired
    private UserTools        userTools;

    @Override
    public OrderDetailVO getById(Long id) {
        Order byId = orderMapper.getById(id);
        User user = userMapper.getById(byId.getUserId());
        UserDetailVO userDetailVO = DO2VOUtils.userDO2UserDetailVO(user);
        List<OrderMeal> orderMeals = orderMealMapper.listByEntity(new OrderMeal(byId.getId()));
        List<OrderMealVO> collect = orderMeals.stream().map(orderMeal -> {
            Meal meal = mealMapper.getById(orderMeal.getMealId());
            String mealName = meal.getMealName();
            String seriesName = mealSeriesMapper.getById(meal.getSeriesId()).getSeriesName();
            return DO2VOUtils.OrderMealDO2OrderMealVO(orderMeal, mealName, seriesName, meal.getMealPrice());
        }).collect(Collectors.toList());
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setOrderMealVOList(collect);
        orderDetailVO.setUserDetailVO(userDetailVO);
        return orderDetailVO;
    }

    @Override
    public Order getByEntity(Order order) {
        return orderMapper.getByEntity(order);
    }

    @Override
    public List<Order> listByEntity(Order order) {
        return orderMapper.listByEntity(order);
    }

    @Override
    public PageInfo<OrderVO> listPageByEntity(int page, int pageSize, Order order) {
        PageHelper.startPage(page, pageSize);
        List<Order> list = orderMapper.listByEntity(order);
        PageInfo<OrderVO> pageInfo = new PageInfo(list);
        List<OrderVO> collect = list.stream().map(DO2VOUtils::orderDO2OrderVO).collect(Collectors.toList());
        pageInfo.setList(collect);
        return pageInfo;
    }

    @Override
    public PageInfo<Order> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Order> list = orderMapper.listByEntity(new Order());
        return new PageInfo<Order>(list);
    }

    @Override
    public List<Order> listByIds(List<Long> ids) {
        return orderMapper.listByIds(ids);
    }

    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int insertBatch(List<Order> list) {
        return orderMapper.insertBatch(list);
    }

    @Override
    public int update(String oneSessionKey, Order order) {
        User doUser = userTools.getUser(oneSessionKey);
        if (!Objects.equals(UserConstant.ADMIN, doUser.getAdmin())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "鉴权异常");
        }

        Order byId = orderMapper.getById(order.getId());
        byId.setOrderState(order.getOrderState());
        return orderMapper.update(byId);
    }

    @Override
    public int updateBatch(List<Order> list) {
        return orderMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return orderMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(Order order) {
        return orderMapper.deleteByEntity(order);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return orderMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return orderMapper.countAll();
    }

    @Override
    public int countByEntity(Order order) {
        return orderMapper.countByEntity(order);
    }

}
