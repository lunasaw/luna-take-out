package com.luna.meal.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.meal.entity.MealSeries;
import com.luna.meal.service.MealSeriesService;
import com.luna.meal.util.CookieUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:21:36
 */
@RestController
@RequestMapping("/mealSeries/api")
public class MealSeriesController {

    @Autowired
    private MealSeriesService mealSeriesService;

    @GetMapping("/get/{id}")
    public ResultDTO<MealSeries> getById(@PathVariable(value = "id") Long id) {
        MealSeries mealSeries = mealSeriesService.getById(id);
        return ResultDTOUtils.success(mealSeries);
    }

    @GetMapping("/get")
    public ResultDTO<MealSeries> getByEntity(MealSeries mealSeries) {
        return ResultDTOUtils.success(mealSeriesService.getByEntity(mealSeries));
    }

    @GetMapping("/list")
    public ResultDTO<List<MealSeries>> list(MealSeries mealSeries) {
        List<MealSeries> mealSeriesList = mealSeriesService.listByEntity(mealSeries);
        return ResultDTOUtils.success(mealSeriesList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<MealSeries>> listPageByEntity(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, MealSeries mealSeries) {
        PageInfo<MealSeries> pageInfo = mealSeriesService.listPageByEntity(page, size, mealSeries);
        return ResultDTOUtils.success(pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<MealSeries>> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
        PageInfo<MealSeries> pageInfo = mealSeriesService.listPage(page, size);
        return ResultDTOUtils.success(pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<MealSeries> insert(HttpServletRequest httpServletRequest, @RequestBody MealSeries mealSeries) {
        mealSeriesService.insert(CookieUtils.getOneSessionKey(httpServletRequest), mealSeries);
        return ResultDTOUtils.success(mealSeries);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<MealSeries>> insert(@RequestBody List<MealSeries> list) {
        mealSeriesService.insertBatch(list);
        return ResultDTOUtils.success(list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(HttpServletRequest httpServletRequest, @RequestBody MealSeries mealSeries) {
        return ResultDTOUtils
            .success(mealSeriesService.update(CookieUtils.getOneSessionKey(httpServletRequest), mealSeries) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<MealSeries> list) {
        return ResultDTOUtils.success(mealSeriesService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(HttpServletRequest httpServletRequest, @PathVariable(value = "id") Long id) {
        return ResultDTOUtils
            .success(mealSeriesService.deleteById(CookieUtils.getOneSessionKey(httpServletRequest), id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody MealSeries mealSeries) {
        return ResultDTOUtils.success(mealSeriesService.deleteByEntity(mealSeries) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(HttpServletRequest httpServletRequest, @RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = mealSeriesService.deleteByIds(CookieUtils.getOneSessionKey(httpServletRequest), ids);
        }
        return ResultDTOUtils.success(result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return ResultDTOUtils.success(mealSeriesService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(MealSeries mealSeries) {
        return ResultDTOUtils.success(mealSeriesService.countByEntity(mealSeries));
    }
}
