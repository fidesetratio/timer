package com.app.dao;

import java.util.List;
import java.util.Map;

import com.app.model.SchedulerJobInfo;
import com.app.model.SchedulerJobInfoLog;

public interface JobDao {
	
	public List<SchedulerJobInfo> selectAll();
	public List<SchedulerJobInfo> selectAllAutomatic();
	public SchedulerJobInfo selectJobById(Long job_id);
	public void updateSinkronJobInfo(Map params);
	public void updateJobInfoTime(Map params);
	
	public void insertJobHistoryLog(SchedulerJobInfoLog schedulerJobInfoLog);
	public void deleteSinkronJobInfo(Long id);
	

}
