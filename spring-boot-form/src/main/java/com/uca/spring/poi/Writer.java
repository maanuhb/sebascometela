package com.uca.spring.poi;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class Writer {
	public static void write(HttpServletResponse response, HSSFSheet worksheet) {

			try {
				// Retrieve the output stream
				ServletOutputStream outputStream = response.getOutputStream();
				// Write to the output stream
				worksheet.getWorkbook().write(outputStream);
				// Flush the stream
				outputStream.flush();
	
			} catch (Exception e) {
                             Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, e);
			}
		}
}

