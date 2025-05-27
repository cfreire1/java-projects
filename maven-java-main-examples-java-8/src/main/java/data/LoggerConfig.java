package data;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerConfig {
    private static Logger logger;
    private static LoggerConfig loggerConfig;

    private Class<?> getClass;

    private LoggerConfig() {
    }
    
    public static LoggerConfig getInstance() {
        if (loggerConfig == null){
            loggerConfig = new LoggerConfig();

        }
        return loggerConfig;
    }

    public LoggerConfig setClass (Class<?> c){
        getClass = c;
        return loggerConfig;
    }

    public LoggerConfig getLogger() {
        logger = Logger.getLogger(getClass.getName());
        return loggerConfig;
    }

    public void getLogger(String msg) {
        logger.log(Level.INFO, msg);
    }

    public void getLogger(Exception e) {
        logger.log(Level.WARNING, e.getMessage());
    }
}