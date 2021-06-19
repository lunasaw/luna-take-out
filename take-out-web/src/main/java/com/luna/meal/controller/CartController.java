package com.luna.meal.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.meal.entity.Cart;
import com.luna.meal.req.ShoppingCartReq;
import com.luna.meal.service.CartService;
import com.luna.meal.util.CookieUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:22:25
 */
@RestController
@RequestMapping("/cart/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/get/{id}")
    public ResultDTO<Cart> getById(@PathVariable(value = "id") Long id) {
        Cart cart = cartService.getById(id);
        return ResultDTOUtils.success(cart);
    }

    @GetMapping("/get")
    public ResultDTO<Cart> getByEntity(Cart cart) {
        return ResultDTOUtils.success(cartService.getByEntity(cart));
    }

    @GetMapping("/list")
    public ResultDTO<List<Cart>> list(Cart cart) {
        List<Cart> cartList = cartService.listByEntity(cart);
        return ResultDTOUtils.success(cartList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<Cart>> listPageByEntity(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, Cart cart) {
        PageInfo<Cart> pageInfo = cartService.listPageByEntity(page, size, cart);
        return ResultDTOUtils.success(pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<Cart>> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
        PageInfo<Cart> pageInfo = cartService.listPage(page, size);
        return ResultDTOUtils.success(pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<Cart> insert(HttpServletRequest httpServletRequest, @RequestBody Cart cart) {
        cartService.insert(CookieUtils.getOneSessionKey(httpServletRequest), cart);
        return ResultDTOUtils.success(cart);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<Cart>> insert(@RequestBody List<Cart> list) {
        cartService.insertBatch(list);
        return ResultDTOUtils.success(list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody Cart cart) {
        return ResultDTOUtils.success(cartService.update(cart) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<Cart> list) {
        return ResultDTOUtils.success(cartService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return ResultDTOUtils.success(cartService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody Cart cart) {
        return ResultDTOUtils.success(cartService.deleteByEntity(cart) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = cartService.deleteByIds(ids);
        }
        return ResultDTOUtils.success(result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return ResultDTOUtils.success(cartService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(Cart cart) {
        return ResultDTOUtils.success(cartService.countByEntity(cart));
    }
}
