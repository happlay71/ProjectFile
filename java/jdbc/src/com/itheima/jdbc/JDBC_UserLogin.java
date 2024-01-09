package com.itheima.jdbc;

import com.itheima.pojo.Account;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// 用户注册
public class JDBC_UserLogin {
    @Test
    public void testResultSet() throws Exception {
        // 获取链接
        String url = "jdbc:mysql:///test";
        String username = "root";
        String password = "547118";
        Connection conn =  DriverManager.getConnection(url, username, password);

        // 接受用户输入，用户名和密码
        String name = "zhangsan";
        String pwd = "' or '1' = '1";  // SQL注入

        String sql = "select * from tb_user where username = '"+ name +"' and '"+ pwd +"'";

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登录失败");
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}
