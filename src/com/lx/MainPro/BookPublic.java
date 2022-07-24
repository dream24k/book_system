package com.lx.MainPro;

import javax.swing.*;

// Book增删改查的公共类

public class BookPublic extends JFrame {
    JLabel lbBookId = new JLabel("图书编号");
    JLabel lbBookName = new JLabel("图书名称");
    JLabel lbBookType = new JLabel("图书类别");
    JLabel lbAuthor = new JLabel("作     者");
    JLabel lbTranslator = new JLabel("译     者");
    JLabel lbPublisher = new JLabel("出版社");
    JLabel lbPublishTime = new JLabel("出版时间");
    JLabel lbPrice = new JLabel("定     价");
    JLabel lbStock = new JLabel("库存数量");

    JTextField tfBookId = new JTextField();
    JTextField tfBookName = new JTextField();
    JTextField tfAuthor = new JTextField();
    JTextField tfTranslator = new JTextField();
    JTextField tfPublisher = new JTextField();
    JTextField tfPublishTime = new JTextField();
    JTextField tfPrice = new JTextField();
    JTextField tfStock = new JTextField();

    JButton closeBtn = new JButton("关闭");

    JComboBox tfBookType = new JComboBox();
}
