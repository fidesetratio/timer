package com.app.quartz.engine.jobs;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.fasterxml.jackson.core.JsonProcessingException;

@DisallowConcurrentExecution
public class HttpJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
			JobDataMap map = context.getMergedJobDataMap();
			String url = (String)map.get("url");
			String timeout = (String)map.get("param0");
			int timeouttt = 10000;
			if(timeout != null) {
				try {
				timeouttt = Integer.parseInt(timeout);
				}catch(NumberFormatException e) {
					timeouttt = 10000;
				}
			}
			
			
			String result = connectUseGet(url, timeouttt);
	    	context.setResult(result);
		
			
	}

	private String connectUseGet(String url, int timeout)  {
		String json = "";
		try { 
		Connection.Response  connection = Jsoup.connect(url).method(Connection.Method.GET)
		.header("Accept","application/json")
		 .header("Content-Type", "application/json")
		 .timeout(timeout)
		 .ignoreContentType(true) // This is used because Jsoup "approved" content-types parsing is enabled by default by 
		 .execute();
			json = connection.body();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			json = "error JSON Processing";
		}catch( IOException e) {
			json = "error IO Exception";
		}
		
		return json;
	
	}
}
