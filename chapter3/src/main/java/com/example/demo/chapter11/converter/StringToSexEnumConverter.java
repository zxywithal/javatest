package com.example.demo.chapter11.converter;

import com.example.demo.chapter10.enums.Sex;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiaoyun3 on 2018/11/15.
 * 格式转化器 spring会根据处理器的参数格式和输入的格式自动匹配converter
 */
@Component
public class StringToSexEnumConverter implements Converter<String,Sex> {
    @Nullable
    @Override
    public Sex convert(String source) {
        Sex sex = Sex.valueOf(source);
        return sex;
    }
}
