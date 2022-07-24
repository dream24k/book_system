package com.lx.MainPro;

import com.lx.PublicModule.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

// 录入读者信息
// 保存记录时要检查数据的有效性：1，读者编号必须唯一，不能重复
// 2，最大可借数或最大可借天数错误为整数

public class ReaderAdd extends ReaderPublic {
    private static final long serialVersionUID = 1L;
    JButton saveBtn = new JButton("保存");

    public ReaderAdd(){
        setLayout(null);
        setTitle("添加读者信息");
        setSize(500,300);

        lbReaderId.setBounds(50,50,60,20);
        tfReaderId.setBounds(110,50,100,20);
        lbReaderName.setBounds(240,50,60,20);
        tfReaderName.setBounds(300,50,100,20);
        lbReaderType.setBounds(50,80,60,20);

        tfReaderType.setBounds(110,80,100,20);
        tfReaderType.addItem("教师");
        tfReaderType.addItem("学生");
        tfReaderType.addItem("职工");

        lbSex.setBounds(240,80,60,20);
        tfSex.setBounds(300,80,100,20);
        tfSex.addItem("男");
        tfSex.addItem("女");

        lbMaxNum.setBounds(50,110,60,20);
        tfMaxNum.setBounds(110,110,100,20);
        lbDaysNum.setBounds(240,110,60,20);
        tfDaysNum.setBounds(300,110,100,20);

        saveBtn.setBounds(120,150,80,25);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSaveActonPerformed(e);
            }
        });

        closeBtn.setBounds(280,150,80,25);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(lbReaderId);
        add(tfReaderId);
        add(lbReaderName);
        add(tfReaderName);
        add(lbReaderType);
        add(tfReaderType);
        add(lbSex);
        add(tfSex);
        add(lbMaxNum);
        add(tfMaxNum);
        add(lbDaysNum);
        add(tfDaysNum);
        add(saveBtn);
        add(closeBtn);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);
    }

    private void btnSaveActonPerformed(ActionEvent e) {
        String id = tfReaderId.getText();
        String name = tfReaderName.getText();
        String sex = tfSex.getSelectedItem().toString();
        String type = tfReaderType.getSelectedItem().toString();

        // 如果读者编号为空，则终止保存记录操作
        if (id.equals("")){
            JOptionPane.showMessageDialog(null,"读者编号不能为空！");
            return ;
        }

        // 如果读者编号重复，则记录无效，需修改读者编号
        if (IfReaderIdExit(id)){
            JOptionPane.showMessageDialog(null,"读者编号重复！");
            return ;
        }

        try{
            int maxNum = Integer.parseInt(tfMaxNum.getText());
            int daysNum = Integer.parseInt(tfDaysNum.getText());

            String sql = "insert into reader(id,name,type,sex,max_num,days_num)values('" + id + "'," + name + "'," +
                    type + "'," + sex + "'," + maxNum + "'," + daysNum + "')";
            int i = JDBC.executeUpdate(sql);

            if (i == 1){
                JOptionPane.showMessageDialog(null,"读者信息添加成功！");
                // 清空全部文本框
                clearAllTextfield();
            }
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"最大可借数或最大可借天数错误，应为整数！");
        }
    }

    // 清空全部文本框
    private void clearAllTextfield() {
        tfReaderId.setText("");
        tfReaderName.setText("");
        tfMaxNum.setText("");
        tfDaysNum.setText("");
    }

    // 判断Reader表中是否存在指定编号的读者，如果存在，返回TRUE，否则，返回FALSE
    private boolean IfReaderIdExit(String id) {
        String sql = "select * from reader where id='" + id + "'";
        ResultSet rs = JDBC.executeQuery(sql);
        try{
            if (rs.next()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"无法正常读取数据库！");
        }
        return false;
    }
}
