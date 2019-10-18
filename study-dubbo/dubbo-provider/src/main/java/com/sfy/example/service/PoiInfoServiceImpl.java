package com.sfy.example.service;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.sfy.example.dao.PoiInfoDao;
import com.sfy.example.domain.PoiInfo;
import com.sfy.example.param.PoiInfoParam;
import com.sfy.example.util.BeanMapper;
import com.sfy.example.vo.PoiInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Function:
 * @Description: PoiInfoServiceImpl.java
 * @Date: 2017/10/25
 * @Author: sunfayun
 * @Version: 1.0
 */
@Service
public class PoiInfoServiceImpl implements PoiInfoService {

    @Resource
    private PoiInfoDao poiInfoDao;

    public int savePoiInfo(PoiInfoParam poiInfoParam) {
        PoiInfo poiInfo = BeanMapper.map(poiInfoParam,PoiInfo.class);
        return poiInfoDao.savePoiInfo(poiInfo);
    }

    public List<PoiInfoVo> queryPoiInfo() {
        List<PoiInfo> poiInfoList = poiInfoDao.queryPoiInfo();
        List<PoiInfoVo> poiInfoVoList = BeanMapper.mapList(poiInfoList,PoiInfoVo.class);
        return poiInfoVoList;
    }

    public List<PoiInfoVo> queryPoiInfoByIds(String ids) {
        List<Integer> idList = Lists.newArrayList(Collections2.transform(Splitter.on(",").splitToList(ids), new Function<String, Integer>() {
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }));
        List<PoiInfo> poiInfoList = poiInfoDao.queryPoiInfoByIds(idList);
        List<PoiInfoVo> poiInfoVoList = BeanMapper.mapList(poiInfoList,PoiInfoVo.class);
        return poiInfoVoList;
    }

    public int deletePoiInfoById(int id) {
        return poiInfoDao.deletePoiInfoById(id);
    }
}
