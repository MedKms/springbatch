package com.example.springbatchpoc.jobs.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.File;

public class FileDeletingTasklet implements Tasklet, InitializingBean {

    private Resource directory;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        File dir=directory.getFile();
        Assert.state(dir.isDirectory());
        File[] files=dir.listFiles();
        for (int i=0;i< files.length;i++){
            boolean deleted=files[i].delete();
            if(!deleted){
                throw new UnexpectedJobExecutionException("Could not delete file"+files[i].getPath());
            }
        }
        return RepeatStatus.FINISHED;
    }

    public void setDirectory(Resource directory) {
        this.directory = directory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(directory,"directory must be set");
    }
}
