package com.lx.MainPro;

import com.lx.PublicModule.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 借图模式
// 输入参数为图书编号和读者编号，有几个判断：1，图书必须有库存  2，每个读者只能借阅自己未借过的图书，
// 即使已经借过，但必须已经归还  3，每种数最多只能借一本   4，每个读者都有允许最大可借图书数量，
// 因此，读者已借未归还的图书数量不能超出此限制

public class Borrow extends BorrowPublic {
    private static final long serialVersionUID = 1L;

    JLabel lbBorrowInfo = new JLabel(SepLine + "借阅信息" + SepLine);
    JLabel lbBorrowedNum = new JLabel("该读者已借阅图书数量：");
    JLabel tfBorrowedNum = new JLabel("xx");
    JLabel lbIfBorrow = new JLabel("该读者是否可借所选图书：");
    JLabel tfIfBorrow = new JLabel("xx");
    JLabel lbBorrowDate = new JLabel("借阅日期：");
    JLabel tfBorrowDate = new JLabel("xx");
    JButton borrowBtn = new JButton("借出");

    public Borrow(){
        setLayout(null);
        setTitle("借阅图书");
        setSize(500,485);
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

        lbBorrowInfo.setBounds(30,260,440,25);
        lbBorrowedNum.setBounds(30,285,150,25);
        tfBorrowedNum.setBounds(170,285,200,25);
        lbIfBorrow.setBounds(30,310,160,25);
        tfIfBorrow.setBounds(180,310,200,25);
        lbBorrowDate.setBounds(30,335,80,25);
        tfBorrowDate.setBounds(90,335,200,25);

        borrowBtn.setBounds(140,370,80,25);
        borrowBtn.setEnabled(false);
        borrowBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBorrowActionPerformed(e);
            }
        });

        closeBtn.setBounds(260,370,80,25);
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
        add(lbBorrowInfo);
        add(lbBorrowedNum);
        add(tfBorrowedNum);
        add(lbIfBorrow);
        add(tfIfBorrow);
        add(lbBorrowDate);
        add(tfBorrowDate);
        add(borrowBtn);
        add(closeBtn);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);

        setForeground(Color.RED);
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

        if (book.getStock() == 0){
            JOptionPane.showMessageDialog(null,"图书已无库存，无法借阅！");
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
        if (IfBorrowBack.findBook(bookId,readerId)){
            JOptionPane.showMessageDialog(null,"该读者已借阅所选图书，且未归还！");
            init();
            return ;
        }

        // 统计读者所借图书数量
        int borrowedNum = statBorrowedNum(readerId);
        tfBorrowedNum.setText(String.valueOf(borrowedNum));

        // 如果读者已借图书尚未超出其允许最大借书量，则允许其继续借阅所选图书
        if (borrowedNum < reader.getMaxNum()){
            tfIfBorrow.setText("是");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            Date currentDate = new Date();
            String borrowDate = simpleDateFormat.format(currentDate);
            tfBorrowDate.setText(borrowDate);
            borrowBtn.setEnabled(true);
        }else {
            JOptionPane.showMessageDialog(null,"该读者借书过多，无法继续借阅");
            init();
            return ;
        }
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
        tfBorrowedNum.setText("xx");
        tfIfBorrow.setText("xx");
        tfBorrowDate.setText("xx");
        borrowBtn.setEnabled(false);
    }

    // 统计某个读者当前已借图书且未归还的数量
    private int statBorrowedNum(String readerId) {
        int borrowedNum = 0;
        String reader_id,ifBack;

        // 读取数据库中的记录
        String sql = "select * from borrow";
        ResultSet rs = JDBC.executeQuery(sql);

        try{
            while (rs.next()){
               reader_id = rs.getString("reader_id");
               ifBack = rs.getString("if_back");
               if (reader_id.equals(readerId) && ifBack.equals("否")){
                   borrowedNum++;
               }
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"数据库统计失败！");
        }
        return borrowedNum;
    }

    private void btnBorrowActionPerformed(ActionEvent e) {
        String sql;
        String bookId = tfBookId.getText();
        String readerId = tfReaderId.getText();
        String borrowDate = tfBorrowDate.getText();

        // 为borrow表增加借书记录
        sql = "insert into borrow(book_id,reader_id,borrow_date,if_back) values('" + bookId
                + "','" + readerId + "','" + borrowDate + "','否')";
        JDBC.executeUpdate(sql);

        // 将该读者所借图书数量加一
        int iBorrowedNum = Integer.parseInt(tfBorrowedNum.getText())+1;
        String sBorrowedNum = String.valueOf(iBorrowedNum);
        tfBorrowedNum.setText(sBorrowedNum);

        // 将图书库存数量减一
        int iStock = Integer.parseInt(tfStock.getText())-1;
        String sStock = String.valueOf(iStock);

        // 更新画面中的图书库存数量
        tfStock.setText(sStock);

        // 更新数据库中的图书库存数量
        sql = "update book set stock='" + sStock + "where id='" + bookId + "'";
        JDBC.executeUpdate(sql);
        JOptionPane.showMessageDialog(null,"借书成功！");
        init();
    }
}
