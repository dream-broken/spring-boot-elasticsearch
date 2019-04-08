package com.example.elasticsearch.entity.highway;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "highway", type = "second")
public class HighwaySecond {

    @Id
    private long id;

    /*@Field(type = FieldType.Long)
    private long stair;

    @Field(type = FieldType.Long)
    private long second;

    @Field(type = FieldType.Long)
    private long three;

    @Field(type = FieldType.Keyword)
    private int type;*/

    private String title;

    private String options;

    /*private String analysis;*/

    private String answer;

    /*@Field(type = FieldType.Long)
    private long sort;*/
}
