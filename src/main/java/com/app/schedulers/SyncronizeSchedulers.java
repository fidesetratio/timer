package com.app.schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.model.SchedulerJobInfo;
import com.app.services.JobServices;
import com.app.services.SchedulerService;

@Component
public class SyncronizeSchedulers {
	
	@Autowired
	private JobServices jobServices;
	
	@Autowired
	private SchedulerService schedulerServices;
	
	@Scheduled(fixedRate = 2000)
	public void scheduleTaskWithFixedRate() {
		
		List<SchedulerJobInfo> list = jobServices.selectAllAutomatic();
		
		for(SchedulerJobInfo j:list) {
			
			if(j.getAction_type() == 1) { // sinkronisze
				syncronizeJobInfo(j);
			};
			if(j.getAction_type() == 2) { // delete job
				deleteJobInfo(j);
			};
			if(j.getAction_type() == 3) { // stop job
				stopJobInfo(j);
			}
			
			
		}
		
	}
	
	private void stopJobInfo(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		schedulerServices.pauseJob(jobInfo);
		jobServices.updateSinkronJobInfo(0,0,0, jobInfo.getId());
		
			
	}

	public void syncronizeJobInfo(SchedulerJobInfo jobInfo) {
		if(schedulerServices.sinkron(jobInfo)) {
			jobServices.updateSinkronJobInfo(1,0,0, jobInfo.getId());
		}else {
			jobServices.updateSinkronJobInfo(0,0,0, jobInfo.getId());
		}
	
	}
	
	
	public void deleteJobInfo(SchedulerJobInfo jobInfo) {
		schedulerServices.pauseJob(jobInfo);
		schedulerServices.deleteJob(jobInfo);
		jobServices.deleteSinkronJobInfo(jobInfo.getId());
	
	}
	

}
