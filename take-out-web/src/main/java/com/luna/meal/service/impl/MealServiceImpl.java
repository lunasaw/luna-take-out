package com.luna.meal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luna.common.dto.constant.ResultCode;
import com.luna.meal.constant.UserConstant;
import com.luna.meal.entity.User;
import com.luna.meal.exception.UserException;
import com.luna.meal.mapper.MealMapper;
import com.luna.meal.mapper.MealSeriesMapper;
import com.luna.meal.service.MealService;
import com.luna.meal.entity.Meal;
import com.luna.meal.tools.UserTools;
import com.luna.meal.util.DO2VOUtils;
import com.luna.meal.vo.MealVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: luna
 * @CreateTime: 2021-06-18 19:10:25
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealMapper       mealMapper;

    @Autowired
    private MealSeriesMapper mealSeriesMapper;

    @Autowired
    private UserTools        userTools;

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
    public PageInfo<MealVO> listPageByEntity(int page, int pageSize, Meal meal) {
        PageHelper.startPage(page, pageSize);
        List<Meal> list = mealMapper.listByEntity(meal);
        PageInfo<MealVO> pageInfo = new PageInfo(list);
        List<MealVO> collect = list.stream().map(tempMeal -> {
            String seriesName = mealSeriesMapper.getById(tempMeal.getSeriesId()).getSeriesName();
            return DO2VOUtils.MealDO2MealVO(tempMeal, seriesName);
        }).collect(Collectors.toList());
        pageInfo.setList(collect);
        return pageInfo;
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
    public int update(String oneSessionKey, Meal meal) {
        User doUser = userTools.getUser(oneSessionKey);
        if (!Objects.equals(UserConstant.ADMIN, doUser.getAdmin())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "鉴权异常");
        }
        Meal byId = mealMapper.getById(meal.getId());
        byId.setSeriesId(meal.getSeriesId());
        byId.setMealName(meal.getMealName());
        byId.setSummarize(meal.getSummarize());
        byId.setDescription(meal.getDescription());
        byId.setMealPrice(meal.getMealPrice());
        return mealMapper.update(byId);
    }

    @Override
    public int updateBatch(List<Meal> list) {
        return mealMapper.updateBatch(list);
    }

    @Override
    public int deleteById(String oneSessionKey, Long id) {
        User doUser = userTools.getUser(oneSessionKey);
        if (!Objects.equals(UserConstant.ADMIN, doUser.getAdmin())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "鉴权异常");
        }

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
