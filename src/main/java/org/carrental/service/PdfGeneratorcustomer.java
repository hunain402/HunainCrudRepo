package org.carrental.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileOutputStream;

public class PdfGeneratorcustomer {
        public static void generatePDF(JTable jTable) throws Exception {
            Document document = new Document(PageSize.A4.rotate());

            PdfWriter.getInstance(document, new FileOutputStream("Customer.pdf"));
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

            document.add(table);
            document.close();


        }

    }





