package com.app.model;

public class SchedulerNotificationHistory {

	private Long history_id;
	private Long notification_id;
	private Long info_id;
	private int error;
	private String message;
	public Long getHistory_id() {
		return history_id;
	}
	public void setHistory_id(Long history_id) {
		this.history_id = history_id;
	}
	public Long getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(Long notification_id) {
		this.notification_id = notification_id;
	}
	public Long getInfo_id() {
		return info_id;
	}
	public void setInfo_id(Long info_id) {
		this.info_id = info_id;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
