package com.example.elasticsearch.dao;

import com.example.elasticsearch.entity.SubjectTopic;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTopicRepository extends JpaBaseRepository<SubjectTopic, Integer> {
}
