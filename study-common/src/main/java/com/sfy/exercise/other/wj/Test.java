package com.sfy.exercise.other.wj;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

/**
 * @function:
 * @description: Test.java
 * @date: 2019/09/05
 * @author: sunfayun
 * @version: 1.0
 */
public class Test {

//    public void test() {
//        if(StringUtils.isNotEmpty(formValue)) {
//            XZBGJiGou subBrandTemp = null;
//            XZBGJiGouVo jiGouVo = JSONObject.parseObject(formVlue, XZBGJiGouVo.class);
//            if(jiGouVo != null) {
//                subBrandTemp = jiGouVo.getXZBGJiGou();
//                InstitutionalChangeDto dto = jiGouVo.getInstitutionalChangeDto();
//            }
//            // 拼接的代码
//            if(dto != null) {
//                // 拼接InstitutionalAttributeDTO
//                List<InstitutionalAttributeDTO> attributeDtoList;
//                if(json.containsKey("xxx")) {
//                    json.get(xx)
//                }
//                if(CollectionUtils.isNotEmpty(attributeDtoList)) {
//                    List<String> attributeList = new ArrayList<>();
//                    for(InstitutionalAttributeDTO attribute : attributeDtoList) {
//                        StringBuilder sb = new StringBuilder();
//                        sb.append(attribute.getAttrName());
//                        sb.append("@");
//                        sb.append(attribute.getAttrValue());
//                        attributeList.add(sb.toString());
//                    }
//                    StringBuilder joinAttributeBuilder = new StringBuilder();
//                    for(String s : attributeList) {
//                        joinAttributeBuilder.append(s).append("&");
//                    }
//                    // 删除最后一个&
//                    String attributeStr = joinAttributeBuilder.substring(0, joinAttributeBuilder.length()-1);
//                    // 把拼接好的字符串设置到dto
//                    dto.setPropertyChangeAttibute(attributeStr);
//                }
//                // 拼接changeSubmitDto
//                List<InstitutionalChangeSubmitDto> submitDtoList = dto.getXX();
//                if(CollectionUtils.isNotEmpty(submitDtoList)) {
//                    StringBuilder propertyChangeSubmitInstituIdBuilder = new StringBuilder();
//                    StringBuilder propertyChangeSubmitInstituNameBuilder = new StringBuilder();
//                    StringBuilder propertyChangeSubmitOldInstituIdBuilder = new StringBuilder();
//                    StringBuilder propertyChangeSubmitNewInstituIdBuilder = new StringBuilder();
//                    for(InstitutionalChangeSubmitDto submitDto : submitDtoList) {
//                        propertyChangeSubmitInstituIdBuilder.append(submitDto.getSubmitInstituId()).append("&");
//                        propertyChangeSubmitInstituNameBuilder.append(submitDto.getSubmitInstituName()).append("&");
//                        propertyChangeSubmitOldInstituIdBuilder.append(submitDto.getSubmitOldInstituId()).append("&");
//                        propertyChangeSubmitNewInstituIdBuilder.append(submitDto.getSubmitNewInstituId()).append("&");
//                    }
//                    dto.setPropertyChangeSubmitInstituId(propertyChangeSubmitInstituIdBuilder.substring(0,propertyChangeSubmitInstituIdBuilder.length()-1));
//                    dto.setPropertyChangeSubmitInstituName(propertyChangeSubmitInstituNameBuilder.substring(0,propertyChangeSubmitInstituNameBuilder.length()-1));
//                    dto.setPropertyChangeSubmitOldInstituId(propertyChangeSubmitOldInstituIdBuilder.substring(0,propertyChangeSubmitOldInstituIdBuilder.length()-1));
//                    dto.setPropertyChangeSubmitNewInstituId(propertyChangeSubmitNewInstituIdBuilder.substring(0,propertyChangeSubmitNewInstituIdBuilder.length()-1));
//                }
//        }
//
//
//
//        if(json.containsKey("institutionalChangeDto")) {
//            InstitutionalChangeDto dto = json.getObject("institutionalChangeDto", InstitutionalChangeDto.class);
//
//
//            }
//        }
//    }

}
