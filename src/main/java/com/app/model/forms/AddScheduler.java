package com.app.model.forms;

import java.util.Arrays;
import java.util.List;

public class AddScheduler {

		private String jobName;
		
		private Long group_id;
		
		private String typeOfJob;
		
		private String expression;
		
		private Long jobCategoryId;
		
		private String url;
		
		private String param0;
		
		private List<String> typeOfJobs;
		
		private String param1;
		
		public AddScheduler() {
			this.typeOfJobs = Arrays.asList("CRON","ONCE");
		}

		public String getJobName() {
			return jobName;
		}

		public void setJobName(String jobName) {
			this.jobName = jobName;
		}

		public Long getGroup_id() {
			return group_id;
		}

		public void setGroup_id(Long group_id) {
			this.group_id = group_id;
		}

		public String getTypeOfJob() {
			return typeOfJob;
		}

		public void setTypeOfJob(String typeOfJob) {
			this.typeOfJob = typeOfJob;
		}

		public String getExpression() {
			return expression;
		}

		public void setExpression(String expression) {
			this.expression = expression;
		}

		public Long getJobCategoryId() {
			return jobCategoryId;
		}

		public void setJobCategoryId(Long jobCategoryId) {
			this.jobCategoryId = jobCategoryId;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getParam0() {
			return param0;
		}

		public void setParam0(String param0) {
			this.param0 = param0;
		}

		public String getParam1() {
			return param1;
		}

		public void setParam1(String param1) {
			this.param1 = param1;
		}

		public List<String> getTypeOfJobs() {
			return typeOfJobs;
		}

		public void setTypeOfJobs(List<String> typeOfJobs) {
			this.typeOfJobs = typeOfJobs;
		}
		
}
