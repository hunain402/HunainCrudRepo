package org.carrental.UI;

import com.itextpdf.text.DocumentException;
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



    public AnnualReportUi() {
        setTitle("Annual Report UI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
                    flag= true;
                }
            }
        });

        radioOption2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (radioOption2.isSelected()) {
                    radioDropdown.setModel(new DefaultComboBoxModel<>(new OwnerService().getOwnerIdAndNameForDropDown()));
               flag= false;
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

        mainPanel.add(submitPanel);

        // add action listener to the submit button

        submitButton.addActionListener(e->{
            submitButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (flag) {
                            //vehicle
                        } else {
                            //owner
                            JTable tableOwner = new JTable();
                            String[][] data = reportService.yearlyRepOwner((Integer) yearDropdown.getSelectedItem(), (String) radioDropdown.getSelectedItem());
                            String[] headers = {"Owner-Name", "Commission-percent", "Vehicle-Name", "Booking-date", "Complete-date", "Commision-price"};
                            DefaultTableModel dtm = new DefaultTableModel(data, headers);
                            JTable jt = new JTable(dtm);
                            JScrollPane sp = new JScrollPane(jt);

                            DefaultTableModel tableModelOwner = new DefaultTableModel(data, headers);
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
                        }                }
            }});
        });

        // set main panel as content pane
        setContentPane(mainPanel);

        // set window size and center on screen
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new AnnualReportUi();
        AnnualReportUi annualReportUI = new AnnualReportUi();
        annualReportUI.setVisible(true);
    }
}


//public class AnnualReportUi {
//
//
//}
