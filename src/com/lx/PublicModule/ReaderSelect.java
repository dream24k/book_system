package com.lx.PublicModule;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

// 按读者编号查询
// 按读者编号查询读者信息表，结果保存在Reader对象中

public class ReaderSelect {
    // 按读者编号查询，查询结果保存在Reader类的对象中
    public static Reader selectBookById(String id){
        String sql = "select * from reader where id = '" + id +"'";
        ResultSet rs = JDBC.executeQuery(sql);
        Reader reader = null;
        try{
            if (rs.next()){
                reader = new Reader();
                reader.setId(rs.getString("id"));
                reader.setReaderName(rs.getString("readerName"));
                reader.setReaderType(rs.getString("readerType"));
                reader.setSex(rs.getString("sex"));
                reader.setMaxNum(rs.getInt("maxNum"));
                reader.setDaysNum(rs.getInt("dayNum"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"无法正常读取数据库！");
        }
        return reader;
    }
}
