package com.example.springbatchpoc.jobs.chunk;

import com.example.springbatchpoc.entities.Student;
import org.springframework.batch.item.ItemReader;


public interface ReadFile {
    ItemReader<Student> readFile();
}
