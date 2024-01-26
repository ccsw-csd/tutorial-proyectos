package com.ccsw.tutorialbatch.config;


import com.ccsw.tutorialbatch.model.Author;
import com.ccsw.tutorialbatch.processor.AuthorItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AuthorBatchConfiguration {

    @Bean
    public ItemReader<Author> readerAuthor() {
        return new FlatFileItemReaderBuilder<Author>().name("authorItemReader")
                .resource(new ClassPathResource("author-list.csv"))
                .delimited()
                .names(new String[] { "name", "nationality" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Author.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<Author, Author> processorAuthor() {

        return new AuthorItemProcessor();
    }

    @Bean
    public ItemWriter<Author> writerAuthor() {
        return  new FlatFileItemWriterBuilder<Author>().name("writerAuthor")
                .resource(new FileSystemResource("target/test-outputs/author-output.txt"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    @Bean
    public Step step1Author(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<Author> readerAuthor, ItemProcessor<Author, Author> processorAuthor, ItemWriter<Author> writerAuthor) {
        return new StepBuilder("step1Author", jobRepository)
                .<Author, Author> chunk(10, transactionManager)
                .reader(readerAuthor)
                .processor(processorAuthor)
                .writer(writerAuthor)
                .build();
    }

    @Bean
    public Job jobAuthor(JobRepository jobRepository, Step step1Author) {
        return new JobBuilder("jobAuthor", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1Author)
                .end()
                .build();
    }

}