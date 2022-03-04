package com.example.springbatchpoc.jobs.chunk;

import com.example.springbatchpoc.entities.Student;
import org.springframework.batch.item.ItemReader;

import java.io.File;

public interface ReadFile {
    ItemReader<Student> readFile(File file);
}
