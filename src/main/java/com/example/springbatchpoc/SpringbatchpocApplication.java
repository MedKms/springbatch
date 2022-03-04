package com.example.springbatchpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbatchpocApplication {

    public static void main(String[] args) {

//        Object[] source ;
        System.exit(SpringApplication.exit(SpringApplication.run(SpringbatchpocApplication.class, args)));
    }

}
