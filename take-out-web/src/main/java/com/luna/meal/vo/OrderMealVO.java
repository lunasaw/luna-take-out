package com.luna.meal.vo;

import java.util.Date;

/**
 * @author luna
 * 2021/6/19
 */
public class OrderMealVO {
    /**
     * 订单详细表
     */
    private Long    id;
    /**
     * 订单编号
     */
    private Long    orderId;
    /**
     * 菜品名称
     */
    private String  mealName;
    /**
     * 菜系名称
     */
    private String  seriesName;

    /**
     * 物品单价
     */
    private String  price;
    /**
     * 物品数量x单价
     */
    private String  mealPrice;
    /**
     * 物品数量
     */
    private Integer mealCount;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }

    public Integer getMealCount() {
        return mealCount;
    }

    public void setMealCount(Integer mealCount) {
        this.mealCount = mealCount;
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
