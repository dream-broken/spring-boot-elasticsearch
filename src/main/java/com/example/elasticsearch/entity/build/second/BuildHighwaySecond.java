package com.example.elasticsearch.entity.build.second;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "build", type = "highway_second")
public class BuildHighwaySecond {

    @Id
    private long id;

    private long groupId;

    private long realTopicId;

    private int type;

    private String title;

    private String content;

    private String analysis;

    private String result;

    private int grade;
}
