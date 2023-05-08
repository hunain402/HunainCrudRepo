package org.carrental.UI;

import org.carrental.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class UpdatecustomerUi {


    private final CustomerService customerService = new CustomerService();

    public UpdatecustomerUi(String id, String name, String number, String cnic, String address, String refnumber) {


        JFrame frame = new JFrame("Car Rental APP - UPDATE CUSTOMER ");

        /**
         * id se query kr ky data ly ao
         * select * from table wherte id is :id
         * ub text field ko set krwao
         * nametf.setText(object.getName())
         */

        frame.setLayout(new GridLayout(6, 4, 10, 10));
        JLabel IdLb = new JLabel("ID");
        JTextField Idtf = new JTextField(20);

        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel phoneLb = new JLabel("PHONE");
        JTextField phoneTf = new JTextField(20);

        JLabel cnicLb = new JLabel("CNIC");
        JTextField cnicTf = new JTextField(20);

        JLabel addressLb = new JLabel("ADDRESS");
        JTextField addressTf = new JTextField(20);

        JLabel refLb = new JLabel("REF_PHONE");
        JTextField refTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(phoneLb);
        frame.add(phoneTf);
        frame.add(cnicLb);
        frame.add(cnicTf);
        frame.add(addressLb);
        frame.add(addressTf);
        frame.add(refLb);
        frame.add(refTf);
        frame.add(save);
        frame.add(back);
//set text filed
        nameTf.setText(name);
        phoneTf.setText(number);
        cnicTf.setText(cnic);
        addressTf.setText(address);
        refTf.setText(refnumber);


        save.addActionListener(e -> {
            customerService.updateCustomer(id, nameTf.getText(), phoneTf.getText(), cnicTf.getText(), addressTf.getText(), refTf.getText());
            frame.dispose();
            new CustomersUi();
        });


        back.addActionListener(e -> {
            frame.dispose();
            new CustomersUi();
        });

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}