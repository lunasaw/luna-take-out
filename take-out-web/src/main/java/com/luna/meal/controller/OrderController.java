package com.luna.meal.controller;

import com.luna.meal.entity.Order;
import com.luna.meal.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:21:22
 */
@RestController
@RequestMapping("/order/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get/{id}")
    public ResultDTO<Order> getById(@PathVariable(value = "id") Long id) {
        Order order = orderService.getById(id);
        return ResultDTOUtils.success(order);
    }

    @GetMapping("/get")
    public ResultDTO<Order> getByEntity(Order order) {
        return ResultDTOUtils.success(orderService.getByEntity(order));
    }

    @GetMapping("/list")
    public ResultDTO<List<Order>> list(Order order) {
        List<Order> orderList = orderService.listByEntity(order);
        return ResultDTOUtils.success(orderList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<Order>> listPageByEntity(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size, Order order) {
        PageInfo<Order> pageInfo = orderService.listPageByEntity(page, size, order);
        return ResultDTOUtils.success(pageInfo);
    }


    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<Order>> listPage(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        PageInfo<Order> pageInfo = orderService.listPage(page, size);
        return ResultDTOUtils.success(pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<Order> insert(@RequestBody Order order) {
        orderService.insert(order);
        return ResultDTOUtils.success(order);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<Order>> insert(@RequestBody List<Order> list) {
        orderService.insertBatch(list);
        return ResultDTOUtils.success(list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody Order order) {
        return ResultDTOUtils.success(orderService.update(order) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<Order> list) {
        return ResultDTOUtils.success(orderService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return ResultDTOUtils.success(orderService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody Order order) {
        return ResultDTOUtils.success(orderService.deleteByEntity(order) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = orderService.deleteByIds(ids);
        }
        return ResultDTOUtils.success(result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return ResultDTOUtils.success(orderService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(Order order) {
        return ResultDTOUtils.success(orderService.countByEntity(order));
    }
}
