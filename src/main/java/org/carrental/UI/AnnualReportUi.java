package org.carrental.UI;

import com.itextpdf.text.DocumentException;
import lombok.SneakyThrows;
import org.carrental.dao.BookingDao;
import org.carrental.domain.Bookingdetails;
import org.carrental.service.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.stream.IntStream;
public class AnnualReportUi extends JFrame {

    private boolean flag;
    ReportService reportService = new ReportService();
    private JTable dataTable;
    private JRadioButton radioOption1;
    private JRadioButton radioOption2;
    private JComboBox<Integer> yearDropdown;
    private JComboBox<String> radioDropdown;
    private JButton submitButton;
    private JButton backbtn;


    public AnnualReportUi() {
        setTitle("Annual Report UI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        // create radio button panel
        JPanel radioPanel = new JPanel(new GridLayout(2, 1));
        radioPanel.setBorder(BorderFactory.createTitledBorder("Select Report Type"));

        radioOption1 = new JRadioButton("VEHICLE REPORT");
        radioOption2 = new JRadioButton("OWNER REPORT");
        radioOption1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (radioOption1.isSelected()) {
                    radioDropdown.setModel(new DefaultComboBoxModel<>(new BookingService().getVehicleIdAndNameForFiveYearDropDown()));
                    flag = true;
                }
            }
        });

        radioOption2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (radioOption2.isSelected()) {
                    radioDropdown.setModel(new DefaultComboBoxModel<>(new OwnerService().getOwnerIdAndNameForDropDown()));
                    flag = false;
                }
            }
        });

        // create button group so that only one radio button can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioOption1);
        buttonGroup.add(radioOption2);

        radioPanel.add(radioOption1);
        radioPanel.add(radioOption2);

        mainPanel.add(radioPanel);

        // create year dropdown panel
        JPanel yearPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        yearPanel.setBorder(BorderFactory.createTitledBorder("Select Year"));

        int currentYear = Year.now().getValue();
        Integer[] yearOptions = IntStream.range(currentYear - 5, currentYear + 1).boxed().toArray(Integer[]::new);
        yearDropdown = new JComboBox<>(yearOptions);
        yearPanel.add(yearDropdown);

        mainPanel.add(yearPanel);


        // create radio dropdown panel
        JPanel radioDropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioDropdownPanel.setBorder(BorderFactory.createTitledBorder("Select Report Subtype"));

        String[] radioOptions = {"Subtype 1", "Subtype 2", "Subtype 3"};
        radioDropdown = new JComboBox<>(radioOptions);
        radioDropdownPanel.add(radioDropdown);

        mainPanel.add(radioDropdownPanel);

        // create submit button panel
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Submit");
        submitPanel.add(submitButton);

        backbtn = new JButton("back");
      //  BookingUi.addImageOnButton(backbtn,"src/main/resources/add-note-icon.png",40,40);

        submitPanel.add(backbtn);
       // backbtn.add(backbtn);

        mainPanel.add(submitPanel);

        // add action listener to the submit button

        submitButton.addActionListener(e -> {
            submitButton.addActionListener(new ActionListener() {
                @SneakyThrows
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (flag) {

                            JTable VehicleTable = new JTable();
                            String[][] data = reportService.yearlyRepVehicle((Integer) yearDropdown.getSelectedItem(), (String) radioDropdown.getSelectedItem());
                            String[] headers = {"Vehicel-Name", "Owner name", "Per day Price", "owner commision",
                                    "booking date","end_date","noOfDays","total_amount","Commision-price","profit"};
                            DefaultTableModel dtm = new DefaultTableModel(data, headers);
                            JTable jt = new JTable(dtm);
                            JScrollPane sp = new JScrollPane(jt);

                            DefaultTableModel tableModelOwner = new DefaultTableModel(data, headers);
                            VehicleTable.setModel(tableModelOwner);

                            Double totalCommssion = reportService.totalprofitOfVehice((Integer) yearDropdown.getSelectedItem(), (String) radioDropdown.getSelectedItem());

                            PdfGenerateVehicle.yearlyRep("Yearly Report", VehicleTable, "yearly_Reportvehicle.pdf", totalCommssion);
                            File file = new File("yearly_Reportvehicle.pdf");
                            if (file.exists()) {
                                Desktop.getDesktop().open(file);
                            } else {
                                throw new FileNotFoundException("file not found");
                            }




                            //vehicle
                        } else {
                            //owner
                            JTable tableOwner = new JTable();
                            String[][] data = reportService.yearlyRepOwner((Integer) yearDropdown.getSelectedItem(), (String) radioDropdown.getSelectedItem());
                            String[] column = {"Owner-Name", "Commission-percent", "Vehicle-Name", "Booking-date", "Complete-date", "Commision-price"};
                            DefaultTableModel dtm = new DefaultTableModel(data, column);
                            JTable jt = new JTable(dtm);
                            JScrollPane sp = new JScrollPane(jt);

                            DefaultTableModel tableModelOwner = new DefaultTableModel(data, column);
                            tableOwner.setModel(tableModelOwner);

                            Double totalCommssion = reportService.totalCommssionOfOwner((Integer) yearDropdown.getSelectedItem(), (String) radioDropdown.getSelectedItem());

                            PdfGenerateVehicle.yearlyRep("Yearly Report", tableOwner, "yearly_Report.pdf", totalCommssion);
                            File file = new File("yearly_Report.pdf");
                            if (file.exists()) {
                                Desktop.getDesktop().open(file);
                            } else {
                                throw new FileNotFoundException("file not found");
                            }
                        }
                    } catch (Exception exception) {
                        try {
                            throw (exception);
                        } catch (DocumentException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
        });

        backbtn.addActionListener(e->{
            dispose();
            new ReportsUi();
        });


        // set main panel as content pane
        setContentPane(mainPanel);

        // set window size and center on screen
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(800, 600);// set the width to 800 and the height to 600
      //  dataTable.setPreferredSize(new Dimension(600, 400)); // set the preferred size to 600x400
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18)); // set the font size to 18
        backbtn.setFont(new Font("Arial", Font.PLAIN, 18)); // set the font size to 18
        yearDropdown.setFont(new Font("Arial", Font.PLAIN, 18)); // set the font size to 18
        radioDropdown.setFont(new Font("Arial", Font.PLAIN, 18)); // set the font size to 18
        radioDropdown.setFont(new Font("Arial", Font.PLAIN, 18)); // set the font size to 18
        radioOption1.setFont(new Font("Arial", Font.PLAIN, 18)); // set the font size to 18
        radioOption2.setFont(new Font("Arial", Font.PLAIN, 18)); // set the font size to 18

    }

    public static void main(String[] args) {
        new AnnualReportUi();

    }}