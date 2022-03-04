package com.example.springbatchpoc.repositories;

import com.example.springbatchpoc.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
