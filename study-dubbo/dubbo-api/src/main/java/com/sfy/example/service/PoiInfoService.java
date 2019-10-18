package com.sfy.example.service;

import com.sfy.example.param.PoiInfoParam;
import com.sfy.example.vo.PoiInfoVo;

import java.util.List;

/**
 * @Function:
 * @Description: PoiInfoService.java
 * @Date: 2017/10/25
 * @Author: sunfayun
 * @Version: 1.0
 */
public interface PoiInfoService {

    int savePoiInfo(PoiInfoParam poiInfoParam);

    List<PoiInfoVo> queryPoiInfo();

    List<PoiInfoVo> queryPoiInfoByIds(String ids);

    int deletePoiInfoById(int id);

}
