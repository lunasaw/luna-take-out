package com.luna.meal.service.impl;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.encrypt.HashTools;
import com.luna.meal.constant.UserConstant;
import com.luna.meal.exception.UserException;
import com.luna.meal.mapper.UserMapper;
import com.luna.meal.service.UserService;
import com.luna.meal.entity.User;

import com.luna.meal.tools.UserTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Objects;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:18:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTools  userTools;

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public User getByEntity(User user) {
        return userMapper.getByEntity(user);
    }

    @Override
    public List<User> listByEntity(User user) {
        return userMapper.listByEntity(user);
    }

    @Override
    public PageInfo<User> listPageByEntity(int page, int pageSize, User user) {
        PageHelper.startPage(page, pageSize);
        List<User> list = userMapper.listByEntity(user);
        return new PageInfo<User>(list);
    }

    @Override
    public PageInfo<User> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> list = userMapper.listByEntity(new User());
        return new PageInfo<User>(list);
    }

    @Override
    public List<User> listByIds(List<Long> ids) {
        return userMapper.listByIds(ids);
    }

    @Override
    public int insert(String oneSessionKey, User user) {
        User doUser = userTools.getUser(oneSessionKey);
        if (!Objects.equals(UserConstant.ADMIN, doUser.getAdmin())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "鉴权异常");
        }
        user.setPassword(HashTools.md5(HashTools.md5(user.getPassword())));
        return userMapper.insert(user);
    }

    @Override
    public int insertBatch(List<User> list) {
        return userMapper.insertBatch(list);
    }

    @Override
    public int update(String oneSessionKey, User user) {
        User doUser = userTools.getUser(oneSessionKey);
        if (!Objects.equals(UserConstant.ADMIN, doUser.getAdmin())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "鉴权异常");
        }
        User mapperById = userMapper.getById(user.getId());
        if (mapperById == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }
        mapperById.setUsername(user.getUsername());
        mapperById.setRealName(user.getRealName());
        mapperById.setEmail(user.getEmail());
        mapperById.setPhone(user.getPhone());
        mapperById.setAddress(user.getAddress());
        mapperById.setPassword(HashTools.md5(HashTools.md5(user.getPassword())));
        return userMapper.update(mapperById);
    }

    @Override
    public int updateBatch(List<User> list) {
        return userMapper.updateBatch(list);
    }

    @Override
    public int deleteById(String oneSessionKey, Long id) {
        User doUser = userTools.getUser(oneSessionKey);
        if (!Objects.equals(UserConstant.ADMIN, doUser.getAdmin())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "鉴权异常");
        }
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(User user) {
        return userMapper.deleteByEntity(user);
    }

    @Override
    public int deleteByIds(String oneSessionKey, List<Long> list) {
        User doUser = userTools.getUser(oneSessionKey);
        if (!Objects.equals(UserConstant.ADMIN, doUser.getAdmin())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "鉴权异常");
        }
        return userMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return userMapper.countAll();
    }

    @Override
    public int countByEntity(User user) {
        return userMapper.countByEntity(user);
    }

}
