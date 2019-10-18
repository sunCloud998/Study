package com.sfy.example.dao;

import com.sfy.example.domain.PoiInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Function:
 * @Description: PoiInfoDao.java
 * @Date: 2017/10/25
 * @Author: sunfayun
 * @Version: 1.0
 */
@Repository
public interface PoiInfoDao {

    int savePoiInfo(PoiInfo poiInfo);

    List<PoiInfo> queryPoiInfo();

    List<PoiInfo> queryPoiInfoByIds(List<Integer> list);

    int deletePoiInfoById(int id);
}
