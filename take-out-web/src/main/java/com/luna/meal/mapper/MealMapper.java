package com.luna.meal.mapper;

import com.luna.meal.entity.Meal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-18 19:10:25
 */
@Mapper
public interface MealMapper {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Meal getById(@NotNull Long id);

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
     * 通过Id列表作为筛选条件查询列表，列表长度不为0
     *
     * @param list 列表
     * @return 对象列表
     */
    List<Meal> listByIds(@NotEmpty List<Long> list);

    /**
     * 新增实体属性不为null的列
     *
     * @param meal 实例
     * @return 影响行数
     */
    int insert(@NotNull Meal meal);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例
     * @return 影响行数
     */
    int insertBatch(@NotEmpty List<Meal> list);

    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param meal 实例
     * @return 影响行数
     */
    int update(@NotNull Meal meal);

    /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param where 条件
     * @param where set
     * @return 影响行数
     */
    int updateByField(@NotNull @Param("where") Meal where, @NotNull @Param("set") Meal set);

    /**
     * 通过主键修改实体列表，列表长度不能为0，注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 列表
     * @return 影响行数
     */
    int updateBatch(@NotEmpty List<Meal> list);

    /**
     * 通过主键删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@NotNull Long id);

    /**
     * 通过实体非空属性删除
     *
     * @param meal 实体
     * @return 影响行数
     */
    int deleteByEntity(@NotNull Meal meal);

    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 列表
     * @return 影响行数
     */
    int deleteByIds(@NotEmpty List<Long> list);

    /**
     * 查询行数
     *
     * @return 影响行数
     */
    int countAll();

    /**
     * 通过实体非空查询行数
     *
     * @param meal 实体
     * @return 影响行数
     */
    int countByEntity(Meal meal);

}
