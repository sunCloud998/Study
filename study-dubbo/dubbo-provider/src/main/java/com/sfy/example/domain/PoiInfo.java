package com.sfy.example.domain;

import lombok.Data;

/**
 * @Function:
 * @Description: PoiInfo.java
 * @Date: 2017/10/25
 * @Author: sunfayun
 * @Version: 1.0
 */
@Data
public class PoiInfo {

    private Integer id;
    private String poiName;
    private String poiImage;
    private String poiPhone;
    private String cityId;

}
