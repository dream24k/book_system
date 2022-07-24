package com.lx.MainPro;

import com.lx.PublicModule.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

// 还书模式
// 输入参数同样为图书编号和读者编号，主要判断该读者已借过此书，且未归还

public class Back extends BorrowPublic {
    private static final long serialVersionUID = 1L;

    JLabel lbBackInfo = new JLabel(SepLine + "还书信息" + SepLine);
    JLabel lbBackDate = new JLabel("还书日期：");
    JLabel tfBackDate = new JLabel("xx");
    JButton backBtn = new JButton("还书");

    public Back(){
        setLayout(null);
        setTitle("还回图书");
        setSize(500,460);
        // 设置前景色为黑色
        this.setForeground(Color.BLACK);

        lbBookId.setBounds(30,30,60,25);
        tfBookId.setBounds(90,30,90,20);
        lbReaderId.setBounds(200,30,60,25);
        tfReaderId.setBounds(260,30,90,20);
        queryBtn.setBounds(370,30,80,25);
        queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnQueryActionPerformed(e);
            }
        });

        lbBookInfo.setBounds(30,70,440,25);
        lbBookName.setBounds(30,100,80,25);
        tfBookName.setBounds(90,100,200,25);
        lbAuthor.setBounds(270,100,80,25);
        tfAuthor.setBounds(330,100,200,25);
        lbPublisher.setBounds(30,125,80,25);
        tfPublisher.setBounds(90,125,200,25);
        lbPublishTime.setBounds(270,125,80,25);
        tfPublishTime.setBounds(330,125,200,25);
        lbPrice.setBounds(30,150,80,25);
        tfPrice.setBounds(90,150,200,25);
        lbStock.setBounds(270,150,80,25);
        tfStock.setBounds(330,150,200,25);

        lbReaderInfo.setBounds(30,180,440,25);
        lbReaderName.setBounds(30,205,100,25);
        tfReaderName.setBounds(90,205,200,25);
        lbReaderType.setBounds(270,205,100,25);
        tfReaderType.setBounds(330,205,200,25);
        lbMaxNum.setBounds(30,230,100,25);
        tfMaxNum.setBounds(105,230,200,25);
        lbDaysNum.setBounds(270,230,100,25);
        tfDaysNum.setBounds(355,230,200,25);

        lbBackInfo.setBounds(30,270,440,20);
        lbBackDate.setBounds(30,295,100,25);
        tfBackDate.setBounds(90,295,200,25);

        backBtn.setBounds(130,335,80,25);
        backBtn.setEnabled(false);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBackActionPerformed(e);
            }
        });

        closeBtn.setBounds(230,335,80,25);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForeground(Color.BLACK);
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(lbBookId);
        add(lbReaderId);
        add(tfBookId);
        add(tfReaderId);
        add(queryBtn);
        add(lbBookInfo);
        add(lbBookName);
        add(tfBookName);
        add(lbAuthor);
        add(tfAuthor);
        add(lbPublisher);
        add(tfPublisher);
        add(lbPublishTime);
        add(tfPublishTime);
        add(lbPrice);
        add(tfPrice);
        add(lbStock);
        add(tfStock);
        add(lbReaderInfo);
        add(lbReaderName);
        add(tfReaderName);
        add(lbReaderType);
        add(tfReaderType);
        add(lbMaxNum);
        add(tfMaxNum);
        add(lbDaysNum);
        add(tfDaysNum);
        add(lbBackInfo);
        add(lbBackDate);
        add(tfBackDate);
        add(backBtn);
        add(closeBtn);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);

        setForeground(Color.RED);
    }

    // 填写还书记录
    private void btnBackActionPerformed(ActionEvent e) {
        String sql;
        String bookId = tfBookId.getText();
        String readerId = tfReaderId.getText();
        String backDate = tfBackDate.getText();

        // 更新borrow表的记录
        sql = "update borrow set if_back='是',back_date='" + backDate + "'where book_id='" + bookId +
                "'and reader_id='" + readerId + "' and if_back='否'";
        JDBC.executeUpdate(sql);

        // 将库存数量加一
        int iStock = Integer.parseInt(tfStock.getText())+1;
        String sStock = String.valueOf(iStock);

        // 更新数据量中的图书库存数量
        sql = "update book set stock='" + sStock + "' where id='" + bookId + "'";
        JDBC.executeUpdate(sql);
        JOptionPane.showMessageDialog(null,"还书成功！");
        init();
    }

    private void btnQueryActionPerformed(ActionEvent e) {
        String bookId = tfBookId.getText();
        String readerId = tfReaderId.getText();

        // 如果图书编号为空，则查询操作终止
        if (bookId.equals("") || readerId.equals("")){
            JOptionPane.showMessageDialog(null,"图书编号和读者编号不能为空！");
            // 重新初始化各参数并禁止借出按钮
            init();
            return ;
        }

        // 按编号查询图书，结果存入book对象中
        Book book = BookSelect.SelectBookById(bookId);

        if(book != null){
            tfBookName.setText(book.getBookName());
            tfAuthor.setText(book.getAuthor());
            tfPublisher.setText(book.getPublisher());
            tfPublishTime.setText(book.getPublishTime().toString());
            tfPrice.setText(String .valueOf(book.getPrice()));
            tfStock.setText(String.valueOf(book.getStock()));
        }else{
            JOptionPane.showMessageDialog(null,"图书编号有误，查无此书！");
            init();
            return ;
        }

        // 按编号查询读者，结果存入reader对象中
        Reader reader = ReaderSelect.selectBookById(readerId);

        if(reader != null){
            tfReaderName.setText(reader.getReaderName());
            tfReaderType.setText(reader.getReaderType());
            tfDaysNum.setText(String.valueOf(reader.getDaysNum()));
            tfMaxNum.setText(String.valueOf(reader.getMaxNum()));
        }else{
            JOptionPane.showMessageDialog(null,"读者编号有误，查无此人！");
            init();
            return ;
        }

        // 查询指定读者是否已借过指定图书且未归还
        if (!IfBorrowBack.findBook(bookId,readerId)){
            JOptionPane.showMessageDialog(null,"该读者没有借过此种图书！");
            init();
            return ;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date currentDate = new Date();
        String borrowDate = simpleDateFormat.format(currentDate);
        tfBackDate.setText(borrowDate);
        backBtn.setEnabled(true);
    }

    // 初始化各参数项并禁止借出按钮
    private void init() {
        tfBookName.setText("xx");
        tfAuthor.setText("xx");
        tfPublisher.setText("xx");
        tfPublishTime.setText("xx");
        tfPrice.setText("xx");
        tfStock.setText("xx");
        tfReaderName.setText("xx");
        tfReaderType.setText("xx");
        tfMaxNum.setText("xx");
        tfDaysNum.setText("xx");
        tfBackDate.setText("xx");
        backBtn.setEnabled(false);
    }
}
