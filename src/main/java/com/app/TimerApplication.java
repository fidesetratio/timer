package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.app.services.SchedulerService;

@SpringBootApplication
@Configuration
@ImportResource("classpath:boot.xml")
@EnableScheduling
public class TimerApplication implements CommandLineRunner {
	@Autowired
	private SchedulerService schedulerService;	

	public static void main(String[] args) {
        SpringApplication.run(TimerApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		schedulerService.startAllSchedulers();
	}

}
