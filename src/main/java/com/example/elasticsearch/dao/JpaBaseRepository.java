package com.example.elasticsearch.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaBaseRepository<T, ID> extends JpaRepository<T, ID>, CrudRepository<T, ID>, JpaSpecificationExecutor<T> {
}
