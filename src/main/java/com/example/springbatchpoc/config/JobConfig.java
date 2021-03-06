package com.example.springbatchpoc.config;

import com.example.springbatchpoc.jobs.chunk.ReadFile;
import com.example.springbatchpoc.jobs.chunk.StudentItemProcessor;
import com.example.springbatchpoc.jobs.chunk.StudentItemWriter;
import com.example.springbatchpoc.jobs.tasklet.FileDeletingTasklet;
import com.example.springbatchpoc.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;


@Slf4j
@Configuration
@EnableBatchProcessing
public class JobConfig {



    private final ReadFile readFile;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public JobConfig(ReadFile readFile, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.readFile = readFile;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }




    // ******** Steps *******


    /**
     * this step reading Students From csv file and Writing into Mysql db
     * @return
     */
    @Bean
    public Step myStepChunk(){
        return stepBuilderFactory.get("myStep")
                .<Student,Student>chunk(10)
                .reader(itemReader())
                .processor(ItemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ItemProcessor<Student,Student> ItemProcessor(){
        return new StudentItemProcessor();
    }
    @Bean
    public ItemWriter<Student> itemWriter(){
        return new StudentItemWriter();
    }
    @Bean
    public ItemReader<Student> itemReader() {
        return readFile.readFile();
    }
    // ******** Step Tasklet deleting files in Directory *******
    @Bean
    public Step deleteFilesInDir(){
        return stepBuilderFactory.get("deletFilesInDir")
                .tasklet(fileDelitingTasklet())
                .build();
    }

    // ***** Tasklet for Job treatment File

    /**
     * Tasklet to delete files
     * @return
     */
     @Bean
    public Tasklet fileDelitingTasklet() {
        FileDeletingTasklet tasklet= new FileDeletingTasklet();
        tasklet.setDirectory(new FileSystemResource("C:\\Users\\delle\\Documents\\FilesToDelete"));
        return tasklet;
    }


    // ** Job ....

    /**
     *
     * @return
     */
    @Bean
    public Job myJob(){
        return jobBuilderFactory.get("myJob")
                .start(myStepChunk())
                .next(deleteFilesInDir())
                .build();
    }








}
