package com.luna.meal.service.impl;

import com.luna.meal.entity.Meal;
import com.luna.meal.entity.User;
import com.luna.meal.mapper.CartMapper;
import com.luna.meal.mapper.MealMapper;
import com.luna.meal.mapper.MealSeriesMapper;
import com.luna.meal.service.CartService;
import com.luna.meal.entity.Cart;

import com.luna.meal.tools.UserTools;
import com.luna.meal.util.DO2VOUtils;
import com.luna.meal.vo.CartMealVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:22:25
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper       cartMapper;

    @Autowired
    private UserTools        userTools;

    @Autowired
    private MealMapper       mealMapper;

    @Autowired
    private MealSeriesMapper mealSeriesMapper;

    @Override
    public Cart getById(Long id) {
        return cartMapper.getById(id);
    }

    @Override
    public Cart getByEntity(Cart cart) {
        return cartMapper.getByEntity(cart);
    }

    @Override
    public List<Cart> listByEntity(Cart cart) {
        return cartMapper.listByEntity(cart);
    }

    @Override
    public PageInfo<CartMealVO> listPageByEntity(int page, int pageSize, Cart cart) {
        PageHelper.startPage(page, pageSize);
        List<Cart> list = cartMapper.listByEntity(cart);
        PageInfo<CartMealVO> pageInfo = new PageInfo(list);
        List<CartMealVO> collect = list.stream().map(cartTemp -> {
            Long mealId = cartTemp.getMealId();
            Meal byId = mealMapper.getById(mealId);
            String seriesName = mealSeriesMapper.getById(byId.getSeriesId()).getSeriesName();
            return DO2VOUtils.cartDO2CartMealVO(cartTemp, byId.getMealName(), seriesName, byId.getMealPrice());
        }).collect(Collectors.toList());
        pageInfo.setList(collect);
        return pageInfo;
    }

    @Override
    public PageInfo<Cart> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Cart> list = cartMapper.listByEntity(new Cart());
        return new PageInfo<Cart>(list);
    }

    @Override
    public List<Cart> listByIds(List<Long> ids) {
        return cartMapper.listByIds(ids);
    }

    @Override
    public int insert(String oneSessionKey, Cart cart) {
        User user = userTools.getUser(oneSessionKey);
        cart.setUserId(user.getId());
        return cartMapper.insert(cart);
    }

    @Override
    public int insertBatch(List<Cart> list) {
        return cartMapper.insertBatch(list);
    }

    @Override
    public int update(Cart cart) {
        return cartMapper.update(cart);
    }

    @Override
    public int updateBatch(List<Cart> list) {
        return cartMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return cartMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(Cart cart) {
        return cartMapper.deleteByEntity(cart);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return cartMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return cartMapper.countAll();
    }

    @Override
    public int countByEntity(Cart cart) {
        return cartMapper.countByEntity(cart);
    }

}
