package com.health.medfinder.config;

import com.health.medfinder.elastic.Medication;
import com.health.medfinder.elastic.service.MedicationIndexingService;
import com.health.medfinder.entity.MedicationEntity;
import com.health.medfinder.repository.MedicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Configuration
@EnableBatchProcessing
@Slf4j
public class MedicationBatchConfig {

    @Autowired
    private MedicationIndexingService medicationIndexingService;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager; // Add this for transactions

    @Autowired
    private EntityManagerFactory entityManagerFactory; // Add this for JPA

    @Bean
    public JpaPagingItemReader<MedicationEntity> medicationItemReader() {
        JpaPagingItemReader<MedicationEntity> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory); // Set the EntityManagerFactory
        reader.setQueryString("SELECT m FROM MedicationEntity m");
        reader.setPageSize(10);
        reader.setSaveState(false); // Disable state persistence
        return reader;
    }

    @Bean
    public ItemProcessor<MedicationEntity, Medication> medicationItemProcessor() {
        return medicationEntity -> {
            Medication medicationElastic = new Medication();
            medicationElastic.setMedicationCode(medicationEntity.getMedicationCode());
            medicationElastic.setMedicationName(medicationEntity.getMedicationName());
            return medicationElastic;
        };
    }

    @Bean
    public ItemWriter<Medication> customMedicationItemWriter() {
        return new ItemWriter<Medication>() {
            @Override
            public void write(List<? extends Medication> medications) throws Exception {
                // Call the indexing service to handle upsert logic and document existence checks.
                for (Medication medication : medications) {
                    medicationIndexingService.indexMedication(medication);
                }

                // Save the medications using the repository.
                medicationRepository.saveAll(medications);
            }
        };
    }

//    @Bean
//    public RepositoryItemWriter<Medication> medicationItemWriter() {
//        RepositoryItemWriter<Medication> writer = new RepositoryItemWriter<>();
//        writer.setRepository(medicationRepository);
//        writer.setMethodName("save");
//
//        return writer;
//    }

    @Bean
    public Job importMedicationsJob() {
        log.info("Test logic called");
        return jobBuilderFactory.get("importMedicationsJob")
                .start(importMedicationsStep())
                .build();
    }

    @Bean
    public Step importMedicationsStep() {
        return stepBuilderFactory.get("importMedicationsStep")
                .<MedicationEntity, Medication>chunk(10)
                .reader(medicationItemReader())
                .processor(medicationItemProcessor())
                .writer(customMedicationItemWriter())
                .transactionManager(transactionManager)
                .allowStartIfComplete(true)// Set the transaction manager
                .build();
    }
}
