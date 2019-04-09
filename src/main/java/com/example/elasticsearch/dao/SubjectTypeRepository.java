package com.example.elasticsearch.dao;

import com.example.elasticsearch.entity.SubjectType;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTypeRepository extends JpaBaseRepository<SubjectType, Integer> {
}
