package com.luna.meal.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.meal.entity.User;
import com.luna.meal.service.UserService;
import com.luna.meal.util.CookieUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:18:26
 */
@RestController
@RequestMapping("/user/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public ResultDTO<User> getById(@PathVariable(value = "id") Long id) {
        User user = userService.getById(id);
        return ResultDTOUtils.success(user);
    }

    @GetMapping("/get")
    public ResultDTO<User> getByEntity(User user) {
        return ResultDTOUtils.success(userService.getByEntity(user));
    }

    @GetMapping("/list")
    public ResultDTO<List<User>> list(User user) {
        List<User> userList = userService.listByEntity(user);
        return ResultDTOUtils.success(userList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<User>> listPageByEntity(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, User user) {
        System.out.println(user);
        PageInfo<User> pageInfo = userService.listPageByEntity(page, size, user);
        return ResultDTOUtils.success(pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<User>> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
        PageInfo<User> pageInfo = userService.listPage(page, size);
        return ResultDTOUtils.success(pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<User> insert(HttpServletRequest httpServletRequest, @RequestBody User user) {
        userService.insert(CookieUtils.getOneSessionKey(httpServletRequest), user);
        return ResultDTOUtils.success(user);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<User>> insert(@RequestBody List<User> list) {
        userService.insertBatch(list);
        return ResultDTOUtils.success(list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(HttpServletRequest httpServletRequest, @RequestBody User user) {
        return ResultDTOUtils.success(userService.update(CookieUtils.getOneSessionKey(httpServletRequest), user) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<User> list) {
        return ResultDTOUtils.success(userService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(HttpServletRequest httpServletRequest, @PathVariable(value = "id") Long id) {
        return ResultDTOUtils
            .success(userService.deleteById(CookieUtils.getOneSessionKey(httpServletRequest), id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody User user) {
        return ResultDTOUtils.success(userService.deleteByEntity(user) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(HttpServletRequest httpServletRequest, @RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = userService.deleteByIds(CookieUtils.getOneSessionKey(httpServletRequest), ids);
        }
        return ResultDTOUtils.success(result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return ResultDTOUtils.success(userService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(User user) {
        return ResultDTOUtils.success(userService.countByEntity(user));
    }
}
