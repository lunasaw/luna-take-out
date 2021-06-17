package com.luna.meal.controller;

import com.luna.meal.entity.OrderMeal;
import com.luna.meal.service.OrderMealService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:21:06
 */
@RestController
@RequestMapping("/orderMeal/api")
public class OrderMealController {

    @Autowired
    private OrderMealService orderMealService;

    @GetMapping("/get/{id}")
    public ResultDTO<OrderMeal> getById(@PathVariable(value = "id") Long id) {
        OrderMeal orderMeal = orderMealService.getById(id);
        return ResultDTOUtils.success(orderMeal);
    }

    @GetMapping("/get")
    public ResultDTO<OrderMeal> getByEntity(OrderMeal orderMeal) {
        return ResultDTOUtils.success(orderMealService.getByEntity(orderMeal));
    }

    @GetMapping("/list")
    public ResultDTO<List<OrderMeal>> list(OrderMeal orderMeal) {
        List<OrderMeal> orderMealList = orderMealService.listByEntity(orderMeal);
        return ResultDTOUtils.success(orderMealList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<OrderMeal>> listPageByEntity(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size, OrderMeal orderMeal) {
        PageInfo<OrderMeal> pageInfo = orderMealService.listPageByEntity(page, size, orderMeal);
        return ResultDTOUtils.success(pageInfo);
    }


    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<OrderMeal>> listPage(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        PageInfo<OrderMeal> pageInfo = orderMealService.listPage(page, size);
        return ResultDTOUtils.success(pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<OrderMeal> insert(@RequestBody OrderMeal orderMeal) {
        orderMealService.insert(orderMeal);
        return ResultDTOUtils.success(orderMeal);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<OrderMeal>> insert(@RequestBody List<OrderMeal> list) {
        orderMealService.insertBatch(list);
        return ResultDTOUtils.success(list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody OrderMeal orderMeal) {
        return ResultDTOUtils.success(orderMealService.update(orderMeal) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<OrderMeal> list) {
        return ResultDTOUtils.success(orderMealService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return ResultDTOUtils.success(orderMealService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody OrderMeal orderMeal) {
        return ResultDTOUtils.success(orderMealService.deleteByEntity(orderMeal) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = orderMealService.deleteByIds(ids);
        }
        return ResultDTOUtils.success(result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return ResultDTOUtils.success(orderMealService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(OrderMeal orderMeal) {
        return ResultDTOUtils.success(orderMealService.countByEntity(orderMeal));
    }
}
