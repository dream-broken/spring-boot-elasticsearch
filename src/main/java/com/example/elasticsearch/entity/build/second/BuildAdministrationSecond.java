package com.example.elasticsearch.entity.build.second;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "build", type = "administration_second")
public class BuildAdministrationSecond {
}
