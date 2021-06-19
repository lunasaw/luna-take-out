package com.luna.meal.service;

import com.luna.meal.entity.Cart;
import com.github.pagehelper.PageInfo;
import com.luna.meal.vo.CartMealVO;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-06-17 19:22:24
 */
public interface CartService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Cart getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param cart 条件
     * @return 对象
     */
    Cart getByEntity(Cart cart);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param cart 条件
     * @return 对象列表
     */
    List<Cart> listByEntity(Cart cart);

    /**
     * 条件分页查询
     *
     * @param page 起始标号
     * @param pageSize 查询条目
     * @param cart 查询条件
     * @return 对象列表
     */
    PageInfo<CartMealVO> listPageByEntity(int page, int pageSize, Cart cart);

    /**
     * 条件分页查询
     *
     * @param page     起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<Cart> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<Cart> listByIds(List<Long> ids);

    /**
     * 插入
     *
     *
     * @param oneSessionKey
     * @param cart 对象
     * @return 影响行数
     */
    int insert(String oneSessionKey, Cart cart);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<Cart> list);

    /**
     * 更新
     *
     * @param cart 对象
     * @return 影响行数
     */
    int update(Cart cart);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<Cart> list);

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
     * @param cart 对象
     * @return 影响行数
     */
    int deleteByEntity(Cart cart);

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
     * @param cart 对象
     * @return 影响行数
     */
    int countByEntity(Cart cart);
}
