package com.hhub.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hhub.service.BlogPostService;

@Component
public class ScheduledTasks {
	
	@Autowired
	private BlogPostService blogPostService;
	
	//Fires at 12 PM every day
	@Scheduled(cron = "0 0 12 * * ?")
    public void archiveBlogPost() {
    	
    	System.out.println("---- archiveBlogPost task running --");
    	blogPostService.archiveBlogPost();
    	
    }
}
