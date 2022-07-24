package com.lx.MainPro;

import com.lx.PublicModule.JDBC;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// 录入图书信息
// 保存记录时要检查数据的有效性：1，图书编号必须唯一，不能重复
// 2，出版时间格式必须正确，有效 3，定价，库存数量必须为有效数字

public class BookAdd extends BookPublic {
    private static final long serialVersionUID = 1L;
    JButton saveBtn = new JButton("保存");
    JComboBox tfBookType = new JComboBox();

    BookAdd(){
        setLayout(null);
        setTitle("添加图书");
        setSize(500,320);

        lbBookId.setBounds(42,50,60,20);
        tfBookId.setBounds(110,50,100,20);
        lbBookName.setBounds(240,50,60,20);
        tfBookName.setBounds(300,50,100,20);
        lbBookType.setBounds(42,80,60,20);
        tfBookType.setBounds(110,80,100,20);

        tfBookType.addItem("科学");
        tfBookType.addItem("文学");
        tfBookType.addItem("社科");
        tfBookType.addItem("其他");

        lbAuthor.setBounds(240,80,60,20);
        tfAuthor.setBounds(300,80,100,20);
        lbTranslator.setBounds(42,110,60,20);
        tfTranslator.setBounds(110,110,100,20);
        lbPublisher.setBounds(240,110,60,20);
        tfPublisher.setBounds(300,110,100,20);
        lbPublishTime.setBounds(42,140,60,20);
        tfPublishTime.setBounds(110,140,100,20);
        lbPrice.setBounds(240,140,60,20);
        tfPrice.setBounds(300,140,100,20);
        lbStock.setBounds(42,170,60,20);
        tfStock.setBounds(110,170,100,20);

        saveBtn.setBounds(130,210,80,25);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSaveActionPerformed(e);
            }
        });

        closeBtn.setBounds(290,210,80,25);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(lbBookId);
        add(tfBookId);
        add(lbBookName);
        add(tfBookName);
        add(lbBookType);
        add(tfBookType);
        add(lbAuthor);
        add(tfAuthor);
        add(lbTranslator);
        add(tfTranslator);
        add(lbPublisher);
        add(tfPublisher);
        add(lbPublishTime);
        add(tfPublishTime);
        add(lbPrice);
        add(tfPrice);
        add(lbStock);
        add(tfStock);
        add(saveBtn);
        add(closeBtn);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);
    }

    // 保存记录
    private void btnSaveActionPerformed(ActionEvent e) {
        String id = tfBookId.getText();
        String bookName = tfBookName.getText();
        String bookType = tfBookType.getSelectedItem().toString();
        String author = tfAuthor.getText();
        String translator = tfTranslator.getText();
        String publisher = tfPublisher.getText();
        String publishTime = tfPublishTime.getText();

        // 如果图书编号为空，则终止保存记录操作
        if (id.equals("")){
            JOptionPane.showMessageDialog(null,"图书编号不能为空！");
            return ;
        }

        // 如果图书编号重复，则记录无效，需修改图书编号
        if (IfBookIdExit(id)){
            JOptionPane.showMessageDialog(null,"图书编号重复！");
            return ;
        }

        // 检查日期是否有效。格式：年-月
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            simpleDateFormat.parse(tfPublishTime.getText());
            float price = Float.parseFloat(tfPrice.getText());
            int stock = Integer.parseInt(tfStock.getText());

            String sql = "insert into book(id,bookname,booktype,author,translator,publisher,"
                    + "publish_time,price,stock)values('" + id +"','" + bookName +"','" + bookType
                    +"','" + author +"','" + translator +"','" + publisher +"','" + publishTime
                    +"','" + price +"','" + stock + "')";
            int i = JDBC.executeUpdate(sql);

            if (i == 1){
                JOptionPane.showMessageDialog(null,"图书信息添加成功！");
                // 清空全部文本框
                clearAllTextfield();
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,"出版时间格式错误（正确格式：年-月）！");
        }catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"库存数量和价格错误，应为数字！");
        }
    }

    // 清空全部文本框
    private void clearAllTextfield() {
        tfBookId.setText("");
        tfBookName.setText("");
        tfAuthor.setText("");
        tfTranslator.setText("");
        tfPublisher.setText("");
        tfPublishTime.setText("");
        tfPrice.setText("");
        tfStock.setText("");
    }

    // 判断Book表中是否存在指定编号的图书，如果存在，返回TRUE，否则，返回FALSE
    private boolean IfBookIdExit(String id) {
        String sql = "select * from book where id='" + id + "'";
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
