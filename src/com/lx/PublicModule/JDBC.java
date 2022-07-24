package com.lx.PublicModule;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

// 数据库操作
// 其构造方法用来创建数据库连接，即打开数据库，其他几个方法分别用来查询、
// 修改记录，以及关闭数据库

public class JDBC {
    // 加载驱动
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String user = "root";
    private static String password = "211263";

    // 数据库URL地址
    private static String url = "jdbc:mysql://localhost:3306/book_management";
    private static Connection con = null;

    // 构造方法，如果数据库未打开，则通过创建连接打开数据库
    private JDBC(){
        try{
            // 如果当前未创建连接，则加载JDBC驱动程序，然后创建连接
            if(con == null){
                Class.forName(driver);
                con = DriverManager.getConnection(url,user,password);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"数据库未连接成功！");
        }
    }

    // 执行数据库查询工作，如果出现异常，返回null
    public static ResultSet executeQuery(String sql){
        try{
            // 如果未创建连接，则创建连接
            if(con == null){
                new JDBC();
            }
            // 返回查询结果
            return con.createStatement().executeQuery(sql);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"数据库不存在，或存在错误！");
            return null;
        }
    }

    // 执行数据库更新操作，如果有问题，则返回-1
    public static int executeUpdate(String sql){
        try{
            // 如果未创建连接，则创建连接
            if (con == null){
                new JDBC();
            }
            // 返回操作结果
            return con.createStatement().executeUpdate(sql);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"数据有误，记录无法正常保存或更新！");
            return -1;
        }
    }

    // 关闭数据库
    public static void Close(){
        try{
            // 如果数据库已打开，则关闭
            if (con != null){
                con.close();
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"数据库未打开！");
        }
    }
}
