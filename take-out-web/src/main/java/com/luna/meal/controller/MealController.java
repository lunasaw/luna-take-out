package com.luna.meal.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.meal.entity.Meal;
import com.luna.meal.service.MealService;
import com.luna.meal.vo.MealVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-18 19:10:25
 */
@RestController
@RequestMapping("/meal/api")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("/get/{id}")
    public ResultDTO<Meal> getById(@PathVariable(value = "id") Long id) {
        Meal meal = mealService.getById(id);
        return ResultDTOUtils.success(meal);
    }

    @GetMapping("/get")
    public ResultDTO<Meal> getByEntity(Meal meal) {
        return ResultDTOUtils.success(mealService.getByEntity(meal));
    }

    @GetMapping("/list")
    public ResultDTO<List<Meal>> list(Meal meal) {
        List<Meal> mealList = mealService.listByEntity(meal);
        return ResultDTOUtils.success(mealList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<MealVO>> listPageByEntity(@PathVariable(value = "page") int page,
                                                      @PathVariable(value = "size") int size, Meal meal) {
        PageInfo<MealVO> pageInfo = mealService.listPageByEntity(page, size, meal);
        return ResultDTOUtils.success(pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<Meal>> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
        PageInfo<Meal> pageInfo = mealService.listPage(page, size);
        return ResultDTOUtils.success(pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<Meal> insert(@RequestBody Meal meal) {
        mealService.insert(meal);
        return ResultDTOUtils.success(meal);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<Meal>> insert(@RequestBody List<Meal> list) {
        mealService.insertBatch(list);
        return ResultDTOUtils.success(list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody Meal meal) {
        return ResultDTOUtils.success(mealService.update(meal) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<Meal> list) {
        return ResultDTOUtils.success(mealService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return ResultDTOUtils.success(mealService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody Meal meal) {
        return ResultDTOUtils.success(mealService.deleteByEntity(meal) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = mealService.deleteByIds(ids);
        }
        return ResultDTOUtils.success(result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return ResultDTOUtils.success(mealService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(Meal meal) {
        return ResultDTOUtils.success(mealService.countByEntity(meal));
    }
}
