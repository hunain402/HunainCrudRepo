package org.carrental.UI;

import org.carrental.service.CustomerService;
import org.carrental.service.OwnerService;

import javax.swing.*;
import java.awt.*;

public class AddOwnerUi {

    public static void main(String[] args) {
        new AddCustomerUI();
    }

    private final OwnerService ownerService = new OwnerService();

    public AddOwnerUi() {
        JFrame frame = new JFrame("Car Rental APP - ADD owner ");
        frame.setLayout(new GridLayout(6, 4, 10, 10));

        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel cniclb = new JLabel("cnic");
        JTextField cnictf = new JTextField(20);

        JLabel numberlb = new JLabel("number");
        JTextField numbertf = new JTextField(20);

        JLabel addressLb = new JLabel("ADDRESS");
        JTextField addressTf = new JTextField(20);

        JLabel commisionlb = new JLabel("commision");
        JTextField commisiontf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(cniclb);
        frame.add(cnictf);
        frame.add(numberlb);
        frame.add(numbertf);
        frame.add(addressLb);
        frame.add(addressTf);
        frame.add(commisionlb);
        frame.add(commisiontf);
        frame.add(save);
        frame.add(back);

        back.addActionListener(e -> {
            frame.dispose();
            new OwnerUi();
        });

        save.addActionListener(e -> {

            try {
                ownerService.save(nameTf.getText(), cnictf.getText(),
                        numbertf.getText(), addressTf.getText(), commisiontf.getText());
                frame.dispose();
                new OwnerUi();

            } catch (Exception ex) {
                throw new RuntimeException(ex);

                //  JOptionPane.showMessageDialog(frame, "Unable to save");
            }

        });

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}



