package com.uca.spring.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.spring.jasper.JasperParameters;
import com.uca.spring.jasper.JasperReports;

@Controller
@RequestMapping("/")
public class ReportController {

    @Autowired
    JasperReports jasperReports;

    @Autowired
    ServletContext servletContext = null;

    @RequestMapping(value = {"/indexReportPersona"}, method = RequestMethod.GET)
    public ModelAndView indexReportSolicitud(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("reports/reportPerson.jsp");
        return model;
    }

    @RequestMapping("/printReportPerson")
    public ModelAndView imprimirReportAgenda(
            @RequestParam(required = false, value = "idbook",defaultValue = "") Integer idbook,
            @RequestParam(required = false, value = "name",defaultValue = "") String name,
            @RequestParam(required = false, value = "description",defaultValue = "") String description,
            @RequestParam(required = false, value = "synopsis",defaultValue = "") String synopsis,
            @RequestParam(required = false, value = "author",defaultValue = "") String author,
            @RequestParam(required = false, value = "isbn",defaultValue = "") String isbn,
            @RequestParam(required = false, value = "typeexport",defaultValue = "") String typeexport,
            HttpServletResponse response, HttpServletRequest request) {

        try {
            System.out.println("---- " + name);
            System.out.println("---- " + idbook);
            System.out.println("---- " + description);
            System.out.println("---- " + synopsis);
            System.out.println("---- " + author);
            System.out.println("---- " + isbn);
            JasperParameters jasperParameters = new JasperParameters();
            jasperParameters.addParameter("P_ID_BOOK", idbook);
            jasperParameters.addParameter("P_DESCRIPCTION", description);
            jasperParameters.addParameter("P_SYNOPSIS", synopsis);
            jasperParameters.addParameter("P_AUTHOR", author);
            jasperParameters.addParameter("P_ISBN", isbn);


            if (typeexport.equals("pdf")) {
                jasperParameters.setType(JasperParameters.PDF_CONTENT_TYPE);
            } else if (typeexport.equals("excel")) {
                jasperParameters.setType(JasperParameters.EXCEL_CONTENT_TYPE);
            }

            jasperParameters.setFolder("books");
            jasperParameters.setTitle("Excel");
            jasperParameters.setFileName("reportPerson.jasper");

            jasperParameters.setType(JasperParameters.PDF_CONTENT_TYPE);

            jasperReports.executeReport(response, jasperParameters);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return null;
    }

}
