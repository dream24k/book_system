package com.lx.MainPro;

import com.lx.PublicModule.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// 查询图书信息
// 可按图书名称，作者，出版社，出版时间组合查询，结果将显示在一个表格中

public class QueryBook extends JFrame{
    private static final long serialVersionUID = 1L;
    // 用来显示和编辑常规二维单元表
    JTable table;
    // 管理视口、可选的垂直和水平滚动条以及可选的行和列标题视口
    JScrollPane scrollPane;

    JLabel lbBookName = new JLabel("图书名称");
    JLabel lbAuthor = new JLabel("作     者");
    JLabel lbPublisher = new JLabel("出  版  社");
    JLabel lbPublishTime = new JLabel("出版时间");
    JLabel lbNotes = new JLabel("（年--月）");

    JTextField tfBookName = new JTextField();
    JTextField tfAuthor = new JTextField();
    JTextField tfPublisher = new JTextField();
    JTextField tfPublishTime = new JTextField();

    JButton queryBtn = new JButton("查询");
    JButton closeBtn = new JButton("关闭");

    String[] heads = {"图书编号","图书名称","图书类别","作者","译者","出版社","出版时间","定价","库存数量"};

    public QueryBook(){
        setLayout(null);
        setTitle("图书查询");
        setSize(800,500);

        lbBookName.setBounds(160,20,60,20);
        tfBookName.setBounds(230,20,160,20);
        lbAuthor.setBounds(410,20,60,20);
        tfAuthor.setBounds(470,20,160,20);
        lbPublisher.setBounds(160,50,60,20);
        tfPublisher.setBounds(230,50,160,20);
        lbPublishTime.setBounds(410,40,60,20);
        tfPublishTime.setBounds(470,50,160,20);
        lbNotes.setBounds(405,60,60,20);

        queryBtn.setBounds(300,90,80,25);
        queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnQueryActionPerformed(e);
            }
        });

        closeBtn.setBounds(420,90,80,25);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(lbBookName);
        add(tfBookName);
        add(lbAuthor);
        add(tfAuthor);
        add(lbPublisher);
        add(tfPublisher);
        add(lbPublishTime);
        add(tfPublishTime);
        add(lbNotes);
        add(queryBtn);
        add(closeBtn);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);
    }

    private void btnQueryActionPerformed(ActionEvent e) {
        try{
            String bookName,author,publisher,publishTime;
            String sql,sql1,sql2,sql3,sql4,sql5;
            String pubYear,pubMonth;
            bookName = tfBookName.getText();
            author = tfAuthor.getText();
            publisher = tfPublisher.getText();
            publishTime = tfPublishTime.getText();

            // 创建一条基本的SQL语句，表示选出表中全部记录
            sql = "select * from book";

            // 如果书名不空，生成SQL1字句
            if (bookName.equals("")){
                sql1 = "";
            }else{
                sql1 = "bookname like '" + bookName + "%'";
            }

            // 如果作者不空，生成SQL2字句
            if (author.equals("")){
                sql2 = "";
            }else {
                sql2 = "author like '" + author + "%'";
                if (! bookName.equals("")) {
                    sql2 = "and" + sql2;
                }
            }

            // 如果出版社不空，生成SQL3字句
            if (publisher.equals("")){
                sql3 = "";
            }else {
                sql3 = "publisher like '" + publisher + "%'";
                if(!(bookName.equals("")&&author.equals(""))){
                    sql3 = "and" + sql3;
                }
            }

            // 如果出版日期不空，生成SQL4字句
            if (publishTime.equals("")){
                sql4 = "";
            }else {
                // 创建日期格式对象 格式：年-月
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                // 创建一个Calender 对象
                Calendar cal = new GregorianCalendar();
                // 将字符串转换为日期
                Date pubTime = simpleDateFormat.parse(tfPublishTime.getText());
                // 使用给定日期设置cal的时间
                cal.setTime(pubTime);
                // 获取年，月
                pubYear = String.valueOf(cal.get(Calendar.YEAR));
                pubMonth = String.valueOf(cal.get(Calendar.MONTH)+1);

                sql4 = "year(publish_time)=" + pubYear + "and month(publish_time)=" + pubMonth;

                // 如果书名，作者或者出版社有一项为空
                if (!(bookName.equals("")&&author.equals("")&&publisher.equals(""))){
                    sql4 = "and " + sql4;
                }
            }

            sql5 = sql1 + sql2 + sql3 + sql4;
            // 如果已设置任意一项条件，则修改SQL语句
            if(!sql5.equals("")){
                sql = sql + "where " + sql5;
            }

            // 执行查询
            ResultSet rs = JDBC.executeQuery(sql);

            // 创建一个对象二维数组
            Object[][] bookQuery = new Object[30][heads.length];
            int i = 0;
            while (rs.next()){
                // 将查询的结果赋予Book数组
                bookQuery[i][0] = rs.getString("id");
                bookQuery[i][1] = rs.getString("bookname");
                bookQuery[i][2] = rs.getString("booktype");
                bookQuery[i][3] = rs.getString("author");
                bookQuery[i][4] = rs.getString("translator");
                bookQuery[i][5] = rs.getString("publisher");
                bookQuery[i][6] = rs.getDate("publish_time");
                bookQuery[i][7] = rs.getFloat("price");
                bookQuery[i][8] = rs.getString("stock");
                i++;
            }

            // 创建一个表格
            table = new JTable(bookQuery,heads);

            // 创建一个显示表格的JScrollPane
            scrollPane = new JScrollPane(table);

            // 设置JScrollPane的位置和尺寸
            scrollPane.setBounds(20,140,760,300);

            // 将JScrollPane添加到窗体中
            add(scrollPane);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,"出版时间格式错误（正确格式：年--月）！");
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null,"数据库不存在或存在错误！");
        }
    }
}
