package com.app.quartz.engine.jobs;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.app.model.SchedulerJobInfo;
import com.app.services.JobServices;

@DisallowConcurrentExecution
public class GalleryJob extends QuartzJobBean {
	@Autowired
	private JobServices jobServices;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		int size = jobServices.selectAll().size();
		JobDataMap map = context.getMergedJobDataMap();
		String info = (String)map.get("jobId");
		SchedulerJobInfo jobInfo = jobServices.selectJobById(Long.parseLong(info));
		
		System.out.println(jobInfo.getAction_type());
		
		context.setResult("patar");
		System.out.println("info id"+info);
		System.out.println("size:"+size);
		System.out.println("gallery job..."+ new Date());
	}

}
