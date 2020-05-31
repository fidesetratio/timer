package com.app.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import com.app.model.SchedulerJobInfo;

@Component
public class JobScheduleCreator {

	public JobDetail createJob(Class<? extends QuartzJobBean> jobClass, boolean isDurable, ApplicationContext context,
			String jobName, String jobGroup) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(jobClass);
		factoryBean.setDurability(isDurable);
		factoryBean.setApplicationContext(context);
		factoryBean.setName(jobName);
		factoryBean.setGroup(jobGroup);

// set job data map
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(jobName + jobGroup, jobClass.getName());
		factoryBean.setJobDataMap(jobDataMap);

		factoryBean.afterPropertiesSet();

		return factoryBean.getObject();
	}
	
	
	public JobDetail createJob(Class<? extends QuartzJobBean> jobClass, boolean isDurable, ApplicationContext context,
			String jobName, String jobGroup,SchedulerJobInfo jobInfo) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(jobClass);
		factoryBean.setDurability(isDurable);
		factoryBean.setApplicationContext(context);
		factoryBean.setName(jobName);
		factoryBean.setGroup(jobGroup);

// set job data map
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobId", Long.toString(jobInfo.getId()));
		jobDataMap.put("url",jobInfo.getUrl());
		jobDataMap.put("param0", jobInfo.getParam0());
		jobDataMap.put("param1", jobInfo.getParam1());
		factoryBean.setJobDataMap(jobDataMap);

		factoryBean.afterPropertiesSet();

		return factoryBean.getObject();
	}

	public CronTrigger createCronTrigger(String triggerName, Date startTime, String cronExpression,
			int misFireInstruction) {
		CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
		factoryBean.setName(triggerName);
		factoryBean.setStartTime(startTime);
		factoryBean.setCronExpression(cronExpression);
		factoryBean.setMisfireInstruction(misFireInstruction);
		try {
			factoryBean.afterPropertiesSet();
		} catch (ParseException e) {

		}
		return factoryBean.getObject();
	}

	public SimpleTrigger createSimpleTrigger(String triggerName, Date startTime, Long repeatTime,
			int misFireInstruction) {
		SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
		factoryBean.setName(triggerName);
		factoryBean.setStartTime(startTime);
		factoryBean.setRepeatInterval(repeatTime);
		factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		factoryBean.setMisfireInstruction(misFireInstruction);
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
	
	public Trigger createOnceByDateAndTime(String triggerName, String dateTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//28/09/2013 09:57:19
		Date startTime = new Date();
		try {
			startTime = dateFormat.parse(dateTime);
			System.out.println("startTime:"+startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName).startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionNextWithExistingCount() ).build();
		return trigger;
	}
	
}
