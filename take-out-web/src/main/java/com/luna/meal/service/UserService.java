package com.luna.meal.service;

import com.luna.meal.mapper.UserMapper;
import com.luna.meal.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:18:26
 */
public interface UserService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    User getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param user 条件
     * @return 对象
     */
    User getByEntity(User user);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param user 条件
     * @return 对象列表
     */
    List<User> listByEntity(User user);

    /**
     * 条件分页查询
     *
     * @param user     查询条件
     * @param page     起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<User> listPageByEntity(int page, int pageSize, User user);

    /**
     * 条件分页查询
     *
     * @param page     起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<User> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<User> listByIds(List<Long> ids);

    /**
     * 插入
     *
     * @param user 对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<User> list);

    /**
     * 更新
     *
     * @param user 对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<User> list);

    /**
     * 删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 条件删除
     *
     * @param user 对象
     * @return 影响行数
     */
    int deleteByEntity(User user);

    /**
     * 主键列表删除
     *
     * @param list 主键列表
     * @return 影响行数
     */
    int deleteByIds(List<Long> list);

    /**
     * 数据条目
     *
     * @return 影响行数
     */
    int countAll();

    /**
     * 条件查询数目
     *
     * @param user 对象
     * @return 影响行数
     */
    int countByEntity(User user);
}
