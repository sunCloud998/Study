package com.sfy.example.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Function:
 * @Description: PoiInfoVo.java
 * @Date: 2017/10/25
 * @Author: sunfayun
 * @Version: 1.0
 */
@Data
public class PoiInfoVo implements Serializable {

    private Integer id;
    private String poiName;
    private String poiImage;
    private String poiPhone;
    private String cityId;

}
