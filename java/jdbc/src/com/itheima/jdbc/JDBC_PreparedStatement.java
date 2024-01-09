package com.itheima.jdbc;

import org.junit.Test;

import java.sql.*;

// 用户注册
public class JDBC_PreparedStatement {
    @Test
    public void testResultSet() throws Exception {
        // 获取链接
        // 开启预编译
        String url = "jdbc:mysql:///test？";
        String username = "root";
        String password = "547118";
        Connection conn =  DriverManager.getConnection(url, username, password);

        // 接受用户输入，用户名和密码
        String name = "zhangsan";
        String pwd = "' or '1' = '1";  // SQL注入
        String pwd1 = "111";

        String sql = "select * from tb_user where username = ? and password = ?";
        String sql1 = "update tb_user set password = ? where username = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        PreparedStatement pstmt1 = conn.prepareStatement(sql1);

        // 设置？值
        pstmt.setString(1, name);
        pstmt.setString(2, pwd);
        pstmt1.setString(1, pwd1);
        pstmt1.setString(2, name);

        int rs = pstmt1.executeUpdate();
        System.out.println(rs);

//        if (rs.next()) {
//            System.out.println("修改成功");
//        } else {
//            System.out.println("修改失败");
//        }

//        rs.close();
        pstmt.close();
        pstmt1.close();
        conn.close();
    }
}
