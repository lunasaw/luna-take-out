package com.luna.meal.service.impl;

import com.luna.meal.mapper.MealSeriesMapper;
import com.luna.meal.service.MealSeriesService;
import com.luna.meal.entity.MealSeries;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:21:36
 */
@Service
public class MealSeriesServiceImpl implements MealSeriesService {

    @Autowired
    private MealSeriesMapper mealSeriesMapper;

    @Override
    public MealSeries getById(Long id) {
        return mealSeriesMapper.getById(id);
    }

    @Override
    public MealSeries getByEntity(MealSeries mealSeries) {
        return mealSeriesMapper.getByEntity(mealSeries);
    }

    @Override
    public List<MealSeries> listByEntity(MealSeries mealSeries) {
        return mealSeriesMapper.listByEntity(mealSeries);
    }

    @Override
    public PageInfo<MealSeries> listPageByEntity(int page, int pageSize, MealSeries mealSeries) {
        PageHelper.startPage(page, pageSize);
        List<MealSeries> list = mealSeriesMapper.listByEntity(mealSeries);
        return new PageInfo<MealSeries>(list);
    }

    @Override
    public PageInfo<MealSeries> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<MealSeries> list = mealSeriesMapper.listByEntity(new MealSeries());
        return new PageInfo<MealSeries>(list);
    }

    @Override
    public List<MealSeries> listByIds(List<Long> ids) {
        return mealSeriesMapper.listByIds(ids);
    }

    @Override
    public int insert(MealSeries mealSeries) {
        return mealSeriesMapper.insert(mealSeries);
    }

    @Override
    public int insertBatch(List<MealSeries> list) {
        return mealSeriesMapper.insertBatch(list);
    }

    @Override
    public int update(MealSeries mealSeries) {
        return mealSeriesMapper.update(mealSeries);
    }

    @Override
    public int updateBatch(List<MealSeries> list) {
        return mealSeriesMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return mealSeriesMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(MealSeries mealSeries) {
        return mealSeriesMapper.deleteByEntity(mealSeries);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return mealSeriesMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return mealSeriesMapper.countAll();
    }

    @Override
    public int countByEntity(MealSeries mealSeries) {
        return mealSeriesMapper.countByEntity(mealSeries);
    }

}

