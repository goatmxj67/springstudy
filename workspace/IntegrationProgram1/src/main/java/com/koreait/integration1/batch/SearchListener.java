package com.koreait.integration1.batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class SearchListener implements ServletContextListener {

	private SchedulerFactory factory = null;
	private Scheduler scheduler = null;
	
    public SearchListener() {
        try {
        	factory = new StdSchedulerFactory();
        	scheduler = factory.getScheduler();
        } catch (SchedulerException e) {
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("===== 검색 서비스 종료 =====");
    	try {
    		scheduler.shutdown();
    	} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("===== 검색 서비스 시작 =====");
    	try {  		
    		JobDetail job = JobBuilder.newJob(SearchJob.class)
    				.withIdentity("myJob", "group1")
    				.build();
    		CronTrigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("myTrigger", "group1")
    				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
    				.build();
    		scheduler.start();
    		scheduler.scheduleJob(job, trigger);
    	} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }
	
}
