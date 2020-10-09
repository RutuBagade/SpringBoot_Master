package com.simple.exportview;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.simple.entity.Employee;
 
 
public class EmployeePDFExporter {
    private List<Employee> listEmployee;
     
    public EmployeePDFExporter(List<Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Employee ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("NAME", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("AGE", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("DEPARTMENT", font));
        table.addCell(cell);
         
            
    }
     
    private void writeTableData(PdfPTable table) {
        for (Employee emp : listEmployee) {
            table.addCell(String.valueOf(emp.getId()));
            table.addCell(emp.getName());
            table.addCell(String.valueOf(emp.getAge()));
            table.addCell(emp.getDept());
           
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Employee", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f,});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}