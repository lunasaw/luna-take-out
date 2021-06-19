package com.luna.meal.req;

/**
 * @author luna
 * 2021/6/19
 */
public class ShoppingCartReq {

    private Long    mealId;

    private Integer size;

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
