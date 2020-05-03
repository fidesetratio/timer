package com.app.services;

import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.app.model.SchedulerJobInfo;

public interface SchedulerService {
	void startAllSchedulers();

    void scheduleNewJob(SchedulerJobInfo jobInfo);

    void updateScheduleJob(SchedulerJobInfo jobInfo);

    boolean unScheduleJob(String jobName);

    boolean deleteJob(SchedulerJobInfo jobInfo);

    boolean pauseJob(SchedulerJobInfo jobInfo);

    boolean resumeJob(SchedulerJobInfo jobInfo);

    boolean startJobNow(SchedulerJobInfo jobInfo);
    
    boolean sinkron(SchedulerJobInfo jobInfo);
    
    SchedulerFactoryBean getSchedulerBean();
}
