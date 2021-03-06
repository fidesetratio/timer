package com.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.app.quartz.engine.component.SchedulerJobFactory;
import com.app.quartz.engine.listener.CustomListeners;

@Configuration
public class SchedulerConfig {
	@Autowired
    private DataSource dataSource;
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
    private QuartzProperties quartzProperties;
	
	@Autowired
	private CustomListeners customerListener;
	
	@Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerJobFactory jobFactory = new SchedulerJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setGlobalTriggerListeners(customerListener);
       
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(properties);
        factory.setJobFactory(jobFactory);
        return factory;
    }
	/*
	 * @Bean public CustomListeners customerListener() { return new
	 * CustomListeners("sinarmasmsig"); }
	 */
}
