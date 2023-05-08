package org.carrental.UI;

import javax.swing.*;
import java.awt.*;

public class UserUi {
    public UserUi(){
        JFrame frame = new JFrame("USER PANEL");
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(8,1,10,10));

        JButton create = new JButton();
        btnPanel.add(create);
        addImageOnButton(create,"src/main/resources/add-note-icon.png",100,100);

        create.addActionListener((event)->{
        });

        JButton update = new JButton();
        btnPanel.add(update);
        addImageOnButton(update,"src/main/resources/data-update-icon.png",100,100);

        update.addActionListener(e -> {
        });

        JButton back = new JButton();
        btnPanel.add(back);
        addImageOnButton(back,"src/main/resources/curved-arrow-back-outline-icon.png",100,100);

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUi();

        });

        JButton delete = new JButton();
        btnPanel.add(delete);
        addImageOnButton(delete,"src/main/resources/delete-icon.png",100,100);
        delete.addActionListener(e -> {
        });

        JButton logout = new JButton();
        btnPanel.add(logout);
        addImageOnButton(logout,"src/main/resources/music-switch-on-off-button-icon.png",100,100);

        logout.addActionListener((event)->{
            try {
                frame.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            new LoginUI();
        });



        JPanel tblAndSearchPanel = new JPanel();

        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        JTextField searchTf = new JTextField("search",30);

        JButton search = new JButton();
        tblAndSearchPanel.add(search);
        search.addActionListener(e -> {

        });
        addImageOnButton(search,"src/main/resources/website-search-icon.png",20,20);


        String column[]={"NAME","PASSWORD"};

        String [][] data={ {"shezy","shezy"},
                {"admin","admin"}};

        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(jt);

        JScrollPane sp=new JScrollPane(jt);

        tblAndSearchPanel.add(sp);


        frame.setLayout(new GridLayout(1,3,150,5));

        frame.add(btnPanel);

        frame.add(tblAndSearchPanel);

        frame.setSize(1500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
    private static void addImageOnButton(JButton button,String imagePath,int height,int width) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));
    }
}



