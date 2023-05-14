package org.carrental.UI;

import org.carrental.service.OwnerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OwnerUi{
    public static void main(String[] args) {
        new OwnerUi();
    }
    public OwnerUi() {
        JFrame frame = new JFrame("OWNER PANEL");

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(8,1,10,10));

        JButton add = new JButton();
        btnPanel.add(add);
        addImageOnButton(add,"src/main/resources/add-note-icon.png",100,100);

        add.addActionListener((event)->{
            frame.dispose();
            new AddOwnerUi();
        });

        JButton update = new JButton();
        btnPanel.add(update);
        addImageOnButton(update,"src/main/resources/data-update-icon.png",100,100);



        JButton back = new JButton();
        btnPanel.add(back);
        addImageOnButton(back,"src/main/resources/curved-arrow-back-outline-icon.png",100,100);

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUi();

        });

        JButton delete = new JButton();
        btnPanel.add(delete);
        addImageOnButton(delete,"src/main/resources/delete-icon.png",100,100);


        JButton logout = new JButton();
        btnPanel.add(logout);
        addImageOnButton(logout,"src/main/resources/music-switch-on-off-button-icon.png",100,100);

        logout.addActionListener((event)->{
            try {
                frame.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            new LoginUI();
        });



        JPanel tblAndSearchPanel = new JPanel();

        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        JTextField searchTf = new JTextField("search",30);

        JButton search = new JButton();
        tblAndSearchPanel.add(search);
        search.addActionListener(e -> {

        });
        addImageOnButton(search,"src/main/resources/website-search-icon.png",20,20);


        String column[]={"id","NAME","CNIC","NUMBER","ADDRESS","COMMISION"};

        String [][] data= new OwnerService().getAllOwnerForJTable();

        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);

        delete.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                new OwnerService().softDeleteOwner(id);   //delete(id);
                DefaultTableModel dtmDelete = new DefaultTableModel(new OwnerService().getAllOwnerForJTable(), column);
                jt.setModel(dtmDelete);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the row");
            }
        });

        update.addActionListener(e -> {

            if (jt.getSelectedRow() >= 0) {
                String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                String name = (String) jt.getValueAt(jt.getSelectedRow(), 1);
                String phoneNo = (String) jt.getValueAt(jt.getSelectedRow(), 3);
                String cnic = (String) jt.getValueAt(jt.getSelectedRow(), 2);
                String address = (String) jt.getValueAt(jt.getSelectedRow(), 4);
                String commision = (String) jt.getValueAt(jt.getSelectedRow(), 5);
                frame.dispose();
                new UpdateOwnerUi(id, name, phoneNo, cnic, address, commision);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the field");
            }
        });

        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(jt);
        JScrollPane sp=new JScrollPane(jt);
        tblAndSearchPanel.add(sp);


        frame.setLayout(new GridLayout(1,3,150,5));

        frame.add(btnPanel);

        frame.add(tblAndSearchPanel);

        frame.setSize(1500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
    private static void addImageOnButton(JButton button,String imagePath,int height,int width) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));
    }
}
