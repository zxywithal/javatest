package org.mybatis.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.example.domain.Blog;

/**
 * Created by zhangxiaoyun3 on 2018/11/1.
 */
@Mapper
public interface BlogMapper {
//    @Select("select * from blog where id=#{id}")
    Blog selectBlog(int id);
}
