package org.mybatis.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.example.domain.TUserRole;
import org.mybatis.example.example.TUserRoleExample;

@Mapper
public interface TUserRoleMapper {
    long countByExample(TUserRoleExample example);

    int deleteByExample(TUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserRole record);

    int insertSelective(TUserRole record);

    List<TUserRole> selectByExample(TUserRoleExample example);

    TUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserRole record, @Param("example") TUserRoleExample example);

    int updateByExample(@Param("record") TUserRole record, @Param("example") TUserRoleExample example);

    int updateByPrimaryKeySelective(TUserRole record);

    int updateByPrimaryKey(TUserRole record);
}