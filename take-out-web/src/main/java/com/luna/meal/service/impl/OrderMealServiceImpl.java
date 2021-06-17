package com.luna.meal.service.impl;

import com.luna.meal.mapper.OrderMealMapper;
import com.luna.meal.service.OrderMealService;
import com.luna.meal.entity.OrderMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:21:06
 */
@Service
public class OrderMealServiceImpl implements OrderMealService {

    @Autowired
    private OrderMealMapper orderMealMapper;

    @Override
    public OrderMeal getById(Long id) {
        return orderMealMapper.getById(id);
    }

    @Override
    public OrderMeal getByEntity(OrderMeal orderMeal) {
        return orderMealMapper.getByEntity(orderMeal);
    }

    @Override
    public List<OrderMeal> listByEntity(OrderMeal orderMeal) {
        return orderMealMapper.listByEntity(orderMeal);
    }

    @Override
    public PageInfo<OrderMeal> listPageByEntity(int page, int pageSize, OrderMeal orderMeal) {
        PageHelper.startPage(page, pageSize);
        List<OrderMeal> list = orderMealMapper.listByEntity(orderMeal);
        return new PageInfo<OrderMeal>(list);
    }

    @Override
    public PageInfo<OrderMeal> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OrderMeal> list = orderMealMapper.listByEntity(new OrderMeal());
        return new PageInfo<OrderMeal>(list);
    }

    @Override
    public List<OrderMeal> listByIds(List<Long> ids) {
        return orderMealMapper.listByIds(ids);
    }

    @Override
    public int insert(OrderMeal orderMeal) {
        return orderMealMapper.insert(orderMeal);
    }

    @Override
    public int insertBatch(List<OrderMeal> list) {
        return orderMealMapper.insertBatch(list);
    }

    @Override
    public int update(OrderMeal orderMeal) {
        return orderMealMapper.update(orderMeal);
    }

    @Override
    public int updateBatch(List<OrderMeal> list) {
        return orderMealMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return orderMealMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(OrderMeal orderMeal) {
        return orderMealMapper.deleteByEntity(orderMeal);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return orderMealMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return orderMealMapper.countAll();
    }

    @Override
    public int countByEntity(OrderMeal orderMeal) {
        return orderMealMapper.countByEntity(orderMeal);
    }

}

