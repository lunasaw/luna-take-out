package com.luna.meal.test;

import com.luna.baidu.config.BaiduKeyGenerate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author luna
 * 2021/6/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MealTest {

    @Autowired
    private BaiduKeyGenerate baiduKeyGenerate;

    @Test
    public void atest() {
        baiduKeyGenerate.getAuth();
    }
}
