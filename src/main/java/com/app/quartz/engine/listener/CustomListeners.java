package com.app.quartz.engine.listener;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.app.model.SchedulerJobInfo;
import com.app.model.SchedulerJobInfoLog;
import com.app.model.SchedulerNotification;
import com.app.model.SchedulerNotificationHistory;
import com.app.services.JobServices;
import com.app.services.SchedulerService;
import com.app.services.notification.NotificationServices;

@Component
public class CustomListeners implements TriggerListener {

	private String name;

	@Autowired
	private JobServices jobServices;
	
	@Autowired
	private SchedulerService schedulerService;
	
	@Autowired
    private ApplicationContext applicationContext;

	/*
	 * @Autowired private SchedulerFactoryBean schedulerFactoryBean;
	 */
	
	public CustomListeners(String name) {
		this.name = "BankSinarmas";
	}

	public CustomListeners() {
		this.name = "BankSinarmas";
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		// TODO Auto-generated method stub
//		System.out.println("3 ==> triggerMisfired");
	};

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		// TODO Auto-generated method stub:
		
		JobKey jobKey = context.getJobDetail().getKey();
		Long jobId = Long.parseLong((String)context.getJobDetail().getJobDataMap().get("jobId"));
		SchedulerJobInfo jobInfo = jobServices.selectJobById(jobId);
		SchedulerJobInfoLog jobInfoLog = SchedulerJobInfoLog.parse(jobInfo);
		String job_result = "";

		if(context.getResult() !=  null) {
			job_result = context.getResult().toString();
		}
		jobInfoLog.setJob_result(job_result);
		jobServices.insertJobHistoryLog(jobInfoLog);
		System.out.println("info id="+jobInfoLog.getInfo_id());
		setNextTime(jobId, jobKey);
		setNotificationProcess(jobInfo,context);
		
		
		
		
	}
	
	
    public void setNotificationProcess(SchedulerJobInfo jobInfo,JobExecutionContext context) {
    	SchedulerNotification notification = jobServices.selectNotificationId(jobInfo.getNotification_id());
    	if(notification != null) {
    		String notificationType = notification.getNotification_type()+"notification";
    		NotificationServices notificationServices = (NotificationServices)applicationContext.getBean(notificationType);
    		
    		String message = "-";
    		int error = 0;
    		try {
    			message = notificationServices.process(notification);
    		}catch(Exception e) {
    			message = e.getMessage();
    			error = 1;
    		}
    		SchedulerNotificationHistory history = new SchedulerNotificationHistory();
    		history.setInfo_id(jobInfo.getId());
    		history.setNotification_id(notification.getNotification_id());
    		history.setError(error);
    		history.setMessage(message);
    		jobServices.insertNotificationHistory(history);
    	}
    }
	
	public void setNextTime(Long jobId, JobKey jobKey) {
		
		 try { Scheduler scheduler = schedulerService.getSchedulerBean().getScheduler();
		  List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
		  String state= "";
		  for (Trigger trigger : triggers) {
				TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());

				if (TriggerState.PAUSED.equals(triggerState)) {
					state = "PAUSED";
				} else if (TriggerState.BLOCKED.equals(triggerState)) {
					state =  "BLOCKED";
				} else if (TriggerState.COMPLETE.equals(triggerState)) {
					state =  "COMPLETE";
				} else if (TriggerState.ERROR.equals(triggerState)) {
					state = "ERROR";
				} else if (TriggerState.NONE.equals(triggerState)) {
					state = "NONE";
				} else if (TriggerState.NORMAL.equals(triggerState)) {
					state = "SCHEDULED";
				}
			}
		  
		  
		  
		  Date scheduleTime = triggers.get(0).getStartTime() != null? triggers.get(0).getStartTime() : new Date();
		  Date nextFireTime = triggers.get(0).getNextFireTime() != null? triggers.get(0).getNextFireTime() : new Date();
		  Date lastFiredTime =triggers.get(0).getPreviousFireTime() != null? triggers.get(0).getPreviousFireTime() :new Date();
		  System.out.println("scheduleTime:"+triggers.get(0).getStartTime());
		  System.out.println("nextFireTime:"+triggers.get(0).getNextFireTime());
		  System.out.println("lastFiredTime:"+triggers.get(0).getPreviousFireTime());
		  
		  
		  jobServices.updateJobInfoTime(1,scheduleTime.toString(),nextFireTime.toString(),lastFiredTime.toString(),state,jobId);
		
		  
		  
		  } catch (SchedulerException e) { e.printStackTrace(); }
		 
	}

}