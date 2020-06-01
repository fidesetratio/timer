package com.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

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
	
	 @Bean(name = "viewResolver")
	    public ViewResolver getViewResolver() {
	        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
	 
	        // TilesView 3
	        viewResolver.setViewClass(TilesView.class);
	 
	        return viewResolver;
	    }
	 
	    @Bean(name = "tilesConfigurer")
	    public TilesConfigurer getTilesConfigurer() {
	        TilesConfigurer tilesConfigurer = new TilesConfigurer();
	 
	        // TilesView 3
	        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
	 
	        return tilesConfigurer;
	    }
	     
	     
	
	/*
	 * @Bean public CustomListeners customerListener() { return new
	 * CustomListeners("sinarmasmsig"); }
	 */
}
