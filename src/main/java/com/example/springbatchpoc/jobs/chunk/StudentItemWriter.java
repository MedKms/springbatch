package com.example.springbatchpoc.jobs.chunk;

import com.example.springbatchpoc.entities.Student;
import com.example.springbatchpoc.repositories.StudentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentItemWriter implements ItemWriter<Student> {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public void write(List<? extends Student> list) throws Exception {
        studentRepository.saveAll(list);
    }
}
