package com.luna.meal.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.meal.entity.Order;
import com.luna.meal.service.OrderService;
import com.luna.meal.util.CookieUtils;
import com.luna.meal.vo.OrderDetailVO;
import com.luna.meal.vo.OrderVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
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
    public ResultDTO<OrderDetailVO> getById(@PathVariable(value = "id") Long id) {
        OrderDetailVO orderDetailVO = orderService.getById(id);
        return ResultDTOUtils.success(orderDetailVO);
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

    @GetMapping("/myPageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<OrderVO>> myListPageByEntity(HttpServletRequest httpServletRequest,
        @PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, Order order) {
        PageInfo<OrderVO> pageInfo =
            orderService.myListPageByEntity(CookieUtils.getOneSessionKey(httpServletRequest), page, size, order);
        return ResultDTOUtils.success(pageInfo);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<OrderVO>> listPageByEntity(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, Order order) {
        PageInfo<OrderVO> pageInfo = orderService.listPageByEntity(page, size, order);
        return ResultDTOUtils.success(pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<Order>> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
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
    public ResultDTO<Boolean> update(HttpServletRequest httpServletRequest, @RequestBody Order order) {
        return ResultDTOUtils
            .success(orderService.update(CookieUtils.getOneSessionKey(httpServletRequest), order) == 1);
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
