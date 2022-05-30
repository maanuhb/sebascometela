package com.uca.spring.poi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

public class LayOutDynamic {

	 public static void buildReport(HSSFSheet worksheet, String title, List<String> headers) {
	        // Set column widths
	        worksheet.setColumnWidth(0, 5000);

	        // Build the title and date headers
	        buildTitle(worksheet, title);
	        // Build the column headers

	        buildHeaders(worksheet, headers);
	    }

	    public static void buildTitle(HSSFSheet worksheet, String title) {

	        // Create font style for the report title
	        Font fontTitle = worksheet.getWorkbook().createFont();
	        fontTitle.setFontHeight((short) 280);

	        // Create cell style for the report title
	        HSSFCellStyle cellStyleTitle = worksheet.getWorkbook().createCellStyle();
	        cellStyleTitle.setWrapText(true);
	        cellStyleTitle.setFont(fontTitle);

	        // Create report title
	        HSSFRow rowTitle = worksheet.createRow(0);
	        rowTitle.setHeight((short) 500);
	        HSSFCell cellTitle = rowTitle.createCell(0);
	        cellTitle.setCellValue(title);
	        cellTitle.setCellStyle(cellStyleTitle);

	        // Create merged region for the report title

	        // Create date header
	        HSSFRow dateTitle = worksheet.createRow(1);
	        HSSFCell cellDate = dateTitle.createCell(0);

	        Calendar date = Calendar.getInstance();
	        SimpleDateFormat dateformatter = new SimpleDateFormat(" dd/MM/yyyy");

	        cellDate.setCellValue("Reporte generado: " + dateformatter.format(date.getTime()));
	    }

	    public static void buildHeaders(HSSFSheet worksheet, List<String> headers) {
	        // Create font style for the headers
	        Font font = worksheet.getWorkbook().createFont();

	        // Create cell style for the headers
	        HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
	        headerCellStyle.setWrapText(true);
	        headerCellStyle.setFont(font);

	        // Create the column headers
	        HSSFRow rowHeader = worksheet.createRow(2);

	        for (int i = 0; i < headers.size(); i++) {
	            HSSFCell cellSup = rowHeader.createCell(i);
	            cellSup.setCellValue(headers.get(i));
	            cellSup.setCellStyle(headerCellStyle);
	        }

	    }

	    @SuppressWarnings({"rawtypes", "rawtypes"})
	    public static void fillReport(HSSFSheet worksheet, Integer headers, List<Object[]> datasource) {
	        // Create cell style for the body
	        HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
	        bodyCellStyle.setWrapText(true);

	        for (int i = 0; i < datasource.size(); i++) {//filas
	            HSSFRow row = worksheet.createRow(i + 3);
	            for (int j = 0; j < headers; j++) {
	                HSSFCell cell = row.createCell(j);
	                cell.setCellStyle(bodyCellStyle);
	                if (datasource.get(i)[j] == null || datasource.get(i)[j].equals("")) {
	                    cell.setCellValue("");
	                } else {
	                    cell.setCellValue(datasource.get(i)[j].toString());
	                }
	                worksheet.autoSizeColumn(j);
	            }

	        }
	    }
}
