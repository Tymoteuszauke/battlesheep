package com.blackship.battlesheep.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mateusz on 30.07.2017.
 */
public class LogUtils extends SecurityManager{

    public static Logger getLogger() {
        String className = new LogUtils().getClassName();
        Logger logger = LoggerFactory.getLogger(className);
        return logger;
    }

    private String getClassName()
    {
        return getClassContext()[2].getName();
    }
}
