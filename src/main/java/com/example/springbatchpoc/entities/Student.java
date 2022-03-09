package com.example.springbatchpoc.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@EqualsAndHashCode
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private double age;
    @Override
    public String toString(){
        return "id: "+id+" firstName: "+firstName+" lastName: "+lastName+" email: "+email+" age: "+age;
    }

}
