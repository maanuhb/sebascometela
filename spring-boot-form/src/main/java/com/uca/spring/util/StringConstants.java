/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uca.spring.util;



public class StringConstants {
	public static final String ERROR = "failure";
	public static final String EXCEPTION = "exception";
	public static final String SUCCESS = "success";
	public static final String CANCEL = "cancel";
	public static final String COMMA = ",";
	public static final String BLANK = " ";
	public static final String EMPTY_STRING = "";
	public static final String EMPTY_STRING_LABEL = "     ";
	public static final String SELECT_VALUE_LABEL_EMPTY = "-Seleccione un valor-";
	public static final String ZERO="0";

	
	public static final String PIPE_DELIMITER = "|";
	public static final String KEY_ATRIB = "key";
	public static final String QUOTATION_MARK = "'";
	public final static String AMP = "&";
	public final static String EQUAL = "=";

	public final static String DATE_PATTERN = "dd/MM/yyyy";
	public final static String DATE_PATTERN_JSON = "yyyy-MM-dd";
	public final static String DATE_HOUR_PATTERN_HIGHPHEN  = "dd-MM-yyyy-HH-mm-ss";
	public final static String DATE_HOUR_PATTERN = "dd/MM/yyyy HH:mm:ss";
	public final static String DATE_HOUR_PATTERN_WSEC = "dd/MM/yyyy HH:mm";
	public final static String HOUR_PATTERN = "HH:mm";
	public final static String YEAR_PATTERN = "yyyy";

	public final static String NUMBER_PATTERN = "###,###,##0.00";
	public final static String NUMBER_PATTERN_FLAT = "#########.0000";
	public final static String NUMBER_PATTERN_DOLLAR="$###,###,##0.00;-$###,###,##0.00";
	public final static String NUMBER_PATTERN_DOLLAR_WITHOUT_SYMBOL="########0.00;-########0.00";
	public final static String INTEGER_PATTERN = "###,###,##0";
	public final static int DECIMAL_PLACES = 2;

	public final static String REG_EXP_NIT = "^\\d{4}[-| ]?\\d{6}[-| ]?\\d{3}[-| ]?\\d{1}$";
	public final static String REG_EXP_SIMPLE_NIT = "^\\d{14}$";
	public final static String REG_EXP_MONT_YEAR ="^\\d{2}[-| ]\\d{4}$";
	public final static String REG_EXP_POSITIVE_INTEGER ="^\\d*\\.{0,0}\\d+$";
	public final static String REG_EXP_POSITIVE_NUMBER ="^\\d*\\.{0,2}\\d+$";
	
	public final static String atilde=new Character((char)225).toString();//a
	public final static String etilde=new Character((char)233).toString();//e
	public final static String itilde=new Character((char)237).toString();//i
	public final static String otilde=new Character((char)243).toString();//o
	public final static String utilde=new Character((char)250).toString();//u
	
	public final static String Atilde=new Character((char)193).toString();//A
	public final static String Etilde=new Character((char)201).toString();//E
	public final static String Itilde=new Character((char)205).toString();//I
	public final static String Otilde=new Character((char)211).toString();//O
	public final static String Utilde=new Character((char)218).toString();//U
	
	public final static String ntilde=new Character((char)241).toString();//enne
	public final static String Ntilde=new Character((char)209).toString();//ENNE
	
}
