/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uca.spring.jasper;

/**
 *
 * @author Walter Salazar
 */
/**
 * 
 */

import java.util.HashMap;
import java.util.Map;

import com.uca.spring.util.ObjectUtils;



/**
 * @author wsalazar 
 * 		   Objeto que poseee los parametros de los reportes es
 *         necesario inyectarle al metodo el WebRequest para que el
 *         jasperParameters tome los valores de esta fuente
 * 
 */
public class JasperParameters {
    
    
        public static final int MAX_NUM_ROWS_PER_REPORT = 5000;

	public final static String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";
	public final static String EXCEL_EXTENSION = ".xls";
	public final static String FILE_CONTENT_HEADER = "inline; filename=";
	public final static String RESPONSE_CONTENT_DISPOSITION = "Content-Disposition";

	public static final String PDF_CONTENT_TYPE = "application/pdf";
	public static final String PDF_EXTENSION = ".pdf";
	public static final String REPORTS_DEFAULT_FOLDER = "/reports";
	public static final String IMAGES_DEFAULT_FOLDER ="/images";
        public static final String IMG_DEFAULT_FOLDER ="/img";
	public static final String REPORT_PARAMETER_PREFIX = "jpr_rpt";
		
	public static final String JASPER_REPORT_TITLE = "JASPER_REPORT_TITLE";
	public static final String JASPER_REPORT_PATH = "JASPER_REPORT_PATH";
	public static final String JASPER_IMAGE_PATH = "JASPER_IMAGE_PATH";
	public static final String JASPER_BASE_DIR = "BaseDir";
	public static final String JASPER_SUB_REPORT_PATH = "SUBREPORT_DIR";
	 

	private String folder;

	private String subReportPath;

	private String imagePath;

	private String title;

	private String fileName;

	private String type;

	private int maxRows;
        
        public Map<String, Object> parameters;

	public String getFolder() {
		return folder;
	}

	public void setFolder(String path) {
		this.folder = path;
	}

	
	public String getSubReportPath() {
		return subReportPath;
	}

	public void setSubReportPath(String subReportPath) {
		this.subReportPath = subReportPath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	 

	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public JasperParameters() {
		this.parameters = new HashMap<String, Object>();
	}

	public void addParameter(String parameter, Object value) {
		this.parameters.put(parameter, value);
	}

	public void removeParameter(String parameter) {
		this.parameters.remove(parameter);
	}

	public Object getParameterValue(String key) {
		if (!ObjectUtils.isNullOrEmpty(key)
				&& !ObjectUtils.isNullOrEmpty(this.parameters.get(key))) {
			return this.parameters.get(key);

		} else {
			return null;

		}
	}

	public Object getParameterValueAsString(String key) {
		if (!ObjectUtils.isNullOrEmpty(key)
				&& !ObjectUtils.isNullOrEmpty(this.parameters.get(key))
				&& (this.parameters.get(key) instanceof String)) {
			return this.parameters.get(key).toString();

		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("path-->" + this.folder  +", ");
		sb.append("fileName-->" + this.fileName  +", ");
		sb.append("imagePath-->" + this.imagePath  +", ");
		sb.append("maxRows-->" + this.maxRows  +", ");
		sb.append("subReportPath-->" + this.subReportPath  +", ");
		sb.append("title-->" + this.title  +", ");
		sb.append("type-->" + this.type  +", ");
		sb.append("parameters:" + getParameters().toString());
		
		
		return sb.toString();
	}

}
