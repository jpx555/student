package com.jxrj.Student.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "user_info")
public class UserInfoCondition {

    private Integer id;
    private String ids;
    private String accountnum;
    private Integer age;
    private String birthday;
    private String email;
    @TableField(value = "authority")
    private String authority;


}
