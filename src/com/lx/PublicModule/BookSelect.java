package com.lx.PublicModule;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

// 按图书编号查询
// 按图书编号查询图书信息表，结果保存在Book对象中

public class BookSelect {
    // 按图书编号查询，查询结果保存在Book类的对象中
    public static Book SelectBookById(String id){
        String sql = "select * from book where id = '" + id +"'";
        ResultSet rs = JDBC.executeQuery(sql);
        Book book = null;
        try{
            if (rs.next()){
                book = new Book();
                book.setId(rs.getString("id"));
                book.setBookType(rs.getString("bookType"));
                book.setBookName(rs.getString("bookName"));
                book.setAuthor(rs.getString("author"));
                book.setTranslator(rs.getString("translator"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishTime(rs.getDate("publishTime"));
                book.setPrice(rs.getFloat("price"));
                book.setStock(rs.getInt("stock"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"无法正常读取数据库！");
        }
        return book;
    }
}
