package com.simple.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Helperdownload {
	public static void prepareResponse(HttpServletRequest request,
            HttpServletResponse response) {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	     String currentDateTime = dateFormatter.format(new Date());
	      
	     String headerKey = "Content-Disposition";
	     String headerValue = "attachment; filename=employee_" + currentDateTime + "."+ request.getParameter("type");
	     response.setHeader(headerKey, headerValue);
	}
}
