package com.xjst.utils.propertiesutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    public static String getProperties(String keyname) throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = PropertiesUtils.class.getResourceAsStream("/properties/config.properties");
        properties.load(inputStream);
        String property = properties.getProperty(keyname);
        return property;
    }
}
