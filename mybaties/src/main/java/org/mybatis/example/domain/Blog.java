package org.mybatis.example.domain;

import lombok.Data;
import lombok.ToString;

/**
 * Created by zhangxiaoyun3 on 2018/11/1.
 */
//Alias 必须指定别名 Mapper不需要手动指定 默认是类的名称(Blog)
//Alias 和 mapper都可以不要，如果都没用那类的默认别名是类首字母小写（blog）
//@Alias("Blog")
//@Mapper
@Data
@ToString
public class Blog {
    private int id;
    private String content;
}
