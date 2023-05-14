package org.carrental.UI;

import org.carrental.service.BookingService;
import org.carrental.service.PdfGenerateVehicle;
import org.carrental.service.VehicleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class ReportsUi extends JFrame {
    public static void main(String[] args) {
       new ReportsUi();
    }
BookingService bookingService = new BookingService();
    private JTable dataTable;

    public ReportsUi() {
        JFrame frame = new JFrame("REPORTS");
        JPanel buttonPanel = new JPanel();

//                                           MONTHLY BUTTON
        JButton reports = new JButton();
        buttonPanel.add(reports);
        addImageOnButton(reports, "src/main/resources/one-month-icon.png", 100, 100);

        reports.addActionListener((event) -> {
            try {
                frame.dispose();
                new MonthlyUi();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

//                                              COMMISION REPORT

        JButton commision = new JButton("COMMISION");
        buttonPanel.add(commision);
        addImageOnButton(commision, "src/main/resources/commission-discounts-icon.png", 100, 100);

        commision.addActionListener((event) -> {
          frame.dispose();
new CommisionUi();
        });


//                                               CAR AVAILABILITY REPORT

        JButton carAvailability = new JButton("AVAILABILITY");
        buttonPanel.add(carAvailability);
        addImageOnButton(carAvailability, "src/main/resources/business-rules-icon.png", 100, 100);

        carAvailability.addActionListener((event) -> {
            frame.dispose();
            setTitle("Availibility Reports");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            inputPanel.add(Box.createHorizontalStrut(10));
            JTable dataTable = new JTable();
            String[][] data = new VehicleService().getAllAvailableV();
            DefaultTableModel model = new DefaultTableModel(data, new String[]{"vehicle id", "vehicle name"});
            dataTable.setModel(model);


            try {
                PdfGenerateVehicle.generatePDF(dataTable, "Monthly.pdf");
                File file = new File("Monthly.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else
                    System.out.println("file not found");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            getContentPane().setLayout(new BorderLayout());
           // getContentPane().add(inputPanel, BorderLayout.NORTH);
            pack();
            setLocationRelativeTo(null);
            //setVisible(true);
        }
        );

//                                                      ANALYTICS REPORTS
        JButton analytics = new JButton("ANALYTICS");
        buttonPanel.add(analytics);
        addImageOnButton(analytics, "src/main/resources/chart-icon.png", 100, 100);

        analytics.addActionListener((event) -> {
            try {
                frame.dispose();
                new AnalyticsReportsUi();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        JButton yearly = new JButton("YEARLY REPORT");
        buttonPanel.add(yearly);
        addImageOnButton(yearly, "src/main/resources/image-outline-icon.png", 100, 100);

        yearly.addActionListener((event) -> {
            frame.dispose();
            new AnnualReportUi();
        });



        JButton back = new JButton("back");
        addImageOnButton(back,"src/main/resources/add-note-icon.png",40,40);

        back.addActionListener(e->{
            frame.dispose();
            new HomeUi();
        });
        buttonPanel.add(back);



        frame.setSize(1000, 500);
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //                                        SET AND ADD IMAGE ICON
    private static void addImageOnButton(JButton button, String imagePath, int height, int width) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));

    }

}