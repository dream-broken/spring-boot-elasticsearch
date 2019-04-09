package com.example.elasticsearch.entity;

import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Data
@Entity
@Lazy(false)
@Table(name = "subject_topic")
public class SubjectTopic {

    @Column(name = "id", updatable = false, insertable = false)
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "exam_type_id", updatable = false, insertable = false)
    private Integer examTypeId;

    @Column(name = "name", updatable = false, insertable = false)
    private String name;

    @Column(name = "parent_id", updatable = false, insertable = false)
    private Integer parentId;

    @Column(name = "sort", updatable = false, insertable = false)
    private Integer sort;
}
