package org.carrental.UI;

import org.carrental.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersUi {
    public static void main(String[] args) {
        new CustomersUi();
    }
    public CustomersUi(){
        JFrame frame  = new JFrame("customer list");

        JLabel customerNameLabel = new JLabel("customer Name");
       JTextField customerTfName = new JTextField(150);
       frame.add(customerNameLabel);
       frame.add(customerTfName);
        JLabel customerNumberLabel = new JLabel("customer Number");
        JTextField customerTfNumber = new JTextField(150);
      frame.add(customerNumberLabel);
      frame.add(customerTfNumber);

        frame.setSize(400, 500);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT,50,50));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
