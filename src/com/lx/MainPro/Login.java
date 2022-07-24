package com.lx.MainPro;

import com.lx.PublicModule.GlobalVar;
import com.lx.PublicModule.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

// 用户登录模块
// 要将用户登录名和密码与用户信息表中内容对比，如果正确无误，
// 则进入系统主操作画面，否则提示错误信息

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;
    JTextField textUser;
    JTextField textPass;

    public Login(){
        this.setTitle("图书后台管理系统登录");
        this.setLayout(null);
        this.setSize(500,300);

        // 添加标签和文本框
        JLabel lbUser = new JLabel("用户名：");
        JLabel lbPass = new JLabel("密    码：");
        JButton btnOk = new JButton("确定");
        JButton btnCancel = new JButton("取消");

        textUser = new JTextField();
        textPass = new JTextField();

        lbUser.setBounds(100,53,60,30);
        lbPass.setBounds(100,83,60,30);
        textUser.setBounds(145,50,230,30);
        textPass.setBounds(145,80,230,30);

        btnOk.setBounds(110,140,100,35);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOkActionPerformed(e);
            }
        });

        btnCancel.setBounds(260,140,100,35);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDBC.Close();
                System.exit(0);
            }
        });

        // 关闭窗口
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(lbUser);
        add(lbPass);
        add(textUser);
        add(textPass);
        add(btnOk);
        add(btnCancel);

        // 使窗体在屏幕上居中放置
        setLocationRelativeTo(null);

        // 使窗体可见
        this.setVisible(true);

    }

    private void btnOkActionPerformed(ActionEvent e) {
        String user = textUser.getText();
        String pass = textPass.getText();
        String isAdmin;

        // 如果用户名或密码任一为空，则终止后续操作
        if (user.equals("") || pass.equals("")){
            JOptionPane.showMessageDialog(null,"用户名或密码不能为空！");
            return ;
        }
        try{
            // 核对用户名和密码
            String sql = "select * from user where username = '" + user + "' and password = '" + pass + "'" ;
            ResultSet rs = JDBC.executeQuery(sql);

            // 如果此用户存在，则记录其状态（是：是管理员，否：不是管理员）
            if (rs.next()){
                isAdmin = rs.getString("is_admin");
            }else {
                JOptionPane.showMessageDialog(null,"用户名或密码不正确！");
                return ;
            }

            // 记录登录的用户名
            GlobalVar.loginUser = user;

            // 调用主程序
            ShowMain show = new ShowMain();

            // 只有管理员才能使用“基础管理”和“借阅管理”菜单
            show.setRights(isAdmin);

            // 释放窗体及其全部组件的屏幕资源，同时释放登录窗体
            dispose();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"用户数据库有误！");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
