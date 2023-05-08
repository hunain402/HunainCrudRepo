package org.carrental.UI;

import com.toedter.calendar.JDateChooser;
import org.carrental.dao.BookingDao;
import org.carrental.domain.Bookingdetails;
import org.carrental.service.BookingService;
import org.carrental.service.PdfGenerateVehicle;
import org.carrental.service.PdfGeneratorMonthly;
import org.carrental.service.VehicleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class carAvailibityReport extends JFrame {


//    public carAvailibityReport() {
//        setTitle("Availibility Reports");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 10));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        inputPanel.add(Box.createHorizontalStrut(10));
//        JTable dataTable = new JTable();
//        String[][] data = new VehicleService().getAllAvailableV();
//        DefaultTableModel model = new DefaultTableModel(data, new String[]{"vehicle id", "vehicle name"});
//        dataTable.setModel(model);
//
//
//        try {
//            PdfGenerateVehicle.generatePDF(dataTable, "Monthly.pdf");
//            File file = new File("Monthly.pdf");
//            if (file.exists()) {
//                Desktop.getDesktop().open(file);
//            } else
//                System.out.println("file not found");
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//        getContentPane().setLayout(new BorderLayout());
//        getContentPane().add(inputPanel, BorderLayout.NORTH);
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
}
