package com.example.elasticsearch;

import com.example.elasticsearch.dao.build.second.BuildHighwaySecondRepository;
import com.example.elasticsearch.entity.build.second.BuildHighwaySecond;
import org.junit.Test;

import javax.annotation.Resource;

public class BuildHighwaySecondTest extends ElasticsearchApplicationTests {

    @Resource
    private BuildHighwaySecondRepository buildHighwaySecondRepository;

    @Test
    public void saveTest() {
        BuildHighwaySecond buildHighwaySecond = new BuildHighwaySecond();
        buildHighwaySecond.setId(1);
        buildHighwaySecond.setGroupId(1);
        buildHighwaySecond.setRealTopicId(1);
        buildHighwaySecond.setType(1);
        buildHighwaySecond.setTitle("1");
        buildHighwaySecond.setContent("1");
        buildHighwaySecond.setAnalysis("1");
        buildHighwaySecond.setResult("1");
        buildHighwaySecond.setGrade(1);
        buildHighwaySecondRepository.save(buildHighwaySecond);
    }
}
