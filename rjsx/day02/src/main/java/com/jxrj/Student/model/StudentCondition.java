package com.jxrj.Student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentCondition {
    private Integer id;
    private String ids;
    private String stuno;
    private String grade;
    private String name;
}
