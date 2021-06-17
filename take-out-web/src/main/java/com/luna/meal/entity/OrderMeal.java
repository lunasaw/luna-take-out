package com.luna.meal.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderMeal)实体类
 *
 * @author luna
 * @since 2021-06-17 19:21:06
 */
public class OrderMeal implements Serializable {
    private static final long serialVersionUID = -99338216936659176L;
    /**
     * 订单详细表
     */
    private Long id;
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 物品编号
     */
    private Long mealId;
    /**
     * 物品价格
     */
    private Double mealPrice;
    /**
     * 物品数量
     */
    private Integer mealCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 锁
     */
    private Integer version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public Double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(Double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public Integer getMealCount() {
        return mealCount;
    }

    public void setMealCount(Integer mealCount) {
        this.mealCount = mealCount;
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
