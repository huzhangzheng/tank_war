package com.kevin;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private static Properties prop = new Properties();

    static{
        try {
            prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        if (key == null) return null;

        return prop.getProperty(key);
    }

    public static void main(String[] args) {
        String initTankCount = PropertyMgr.get("initTankCount");
        System.out.println(initTankCount);
    }
}
