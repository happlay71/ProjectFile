package com.itheima.jdbc;

import com.itheima.pojo.Account;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCResultSet {
    @Test
    public void testResultSet() throws Exception {
        // 注册驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");

        // 获取链接
        String url = "jdbc:mysql:///test";
        String username = "root";
        String password = "547118";
        Connection conn =  DriverManager.getConnection(url, username, password);

        // 定义SQL
        String sql = "select * from user";

        // 获取statment对象
        Statement stmt = conn.createStatement();

        // 执行SQL
        ResultSet rs = stmt.executeQuery(sql);

        // 创建集合
        List<Account> list = new ArrayList<>();

        // 将光标从当前位置向前移动一行,判断当前行是否为有效行
        while (rs.next()) {
            Account account = new Account();
            // xxx getXxx(参数)：获取数据
            int id = rs.getInt(1);
            String name = rs.getString("name");
            double money = rs.getDouble(3);

            account.setId(id);
            account.setName(name);
            account.setMoney(money);

            // 存入集合
            list.add(account);
        }

        System.out.println(list);

        rs.close();
        stmt.close();
        conn.close();
    }
}
