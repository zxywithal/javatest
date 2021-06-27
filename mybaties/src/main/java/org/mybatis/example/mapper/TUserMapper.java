package org.mybatis.example.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.example.domain.TUser;

import java.util.List;

@Mapper
public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    PageList<TUser> selectByName(String userName, PageBounds pageBounds);

    int updateUser(TUser record);

    int inserBatch(List<TUser> record);

    TUser selectById(Integer id);
}