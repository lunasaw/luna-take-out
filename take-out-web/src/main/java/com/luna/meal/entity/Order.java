package com.luna.meal.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 订单主表(Order)实体类
 *
 * @author luna
 * @since 2021-06-19 10:09:04
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -62588210757812743L;
    /** 订单编号 */
    private Long              id;
    /** 创建用户 */
    private Long              userId;
    /** 订单时间 */
    private Date              orderTime;
    /** 订单状态 */
    private Integer           orderState;
    /** 订单价格 */
    private Double            orderPrice;
    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
    private Date              modifiedTime;
    /** 锁 */
    private Integer           version;

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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
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
