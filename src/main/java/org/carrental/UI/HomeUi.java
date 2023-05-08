package org.carrental.UI;
import javax.swing.*;
import java.awt.*;


public class HomeUi {
    public static void main(String[] args) {
        new HomeUi();
    }
//

         public HomeUi() {
         JFrame frame  = new JFrame("HOME-PAGE");
         JPanel buttonPanel = new JPanel();

         //                                                CUSTOMER


             JButton customer = new JButton("CUSTOMER");
             addImageOnButton(customer,"src/main/resources/employees-users-icon.png",100,100);
             buttonPanel.add(customer);
             customer.addActionListener((event)->{
            try {
                frame.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            new CustomersUi();
        });

             //                                                VEHICLE


        JButton vehicle = new JButton("VEHICLE");
        addImageOnButton(vehicle,"src/main/resources/cars-icon.png",100,100);

        buttonPanel.add(vehicle);
        vehicle.addActionListener((event)->{
            try {
                frame.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            new VehicleUi();
        });


        //                                                    BOOKING


        JButton booking = new JButton("BOOKING");
        buttonPanel.add(booking);
        addImageOnButton(booking,"src/main/resources/booking-icon.png",100,100);

        booking.addActionListener((event)->{
            try {
                frame.dispose();
                new BookingUi();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            //
            //
            //                                             OWNER
        });
        JButton owner = new JButton("OWNER");
        buttonPanel.add(owner);
        addImageOnButton(owner,"src/main/resources/user-key-icon.png",100,100);

        owner.addActionListener((event)->{
            try {
                frame.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            new OwnerUi();
        });


                                                      //USER

             JButton user = new JButton("USER");
        buttonPanel.add(user);
        addImageOnButton(user,"src/main/resources/administrator-developer-icon.png",100,100);

        user.addActionListener((event)->{
            try {
                frame.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            new UserUi();
        });

//                                                     LOGOUT

        JButton logout = new JButton("LOGOUT");
        buttonPanel.add(logout);
        addImageOnButton(logout,"src/main/resources/music-switch-on-off-button-icon.png",100,100);

        logout.addActionListener((event)->{
            try {
                frame.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            new LoginUI();
        });

        //                                              REPORTS

             JButton reports = new JButton("REPORTS");
             buttonPanel.add(reports);
             addImageOnButton(reports,"src/main/resources/data-sheet-icon.png",100,100);

             reports.addActionListener((event)->{
                 try {
                     frame.dispose();
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
                 new ReportsUi();
             });


             frame.setSize(1000, 500);
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
          }

//                                        SET AND ADD IMAGE ICON
    private static void addImageOnButton(JButton button,String imagePath,int height,int width){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));

    }

}