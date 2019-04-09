package com.example.elasticsearch.entity.build.second;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "build", type = "legal_second")
public class BuildLegalSecond {
}
