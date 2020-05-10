package com.app.model;

public class SchedulerNotification {

		private Long notification_id;
		private Long notification_type_id;
		private String notification_type;
		private String params;
		private String fromemail;
		private String toemail;
		private String ccemail;
		private String subject_email;
		private String message;
		private int flag_active;
		public Long getNotification_id() {
			return notification_id;
		}
		public void setNotification_id(Long notification_id) {
			this.notification_id = notification_id;
		}
		public Long getNotification_type_id() {
			return notification_type_id;
		}
		public void setNotification_type_id(Long notification_type_id) {
			this.notification_type_id = notification_type_id;
		}
		public String getNotification_type() {
			return notification_type;
		}
		public void setNotification_type(String notification_type) {
			this.notification_type = notification_type;
		}
		public String getParams() {
			return params;
		}
		public void setParams(String params) {
			this.params = params;
		}
		public String getFromemail() {
			return fromemail;
		}
		public void setFromemail(String fromemail) {
			this.fromemail = fromemail;
		}
		public String getToemail() {
			return toemail;
		}
		public void setToemail(String toemail) {
			this.toemail = toemail;
		}
		public String getCcemail() {
			return ccemail;
		}
		public void setCcemail(String ccemail) {
			this.ccemail = ccemail;
		}
		public String getSubject_email() {
			return subject_email;
		}
		public void setSubject_email(String subject_email) {
			this.subject_email = subject_email;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public int getFlag_active() {
			return flag_active;
		}
		public void setFlag_active(int flag_active) {
			this.flag_active = flag_active;
		}
		
}
