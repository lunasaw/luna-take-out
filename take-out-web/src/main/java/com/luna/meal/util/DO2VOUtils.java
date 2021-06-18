package com.luna.meal.util;

import com.luna.common.date.DateUtil;
import com.luna.meal.entity.Meal;
import com.luna.meal.vo.MealVO;

/**
 * @author luna
 * 2021/6/18
 */
public class DO2VOUtils {

    public static MealVO MealDO2MealVO(Meal meal, String seriesName) {
        if (meal == null) {
            return null;
        }
        MealVO mealVO = new MealVO();
        mealVO.setId(meal.getId());
        mealVO.setSeriesName(seriesName);
        mealVO.setMealName(meal.getMealName());
        mealVO.setSummarize(meal.getSummarize());
        mealVO.setDescription(meal.getDescription());
        mealVO.setMealPrice(meal.getMealPrice());
        mealVO.setImage(meal.getImage());
        mealVO.setCreateTime(
            meal.getCreateTime() != null ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, meal.getCreateTime()) : "");
        mealVO.setModifiedTime(
            meal.getModifiedTime() != null ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, meal.getModifiedTime()) : "");
        mealVO.setVersion(meal.getVersion());
        return mealVO;
    }
}
