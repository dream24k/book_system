package com.lx.PublicModule;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

// 查询读者是否借过某本图书且未归还
// 查询指定读者是否借过指定图书，且未归还

public class IfBorrowBack {
    // 查指定读者是否借过指定图书且未归还
    public static Boolean findBook(String bookId,String readerId){
        String sql;
        sql = "select * from borrow where book_id = '" + bookId +"'and reader_id = '" +
                readerId + "'and if_back = '否' ";
        ResultSet rs = JDBC.executeQuery(sql);
        try{
            // 如果指定读者借阅了指定图书，且未归还，返回TRUE，否则返回FALSE
            if (rs.next()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"数据库查询失败！");
        }
        return true;
    }
}
