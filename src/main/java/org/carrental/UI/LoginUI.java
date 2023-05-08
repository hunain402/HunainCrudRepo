package org.carrental.UI;

import org.carrental.service.AuthenticationService;

import javax.swing.*;
import java.awt.*;

public class LoginUI {

    private final AuthenticationService authenticationService = new AuthenticationService();

    public LoginUI() {
        JFrame frame = new JFrame("RENT A CAR APP");

        // Set background image
        ImageIcon backgroundImage = new ImageIcon("src/main/resources/peakpx.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 500, 500);
        frame.add(backgroundLabel);

        //USER TEXT FIELD
        JTextField userTf = new JTextField();
        userTf.setBounds(200, 240, 200, 23);
        backgroundLabel.add(userTf);
        //PASSWORD TEXT FIELD
        JTextField passTf = new JTextField();
        passTf.setBounds(200, 290, 200, 23);
        backgroundLabel.add(passTf);
        //USER LABEL
        JLabel usrLabel = new JLabel("USER NAME");
        usrLabel.setBounds(130, 227, 150, 50);
        usrLabel.setForeground(Color.WHITE);
        backgroundLabel.add(usrLabel);
        //PASSWORD LABEL
        JLabel pssLabel = new JLabel("PASSWORD");
        pssLabel.setBounds(130, 277, 150, 50);
        pssLabel.setForeground(Color.WHITE);
        backgroundLabel.add(pssLabel);


        //LOGIN BUTTON

        JButton login = new JButton("Login");
        login.setBounds(220, 340, 140, 30);
        backgroundLabel.add(login);
        //BUTTONS ACTION

        login.addActionListener((event)->{
            if(authenticationService.checkLogin(userTf.getText(),passTf.getText())){
                frame.dispose();
                new HomeUi();
            }else {
                JOptionPane.showMessageDialog(frame,"Incorrect credentials");
            }
        });

        frame.setSize(700, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Main method to start the application
    public static void main(String[] args) {
        new LoginUI();
    }
}
