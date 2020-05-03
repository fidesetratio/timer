package com.app.services.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.app.component.JobScheduleCreator;
import com.app.model.SchedulerJobInfo;
import com.app.services.JobServices;
import com.app.services.SchedulerService;

@Service
public class DefaultSchedulerService implements SchedulerService {

	
	@Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private JobServices jobServices;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private JobScheduleCreator scheduleCreator;
    
	 private static final Logger logger = LogManager.getLogger(DefaultSchedulerService.class);
		
	
	@Override
	public void startAllSchedulers() {
		// TODO Auto-generated method stub
        List<SchedulerJobInfo> jobInfoList = jobServices.selectAll();
        ConcurrentHashMap<String, SchedulerJobInfo> listAvailableDatabase = new ConcurrentHashMap<String, SchedulerJobInfo>();
        
        if(jobInfoList !=  null) {
        	
        	  Scheduler scheduler = schedulerFactoryBean.getScheduler();
        	  for(SchedulerJobInfo jobInfo:jobInfoList) {
        		  try {
        			  System.out.println("jobInfo.getJobClass()="+jobInfo.getJob_class());

        			  System.out.println("jobInfo.getJobClass()="+jobInfo.getId());
                      JobDetail jobDetail = JobBuilder.newJob((Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJob_class().trim()))
                              .withIdentity(jobInfo.getJob_name(), jobInfo.getJob_group()).build();
                      
                      listAvailableDatabase.putIfAbsent(jobInfo.getJob_name()+""+jobInfo.getJob_group(), jobInfo);
                      if (!scheduler.checkExists(jobDetail.getKey())) {
                          Trigger trigger;
                          jobDetail = scheduleCreator.createJob((Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJob_class()),
                                  false, context, jobInfo.getJob_name(), jobInfo.getJob_group(),jobInfo);
                          
                          if (jobInfo.getCron_job().equals("Y") && CronExpression.isValidExpression(jobInfo.getCron_expression())) {
                              trigger = scheduleCreator.createCronTrigger(jobInfo.getJob_name(), new Date(),
                                      jobInfo.getCron_expression(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
                          } else {
                              trigger = scheduleCreator.createSimpleTrigger(jobInfo.getJob_name(), new Date(),
                                      jobInfo.getRepeat_time(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
                          }

                          scheduler.scheduleJob(jobDetail, trigger);

                      }
                  } catch (ClassNotFoundException e) {
                	  System.out.println("class is not found..");
                	  logger.error("Class Not Found - {}", jobInfo.getJob_class(), e);
                  } catch (SchedulerException e) {
                	  logger.error(e.getMessage(), e);
                  }
        	  }
        	  
        	  
        	  
        	  
        	  
        	  
        }
        
        
        sinkronizeAllInMemory(listAvailableDatabase);
        
        
        
	}
	
	public void sinkronizeAllInMemory(ConcurrentHashMap<String, SchedulerJobInfo> listAvailableDatabase) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();
					String key = jobName+""+jobGroup;
					if(!listAvailableDatabase.containsKey(key)) {
						SchedulerJobInfo jobInfo = new SchedulerJobInfo();
						jobInfo.setJob_group(jobGroup);
						jobInfo.setJob_name(jobName);
						pauseJob(jobInfo);
						deleteJob(jobInfo);
					}
				}
			}

		} catch (SchedulerException e) {
			
		}
	}
	

	
	@Override
	public void scheduleNewJob(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateScheduleJob(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean unScheduleJob(String jobName) {
		// TODO Auto-generated method stub
		try {
			return schedulerFactoryBean.getScheduler().unscheduleJob(new TriggerKey(jobName));
		} catch (SchedulerException e) {
			return false;
		}
	}

	@Override
	public boolean deleteJob(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		try {
			boolean ret = true;
			ret = schedulerFactoryBean.getScheduler()
					.deleteJob(new JobKey(jobInfo.getJob_name(), jobInfo.getJob_group()));
			
			return ret;
		} catch (SchedulerException e) {

		}
		return false;
	}

	@Override
	public boolean pauseJob(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		try {
			schedulerFactoryBean.getScheduler().pauseJob(new JobKey(jobInfo.getJob_name(), jobInfo.getJob_group()));
			return true;
		} catch (SchedulerException e) {
			return false;
		}
	}

	@Override
	public boolean resumeJob(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean startJobNow(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean sinkron(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		 Scheduler scheduler = schedulerFactoryBean.getScheduler();
	try {
		JobDetail jobDetail = JobBuilder.newJob((Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJob_class().trim()))
	
                 .withIdentity(jobInfo.getJob_name(), jobInfo.getJob_group()).build();
         if (scheduler.checkExists(jobDetail.getKey())) {
        	 pauseJob(jobInfo);
        	 deleteJob(jobInfo);
         };
         
         if(!scheduler.checkExists(jobDetail.getKey())){
        	  Trigger trigger;
        	  jobDetail = scheduleCreator.createJob((Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJob_class()),
                       false, context, jobInfo.getJob_name(), jobInfo.getJob_group(),jobInfo);
               
             
        	  if (jobInfo.getCron_job().equals("Y") && CronExpression.isValidExpression(jobInfo.getCron_expression())) {
                  trigger = scheduleCreator.createCronTrigger(jobInfo.getJob_name(), new Date(),
                          jobInfo.getCron_expression(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
              } else {
                  trigger = scheduleCreator.createSimpleTrigger(jobInfo.getJob_name(), new Date(),
                          jobInfo.getRepeat_time(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
              }
              scheduler.scheduleJob(jobDetail, trigger);
              return true;
         }
	
	} catch (ClassNotFoundException e) {
     	  System.out.println("class is not found..");
     	  logger.error("Class Not Found - {}", jobInfo.getJob_class(), e);
       } catch (SchedulerException e) {
     	  logger.error(e.getMessage(), e);
       }
	
		return false;

	}

	@Override
	public SchedulerFactoryBean getSchedulerBean() {
		// TODO Auto-generated method stub
		return schedulerFactoryBean;
	}
	

}
