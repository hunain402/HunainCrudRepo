package org.carrental.UI;
import org.carrental.dao.BaseDao;

import org.carrental.service.BookingService;
import org.carrental.service.PdfGeneratorcustomer;
import org.carrental.service.pdfGeneratorbooking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import static org.carrental.service.PdfGeneratorcustomer.generatePDF;

public class BookingUi extends BaseDao {

   public static void main(String[] args) {
       new BookingUi();
    }

    private final BookingService bookingService = new BookingService();

    public BookingUi(){
//J FRAME/J BUTTON
        JFrame frame = new JFrame("Booking Panel");
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(8,1,10,10));
//ADD BUTTON
        JButton add = new JButton("Add Booking");
        addImageOnButton(add,"src/main/resources/add-note-icon.png",40,40);
        btnPanel.add(add);

        add.addActionListener((event)->{
            frame.dispose();
            new AddBookingUi();
        });


//EDIT BUTTON
        JButton edit = new JButton("Update");
        btnPanel.add(edit);
        addImageOnButton(edit,"src/main/resources/data-update-icon.png",40,40);


//BACK BUTTON
        JButton back = new JButton("Back");
        btnPanel.add(back);
        addImageOnButton(back,"src/main/resources/curved-arrow-back-outline-icon.png",40,40);

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUi();

        });

//DELETE BUTTON

        JButton delete = new JButton("Delete");
        btnPanel.add(delete);
        addImageOnButton(delete,"src/main/resources/delete-icon.png",40,40);


//complete BUTTON

        JButton  completeBooking = new JButton("Complete Booking");
        btnPanel.add( completeBooking);
        addImageOnButton( completeBooking,"src/main/resources/complete-icon.png",40,40);



//                                            TABLE AND SEARCH PANEL

        JPanel tblAndSearchPanel = new JPanel();

        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        JTextField searchTf = new JTextField("search",30);
//SEARCH BUTTON
        JButton search = new JButton();
        tblAndSearchPanel.add(search);
        addImageOnButton(search,"src/main/resources/website-search-icon.png",20,20);

//TABLE DATA

        String column[]={"ID","PRICE","STATUS","BOOKING DATE","CUSTOMER NAME","VEHICLE NAME","END DATE"};
       // String[][] data = bookingService.getAllBookingForJTable();
        String[][] data = bookingService.getAllBookingForJTable();

//TABLE
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(jt);





        edit.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                String price = (String) jt.getValueAt(jt.getSelectedRow(), 1);
                String status  = (String) jt.getValueAt(jt.getSelectedRow(), 2);
                String bookingDate  = (String) jt.getValueAt(jt.getSelectedRow(), 3);
                String customerId = (String) jt.getValueAt(jt.getSelectedRow(), 4);
                String vehicleId = (String) jt.getValueAt(jt.getSelectedRow(), 5);
                frame.dispose();
                new Updatebookingui(id, price, status, bookingDate, customerId, vehicleId);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the field");
            }
        });

        //DELETE
        delete.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                bookingService.softDelete(id);
                DefaultTableModel dtmDelete = new DefaultTableModel(bookingService.getAllBookingForJTable(), column);
                jt.setModel(dtmDelete);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the row");
            }
        });

        completeBooking.addActionListener(e1 -> {
            AddBookingUi addBookingUi= new AddBookingUi();
            if (jt.getSelectedRow() >= 0) {
                String id = String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0));

                    frame.dispose();
                new CompleteUi(id);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a field");
            }
        });


//SCROLL
        JScrollPane sp=new JScrollPane(jt);
        sp.setPreferredSize(new Dimension(1000, 500));

        tblAndSearchPanel.add(sp);



        // frame.setLayout(new GridLayout(1,3,150,5));
        frame.setLayout(new GridLayout(2, 2, 50, 20));

        frame.add(btnPanel);
        frame.add(tblAndSearchPanel);

        frame.setSize(1200, 800);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        searchTf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String[][] data =  bookingService.searchByPrice(Double.parseDouble(searchTf.getText()));
                DefaultTableModel dtm = new DefaultTableModel(data,column);
                jt.setModel(dtm);

            }

        });
    }
    //******************************************************************
    public static void addImageOnButton(JButton button,String imagePath,int height,int width) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));
    }

}
