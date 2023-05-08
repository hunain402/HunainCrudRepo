package org.carrental.UI;
import org.carrental.dao.BaseDao;
import org.carrental.service.CustomerService;
import org.carrental.service.PdfGenerateVehicle;
import org.carrental.service.PdfGeneratorcustomer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class CustomersUi extends BaseDao {
    public static void main(String[] args) {
        new CustomersUi();
    }
    private final CustomerService customerService = new CustomerService();

    public CustomersUi(){

//ADD FRAME AND BUTTON LAYOUT
        JFrame frame = new JFrame("CUSTOMER PANEL");
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(8,1,10,10));

//ADD BUTTON
        JButton add = new JButton("New Registration");
        addImageOnButton(add,"src/main/resources/add-note-icon.png",100,100);
        btnPanel.add(add);

// ADD BUTTON FUNCTION PERFORM
        add.addActionListener((event)->{
                frame.dispose();
               new AddCustomerUI();
            });


//BACK BUTTON
        JButton back = new JButton("Back");
        btnPanel.add(back);
        addImageOnButton(back,"src/main/resources/curved-arrow-back-outline-icon.png",100,100);

//BACK BUTTON FUNCTION PERFORM
        back.addActionListener(e -> {
            frame.dispose();
            new HomeUi();
        });

// DELETE BUTTON
        JButton delete = new JButton("Delete");
        addImageOnButton(delete,"src/main/resources/delete-icon.png",100,100);
        btnPanel.add(delete);



//        JButton genratepdf = new JButton("GENERATE PDF");
//        btnPanel.add(genratepdf);
//        addImageOnButton(genratepdf,"src/main/resources/icons8-pdf-50.png",100,100);


//TABLE AND SEARCH PANEL

        JPanel tblAndSearchPanel = new JPanel();

        tblAndSearchPanel.setBackground(Color.BLUE);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

//SEARCH TEXT FIELD
        JTextField searchTf = new JTextField(30);

//SEARCH BUTTON
        JButton search = new JButton("Search");
        addImageOnButton(search,"src/main/resources/website-search-icon.png",14,14);
        tblAndSearchPanel.add(search);

        String[] columnName = {"ID","NAME","NUMBER","CNIC","ADDRESS","REFERENCE"};
        String[][] data = customerService.getAllCustomerForJTable();

//TABLE
        JTable jt=new JTable(data,columnName);
        jt.setBounds(30,40,200,300);
        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(jt);
//EDIT BUTTON
        JButton edit = new JButton("Update");
        addImageOnButton(edit,"src/main/resources/data-update-icon.png",100,100);
        btnPanel.add(edit);

//EDIT ACTION

        edit.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
               String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                String name = (String) jt.getValueAt(jt.getSelectedRow(), 1);
                String phoneNo = (String) jt.getValueAt(jt.getSelectedRow(), 2);
                String cnic = (String) jt.getValueAt(jt.getSelectedRow(), 3);
                String address = (String) jt.getValueAt(jt.getSelectedRow(), 4);
                String refNo = (String) jt.getValueAt(jt.getSelectedRow(), 5);
                frame.dispose();
                new UpdatecustomerUi(id, name, phoneNo, cnic, address, refNo);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the field");
            }
        });

//DELETE
            delete.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                customerService.softDelete(id);   //delete(id);
                DefaultTableModel dtmDelete = new DefaultTableModel(customerService.getAllCustomerForJTable(), columnName);
                jt.setModel(dtmDelete);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the row");
            }
        });

//PDF ACTION
//        genratepdf.addActionListener((event) -> {
//            try {
//                PdfGeneratorcustomer.generatePDF(jt);
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//            try {
//                File file = new File("Customer.pdf");
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
//
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
        String[][] data =  customerService.searchByName(searchTf.getText());
        DefaultTableModel dtm = new DefaultTableModel(data,columnName);
        jt.setModel(dtm);

    }

});
    }

    //*******************************************   ADD AND SIZE OF IMAGE ICON        ***********************
    private static void addImageOnButton(JButton button,String imagePath,int height,int width) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));
    }

}
