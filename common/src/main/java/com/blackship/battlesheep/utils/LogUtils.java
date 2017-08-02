package com.blackship.battlesheep.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mateusz Słaboński
 * @since 30.07.2017
 */
public class LogUtils extends SecurityManager {

    public static Logger getLogger() {
        String className = new LogUtils().getClassName();
        return LoggerFactory.getLogger(className);
    }

    private String getClassName()
    {
        return getClassContext()[2].getName();
    }
}
