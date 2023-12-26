package com.health.medfinder.elastic;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.Data;

@Data
@Document(indexName = "medications")
public class Medication {
    @Id
    private String id;
    private String medicationName;
    private String medicationCode;
    private Integer age;
}

