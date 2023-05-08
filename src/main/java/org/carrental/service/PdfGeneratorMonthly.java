package org.carrental.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import org.carrental.domain.Bookingdetails;

import javax.swing.*;
import java.io.FileOutputStream;
import java.util.List;

public class PdfGeneratorMonthly {

    public static void generatePDF(JTable jTable, Double totalAmount, List<Bookingdetails> commision, JDateChooser strt,JDateChooser end) throws Exception {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, new FileOutputStream("Monthly.pdf"));
        document.open();

        PdfPTable table = new PdfPTable(jTable.getColumnCount());
        table.setWidthPercentage(100);

        for (int i = 0; i < jTable.getColumnCount(); i++) {
            table.addCell(new PdfPCell(new Paragraph(jTable.getColumnName(i))));
        }

        for (int i = 0; i < jTable.getRowCount(); i++) {
            for (int j = 0; j < jTable.getColumnCount(); j++) {
                table.addCell(new PdfPCell(new Paragraph(jTable.getValueAt(i, j).toString())));
            }
        }

        Paragraph TOTALAMOUNT = new Paragraph("TOTAL AMOUNT = "+totalAmount);
        Paragraph strtdate = new Paragraph("from = "+strt.getDate());
        Paragraph enddate = new Paragraph("from = "+end.getDate());
        Paragraph TOTALCOMMISION = new Paragraph("TOAL COMMISIOM = "+commision.get(0).getCommision());
        Paragraph totalProfit = new Paragraph("TOTAL PROFIT = "+ (totalAmount-commision.get(0).getCommision()));
        Paragraph monthlyrepo = new Paragraph("Monthly Report ");
document.add(monthlyrepo); document.add(strtdate);
                          document.add(enddate);
        document.add(table);
        document.add(TOTALAMOUNT);
        document.add(TOTALCOMMISION);
        document.add(totalProfit);
        document.close();


    }

}

