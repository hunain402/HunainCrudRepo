package org.carrental.UI;

import org.carrental.service.OwnerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;

public class AddVehicleUi {
    public static void main(String[] args) {
        new AddVehicleUi();
    }
    private final VehicleService vehicleService = new VehicleService();
    public AddVehicleUi(){
        JFrame frame = new JFrame(" VEHICLE REGISTRATION");
        frame.setLayout(new GridLayout(6,4,10,10));

        JLabel nameLb = new JLabel("VEHICLE NAME");
        JTextField nameTf = new JTextField(20);

        JLabel modellb = new JLabel("MODEL");
        JTextField modeltf = new JTextField(20);

        JLabel brandlb = new JLabel("brand");
        JTextField brandtf = new JTextField(20);

        JLabel colourlb  = new JLabel("colour");
        JTextField colourtf = new JTextField(20);

//        JLabel owneridlb = new JLabel("owner id");
//        JTextField owneriidtf = new JTextField(20);
        String[] oidData = new OwnerService().getOwnerIdAndNameForDropDown();
        JLabel oidlb = new JLabel("OWNER ID");
        JComboBox<String> dropdownOwnerId = new JComboBox<>(oidData);


        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(modellb);
        frame.add(modeltf);
        frame.add(brandlb);
        frame.add(brandtf);
        frame.add(colourlb);
        frame.add(colourtf);
        frame.add(oidlb);
        frame.add(dropdownOwnerId);
        frame.add(save);
        frame.add(back);
//back button
        back.addActionListener(e->{
            frame.dispose();
            new VehicleUi();
        });

        save.addActionListener(e->{

            try {
                vehicleService.save(nameTf.getText(), modeltf.getText(),
                        brandtf.getText(), colourtf.getText(), String.valueOf(dropdownOwnerId.getSelectedItem()));
                frame.dispose();
                new VehicleUi();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,"Unable to save");
            }

        });

        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
