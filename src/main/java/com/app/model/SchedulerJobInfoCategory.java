package com.app.model;

public class SchedulerJobInfoCategory {
	
	private Long cat_id;
	private String job_class;
	private String job_label;
	public Long getCat_id() {
		return cat_id;
	}
	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
	}
	public String getJob_class() {
		return job_class;
	}
	public void setJob_class(String job_class) {
		this.job_class = job_class;
	}
	public String getJob_label() {
		return job_label;
	}
	public void setJob_label(String job_label) {
		this.job_label = job_label;
	}

}
