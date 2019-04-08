package com.example.elasticsearch.dao.highway;

import com.example.elasticsearch.entity.highway.HighwaySecond;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighwaySecondRepository extends ElasticsearchRepository<HighwaySecond,Long> {
}
