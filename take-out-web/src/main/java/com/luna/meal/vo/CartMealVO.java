package com.luna.meal.vo;

/**
 * @author luna
 * 2021/6/19
 */
public class CartMealVO {
    /**
     * 购物车
     */
    private Long    id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
