package com.luna.meal.service;

import com.luna.meal.mapper.MealMapper;
import com.luna.meal.entity.Meal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luna.meal.vo.MealVO;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-18 19:10:25
 */
public interface MealService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Meal getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param meal 条件
     * @return 对象
     */
    Meal getByEntity(Meal meal);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param meal 条件
     * @return 对象列表
     */
    List<Meal> listByEntity(Meal meal);

    /**
     * 条件分页查询
     *
     * @param meal 查询条件
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<MealVO> listPageByEntity(int page, int pageSize, Meal meal);

    /**
     * 条件分页查询
     *
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<Meal> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<Meal> listByIds(List<Long> ids);

    /**
     * 插入
     *
     * @param meal 对象
     * @return 影响行数
     */
    int insert(Meal meal);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<Meal> list);

    /**
     * 更新
     *
     * @param meal 对象
     * @return 影响行数
     */
    int update(Meal meal);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<Meal> list);

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
     * @param meal 对象
     * @return 影响行数
     */
    int deleteByEntity(Meal meal);

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
     * @param meal 对象
     * @return 影响行数
     */
    int countByEntity(Meal meal);
}
