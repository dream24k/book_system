package com.lx.MainPro;

import com.lx.PublicModule.Book;
import com.lx.PublicModule.BookSelect;
import com.lx.PublicModule.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 删除图书信息
// 按图书编号查询记录，确认无误后可删除所选记录

public class BookDelete extends BookPublic{
    private static final long serialVersionUID = 1L;
    JLabel lbBookId1 = new JLabel("图书编号");
    JButton deleteBtn = new JButton("删除");
    JButton queryBtn = new JButton("查询");
    JTextField tfBookId1 = new JTextField();

    BookDelete(){
        setLayout(null);
        setTitle("删除图书");
        setSize(500,320);

        lbBookId1.setBounds(100,20,60,20);
        tfBookId1.setBounds(160,20,100,20);

        queryBtn.setBounds(280,20,80,20);
        queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnQueryActionPerformed(e);
            }
        });

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

        deleteBtn.setBounds(130,210,80,25);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteActionPerformed(e);
            }
        });

        closeBtn.setBounds(280,210,80,25);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(lbBookId1);
        add(tfBookId1);
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
        add(deleteBtn);
        add(closeBtn);
        add(queryBtn);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);
    }

    private void btnDeleteActionPerformed(ActionEvent e) {
        String id = tfBookId.getText();

        // 如果图书编号为空，则删除操作终止
        if (id.equals("")){
            JOptionPane.showMessageDialog(null,"图书编号不能为空！");
            return ;
        }

        String sql = "delete from book where id='" + id + "'";
        int i = JDBC.executeUpdate(sql);

        if (i == 1){
            JOptionPane.showMessageDialog(null,"图书信息删除成功！");
            // 清空全部文本框
            clearAllTextfield();
        }else{
            JOptionPane.showMessageDialog(null,"图书信息删除失败！");
        }
    }

    private void btnQueryActionPerformed(ActionEvent e) {
        String id = tfBookId1.getText();

        // 如果图书编号为空，则查询操作终止
        if (id.equals("")){
            JOptionPane.showMessageDialog(null,"图书编号不能为空！");
            return ;
        }

        // 按编号查询图书，结果存入book对象中
        Book book = BookSelect.SelectBookById(id);

        if(book != null){
            tfBookId.setText(book.getId());
            tfBookName.setText(book.getBookName());
            tfBookType.setSelectedItem(book.getBookType());
            tfAuthor.setText(book.getAuthor());
            tfTranslator.setText(book.getTranslator());
            tfPublisher.setText(book.getPublisher());
            tfPublishTime.setText(book.getPublishTime().toString());
            tfPrice.setText(String .valueOf(book.getPrice()));
            tfStock.setText(String.valueOf(book.getStock()));
        }else{
            JOptionPane.showMessageDialog(null,"图书编号有误，查无此书！");
        }
    }

    // 清空全部文本框
    private void clearAllTextfield() {
        tfBookId1.setText("");
        tfBookId.setText("");
        tfBookName.setText("");
        tfAuthor.setText("");
        tfTranslator.setText("");
        tfPublisher.setText("");
        tfPublishTime.setText("");
        tfPrice.setText("");
        tfStock.setText("");
    }
}
