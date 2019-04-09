package com.example.elasticsearch.entity;

import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Lazy(false)
@Table(name = "subject_exam_test")
public class SubjectExamTest implements Serializable {

    @Column(name = "id", updatable = false, insertable = false)
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "exam_type_id", updatable = false, insertable = false)
    private Integer examTypeId;

    @Column(name = "title", updatable = false, insertable = false)
    private String title;

    @Column(name = "total_grade", updatable = false, insertable = false)
    private Integer totalGrade;

    @Column(name = "duration", updatable = false, insertable = false)
    private Integer duration;
}
