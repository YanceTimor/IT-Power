package com.yance520.itpower.model.sys;

import java.io.Serializable;

public class PosterImportArea implements Serializable {

    private static final long serialVersionUID = 1L;

    // 海报档期id
    private Integer posterId;

    // 区域
    private String area;

    // 城市
    private String city;

    private String jobNumber;

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Integer getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
