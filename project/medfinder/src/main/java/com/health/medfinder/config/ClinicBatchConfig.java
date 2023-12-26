package com.health.medfinder.config;

import com.health.medfinder.writer.ClinicItemWriter;
import com.health.medfinder.elastic.Clinic;
import com.health.medfinder.entity.ClinicEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class ClinicBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ClinicItemWriter clinicItemWriter;

    @Bean
    public Job importClinicsJob() {
        return jobBuilderFactory.get("importClinicsJob")
                .start(step1())
                .build();
    }

    @Bean
    public JpaPagingItemReader<ClinicEntity> clinicItemReader() {
        JpaPagingItemReader<ClinicEntity> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory); // Set the EntityManagerFactory
        reader.setQueryString("SELECT c FROM ClinicEntity c");
        reader.setPageSize(10);
        reader.setSaveState(false); // Disable state persistence
        return reader;
    }

    @Bean
    public ItemProcessor<ClinicEntity, Clinic> clinicItemProcessor() {
        return clinicEntity -> {
            Clinic clinic = new Clinic();
            clinic.setId(clinicEntity.getId().toString());
            clinic.setName(clinicEntity.getName());
            clinic.setLatitude(clinicEntity.getLatitude());
            clinic.setLongitude(clinicEntity.getLongitude());
            // Map other fields as needed
            return clinic;
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<ClinicEntity, Clinic>chunk(10)
                .reader(clinicItemReader())
                .processor(clinicItemProcessor()) // Optional: Perform transformations or validations
                .writer(clinicItemWriter) // Use the custom writer to index clinics with geo-points
                .transactionManager(new JpaTransactionManager(entityManagerFactory))
                .allowStartIfComplete(true)// Set the transaction manager
                .build();
    }
}
