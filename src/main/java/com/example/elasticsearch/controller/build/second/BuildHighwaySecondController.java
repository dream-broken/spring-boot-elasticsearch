package com.example.elasticsearch.controller.build.second;

import com.example.elasticsearch.dao.build.second.BuildHighwaySecondRepository;
import com.example.elasticsearch.entity.build.second.BuildHighwaySecond;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.range.InternalRange;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/count_all")
    public Map<String, Object> countAll() {
        return ImmutableMap.of("count", buildHighwaySecondRepository.count());
    }

    @GetMapping("/group_by")
    public List<Map<String, Object>> groupBy() {
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("group_by_grade").field("grade.keyword")
                .subAggregation(AggregationBuilders.avg("average_id").field("id"));
        NativeSearchQuery query = new NativeSearchQueryBuilder().addAggregation(termsAggregationBuilder).build();
        AggregatedPage<BuildHighwaySecond> aggregatedPage = (AggregatedPage<BuildHighwaySecond>) buildHighwaySecondRepository.search(query);
        Terms agg = (Terms) aggregatedPage.getAggregation("group_by_grade");
        return agg.getBuckets().stream().collect(ArrayList::new, (l, d) -> {
            Map<String, Object> data = new HashMap<>(2);
            data.put("group_by_grade", d.getKeyAsString());
            InternalAvg avg = (InternalAvg) d.getAggregations().asMap().get("average_id");
            data.put("average_id", avg.getValue());
            l.add(data);
        }, (l1, l2) -> l1.addAll(l2));
    }

    @GetMapping("group_by1")
    public List<Map<String, Object>> groupBy1() {
        RangeAggregationBuilder rangeAggregationBuilder = AggregationBuilders.range("group_by_groupId").field("groupId")
                .addRange(0, 1000).addRange(1000, 2000).addRange(2000, 3000).addRange(3000, 4000)
                .subAggregation(
                        AggregationBuilders.terms("group_by_grade").field("grade.keyword")
                                .subAggregation(AggregationBuilders.avg("average_id").field("id"))
                );
        NativeSearchQuery query = new NativeSearchQueryBuilder().addAggregation(rangeAggregationBuilder).build();

        AggregatedPage<BuildHighwaySecond> aggregatedPage = (AggregatedPage<BuildHighwaySecond>) buildHighwaySecondRepository.search(query);
        InternalRange agg = (InternalRange)aggregatedPage.getAggregation("group_by_groupId");

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Object d : agg.getBuckets()) {
            InternalRange.Bucket o = (InternalRange.Bucket)d;
            Map<String, Object> data = new HashMap<>(2);
            data.put("group_by_groupId", o.getKeyAsString());

            Terms groupByGrade = (Terms) o.getAggregations().asMap().get("group_by_grade");
            List<? extends Terms.Bucket> buckets = groupByGrade.getBuckets();
            List<Object> gee = new ArrayList<>();
            for (Terms.Bucket bucket : buckets) {
                Map<String, Object> ndata = new HashMap<>(2);
                ndata.put("group_by_grade", bucket.getKeyAsString());
                InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("average_id");
                ndata.put("average_id", avg.getValue());
                gee.add(ndata);
            }
            data.put("group_by_grades", gee);

            dataList.add(data);
        }

        return dataList;
    }
}
