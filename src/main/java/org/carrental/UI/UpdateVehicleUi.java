package org.carrental.UI;

import org.carrental.service.OwnerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;

public class UpdateVehicleUi{

        private final VehicleService vehicleService = new VehicleService();

        public UpdateVehicleUi(String id,String name, String model, String brand, String colour, String ownerId) {

                JFrame frame = new JFrame("Car Rental APP - UPDATE vehicle ");
                // JFrame frame = new JFrame("Car Rental APP - ADD CUSTOMER ");
                frame.setLayout(new GridLayout(6,4,10,10));
                JLabel IdLb = new JLabel("ID");
                JTextField Idtf = new JTextField(20);

                JLabel nameLb = new JLabel("NAME");
                JTextField nameTf = new JTextField(20);
                JLabel modellb = new JLabel("model");
                JTextField modeltf = new JTextField(20);
                JLabel brandlb = new JLabel("brand");
                JTextField brandtf = new JTextField(20);
                JLabel colourlb = new JLabel("colour");
                JTextField colourtf = new JTextField(20);
//                JLabel owneridlb = new JLabel("owner id");
                String[] oidData = new OwnerService().getOwnerIdAndNameForDropDownV();
                JLabel oidlb = new JLabel("OWNER ID");
                JComboBox<String> dropdownOwnerId = new JComboBox<>(oidData);
               // JTextField ownerIdtf = new JTextField(20);

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

               //
                nameTf.setText(name);
                modeltf.setText(model);
                brandtf.setText(brand);
                colourtf.setText(colour);
                dropdownOwnerId.setToolTipText(ownerId);

                save.addActionListener(e -> {
                        String abc = (String) dropdownOwnerId.getSelectedItem();
                        String[] partsVehicle = abc.split(",");
                        Long ownerid = Long.valueOf(partsVehicle[0]);


                        vehicleService.update(id, nameTf.getText(), Long.parseLong(modeltf.getText()), brandtf.getText(),
                                colourtf.getText(),ownerid);
                        frame.dispose();
                        new VehicleUi();
                });

                back.addActionListener(e->{
                        frame.dispose();
                        new VehicleUi();
                });


                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

        }}