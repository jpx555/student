package com.jxrj.Student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private Integer id;
    private String stuno;
    private String grade;
    private String name;
    private String gender;
    private Integer age;
    private Integer score;
}
