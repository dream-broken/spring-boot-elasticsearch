package com.example.elasticsearch.controller.highway;

import com.example.elasticsearch.dao.highway.HighwaySecondRepository;
import com.example.elasticsearch.entity.highway.HighwaySecond;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class HighwaySecondController {

    @Resource
    private HighwaySecondRepository highwaySecondRepository;

    @GetMapping("/all/{page}/{size}")
    public List<HighwaySecond> all(@PathVariable int page, @PathVariable int size) {
        return highwaySecondRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @PostMapping("/save")
    public HighwaySecond save(@RequestBody HighwaySecond highwaySecond) {
        return highwaySecondRepository.save(highwaySecond);
    }


}
