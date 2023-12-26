package com.health.medfinder.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private final Job job;
    private final JobLauncher jobLauncher;

    @Autowired
    public BatchController(@Qualifier("importMedicationsJob") Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    @GetMapping("/run")
    public String runBatchJob() {
        try {
            // Define Job parameters (if needed)
            JobParameters jobParameters = new JobParameters();

            // Launch the Job
            jobLauncher.run(job, jobParameters);

            return "Batch job started successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error starting the batch job: " + e.getMessage();
        }
    }
}

