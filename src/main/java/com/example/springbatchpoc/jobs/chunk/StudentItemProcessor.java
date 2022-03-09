package com.example.springbatchpoc.jobs.chunk;

import com.example.springbatchpoc.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class StudentItemProcessor implements ItemProcessor<Student,Student> {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public Student process(Student student) throws Exception {
        logger.error("ERROR");
        return student;
    }
}
