package com.example.springbatchpoc.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job myJob;
    @Bean
    public void runJob() throws Exception{
        //JobParameters jobParameters=new JobParameters();
        JobParameters jobParameters=new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(myJob,jobParameters);
    }
}
