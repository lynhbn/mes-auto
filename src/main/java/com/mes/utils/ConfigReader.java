package com.mes.utils;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    public static Properties loadProperties(String env) {
        String fileName = "env/" + env + ".properties";
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Not found file: " + fileName);
            }
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}