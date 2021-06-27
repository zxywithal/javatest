package com.example.demo.chapter5.service.impl;

import com.example.demo.chapter5.enumeration.SexEnum;
import com.example.demo.chapter5.pojo.User;
import com.example.demo.chapter5.service.JdbcTmplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/10/31.
 */
@Service
public class JdbcTmplUserServiceImpl implements JdbcTmplUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> getUserMapper(){
        return new RowMapper<User>() {
            @Nullable
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setNote(resultSet.getString("note"));
                user.setSexEnum(SexEnum.valueOf(resultSet.getString("sex")));
                return user;
            }
        };
    }

    @Override
    public User getUser(Long id) {
        User user = jdbcTemplate.queryForObject("select * from t_user where id =?", new Object[]{id}, getUserMapper());
        return user;
    }

    @Override
    public List<User> findUsers(String userNmae, String note) {
        String sql = "select * from t_user where user_name like concat('%',?,'%') or note like concat('%',?,'%')";
        Object[] params = new Object[]{userNmae, note};
        List<User> query = jdbcTemplate.query(sql, params, getUserMapper());
        return query;
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user (user_name,sex,note) values (?,?,?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSexEnum().toString(), user.getNote());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update table t_user set user_name=?,sex=?,note=? where id=?";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSexEnum().toString(), user.getNote(), user.getId());
    }

    @Override
    public int delteUser(User user) {
        return jdbcTemplate.update("delete from t_user where id=?", user.getId());
    }
}
