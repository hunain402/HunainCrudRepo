package org.carrental.UI;

import org.carrental.service.BookingService;

import javax.swing.*;
import java.awt.*;

public class Updatebookingui {

    BookingService bookingService=new BookingService();
    public Updatebookingui(String id, String price, String status, String bookingDate, String customerId, String vehicleId)
    {
        JFrame frame = new JFrame("Car Rental APP - UPDATE BOOKING ");
        frame.setLayout(new GridLayout(6,4,10,10));
//        JLabel idlb = new JLabel("ID");
//        JTextField Idtf = new JTextField(20);

        JLabel pricelb = new JLabel("PRICE");
        JTextField pricetf = new JTextField(20);

        JLabel statuslb = new JLabel("STATUS");
        JTextField statustf = new JTextField(20);

        JLabel datelb = new JLabel("BOOKING DATE");
        JTextField datetf = new JTextField(20);

        JLabel cidlb = new JLabel("CUSTOMER ID");
        JTextField cidtf = new JTextField(20);

        JLabel vidlb = new JLabel("VEHICLE ID");
        JTextField vidtf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(pricelb);
        frame.add(pricetf);
        frame.add(statuslb);
        frame.add(statustf);
        frame.add(datelb);
        frame.add(datetf);
        frame.add(cidlb);
        frame.add(cidtf);
        frame.add(vidlb);
        frame.add(vidtf);
        frame.add(save);
        frame.add(back);

        pricetf.setText(price);
        statustf.setText(status);
        datetf.setText(bookingDate);
        cidtf.setText(customerId);
        vidtf.setText(vehicleId);



        save.addActionListener(e -> {
            bookingService.save(id, pricetf.getText(), statustf.getText(), datetf.getText(), cidtf.getText(), vidtf.getText());
            frame.dispose();
            new BookingUi();
        });

        back.addActionListener(e->{
            frame.dispose();
            new BookingUi();
        });


        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }}


