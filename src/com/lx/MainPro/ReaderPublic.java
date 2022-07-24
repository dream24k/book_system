package com.lx.MainPro;

import javax.swing.*;

// Reader的增删改查的公共类

public class ReaderPublic extends JFrame {
    JLabel lbReaderId = new JLabel("读者编号");
    JLabel lbReaderName = new JLabel("读者姓名");
    JLabel lbReaderType = new JLabel("读者类别");
    JLabel lbSex = new JLabel("读者性别");
    JLabel lbMaxNum = new JLabel("可借数量");
    JLabel lbDaysNum = new JLabel("可借天数");

    JTextField tfReaderId = new JTextField();
    JTextField tfReaderName = new JTextField();
    JTextField tfMaxNum = new JTextField();
    JTextField tfDaysNum = new JTextField();

    JComboBox tfReaderType = new JComboBox();
    JComboBox tfSex = new JComboBox();

    JButton closeBtn = new JButton("关闭");

}
