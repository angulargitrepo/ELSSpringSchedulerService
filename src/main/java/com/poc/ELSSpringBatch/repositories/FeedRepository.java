package com.poc.ELSSpringBatch.repositories;

import com.poc.ELSSpringBatch.model.Feed;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends ElasticsearchRepository<Feed, String> {
    List<Feed> findByTitle(String title);

    List<Feed> findByTitleContaining(String title);

    List<Feed> findByTitleAndDescription
            (String title, String description);
}
