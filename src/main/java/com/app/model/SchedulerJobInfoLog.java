package com.app.model;

public class SchedulerJobInfoLog {
	


	private Long info_id;
	private Long id;

    private String job_name;

    private String job_group;

    private String job_class;

    private String cron_expression;

    private Long repeat_time;

    private String cron_job;
    
    private int action_type;
    
    private int automatic;
    
    private String url;
    
    public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	private String params;
    
  

	private String job_result;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public String getJob_group() {
		return job_group;
	}

	public void setJob_group(String job_group) {
		this.job_group = job_group;
	}

	public String getJob_class() {
		return job_class;
	}

	public void setJob_class(String job_class) {
		this.job_class = job_class;
	}

	public String getCron_expression() {
		return cron_expression;
	}

	public void setCron_expression(String cron_expression) {
		this.cron_expression = cron_expression;
	}

	public Long getRepeat_time() {
		return repeat_time;
	}

	public void setRepeat_time(Long repeat_time) {
		this.repeat_time = repeat_time;
	}

	public String getCron_job() {
		return cron_job;
	}

	public void setCron_job(String cron_job) {
		this.cron_job = cron_job;
	}

	public int getAction_type() {
		return action_type;
	}

	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}

	public int getAutomatic() {
		return automatic;
	}

	public void setAutomatic(int automatic) {
		this.automatic = automatic;
	}
	  public String getJob_result() {
			return job_result;
		}

		public void setJob_result(String job_result) {
			this.job_result = job_result;
		}
		
		public static SchedulerJobInfoLog parse(SchedulerJobInfo jobInfo) {
			SchedulerJobInfoLog schedulerJobInfo = new SchedulerJobInfoLog();
			schedulerJobInfo.setId(jobInfo.getId());
			schedulerJobInfo.setAction_type(jobInfo.getAction_type());
			schedulerJobInfo.setAutomatic(jobInfo.getAutomatic());
			schedulerJobInfo.setCron_expression(jobInfo.getCron_expression());
			schedulerJobInfo.setCron_job(jobInfo.getCron_job());
			schedulerJobInfo.setJob_class(jobInfo.getJob_class());
			schedulerJobInfo.setJob_group(jobInfo.getJob_group());
			schedulerJobInfo.setJob_result("");
			schedulerJobInfo.setJob_name(jobInfo.getJob_name());
			schedulerJobInfo.setRepeat_time(jobInfo.getRepeat_time());
			schedulerJobInfo.setParams(jobInfo.getParams());
			schedulerJobInfo.setUrl(jobInfo.getUrl());
			return schedulerJobInfo;
			
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		
		public Long getInfo_id() {
			return info_id;
		}

		public void setInfo_id(Long info_id) {
			this.info_id = info_id;
		}
}
