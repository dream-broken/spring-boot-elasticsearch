package com.example.elasticsearch.dao.highway;

import com.example.elasticsearch.entity.highway.Second;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondRepository extends ElasticsearchRepository<Second,Long> {
}
