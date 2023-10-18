package com.jxrj.Student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseCondition {
    private Integer id;
    private String ids;
    private String courseno;
    private String coursename;
    private String teacher;
}
