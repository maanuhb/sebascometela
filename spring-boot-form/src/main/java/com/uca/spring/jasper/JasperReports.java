/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uca.spring.jasper;

import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author wsalazar
 */
public interface JasperReports {

    public HttpServletResponse executeReport(HttpServletResponse response, JasperParameters param) throws Exception;
    public JasperPrint print(JasperParameters param,Connection connection) throws JasperDataAccessException;
    public JasperPrint print(JasperParameters param) throws JasperDataAccessException;
    public HttpServletResponse executeReport(HttpServletResponse response, JasperParameters param,JasperPrint jp) throws Exception;
    public HttpServletResponse executeReport(HttpServletResponse response, String title, String type,JasperPrint jp) throws Exception;
}
