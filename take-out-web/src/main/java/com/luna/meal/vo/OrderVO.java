package com.luna.meal.vo;

/**
 * @author luna
 * 2021/6/19
 */
public class OrderVO {

    /**
     * 订单编号
     */
    private Long    id;
    /**
     * 创建用户
     */
    private Long    userId;
    /**
     * 订单时间
     */
    private String  orderTime;
    /**
     * 订单状态
     */
    private String  orderState;
    /**
     * 订单总价
     */
    private String  orderPrice;
    /**
     * 创建时间
     */
    private String  createTime;
    /**
     * 修改时间
     */
    private String  modifiedTime;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
