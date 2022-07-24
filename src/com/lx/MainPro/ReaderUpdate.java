package com.lx.MainPro;

import com.lx.PublicModule.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 修改读者信息
// 按读者编号查询记录，然后修改读者的其余信息。此时应确保其他数据的有效性

public class ReaderUpdate extends ReaderPublic  {
    private static final long serialVersionUID = 1L;
    JLabel lbReaderId1 = new JLabel("读者编号");
    JTextField tfReaderId1 = new JTextField();
    JButton queryBtn = new JButton("查询");
    JButton saveBtn = new JButton("保存");

    public ReaderUpdate(){
        setLayout(null);
        setTitle("修改读者信息");
        setSize(500,270);

        lbReaderId1.setBounds(100,15,60,20);
        tfReaderId1.setBounds(160,15,100,20);

        queryBtn.setBounds(290,15,80,20);
        queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnQueryActionPerformed(e);
            }
        });

        lbReaderId.setBounds(50,50,60,20);
        tfReaderId.setBounds(110,50,100,20);

        lbReaderId.setEnabled(false);
        tfReaderId.setEnabled(false);

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

        add(lbReaderId1);
        add(tfReaderId1);
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
        add(queryBtn);
        add(saveBtn);
        add(closeBtn);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);
    }

    private void btnQueryActionPerformed(ActionEvent e) {
        String id = tfReaderId1.getText();

        // 如果读者编号为空，则查询操作终止
        if (id.equals("")){
            JOptionPane.showMessageDialog(null,"读者编号不能为空！");
            return ;
        }

        // 按编号查询读者，结果存入reader对象中
        Reader reader = ReaderSelect.selectBookById(id);

        if(reader != null){
            tfReaderId.setText(reader.getId());
            tfReaderName.setText(reader.getReaderName());
            tfReaderType.setSelectedItem(reader.getReaderType());
            tfSex.setSelectedItem(reader.getSex());
            tfDaysNum.setText(String.valueOf(reader.getDaysNum()));
            tfMaxNum.setText(String.valueOf(reader.getMaxNum()));
        }else{
            JOptionPane.showMessageDialog(null,"读者编号有误，查无此人！");
        }
    }

    private void btnSaveActonPerformed(ActionEvent e) {
        String id = tfReaderId.getText();
        String readerName = tfReaderName.getText();
        String readerType = tfReaderType.getSelectedItem().toString();

        // 如果读者编号为空，则终止保存记录操作
        if (id.equals("")){
            JOptionPane.showMessageDialog(null,"读者编号不能为空！");
            return ;
        }

        try{
            int maxNum = Integer.parseInt(tfMaxNum.getText());
            int daysNum = Integer.parseInt(tfDaysNum.getText());
            String sex = tfSex.getSelectedItem().toString();

            String sql = "update reader set readername='" + readerName + "',readertype='" + readerType + "',days_num='"
                    + daysNum + "',sex='" + sex + "',max_num" + maxNum + "' where id='" + id + "'";
            int i = JDBC.executeUpdate(sql);

            if (i == 1){
                JOptionPane.showMessageDialog(null,"读者信息修改成功！");
                // 清空全部文本框
                clearAllTextfield();
            }else {
                JOptionPane.showMessageDialog(null,"读者信息修改失败！");
            }
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"最大可借数或最大可借天数错误，应为整数！");
        }
    }

    // 清空全部文本框
    private void clearAllTextfield() {
        tfReaderId1.setText("");
        tfReaderId.setText("");
        tfReaderName.setText("");
        tfMaxNum.setText("");
        tfDaysNum.setText("");
    }
}
