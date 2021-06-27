package org.mybatis.example.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhangxiaoyun3 on 2018/12/17.
 */
@MappedJdbcTypes(JdbcType.INTEGER)
public class AvailableTypehandler implements TypeHandler<String> {

    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter);
    }

    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName).equals("1")?"可用":"不可用";
    }

    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex).equals("1")?"可用":"不可用";
    }

    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }
}
