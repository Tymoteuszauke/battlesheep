package com.blackship.battlesheep.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
<<<<<<< HEAD
 * Created by Mateusz on 30.07.2017.
=======
 * @author Mateusz Słaboński
 * @since 30.07.2017
>>>>>>> dev_communication_milosz
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
