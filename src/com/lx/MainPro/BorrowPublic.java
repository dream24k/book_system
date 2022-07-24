package com.lx.MainPro;

import javax.swing.*;

// Borrow和Back的公共变量

public class BorrowPublic extends JFrame {
    String SepLine = "----------------------------------------------";
    JLabel lbBookId = new JLabel("图书编号");
    JLabel lbReaderId = new JLabel("读者编号");

    JTextField tfBookId = new JTextField();
    JTextField tfReaderId = new JTextField();
    JButton queryBtn = new JButton("查询");

    JLabel lbBookInfo = new JLabel(SepLine + "图书信息" + SepLine);
    JLabel lbBookName = new JLabel("图书名称：");
    JLabel tfBookName = new JLabel("xx");
    JLabel lbAuthor = new JLabel("作        者：");
    JLabel tfAuthor = new JLabel("xx");
    JLabel lbPublisher = new JLabel("出  版  社：");
    JLabel tfPublisher = new JLabel("xx");
    JLabel lbPublishTime = new JLabel("出版时间：");
    JLabel tfPublishTime = new JLabel("xx");
    JLabel lbPrice = new JLabel("定        价：");
    JLabel tfPrice = new JLabel("xx");
    JLabel lbStock = new JLabel("库存数量：");
    JLabel tfStock = new JLabel("xx");

    JLabel lbReaderInfo = new JLabel(SepLine + "读者信息" + SepLine);
    JLabel lbReaderName = new JLabel("读者姓名：");
    JLabel tfReaderName = new JLabel("xx");
    JLabel lbReaderType = new JLabel("读者类型：");
    JLabel tfReaderType = new JLabel("xx");
    JLabel lbMaxNum = new JLabel("最大可借数：");
    JLabel tfMaxNum = new JLabel("xx");
    JLabel lbDaysNum = new JLabel("最大可借天数：");
    JLabel tfDaysNum = new JLabel("xx");

    JButton closeBtn = new JButton("关闭");
}
