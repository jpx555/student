package com.jxrj.converters;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StringToDateConverter implements Converter<String, Date> {
    private List<String> patterns;
    public List<String> getPatterns() {
        return patterns;
    }
    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }

    @Override
    public Date convert(String s) {
        SimpleDateFormat sdf = null;
        for (String pattern : patterns) {
            if (pattern.length() == s.length()){
                sdf = new SimpleDateFormat(pattern);
                break;
            }
        }
        Date parse = null;
        try {
            parse = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
