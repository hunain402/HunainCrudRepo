package org.carrental.UI;

import javax.swing.*;
import java.awt.*;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class HomeUi {
    public static void main(String[] args) {
        new HomeUi();
    }

    public HomeUi() {
        JFrame frm  = new JFrame("Homepage");

        JButton customerBtn=new JButton("customer");
             customerBtn.addActionListener(button->{
                 try {
                     dispose();
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
                 new CustomersUi();
             });                                    // customerBtn.setIcon(new ImageIcon("customers-icon-35912 (1).png"));
        frm.add(customerBtn);
       JButton vehicleBtn= new JButton("vehicle");
        frm.add(vehicleBtn);
        JButton bookingBtn=new JButton("Booking");
        frm.add(bookingBtn);


        frm.setSize(500, 500);
        frm.setLayout(new FlowLayout(FlowLayout.LEFT,20,25));
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
          }

}