package com.luna.meal.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 菜系表(MealSeries)实体类
 *
 * @author luna
 * @since 2021-06-17 19:21:36
 */
public class MealSeries implements Serializable {
    private static final long serialVersionUID = -50152331145150037L;
    /**
     * 菜系编号
     */
    private Long id;
    /**
     * 菜系名称
     */
    private String seriesName;
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

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
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
