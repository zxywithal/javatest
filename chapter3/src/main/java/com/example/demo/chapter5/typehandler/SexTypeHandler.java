package com.example.demo.chapter5.typehandler;

import com.example.demo.chapter5.enumeration.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

/**
 * Created by zhangxiaoyun3 on 2018/11/2.
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(SexEnum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.toString());
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String res = rs.getString(columnName);
        return SexEnum.valueOf(res);
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String res = rs.getString(columnIndex);
        return SexEnum.valueOf(res);
    }

    @Override
    public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String res = cs.getString(columnIndex);
        return SexEnum.valueOf(res);
    }
}
