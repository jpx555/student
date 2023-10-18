package com.jxrj.Student.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "role_info")
public class RoleInfoCondition {

    private Integer id;
    private String ids;
    private String name;
    private Integer stuno;
    private String rolepos;
    private String roledes;


}
