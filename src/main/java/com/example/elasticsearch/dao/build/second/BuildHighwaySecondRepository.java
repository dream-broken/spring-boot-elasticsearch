package com.example.elasticsearch.dao.build.second;

import com.example.elasticsearch.entity.build.second.BuildHighwaySecond;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BuildHighwaySecondRepository extends ElasticsearchRepository<BuildHighwaySecond, Long> {

    List<BuildHighwaySecond> findByTitleAndAnalysis(String title, String analysis, Pageable pageable);

    List<BuildHighwaySecond> findByTitleOrAnalysis(String title, String analysis, Pageable pageable);

    List<BuildHighwaySecond> findByTitleNot(String title, Pageable pageable);


}
