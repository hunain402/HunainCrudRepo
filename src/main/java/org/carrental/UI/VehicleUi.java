package org.carrental.UI;

import org.carrental.service.PdfGenerateVehicle;
import org.carrental.service.PdfGeneratorcustomer;
import org.carrental.service.VehicleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class VehicleUi {
    public static void main(String[] args) {
        new VehicleUi();
    }

    VehicleService vehicleService = new VehicleService();
    public VehicleUi() {
        JFrame frame = new JFrame("Vehicle Panel");
//BUTTON PANEL
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(8, 1, 10, 10));
//BUTTON
        JButton ADD = new JButton("New Registration");
        btnPanel.add(ADD);
        addImageOnButton(ADD, "src/main/resources/add-note-icon.png", 100, 100);

        ADD.addActionListener((event) -> {
            frame.dispose();
            new AddVehicleUi();
        });
//edit
        JButton edit = new JButton("Update");
        btnPanel.add(edit);
        addImageOnButton(edit, "src/main/resources/data-update-icon.png", 100, 100);



        JButton back = new JButton("Back");
        btnPanel.add(back);
        addImageOnButton(back, "src/main/resources/curved-arrow-back-outline-icon.png", 100, 100);

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUi();

        });

        JButton delete = new JButton("Delete");
        btnPanel.add(delete);
        addImageOnButton(delete, "src/main/resources/delete-icon.png", 100, 100);

//pdf button
//        JButton  genratepdf = new JButton("GENERATE PDF");
//        btnPanel.add( genratepdf);
//        addImageOnButton( genratepdf,"src/main/resources/icons8-pdf-50.png",100,100);



        JPanel tblAndSearchPanel = new JPanel();

        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextField searchTf = new JTextField("search", 30);

        JButton search = new JButton();
        tblAndSearchPanel.add(search);
        search.addActionListener(e -> {
        });
        addImageOnButton(search, "src/main/resources/website-search-icon.png", 20, 20);


        String column[] = {"id","NAME", "MODEL", "BRAND", "COLOUR", "OWNER NAME"};
        String[][] data = vehicleService.getAllVehicleForJtable();

        JTable jt = new JTable(data, column);
        jt.setBounds(30, 40, 200, 300);
        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(jt);

        edit.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                String name = (String) jt.getValueAt(jt.getSelectedRow(), 1);
                String model  = (String) jt.getValueAt(jt.getSelectedRow(), 2);
                String brand  = (String) jt.getValueAt(jt.getSelectedRow(), 3);
                String colour = (String) jt.getValueAt(jt.getSelectedRow(), 4);
                String ownerId = (String) jt.getValueAt(jt.getSelectedRow(), 5);
                frame.dispose();
                new UpdateVehicleUi(id,name, model, brand, colour, ownerId);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the field");
            }
        });
        //DELETE

        delete.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                Long id = Long.parseLong((String) jt.getValueAt(jt.getSelectedRow(), 0));
                vehicleService.softDelete(id);
                DefaultTableModel dtmDelete = new DefaultTableModel(vehicleService.getAllVehicleForJtable(), column);
                jt.setModel(dtmDelete);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the row");
            }
        });


        JScrollPane sp = new JScrollPane(jt);
        tblAndSearchPanel.add(sp);
//pdf
//        genratepdf.addActionListener((event) -> {
//            try {
//                PdfGenerateVehicle.generatePDF(jt,"Vehicle.pdf");
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//            try {
//                File file = new File("Vehicle.pdf");
//                if (file.exists()) {
//                    Desktop.getDesktop().open(file);
//                } else {
//                    System.out.println("File not Found");
//                }
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//
//        });



        frame.setLayout(new GridLayout(1, 3, 150, 5));
        frame.add(btnPanel);
        frame.add(tblAndSearchPanel);
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    private static void addImageOnButton(JButton button, String imagePath, int height, int width) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));
    }
}
