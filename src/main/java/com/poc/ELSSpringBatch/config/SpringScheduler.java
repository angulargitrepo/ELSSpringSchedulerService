package com.poc.ELSSpringBatch.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.ELSSpringBatch.model.Feed;
import com.poc.ELSSpringBatch.repositories.FeedRepository;
import com.poc.ELSSpringBatch.service.FeedSearchServiceWithRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableScheduling
@Slf4j
public class SpringScheduler {

    @Autowired
    FeedSearchServiceWithRepo feedSearchServiceWithRepo;

    @Value("classpath:json_data_file.json")
    Resource resourceFile;

    @Scheduled(cron = "${cron.expression}")
    public void scheduleTaskUsingCronExpression() throws IOException {
        log.info("Spring scheduler called....");
        ObjectMapper mapper = new ObjectMapper();
        List<Feed> feedList = mapper.readValue(resourceFile.getURL(),new TypeReference<List<Feed>>(){});
        //reading Json and post to ELS
        feedSearchServiceWithRepo.createFeedBulk(feedList);
        log.info("Feed data post to Elastic search successfully,Updated row count: "+feedList.size());
        String.format("Last Scheduler run at: %1$TH:%1$TM:%1$TS", System.currentTimeMillis());
    }
}
