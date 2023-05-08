package org.carrental.UI;

import com.toedter.calendar.JCalendar;
import org.carrental.dao.BookingDao;
import org.carrental.dao.VehicleDao;
import org.carrental.service.BookingService;

import javax.swing.*;
import java.awt.*;

public class AddBookingUi {

    BookingService bookingService = new BookingService();
BookingDao bookingDao = new BookingDao();
VehicleDao vehicleDao = new VehicleDao();

    public static void main(String[] args) {

        new AddBookingUi();
    }


    public AddBookingUi(){
        JFrame frame = new JFrame("Rental Car App | Add BOOKING");
        frame.setLayout(new GridLayout(6, 2, 10, 30));
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

//                                                             ADD BOOKING UI
        JLabel pricelb = new JLabel(" PRICE");
        JTextField pricetf = new JTextField();



        JLabel datelb = new JLabel("Booking date");
       // JTextField datepicker = new JTextField();
        JCalendar datepicker = new JCalendar();

        String[] cidData = bookingService.getCustomerIdforDropDown();
        JLabel vidlb = new JLabel("VEHICLE ID");
        JComboBox<String> dropdowncustomerId = new JComboBox<>(cidData);

        String[] vidData = bookingService.getVehicleIdAndNameForDropDown();
        JLabel cidlb = new JLabel("CUSTOMER ID");
        JComboBox<String> dropdownvehicleId = new JComboBox<>(vidData);


        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(pricelb);
        frame.add(pricetf);
//        frame.add(statuslb);
//        frame.add(dropdownstatusId);
        frame.add(datelb);
        frame.add(datepicker);
        frame.add(cidlb);
        frame.add(dropdowncustomerId);
        frame.add(vidlb);
        frame.add(dropdownvehicleId);

        frame.add(save);
        frame.add(back);


        back.addActionListener(e -> {
            frame.dispose();
            new BookingUi();
        });

        save.addActionListener(e -> {
            String selectedCustomerId = (String) dropdowncustomerId.getSelectedItem();
            String[] parts = selectedCustomerId.split(" ");
            String customerId = parts[0]; // get the customer ID from the selected item
            String selectedVehicleId = (String) dropdownvehicleId.getSelectedItem();
            parts = selectedVehicleId.split(" ");
            String vehicleId = parts[0]; // get the vehicle ID from the selected item

            Integer bookingAmount = 0;
            Boolean flag = true;
            if (Integer.valueOf(pricetf.getText()) < 0) {
                JOptionPane.showMessageDialog(frame, "Amount should not be negative");
                flag = false;
            } else {
                bookingAmount = Integer.valueOf(pricetf.getText());
            }
            if (flag) {
                bookingService.add(customerId, vehicleId, datepicker.getDate(), String.valueOf(bookingAmount));
                frame.dispose();
                new BookingUi();
            }


        });

    }
}