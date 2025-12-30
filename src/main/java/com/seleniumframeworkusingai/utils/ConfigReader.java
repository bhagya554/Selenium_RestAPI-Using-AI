package com.seleniumframeworkusingai.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();
    private static final String CONFIG_PATH = "src/main/resources/config.properties";

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties at " + CONFIG_PATH, e);
        }
    }

    public static String get(String key) {
        String systemOverride = System.getProperty(key);
        return (systemOverride != null) ? systemOverride : props.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}
