package org.carrental.UI;

import javax.swing.*;
import java.awt.*;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;


public class LoginUI {
    public LoginUI() {
        JFrame frame = new JFrame();


        JTextField userTf = new JTextField();
        userTf.setBounds(200, 100, 200, 30);
        frame.add(userTf);

        JTextField passTf = new JTextField();
        passTf.setBounds(200, 200, 200, 30);
        frame.add(passTf);
        JLabel usrLabel = new JLabel("USERNAME");
        usrLabel.setBounds(130, 90, 150, 50);
        frame.add(usrLabel);

        JLabel pssLabel = new JLabel("PASSWORD");
        pssLabel.setBounds(130, 190, 150, 50);
        frame.add(pssLabel);

        JButton login = new JButton("Login");
        login.setBounds(200, 250, 150, 50);
        frame.add(login);
        login.addActionListener((event)->{
            String username = userTf.getText();
            String password = passTf.getText();
            if(username.equalsIgnoreCase("admin")&&
                    password.equalsIgnoreCase("admin")){
                try {
                    dispose();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                new HomeUi();
            }
        });
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

