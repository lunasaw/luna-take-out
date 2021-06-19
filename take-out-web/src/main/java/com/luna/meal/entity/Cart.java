package com.luna.meal.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 商品购物车(Cart)实体类
 *
 * @author luna
 * @since 2021-06-17 19:22:24
 */
public class Cart implements Serializable {
    private static final long serialVersionUID = 548680489621419484L;
    /**
     * 购物车
     */
    private Long              id;
    /**
     * 用户编号
     */
    private Long              userId;
    /**
     * 商品编号
     */
    private Long              mealId;
    /**
     * 数量
     */
    private Integer           count;
    /**
     * 创建时间
     */
    private Date              createTime;
    /**
     * 修改时间
     */
    private Date              modifiedTime;
    /**
     * 锁
     */
    private Integer           version;

    public Cart() {}

    public Cart(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
