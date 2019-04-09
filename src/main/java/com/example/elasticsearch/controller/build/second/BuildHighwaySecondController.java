package com.example.elasticsearch.controller.build.second;

import com.example.elasticsearch.dao.build.second.BuildHighwaySecondRepository;
import com.example.elasticsearch.entity.build.second.BuildHighwaySecond;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class BuildHighwaySecondController {

    @Resource
    private BuildHighwaySecondRepository buildHighwaySecondRepository;

    @GetMapping("/all_page/{num}/{size}")
    public List<BuildHighwaySecond> allPage(@PathVariable int num, @PathVariable int size) {
        return buildHighwaySecondRepository.findAll(PageRequest.of(num, size)).getContent();
    }

    @GetMapping("/all_page_sort/{num}/{size}/{sort}")
    public List<BuildHighwaySecond> allPageSort(@PathVariable int num, @PathVariable int size, @PathVariable String sort) {
        return buildHighwaySecondRepository.findAll(PageRequest.of(num, size, Sort.by(sort))).getContent();
    }

}
