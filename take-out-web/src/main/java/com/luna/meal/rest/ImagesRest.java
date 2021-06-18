package com.luna.meal.rest;

import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.meal.admin.ImagesService;
import com.luna.meal.entity.Meal;
import com.luna.meal.entity.MealSeries;
import com.luna.meal.mapper.MealMapper;
import com.luna.meal.service.MealService;
import com.luna.meal.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

/**
 * @author luna
 * 2021/6/18
 */
@RestController

@RequestMapping("img/api")
public class ImagesRest {

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private MealMapper    mealMapper;

    /**
     * 通用上传请求
     */
    @PostMapping("/upload/meal/{id}")
    public ResultDTO<?> uploadFile(HttpServletRequest request, MultipartFile file,
        @PathVariable(name = "id") Long id) {
        String path = imagesService.uploadImg(CookieUtils.getOneSessionKey(request), file);
        Meal meal = mealMapper.getById(id);
        meal.setImage(path);
        return ResultDTOUtils.success(mealMapper.update(meal) == 1);
    }

}
