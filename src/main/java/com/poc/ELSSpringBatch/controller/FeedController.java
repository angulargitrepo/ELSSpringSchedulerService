package com.poc.ELSSpringBatch.controller;

import com.poc.ELSSpringBatch.model.Feed;
import com.poc.ELSSpringBatch.repositories.FeedRepository;
import com.poc.ELSSpringBatch.service.FeedSearchServiceWithRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/feed")
@Slf4j
public class FeedController {

    @Autowired
    private FeedSearchServiceWithRepo feedSearchServiceWithRepo;
    @Autowired
    private FeedRepository feedRepository;

    @GetMapping("/searchById/{id}")
    public ResponseEntity<Feed> searchById(@PathVariable String id) {
        log.info("called Get id method");
        Optional<Feed> feedObject = feedRepository.findById(id);
        return new ResponseEntity(feedObject,HttpStatus.OK);
    }

    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity<List<Feed>> searchByTitle(@PathVariable String title) {
        log.info("called Get By title method");
        List<Feed> feedObject = feedRepository.findByTitle(title);
        return new ResponseEntity(feedObject,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Feed> save(@RequestBody Feed feed) {
        log.info("called save method");
        Feed feedObject = feedRepository.save(feed);
        return new ResponseEntity(feedObject,HttpStatus.OK);
    }

    @GetMapping("/searchCustom/{name}")
    public ResponseEntity<List<Feed>> searchCustom(@PathVariable String name) {
        log.info("called Get By searchCustom method");
        List<Feed>  feedList = feedSearchServiceWithRepo.customQueryFilter(name);
        return new ResponseEntity(feedList,HttpStatus.OK);
    }
}
