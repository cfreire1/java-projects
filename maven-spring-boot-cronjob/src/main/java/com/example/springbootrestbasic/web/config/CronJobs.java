package com.example.springbootrestbasic.web.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * https://es.rakko.tools/tools/88/
 */
@Component
public class CronJobs {
    private static final Logger logger = LogManager.getLogger(CronJobs.class);

    @Scheduled(cron = "0 20 18 * * ?")//A las 06:20 PM
    public void job1() {
        logger.info("Ejecucion:job1");
    }

    @Scheduled(cron = "0 * * * * ?")//Cada minuto
    public void job2() {
        logger.info("Ejecucion:job2");
    }


}
