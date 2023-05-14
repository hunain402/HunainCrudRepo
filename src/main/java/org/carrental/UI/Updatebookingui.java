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
        //JTextField cidtf = new JTextField(20);
        String[] cidData = bookingService.getCustomerIdforDropDown();
        JComboBox<String> dropdowncustomerId = new JComboBox<>(cidData);

       JLabel vidlb = new JLabel("VEHICLE ID");
//        JTextField vidtf = new JTextField(20);
        String[] vidData = bookingService.getVehicleIdAndNameForDropDown();
        JComboBox<String> dropdownvehicleId = new JComboBox<>(vidData);



        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(pricelb);
        frame.add(pricetf);
        frame.add(statuslb);
        frame.add(statustf);
        frame.add(datelb);
        frame.add(datetf);
        frame.add(cidlb);
        frame.add(dropdowncustomerId);
        frame.add(vidlb);
        frame.add(dropdownvehicleId);
        frame.add(save);
        frame.add(back);

        pricetf.setText(price);
        statustf.setText(status);
        datetf.setText(bookingDate);
        dropdowncustomerId.setSelectedItem(customerId);
        dropdownvehicleId.setSelectedItem(vehicleId);



        save.addActionListener(e -> {
            String selectedCustomerId = (String) dropdowncustomerId.getSelectedItem();
            String[] parts = selectedCustomerId.split(" ");
            String customerIdSplit = parts[0]; // get the customer ID from the selected item
            String selectedVehicleId = (String) dropdownvehicleId.getSelectedItem();
            parts = selectedVehicleId.split(",");
            String vehicleIdSplit = parts[0]; // get the vehicle ID from the selected item



            bookingService.save(id, pricetf.getText(), statustf.getText(), datetf.getText(),customerIdSplit,vehicleIdSplit);
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


