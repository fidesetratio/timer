package com.app.services.notification;

import org.springframework.stereotype.Component;

import com.app.model.SchedulerNotification;

@Component("gmailnotification")
public class GmailNotificationServices implements NotificationServices {

	@Override
	public String process(SchedulerNotification notification) {
		// TODO Auto-generated method stub
		
		System.out.println("notification gmail type ini");
		return "";
		
	}



}
