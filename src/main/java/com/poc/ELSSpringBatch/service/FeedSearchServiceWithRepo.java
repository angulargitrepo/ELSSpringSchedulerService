package com.poc.ELSSpringBatch.service;

import com.poc.ELSSpringBatch.model.Feed;
import com.poc.ELSSpringBatch.repositories.FeedRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FeedSearchServiceWithRepo {
    @Autowired
    private FeedRepository feedRepository;

    private static final String FEED_INDEX = "feed";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void createFeedBulk(final List<Feed> feeds) {
        feedRepository.saveAll(feeds);
    }

    public void createFeed(final Feed feeds) {
        feedRepository.save(feeds);
    }

    public List<Feed> customQueryFilter(String name) {
        QueryBuilder queryBuilder  = new BoolQueryBuilder()
                .minimumShouldMatch(1)
                .should(QueryBuilders.termQuery("name", name))
                .should(QueryBuilders.termQuery("commentCount", "10"));

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Feed> feedSearchHits = elasticsearchOperations.search(searchQuery, Feed.class, IndexCoordinates.of(FEED_INDEX));
        log.info("customQueryFilter search Hit count:: "+feedSearchHits.getTotalHits());
        List<Feed> feedList = new ArrayList<>();
        feedSearchHits.forEach(feed -> {
            feedList.add(feed.getContent());
        });
        return feedList;
    }
}
