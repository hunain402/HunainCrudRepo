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

//        JLabel startDateLabel = new JLabel("From:");
//        startDateChooser = new JDateChooser();
      //  startDateChooser.addPropertyChangeListener("date", evt -> updateEndDate());
        JLabel endDateLabel = new JLabel("End DATE");
        endDateChooser = new JDateChooser();
        //endDateChooser.addPropertyChangeListener("date", evt -> updateStartDate());
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
       // startDateChooser.setSelectableDateRange(Date.from(oneYearAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()), null);
        endDateChooser.setSelectableDateRange(Date.from(oneYearAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()), null);

      //  inputPanel.add(startDateLabel);
//        inputPanel.add(startDateChooser);
//        inputPanel.add(Box.createHorizontalStrut(10));
        inputPanel.add(endDateLabel);
        inputPanel.add(endDateChooser);
        inputPanel.add(Box.createHorizontalStrut(10));

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
new BookingService().completeBookingService(id,endDateChooser);
            dispose();
            new BookingUi();
        });
        buttonPanel.add(submitButton);
//
//        JPanel tablePanel = new JPanel(new BorderLayout());
//        dataTable = new JTable();
//        JScrollPane scrollPane = new JScrollPane(dataTable);
//        tablePanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
     //   getContentPane().add(tablePanel, BorderLayout.EAST);

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
//    private void updateStartDate() {
//        LocalDate endDate = endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate startDate = endDate.minusDays(30);
//        Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        startDateChooser.setDate(startDateAsDate);
//        startDateChooser.setSelectableDateRange(null, endDateChooser.getDate());
//    }

    private void generateReport() {
        // TODO: write code to generate report
    }
}






