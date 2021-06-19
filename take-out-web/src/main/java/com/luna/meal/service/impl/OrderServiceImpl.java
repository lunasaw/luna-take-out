package com.luna.meal.service.impl;

import com.luna.common.dto.constant.ResultCode;
import com.luna.meal.constant.OrderState;
import com.luna.meal.constant.UserConstant;
import com.luna.meal.entity.*;
import com.luna.meal.exception.UserException;
import com.luna.meal.mapper.*;
import com.luna.meal.service.OrderService;
import com.luna.meal.tools.UserTools;
import com.luna.meal.util.DO2VOUtils;
import com.luna.meal.vo.OrderDetailVO;
import com.luna.meal.vo.OrderMealVO;
import com.luna.meal.vo.OrderVO;
import com.luna.meal.vo.UserDetailVO;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
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
    private CartMapper       cartMapper;

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
    public PageInfo<OrderVO> myListPageByEntity(String oneSessionKey, int page, int size, Order order) {
        User user = userTools.getUser(oneSessionKey);
        order.setUserId(user.getId());
        return listPageByEntity(page, size, order);
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
    @Transactional
    public OrderVO createOrder(String oneSessionKey) {
        User user = userTools.getUser(oneSessionKey);
        List<Cart> carts = cartMapper.listByEntity(new Cart(user.getId()));
        // 新建一个订单插入
        Order order = new Order();
        order.setUserId(user.getId());
        order.setOrderState(OrderState.NORMAL.getOrderType());
        order.setOrderTime(new Date());
        orderMapper.insert(order);
        final BigDecimal[] orderPrice = {new BigDecimal("0.0")};
        carts.forEach(cart -> {
            Long mealId = cart.getMealId();
            Meal meal = mealMapper.getById(mealId);
            // 新建订单详细插入
            OrderMeal orderMeal = new OrderMeal();
            orderMeal.setOrderId(order.getId());
            orderMeal.setMealId(mealId);
            orderMeal.setMealCount(cart.getCount());
            // 计算部分总价
            BigDecimal priceBigDecimal = new BigDecimal(Double.toString(meal.getMealPrice()));
            BigDecimal decimal = priceBigDecimal.multiply(BigDecimal.valueOf(cart.getCount()));
            decimal = decimal.setScale(2, BigDecimal.ROUND_DOWN);
            orderPrice[0] = orderPrice[0].add(decimal);
            orderMeal.setMealPrice(decimal.doubleValue());
            orderMealMapper.insert(orderMeal);
            // 删除购物车
            cartMapper.deleteById(cart.getId());
        });
        Order byId = orderMapper.getById(order.getId());
        byId.setOrderPrice(orderPrice[0].doubleValue());
        orderMapper.update(byId);
        return DO2VOUtils.orderDO2OrderVO(byId);
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
        Order byId = orderMapper.getById(order.getId());
        if (!Objects.equals(doUser.getId(), byId.getUserId())
            && !Objects.equals(UserConstant.ADMIN, doUser.getAdmin())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "鉴权异常");
        }

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
