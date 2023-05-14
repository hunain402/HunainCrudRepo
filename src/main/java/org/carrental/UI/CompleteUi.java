package org.carrental.UI;

import com.toedter.calendar.JDateChooser;
import org.carrental.service.BookingService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CompleteUi extends JFrame{



    private static JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private JTable dataTable;


    public CompleteUi(String id){

        setTitle("COMPLETE BOOKING");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JLabel endDateLabel = new JLabel("End DATE");
        endDateChooser = new JDateChooser();
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        endDateChooser.setSelectableDateRange(Date.from(oneYearAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()), null);


        inputPanel.add(endDateLabel);
        inputPanel.add(endDateChooser);
        inputPanel.add(Box.createHorizontalStrut(10));

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");


//submit button
        submitButton.addActionListener(e -> {
new BookingService().completeBookingService(Long.valueOf(id),endDateChooser);
            dispose();
            new BookingUi();
        });
        buttonPanel.add(submitButton);

        JButton back = new JButton("back");

        back.addActionListener(e->{
            dispose();
            new HomeUi();
        });
        buttonPanel.add(back);


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateEndDate() {
        LocalDate startDate = LocalDate.now();
        if (startDateChooser.getDate() != null) {
            startDate = startDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        LocalDate endDate = startDate.plusDays(30);
        Date endDateAsDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        endDateChooser.setDate(endDateAsDate);
        endDateChooser.setSelectableDateRange(startDateChooser.getDate(), null);
    }

    private void generateReport() {
        // TODO: write code to generate report
    }
}






