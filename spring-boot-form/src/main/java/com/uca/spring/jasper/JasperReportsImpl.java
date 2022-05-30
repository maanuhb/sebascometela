/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uca.spring.jasper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author wsalazar
 */
@Component
public class JasperReportsImpl implements JasperReports{

    @Autowired
    private DataSource datasource;

    @Autowired
    ServletContext servletContext=null;


    private final Logger logger = LoggerFactory.getLogger(JasperReportsImpl.class);

    JasperReportsImpl(){

    }

    public JasperPrint print(JasperParameters param,Connection connection) throws JasperDataAccessException{
        	JasperPrint jp = null;
        try{
        	String path = servletContext.getRealPath("");
            if(!path.endsWith(File.separator)) path = path + File.separator;
            path = path +"resources"+File.separator+"reports"+File.separator;
            if(param.getType().equalsIgnoreCase(JasperParameters.EXCEL_CONTENT_TYPE)){
                param.getParameters().put(JRParameter.IS_IGNORE_PAGINATION, true);
            }
           jp = JasperFillManager.fillReport(path+param.getFolder()+File.separator+param.getFileName(), param.getParameters(),connection);
        } catch (RuntimeException ex) {
            logger.error("could not generate jasper report", ex);
            throw new JasperDataAccessException("could not generate jasper report",ex);
        }catch (Exception e) {
            logger.error("could not generate jasper report", e);
            throw new JasperDataAccessException("could not generate jasper report",e);
        }
        return jp;
     }

    public JasperPrint print(JasperParameters param) throws JasperDataAccessException{
        Connection connection = null;
        JasperPrint jp = null;
        try {
            String path = servletContext.getRealPath("");
            if(!path.endsWith(File.separator)) path = path + File.separator;
            path = path +"resources"+File.separator+"reports"+File.separator;
            connection = getDataSource().getConnection();
            if(param.getType().equalsIgnoreCase(JasperParameters.EXCEL_CONTENT_TYPE)){
                param.getParameters().put(JRParameter.IS_IGNORE_PAGINATION, true);
            }
           jp = JasperFillManager.fillReport(path+param.getFolder()+File.separator+param.getFileName(), param.getParameters(),connection);
        } catch (RuntimeException ex) {
            throw new JasperDataAccessException("could not generate jasper report",ex);
        }catch (Exception e) {
            logger.error("could not generate jasper report", e);
            throw new JasperDataAccessException("could not generate jasper report",e);
        }finally{
        	try {
                if (connection!=null && !connection.isClosed()) {
                        connection.close();
                }
            }catch (Throwable e) {
                    logger.warn("could not close connection for jasper report");
            }
        }

        return jp;
     }


    public HttpServletResponse executeReport(HttpServletResponse response, JasperParameters param) throws Exception{
       Connection connection = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String path = servletContext.getRealPath("");
            if(!path.endsWith(File.separator)) path = path + File.separator;
            path = path +"resources"+File.separator+"reports"+File.separator;
            connection = getDataSource().getConnection();
            if(param.getType().equalsIgnoreCase(JasperParameters.EXCEL_CONTENT_TYPE)){
                param.getParameters().put(JRParameter.IS_IGNORE_PAGINATION, true);
            }
            JasperPrint jp = JasperFillManager.fillReport(path+param.getFolder()+File.separator+param.getFileName(), param.getParameters(),connection);
            exportToResponse(param.getTitle(), param.getType(), jp, response, baos);
            writeToResponse(response, baos);
        } catch (Throwable ex) {
            logger.error("could not generate jasper report", ex);
            throw new Exception(ex);
        }finally{
            try {
                if (connection!=null && !connection.isClosed()) {
                        connection.close();
                }
            }catch (Throwable e) {
                    logger.warn("could not close connection for jasper report");
            }
        }

        return response;

    }

    public HttpServletResponse executeReport(HttpServletResponse response, JasperParameters param,JasperPrint jp) throws Exception{
        try {
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             exportToResponse(param.getTitle(), param.getType(), jp, response, baos);
             writeToResponse(response, baos);
         } catch (RuntimeException ex) {
             logger.error("could not generate jasper report", ex);
             throw ex;
         }catch (Exception e) {
             logger.error("could not generate jasper report", e);
             throw e;
         }
         return response;
    }


    public HttpServletResponse executeReport(HttpServletResponse response, String title, String type,JasperPrint jp) throws Exception{
        try {
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             exportToResponse(title, type, jp, response, baos);
             writeToResponse(response, baos);
         } catch (RuntimeException ex) {
             logger.error("could not generate jasper report", ex);
             throw ex;
         }catch (Exception e) {
             logger.error("could not generate jasper report", e);
             throw e;
         }
         return response;
    }

    public DataSource getDataSource() throws SQLException {
        return datasource;

    }

    public HttpServletResponse exportToResponse(String title, String type,
			JasperPrint jp, HttpServletResponse response,
			ByteArrayOutputStream baos) {

		if (type.equalsIgnoreCase(JasperParameters.EXCEL_CONTENT_TYPE)) {
			// Export to output stream

			try {
                            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                            configuration.setOnePagePerSheet(true);
                            configuration.setDetectCellType(true);
                            configuration.setCollapseRowSpan(false);
                            configuration.setWhitePageBackground(false);
                            configuration.setRemoveEmptySpaceBetweenRows(true);
                            jp.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");


                            JRXlsExporter exporterXLS = new JRXlsExporter();
                            exporterXLS.setExporterInput(new SimpleExporterInput(jp));
                            exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                            exporterXLS.setConfiguration(configuration);
                            exporterXLS.exportReport();


			} catch (JRException ex) {
				throw new RuntimeException(ex);

			}

			String fileName = title + JasperParameters.EXCEL_EXTENSION;
			response.setHeader(JasperParameters.RESPONSE_CONTENT_DISPOSITION,JasperParameters.FILE_CONTENT_HEADER + fileName);
			response.setContentType(JasperParameters.EXCEL_CONTENT_TYPE);
			response.setContentLength(baos.size());

			return response;
		}

		if (type.equalsIgnoreCase(JasperParameters.PDF_CONTENT_TYPE)) {

			try {
      SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
      configuration.setOnePagePerSheet(true);
      configuration.setDetectCellType(true);
      configuration.setCollapseRowSpan(false);
      configuration.setWhitePageBackground(false);
      configuration.setRemoveEmptySpaceBetweenRows(true);

      JRPdfExporter exporterPdf = new JRPdfExporter();
      exporterPdf.setExporterInput(new SimpleExporterInput(jp));
      exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
      exporterPdf.exportReport();
			} catch (JRException ex) {
        throw new RuntimeException(ex);
			}

			String fileName = title + JasperParameters.PDF_EXTENSION;
			response.setHeader(JasperParameters.RESPONSE_CONTENT_DISPOSITION,JasperParameters.FILE_CONTENT_HEADER + fileName);
			response.setContentType(JasperParameters.PDF_CONTENT_TYPE);
			response.setContentLength(baos.size());

			return response;

		}

		throw new RuntimeException("No type set for type " + type);
	}


    public void writeToResponse(HttpServletResponse response, ByteArrayOutputStream baos) {

		logger.debug("Writing report to the stream");
		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			baos.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (Exception e) {
			logger.error("Unable to write report to the output stream");
		}
	}

}
