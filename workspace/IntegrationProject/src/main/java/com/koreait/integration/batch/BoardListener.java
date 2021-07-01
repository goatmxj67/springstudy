package com.koreait.integration.batch;

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
public class BoardListener implements ServletContextListener {

	// Scheduler, SchedulerFactory : quartz
	private Scheduler scheduler;
	private SchedulerFactory factory;
	
    public BoardListener() {
        // BoardListener가 동작하면 factory와 scheduler 생성
    	try {
    		factory = new StdSchedulerFactory();
			scheduler = factory.getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("---IntegrationProject 시작---"); 
    	// Scheduler 동작에 필요한 2가지
    	// 1. Job : 무슨 일을 => Job 인터페이스를 구현한 클래스
    	// 2. Trigger : 언제 하겠다. -> CronTrigger
    	try {
	    	JobDetail job = JobBuilder.newJob(BoardJob.class)
	    					.withIdentity("myBoardJob", "myGroup")
	    					.build();
	    	CronTrigger trigger = TriggerBuilder.newTrigger()
	    							.withIdentity("myBoardTrigger", "myGroup")
	    							.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
	    							.build();
	    	scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
    	} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("---IntegrationProject 종료---");
    	try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }

}
