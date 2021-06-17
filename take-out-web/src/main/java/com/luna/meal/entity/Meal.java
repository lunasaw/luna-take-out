package com.luna.meal.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 餐品信息表(Meal)实体类
 *
 * @author luna
 * @since 2021-06-17 19:22:00
 */
public class Meal implements Serializable {
    private static final long serialVersionUID = -12769820278469815L;
    /**
     * 食物编号
     */
    private Long id;
    /**
     * 菜系编号
     */
    private Long seriseId;
    /**
     * 食物名称
     */
    private String mealName;
    /**
     * 食物摘要
     */
    private String summarize;
    /**
     * 食物描述
     */
    private String description;
    /**
     * 物品价格
     */
    private Double mealPrice;
    /**
     * 物品图片
     */
    private String image;
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

    public Long getSeriseId() {
        return seriseId;
    }

    public void setSeriseId(Long seriseId) {
        this.seriseId = seriseId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(Double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
