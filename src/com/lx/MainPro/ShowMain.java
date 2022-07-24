package com.lx.MainPro;

import com.lx.PublicModule.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 系统主画面
// 主要是菜单设计，并通过为各子菜单增加事件侦听器以调佣其他功能模块

public class ShowMain extends JFrame {
    private static final long serialVersionUID = 1L;
    JMenuBar jmenuBar;
    JMenu menu1,menu2,menu3,menu4,menu5,menu6;
    JMenuItem miBookAdd,miBookUpdate,miBookDelete,miReaderAdd,miReaderUpdate,miReaderDelete,
            miBorrow,miBack,miQueryBook,miQueryReader,miUpdatePass,miExit;

    public void setRights(String rights){
        // 如果不是管理员，则禁止用户维护图书信息和读者信息，以及禁止进行借阅管理，即只能查询
        if (rights.equals("否")){
            // 设置控件是否可用
            menu1.setEnabled(false);
            // 设置控件是否可用
            menu5.setEnabled(false);
        }
    }

    public ShowMain(){
        setTitle("图书管理系统");
        setLayout(new BorderLayout());
        setSize(640,480);
        jmenuBar = new JMenuBar();

        menu1 = new JMenu("借阅管理");
        miBorrow = new JMenuItem("借书管理");
        miBack = new JMenuItem("还书管理");

        menu2 = new JMenu("查询管理");
        miQueryBook = new JMenuItem("图书查询");
        miQueryReader = new JMenuItem("读者查询");

        menu3 = new JMenu("系统管理");
        miUpdatePass = new JMenuItem("修改密码");
        miExit = new JMenuItem("退出系统");

        menu4 = new JMenu("基础维护");
        menu5 = new JMenu("图书维护");
        miBookAdd = new JMenuItem("添加图书");
        miBookUpdate = new JMenuItem("修改图书");
        miBookDelete = new JMenuItem("删除图书");

        menu6 = new JMenu("读者维护");
        miReaderAdd = new JMenuItem("添加读者");
        miReaderUpdate = new JMenuItem("修改读者");
        miReaderDelete = new JMenuItem("删除读者");

        miBookAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookAdd();
            }
        });

        miBookUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookUpdate();
            }
        });

        miBookDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookDelete();
            }
        });

        miReaderAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReaderAdd();
            }
        });

        miReaderUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReaderUpdate();
            }
        });

        miReaderDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReaderDelete();
            }
        });

        miBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Borrow();
            }
        });

        miBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Back();
            }
        });

        miQueryBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryBook();
            }
        });

        miQueryReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryReader();
            }
        });

        miUpdatePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdatePass();
            }
        });

        miExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDBC.Close();
                System.exit(0);
            }
        });

        // 关闭窗口
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu4.add(menu1);
        menu1.add(miBorrow);
        menu1.add(miBack);

        menu4.add(menu2);
        menu2.add(miQueryBook);
        menu2.add(miQueryReader);

        menu4.add(menu3);
        menu3.add(miUpdatePass);
        menu3.add(miExit);

        menu4.add(menu5);
        menu5.add(miBookAdd);
        menu5.add(miBookUpdate);
        menu5.add(miBookDelete);

        menu4.add(menu6);
        menu6.add(miReaderAdd);
        menu6.add(miReaderUpdate);
        menu6.add(miReaderDelete);

        jmenuBar.add(menu1);
        jmenuBar.add(menu2);
        jmenuBar.add(menu3);
        jmenuBar.add(menu4);

        setJMenuBar(jmenuBar);

        // 使窗体在屏幕居中位置显示
        setLocationRelativeTo(null);

        // 使窗体可见
        setVisible(true);
    }
}
