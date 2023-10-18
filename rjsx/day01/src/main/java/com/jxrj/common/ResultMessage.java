package com.jxrj.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultMessage {

    private String status; // 200,400,500
    private String msg;
    private boolean flag;
    private Object resultData;

}
