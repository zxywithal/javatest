package com.example.demo.chapter6.service.impl;

import com.example.demo.chapter6.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zhangxiaoyun3 on 2018/11/5.
 */
@Service
public class JdbcServiceImpl implements JdbcService{

    @Autowired
    private DataSource dataSource;
    @Override
    public void insertUser(String userName, String sex, String note) {
        Connection connection = null;
        try {
            connection  = dataSource.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into t_user (user_name,sex,note) VALUES (?,?,?)");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, sex);
            preparedStatement.setString(3, note);
            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }finally {
            try {
                if(connection!=null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
