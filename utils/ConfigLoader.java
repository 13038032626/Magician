package com.example.meitu2.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties;
    static {
        properties = new Properties();
        loadProperties("application.properties");
}
    private static void loadProperties(String configFileName) {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(configFileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + configFileName);
                return;
            }
            // 加载属性文件内容
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
