package com.itheima.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        // 注册驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");

        // 获取链接
        String url = "jdbc:mysql:///test";
        String username = "root";
        String password = "547118";
        Connection conn =  DriverManager.getConnection(url, username, password);

        // 定义sql
        String sql1 = "update user set money = 3000 where id = 1";
        String sql2 = "update user set money = 3000 where id = 2";

        // 获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();

        try {
            // 开启事务
            conn.setAutoCommit(false);

            // 执行sql
            int count1 = stmt.executeUpdate(sql1); // 受影响的行数
            System.out.println(count1);

            int count2 = stmt.executeUpdate(sql2);
            // 处理结果
            System.out.println(count2);

            // 提交事务
            conn.commit();
        } catch (Exception e) {
            // 回滚事务
            conn.rollback();
            e.printStackTrace();
        }

        // 释放资源
        stmt.close();
        conn.close();
    }
}
