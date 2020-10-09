package com.simple.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.lowagie.text.DocumentException;
import com.simple.entity.Employee;
import com.simple.exportview.EmployeeExcelExporter;
import com.simple.exportview.EmployeePDFExporter;
import com.simple.helper.Helperdownload;
import com.simple.repository.EmployeeRepository;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository repo;

    @RequestMapping("/emplist")
    public String home(Model model) {
         model.addAttribute("employees", repo.findAll());
         return "list_emp";
    }
    @GetMapping(value="/report")
    public String userListReport(HttpServletRequest req, HttpServletResponse response)throws DocumentException, IOException{
     
     String typeReport = req.getParameter("type");
     
	Helperdownload.prepareResponse(req, response);
      
     List<Employee> listemployee = (List<Employee>) repo.findAll();
    
     
     if(typeReport != null && typeReport.equals("xls")){
    	 EmployeeExcelExporter excelExporter = new EmployeeExcelExporter(listemployee);
         
         excelExporter.export(response); 
        return "redirect:/emplist/";
      
     } else if(typeReport != null && typeReport.equals("pdf")){
    	 EmployeePDFExporter exporter = new EmployeePDFExporter(listemployee);
         exporter.export(response);
         return "redirect:/emplist/";
     }
     else if(typeReport != null && typeReport.equals("csv")){
    	 ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
         String[] csvHeader = {"User ID", "NAME", "AGE", "DEPART"};
         String[] nameMapping = {"id", "name", "age", "dept"};
          
         csvWriter.writeHeader(csvHeader);
          
         for (Employee emp : listemployee) {
             csvWriter.write(emp, nameMapping);
         }
          
         csvWriter.close();
         return "redirect:/emplist/";
   	  }
     
     return "list_emp";
    }
}

