package com.ccsw.tutorialbatch.config;


import com.ccsw.tutorialbatch.model.Category;
import com.ccsw.tutorialbatch.processor.CategoryItemProcessor;
import com.ccsw.tutorialbatch.listener.JobCategoryCompletionNotificationListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CategoryBatchConfiguration {

    @Bean
    public ItemReader<Category> readerCategory() {
        return new FlatFileItemReaderBuilder<Category>().name("categoryItemReader")
                .resource(new ClassPathResource("category-list.csv"))
                .delimited()
                .names(new String[] { "name", "type", "characteristics" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Category.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<Category, Category> processorCategory() {

        return new CategoryItemProcessor();
    }

    @Bean
    public ItemWriter<Category> writerCategory(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Category>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO category (name, type, characteristics) VALUES (:name, :type, :characteristics)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step step1Category(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<Category> readerCategory, ItemProcessor<Category, Category> processorCategory, ItemWriter<Category> writerCategory) {
        return new StepBuilder("step1Category", jobRepository)
                .<Category, Category> chunk(10, transactionManager)
                .reader(readerCategory)
                .processor(processorCategory)
                .writer(writerCategory)
                .build();
    }

    @Bean
    public Job jobCategory(JobRepository jobRepository, JobCategoryCompletionNotificationListener listener, Step step1Category) {
        return new JobBuilder("jobCategory", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1Category)
                .end()
                .build();
    }

}