package com.example.elasticsearch.dao.build.second;

import com.example.elasticsearch.entity.build.second.BuildHighwaySecond;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BuildHighwaySecondRepository extends ElasticsearchRepository<BuildHighwaySecond, Long> {
}
