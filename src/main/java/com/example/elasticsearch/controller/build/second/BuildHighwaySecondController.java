package com.example.elasticsearch.controller.build.second;

import com.example.elasticsearch.dao.build.second.BuildHighwaySecondRepository;
import com.example.elasticsearch.entity.build.second.BuildHighwaySecond;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class BuildHighwaySecondController {

    @Resource
    private BuildHighwaySecondRepository buildHighwaySecondRepository;

    @GetMapping("/all")
    public List<BuildHighwaySecond> all() {
        return Lists.newArrayList(buildHighwaySecondRepository.findAll());
    }

    @GetMapping("/all_page/{num}/{size}")
    public List<BuildHighwaySecond> allPage(@PathVariable int num, @PathVariable int size) {
        return buildHighwaySecondRepository.findAll(PageRequest.of(num, size)).getContent();
    }

    @GetMapping("/all_page_sort/{num}/{size}/{sort}")
    public List<BuildHighwaySecond> allPageSort(@PathVariable int num, @PathVariable int size, @PathVariable String sort) {
        return buildHighwaySecondRepository.findAll(PageRequest.of(num, size, Sort.by(sort))).getContent();
    }

    @GetMapping("/match/{title}")
    public List<BuildHighwaySecond> match(@PathVariable String title) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("title", title));
        return buildHighwaySecondRepository.search(nativeSearchQueryBuilder.build().getQuery(), PageRequest.of(0, 5)).getContent();
    }

    @GetMapping("/match_phrase/{title}")
    public List<BuildHighwaySecond> matchPhrase(@PathVariable String title) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchPhraseQuery("title", title));
        return buildHighwaySecondRepository.search(nativeSearchQueryBuilder.build().getQuery(), PageRequest.of(0, 5)).getContent();
    }

    @GetMapping("/like_title_and_analysis1/{title}/{analysis}")
    public List<BuildHighwaySecond> likeTitleAndAnalysis1(@PathVariable String title, @PathVariable String analysis) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("title", title))
                .must(QueryBuilders.matchQuery("analysis", analysis));
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        return buildHighwaySecondRepository.search(nativeSearchQueryBuilder.build().getQuery(), PageRequest.of(0, 5)).getContent();
    }

    @GetMapping("/like_title_and_analysis2/{title}/{analysis}")
    public List<BuildHighwaySecond> likeTitleAndAnalysis2(@PathVariable String title, @PathVariable String analysis) {
        return buildHighwaySecondRepository.findByTitleAndAnalysis(title, analysis, PageRequest.of(0, 5));
    }

    @GetMapping("/like_title_or_analysis1/{title}/{analysis}")
    public List<BuildHighwaySecond> likeTitleOrAnalysis1(@PathVariable String title, @PathVariable String analysis) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", title))
                .should(QueryBuilders.matchQuery("analysis", analysis));
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        return buildHighwaySecondRepository.search(nativeSearchQueryBuilder.build().getQuery(), PageRequest.of(0, 5)).getContent();
    }

    @GetMapping("/like_title_or_analysis2/{title}/{analysis}")
    public List<BuildHighwaySecond> likeTitleOrAnalysis2(@PathVariable String title, @PathVariable String analysis) {
        return buildHighwaySecondRepository.findByTitleOrAnalysis(title, analysis, PageRequest.of(0, 5));
    }

    @GetMapping("/like_not_title1/{title}")
    public List<BuildHighwaySecond> likeNotTitle1(@PathVariable String title) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .mustNot(QueryBuilders.matchQuery("title", title));
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        return buildHighwaySecondRepository.search(nativeSearchQueryBuilder.build().getQuery(), PageRequest.of(0, 5)).getContent();
    }

    @GetMapping("/like_not_title2/{title}")
    public List<BuildHighwaySecond> likeNotTitle2(@PathVariable String title) {
        return buildHighwaySecondRepository.findByTitleNot(title, PageRequest.of(0, 5));
    }

    @GetMapping("/between1/{start}/{end}")
    public List<BuildHighwaySecond> between1(@PathVariable long start, @PathVariable long end) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.rangeQuery("id").gte(start).lte(end));
        QueryBuilder query = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build().getQuery();
        return buildHighwaySecondRepository.search(query, PageRequest.of(0, 5)).getContent();
    }
}
