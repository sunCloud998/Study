package com.sfy.example.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Function:
 * @Description: PoiInfoParam.java
 * @Date: 2017/10/25
 * @Author: sunfayun
 * @Version: 1.0
 */
@Data
public class PoiInfoParam implements Serializable {

    private Integer id;
    private String poiName;
    private String poiImage;
    private String poiPhone;
    private String cityId;

}
