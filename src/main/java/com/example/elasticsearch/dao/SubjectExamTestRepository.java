package com.example.elasticsearch.dao;

import com.example.elasticsearch.entity.SubjectExamTest;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectExamTestRepository extends JpaBaseRepository<SubjectExamTest, Integer> {
}
