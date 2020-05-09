package com.app.model;

public class SchedulerJobInfo {
		private Long id;

	    private String job_name;

	    private String job_group;

	    private String job_class;

	    private String cron_expression;

	    private Long repeat_time;

	    private String cron_job;
	    
	    private int action_type;
	    
	    private int automatic;
	    
	    private String params;

	    private String url;
	    
	    public int getStatus_job() {
			return status_job;
		}

		public void setStatus_job(int status_job) {
			this.status_job = status_job;
		}

		private int status_job;
	    
	    
		public String getParams() {
			return params;
		}

		public void setParams(String params) {
			this.params = params;
		}

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

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

}
