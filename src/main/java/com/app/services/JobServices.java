package com.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.JobDao;
import com.app.model.SchedulerJobInfo;
import com.app.model.SchedulerJobInfoLog;

@Service
public class JobServices {
	@Autowired
	private SqlSession sqlSession;
	
	public List<SchedulerJobInfo> selectAll(){
		JobDao dao=sqlSession.getMapper(JobDao.class);
		return ( List<SchedulerJobInfo>)dao.selectAll();
	}
	
	public List<SchedulerJobInfo> selectAllAutomatic(){
		JobDao dao=sqlSession.getMapper(JobDao.class);
		return ( List<SchedulerJobInfo>)dao.selectAllAutomatic();
	}
	
	public void updateSinkronJobInfo(int status_job,int action_type,int automatic, Long id){
		JobDao dao=sqlSession.getMapper(JobDao.class);
		Map params = new HashMap();
		params.put("action_type",action_type);
		params.put("automatic",automatic);
		params.put("id",id);
		params.put("status_job", status_job);
		dao.updateSinkronJobInfo(params);
		
	}
	public void updateJobInfoTime(int status_job,String schedule_time,String next_fire_time,String last_fire_time,String state, Long id){
		JobDao dao=sqlSession.getMapper(JobDao.class);
		Map params = new HashMap();
		params.put("schedule_time",schedule_time);
		params.put("next_fire_time",next_fire_time);
		params.put("last_fire_time",last_fire_time);
		params.put("state",state);
		params.put("id",id);
		params.put("status_job", status_job);
		dao.updateJobInfoTime(params);
		
	}
	
	public SchedulerJobInfo selectJobById(Long job_id) {
		JobDao dao=sqlSession.getMapper(JobDao.class);
		Map params = new HashMap();
		return (SchedulerJobInfo)dao.selectJobById(job_id);
	}
	
	public void insertJobHistoryLog(SchedulerJobInfoLog jobInfoLog) {
		JobDao dao=sqlSession.getMapper(JobDao.class);
		dao.insertJobHistoryLog(jobInfoLog);
		
	}
	
	public void deleteSinkronJobInfo( Long id){
		JobDao dao=sqlSession.getMapper(JobDao.class);
		
		dao.deleteSinkronJobInfo(id);
		
	}
}
