package com.app.services.notification;

import org.springframework.stereotype.Component;

import com.app.model.SchedulerNotification;
@Component("telegramnotification")
public class TelegramNotifcationServices implements NotificationServices {

	@Override
	public String process(SchedulerNotification notification) {
		return null;
		// TODO Auto-generated method stub

	}

}
