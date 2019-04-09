package com.example.elasticsearch.entity;

import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Lazy(false)
@Table(name = "subject_type")
public class SubjectType implements Serializable {

    @Column(name = "id", updatable = false, insertable = false)
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", updatable = false, insertable = false)
    private String type;

    @Column(name = "describe", updatable = false, insertable = false)
    private String describe;

}
