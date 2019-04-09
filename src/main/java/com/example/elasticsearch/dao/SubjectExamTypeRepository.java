package com.example.elasticsearch.dao;

import com.example.elasticsearch.entity.SubjectExamType;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectExamTypeRepository extends JpaBaseRepository<SubjectExamType, Integer> {
}
