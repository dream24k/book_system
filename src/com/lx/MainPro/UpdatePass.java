package com.lx.MainPro;

import com.lx.PublicModule.GlobalVar;
import com.lx.PublicModule.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// 修改用户密码
// 修改密码，并将修改结果保存到用户信息表中

public class UpdatePass extends JFrame {
    private static final long serialVersionUID = 1L;
    JLabel newPassword = new JLabel("请 输 入 新 密 码：");
    JLabel confirmPass = new JLabel("再次确认新密码：");
    JTextField text1 = new JTextField();
    JTextField text2 = new JTextField();
    
    JButton confirmBtn = new JButton("确认");
    JButton cancelBtn = new JButton("取消");
    
    public UpdatePass(){
        setLayout(null);
        setTitle("修改密码");
        setSize(320,240);
        newPassword.setBounds(30,50,110,30);
        text1.setBounds(130,53,140,20);
        confirmPass.setBounds(30,80,110,30);
        text2.setBounds(130,83,140,20);
        confirmBtn.setBounds(70,130,70,25);
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmButtonPerformed(e);
            }
        });

        cancelBtn.setBounds(160,130,70,25);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(newPassword);
        add(confirmPass);
        add(text1);
        add(text2);
        add(cancelBtn);
        add(confirmBtn);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);
    }

    private void confirmButtonPerformed(ActionEvent e) {
        System.out.println(GlobalVar.loginUser);
        String password1 = text1.getText();
        String password2 = text2.getText();

        // 如果两个密码输入框中有一个为空，则显示错误提示信息并返回
        if (password1.equals("") || password2.equals("")){
            JOptionPane.showMessageDialog(null,"密码不能为空，请重新输入！");
            return ;
        }

        // 如果两个密码输入框中输入的内容不一致，则显示错误提示信息并返回
        if (!password1.equals(password2)){
            JOptionPane.showMessageDialog(null,"两次输入的密码不一致，请重新输入！");
            text1.setText("");
            text2.setText("");
            return ;
        }

        String sql = "update user set password='" + password1 + "where username='" + GlobalVar.loginUser + "'";
        int i = JDBC.executeUpdate(sql);

        if (i == 1){
            JOptionPane.showMessageDialog(null,"密码修改成功！");
        }else {
            JOptionPane.showMessageDialog(null,"用户数据库有误或不存在，修改密码失败！");
        }
    }
}
