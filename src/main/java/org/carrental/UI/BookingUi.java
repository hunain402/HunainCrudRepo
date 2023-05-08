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
        addImageOnButton(add,"src/main/resources/add-note-icon.png",100,100);
        btnPanel.add(add);

        add.addActionListener((event)->{
            frame.dispose();
            new AddBookingUi();
        });


//EDIT BUTTON
        JButton edit = new JButton("Update");
        btnPanel.add(edit);
        addImageOnButton(edit,"src/main/resources/data-update-icon.png",100,100);


//BACK BUTTON
        JButton back = new JButton("Back");
        btnPanel.add(back);
        addImageOnButton(back,"src/main/resources/curved-arrow-back-outline-icon.png",100,100);

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUi();

        });

//DELETE BUTTON

        JButton delete = new JButton("Delete");
        btnPanel.add(delete);
        addImageOnButton(delete,"src/main/resources/delete-icon.png",100,100);


//complete BUTTON

        JButton  completeBooking = new JButton("Complete Booking");
        btnPanel.add( completeBooking);
        addImageOnButton( completeBooking,"src/main/resources/complete-icon.png",100,100);



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




//        genratepdf.addActionListener((event)->{
//            try {
//                pdfGeneratorbooking.generatePDF(jt);
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//            try {
//                File file = new File("booking.pdf");
//                if (file.exists()) {
//                    Desktop.getDesktop().open(file);
//                } else {
//                    System.out.println("File not Found");
//                }
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//EDIT ACTION

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

        completeBooking.addActionListener (e1->{
                    if (jt.getSelectedRow() >= 0) {
                        String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                 frame.dispose();
                 new CompleteUi(id);
                    }
                    else  {
                        JOptionPane.showMessageDialog(frame, "Please select the row");
        }
    });

//SCROLL
        JScrollPane sp=new JScrollPane(jt);
        tblAndSearchPanel.add(sp);


        frame.setLayout(new GridLayout(1,3,150,5));
        frame.add(btnPanel);
        frame.add(tblAndSearchPanel);

        frame.setSize(1500,1000);
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
