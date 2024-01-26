package com.ccsw.tutorialbatch.config;

import com.ccsw.tutorialbatch.tasklet.CleanTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CleanBatchConfiguration {

    @Bean
    public Tasklet taskletClean() {
        CleanTasklet tasklet = new CleanTasklet();

        tasklet.setDirectoryResource(new FileSystemResource("target/test-outputs"));

        return tasklet;
    }

    @Bean
    public Step step1Clean(JobRepository jobRepository, PlatformTransactionManager transactionManager, Tasklet taskletClean) {
        return new StepBuilder("step1Clean", jobRepository)
                .tasklet(taskletClean, transactionManager)
                .build();
    }

    @Bean
    public Job jobClean(JobRepository jobRepository, Step step1Clean) {
        return new JobBuilder("jobClean", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1Clean)
                .build();
    }

}