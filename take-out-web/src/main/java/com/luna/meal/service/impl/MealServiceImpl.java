package com.luna.meal.service.impl;

import com.luna.meal.mapper.MealMapper;
import com.luna.meal.service.MealService;
import com.luna.meal.entity.Meal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:22:00
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealMapper mealMapper;

    @Override
    public Meal getById(Long id) {
        return mealMapper.getById(id);
    }

    @Override
    public Meal getByEntity(Meal meal) {
        return mealMapper.getByEntity(meal);
    }

    @Override
    public List<Meal> listByEntity(Meal meal) {
        return mealMapper.listByEntity(meal);
    }

    @Override
    public PageInfo<Meal> listPageByEntity(int page, int pageSize, Meal meal) {
        PageHelper.startPage(page, pageSize);
        List<Meal> list = mealMapper.listByEntity(meal);
        return new PageInfo<Meal>(list);
    }

    @Override
    public PageInfo<Meal> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Meal> list = mealMapper.listByEntity(new Meal());
        return new PageInfo<Meal>(list);
    }

    @Override
    public List<Meal> listByIds(List<Long> ids) {
        return mealMapper.listByIds(ids);
    }

    @Override
    public int insert(Meal meal) {
        return mealMapper.insert(meal);
    }

    @Override
    public int insertBatch(List<Meal> list) {
        return mealMapper.insertBatch(list);
    }

    @Override
    public int update(Meal meal) {
        return mealMapper.update(meal);
    }

    @Override
    public int updateBatch(List<Meal> list) {
        return mealMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return mealMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(Meal meal) {
        return mealMapper.deleteByEntity(meal);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return mealMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return mealMapper.countAll();
    }

    @Override
    public int countByEntity(Meal meal) {
        return mealMapper.countByEntity(meal);
    }

}

