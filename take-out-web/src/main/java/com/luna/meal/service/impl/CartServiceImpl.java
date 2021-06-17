package com.luna.meal.service.impl;

import com.luna.meal.mapper.CartMapper;
import com.luna.meal.service.CartService;
import com.luna.meal.entity.Cart;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:22:25
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

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
    public PageInfo<Cart> listPageByEntity(int page, int pageSize, Cart cart) {
        PageHelper.startPage(page, pageSize);
        List<Cart> list = cartMapper.listByEntity(cart);
        return new PageInfo<Cart>(list);
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
    public int insert(Cart cart) {
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

