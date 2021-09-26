package com.omkar.mysqltocsv.config;

import com.omkar.mysqltocsv.batch.Processor;
import com.omkar.mysqltocsv.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {


    @Autowired
    private DataSource dataSource;

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Employee> itemReader,
                   ItemProcessor<Employee, Employee> itemProcessor,
                   ItemWriter<Employee> itemWriter
    ) {

        Step step = stepBuilderFactory.get("ETL-DB-load")
                .<Employee, Employee>chunk(1000 )
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Processor itemProcessor(){
        return new Processor();
    }


    @Bean
    public JdbcCursorItemReader<Employee> itemReader() {
        JdbcCursorItemReader<Employee> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT emp_no,birth_date,first_name,last_name,gender,hire_date FROM employees;");
        cursorItemReader.setRowMapper(new EmployeeRowMapper());
        return cursorItemReader;
    }

    @Bean
    public FlatFileItemWriter<Employee> writer(){
        FlatFileItemWriter<Employee> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("src/main/resources/employees.csv"));

        DelimitedLineAggregator<Employee> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Employee>  fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"emp_no", "birth_date", "first_name", "last_name", "gender","hire_date"});
        lineAggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(lineAggregator);
        return writer;
    }
}
