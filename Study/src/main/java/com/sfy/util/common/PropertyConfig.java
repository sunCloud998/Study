package com.sfy.util.common;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.util.List;
import java.util.Properties;

public enum  PropertyConfig {
    propertyConfig;

    private Logger logger = LoggerFactory.getLogger("Study");
    private final String file = "commons.properties";
    private Properties properties = new Properties();

    /**
     * 初始化
     */
    private PropertyConfig() {
        reload();
    }

    /**
     * 加载配置
     */
    public void reload() {
        try {
            Reader reader = Resources.asCharSource(Resources.getResource(file), Charsets.UTF_8).openStream();
            properties.load(reader);
        } catch (Exception e) {
            logger.error("Load commons.properties error", e);
        }
    }

    /**
     * 获取属性值
     * @param key
     * @return
     */
    public String getValue(String key) {
        return properties.getProperty(key);
    }

    /**
     * 获取属性值，带默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public String getValue(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 以Integer类型获取属性值，带默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public Integer valueAsInt(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }

    /**
     * 获取Double类型属性值，带默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public Double valueAsDouble(String key, Double defaultValue) {
        String value = getValue(key);
        return value != null ? Double.valueOf(value) : defaultValue;
    }

    /**
     * 获取Boolean类型属性值，带默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public Boolean valueAsBoolean(String key, Boolean defaultValue) {
        String value = getValue(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }

    /**
     * 以List&lt;String&gt;类型获取属性值，带分隔符
     * @param key
     * @param separator
     * @return
     */
    public List<String> valueAsList(String key, String separator) {
        String value = getValue(key, "");
        if (Strings.isNullOrEmpty(value)) {
            return Lists.newArrayList();
        }
        return Splitter.on(separator).trimResults().splitToList(value);
    }
}
