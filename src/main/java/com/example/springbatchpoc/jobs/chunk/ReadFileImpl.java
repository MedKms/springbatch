package com.example.springbatchpoc.jobs.chunk;

import com.example.springbatchpoc.entities.Student;
import org.springframework.batch.item.ItemReader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
@Component
public class ReadFileImpl implements ReadFile{


    @Bean(name="namedFile")
    public File file(@Value("${inputFile}") String path) {
         return new File(path);

    }
    @Override
    public ItemReader<Student> readFile(File file) {
        FlatFileItemReader<Student> reader = new FlatFileItemReader<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        String[] tokens = {"id", "firstName", "lastName", "email", "age"};

        reader.setLinesToSkip(1);
        reader.setResource(new FileSystemResource(file));

        tokenizer.setNames(tokens);
        tokenizer.setStrict(false);
        tokenizer.setDelimiter(",");

        DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();
        BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Student.class);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);
        return reader;
    }


}
