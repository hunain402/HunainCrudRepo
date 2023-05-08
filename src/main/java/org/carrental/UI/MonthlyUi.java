package org.carrental.UI;

import com.toedter.calendar.JDateChooser;
import org.carrental.dao.BookingDao;
import org.carrental.domain.Bookingdetails;
import org.carrental.service.BookingService;
import org.carrental.service.PdfGeneratorMonthly;

import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MonthlyUi extends JFrame {

    private final BookingService bookingService = new BookingService();

     static JDateChooser startDateChooser;
    private JTable dataTable;

    static   JDateChooser endDateChooser;

    public static void main(String[] args) {
        new MonthlyUi();
    }
    public MonthlyUi() {
        setTitle("MONTHLY Reports");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel startDateLabel = new JLabel("From:");
        startDateChooser = new JDateChooser();
        startDateChooser.addPropertyChangeListener("date", evt -> updateEndDate());
        JLabel endDateLabel = new JLabel("To:");
        endDateChooser = new JDateChooser();
        endDateChooser.addPropertyChangeListener("date", evt -> updateStartDate());
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        startDateChooser.setSelectableDateRange(Date.from(oneYearAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()), null);
        endDateChooser.setSelectableDateRange(Date.from(oneYearAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()), null);

        inputPanel.add(startDateLabel);
        inputPanel.add(startDateChooser);
        inputPanel.add(Box.createHorizontalStrut(10));
        inputPanel.add(endDateLabel);
        inputPanel.add(endDateChooser);
        inputPanel.add(Box.createHorizontalStrut(10));

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            dispose();
            String[][] data = bookingService.getAll30DaysBookingForJTable(startDateChooser,endDateChooser);
            DefaultTableModel model = new DefaultTableModel(data, new String[]{"ID","PER DAY PRICE","STATUS","BOOKING DATE","CUSTOMER NAME","VEHICLE NAME","END DATE","TOTAL PRICE","no of days"});
            dataTable.setModel(model);
           Double totalProfit = bookingService.totalProfit(startDateChooser,endDateChooser);

           List<Bookingdetails> commision = new BookingDao().totalCommission(startDateChooser,endDateChooser);

           try {
                PdfGeneratorMonthly.generatePDF(dataTable,totalProfit,commision,startDateChooser,endDateChooser);
                File file = new File("Monthly.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                }else
                    System.out.println("file not found");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(submitButton);

        JButton back = new JButton("back");
        back.addActionListener(e->{
            new ReportsUi();
        });
        buttonPanel.add(back);



        JPanel tablePanel = new JPanel(new BorderLayout());
        dataTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(dataTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        //getContentPane().add(tablePanel, BorderLayout.EAST);

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
    private void updateStartDate() {
        LocalDate endDate = endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate startDate = endDate.minusDays(30);
        Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        startDateChooser.setDate(startDateAsDate);
        startDateChooser.setSelectableDateRange(null, endDateChooser.getDate());
    }

    private void generateReport() {
        // TODO: write code to generate report
    }
}


