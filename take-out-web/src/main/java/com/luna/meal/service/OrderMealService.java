package com.luna.meal.service;

import com.luna.meal.mapper.OrderMealMapper;
import com.luna.meal.entity.OrderMeal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:21:06
 */
public interface OrderMealService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    OrderMeal getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param orderMeal 条件
     * @return 对象
     */
    OrderMeal getByEntity(OrderMeal orderMeal);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param orderMeal 条件
     * @return 对象列表
     */
    List<OrderMeal> listByEntity(OrderMeal orderMeal);

    /**
     * 条件分页查询
     *
     * @param orderMeal 查询条件
     * @param page      起始标号
     * @param pageSize  查询条目
     * @return 对象列表
     */
    PageInfo<OrderMeal> listPageByEntity(int page, int pageSize, OrderMeal orderMeal);

    /**
     * 条件分页查询
     *
     * @param page     起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<OrderMeal> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<OrderMeal> listByIds(List<Long> ids);

    /**
     * 插入
     *
     * @param orderMeal 对象
     * @return 影响行数
     */
    int insert(OrderMeal orderMeal);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<OrderMeal> list);

    /**
     * 更新
     *
     * @param orderMeal 对象
     * @return 影响行数
     */
    int update(OrderMeal orderMeal);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<OrderMeal> list);

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
     * @param orderMeal 对象
     * @return 影响行数
     */
    int deleteByEntity(OrderMeal orderMeal);

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
     * @param orderMeal 对象
     * @return 影响行数
     */
    int countByEntity(OrderMeal orderMeal);
}
