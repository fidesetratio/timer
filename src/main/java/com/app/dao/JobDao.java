package com.app.dao;

import java.util.List;
import java.util.Map;

import com.app.model.SchedulerJobInfo;
import com.app.model.SchedulerJobInfoLog;
import com.app.model.SchedulerNotification;
import com.app.model.SchedulerNotificationHistory;

public interface JobDao {
	
	public List<SchedulerJobInfo> selectAll();
	public List<SchedulerJobInfo> selectAllAutomatic();
	public SchedulerJobInfo selectJobById(Long job_id);
	public SchedulerNotification selectNotificationId(Long notification_id);
	public void updateSinkronJobInfo(Map params);
	public void updateJobInfoTime(Map params);
	
	public void insertJobHistoryLog(SchedulerJobInfoLog schedulerJobInfoLog);
	public void insertNotificationHistory(SchedulerNotificationHistory schedulerNotificationHistory);
	public void deleteSinkronJobInfo(Long id);
	

}
