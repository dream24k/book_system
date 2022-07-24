package com.lx.MainPro;

import com.lx.PublicModule.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

// 查询读者信息
// 可按读者名称，类别查询，结果将显示在一个表格中

public class QueryReader extends JFrame {
    private static final long serialVersionUID = 1L;
    // 用来显示和编辑常规二维单元表
    JTable table;
    // 管理视口、可选的垂直和水平滚动条以及可选的行和列标题视口
    JScrollPane scrollPane;

    JLabel lbReaderName = new JLabel("读者姓名");
    JLabel lbReaderType = new JLabel("读者类别");

    JTextField tfReaderName = new JTextField();
    JTextField tfReaderType = new JTextField();

    JButton queryBtn = new JButton("查询");
    JButton closeBtn = new JButton("关闭");

    String[] heads = {"读者编号","读者姓名","读者类别","读者性别","最大可借数","可借天数"};

    public QueryReader(){
        setLayout(null);
        setTitle("读者查询");
        setSize(800,500);

        lbReaderName.setBounds(160,20,60,20);
        tfReaderName.setBounds(230,20,160,20);
        lbReaderType.setBounds(410,20,60,20);
        tfReaderType.setBounds(470,20,160,20);

        queryBtn.setBounds(300,60,80,25);
        queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnQueryActionPerformed(e);
            }
        });

        closeBtn.setBounds(420,60,80,25);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(lbReaderName);
        add(tfReaderName);
        add(lbReaderType);
        add(tfReaderType);
        add(queryBtn);
        add(closeBtn);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);
    }

    private void btnQueryActionPerformed(ActionEvent e) {
        try{
            String readerName,readerType;
            String sql,sql1,sql2,sql3;
            readerName = tfReaderName.getText();
            readerType = tfReaderType.getText();

            // 创建一条基本的SQL语句，表示选出表中全部记录
            sql = "select * from reader";

            // 如果书名不空，生成SQL1字句
            if (readerName.equals("")){
                sql1 = "";
            }else{
                sql1 = "readername like '" + readerName + "%'";
            }

            // 如果类别不空，生成SQL2字句
            if (readerType.equals("")){
                sql2 = "";
            }else {
                sql2 = "author like '" + readerType + "%'";
                if (! readerName.equals("")) {
                    sql2 = "and" + sql2;
                }
            }

            sql3 = sql1 + sql2;
            // 如果已设置任意一项条件，则修改SQL语句
            if(!sql3.equals("")){
                sql = sql + "where " + sql3;
            }

            // 执行查询
            ResultSet rs = JDBC.executeQuery(sql);

            // 创建一个对象二维数组
            Object[][] readerQuery = new Object[30][heads.length];
            int i = 0;
            while (rs.next()){
                // 将查询的结果赋予Book数组
                readerQuery[i][0] = rs.getString("id");
                readerQuery[i][1] = rs.getString("readername");
                readerQuery[i][2] = rs.getString("readertype");
                readerQuery[i][3] = rs.getString("sex");
                readerQuery[i][4] = rs.getString("max_num");
                readerQuery[i][5] = rs.getString("days_num");
                i++;
            }

            // 创建一个表格
            table = new JTable(readerQuery,heads);

            // 创建一个显示表格的JScrollPane
            scrollPane = new JScrollPane(table);

            // 设置JScrollPane的位置和尺寸
            scrollPane.setBounds(20,140,760,300);

            // 将JScrollPane添加到窗体中
            add(scrollPane);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null,"数据库不存在或存在错误！");
        }
    }
}
