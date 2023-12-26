package com.health.medfinder.elastic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.medfinder.elastic.Medication;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import java.io.IOException;

@Service
public class MedicationIndexingService {
    @Autowired
    private RestHighLevelClient elasticsearchClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void indexMedication(Medication medication) throws IOException {
        String medicationCode = medication.getMedicationCode();

        // Check if a document with the same Medication Code already exists.
        boolean documentExists = elasticsearchClient.exists(
                new GetRequest("medications", medicationCode), RequestOptions.DEFAULT
        );

        // Convert Medication object to JSON string.
        String medicationJson = objectMapper.writeValueAsString(medication);

        // Prepare an IndexRequest to insert or update the document.
        IndexRequest indexRequest = new IndexRequest("medications")
                .id(medicationCode)
                .source(medicationJson, XContentType.JSON);

        if (documentExists) {
            // Update the existing document.
            UpdateRequest updateRequest = new UpdateRequest("medications", medicationCode)
                    .doc(medicationJson, XContentType.JSON)
                    .upsert(indexRequest);

            elasticsearchClient.update(updateRequest, RequestOptions.DEFAULT);
        } else {
            // Insert a new document.
            elasticsearchClient.index(indexRequest, RequestOptions.DEFAULT);
        }
    }
}


