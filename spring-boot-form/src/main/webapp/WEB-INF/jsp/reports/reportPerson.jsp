<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jf" uri="/WEB-INF/tlds/jform.tld" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/WEB-INF/jsp/include-css.jsp" %>
        <%@include file="/WEB-INF/jsp/include-js.jsp" %>
        <script>
            function excelexport() {
                $("#typeexport").val("excel");
                $("#formReporte").submit();
            }

            function pdfexport() {
                $("#typeexport").val("pdf");
                $("#formReporte").submit();
            }
        </script>
    </head>
    <body class="skin-megna fixed-layout">
        <div class="preloader">
            <div class="loader">
                <div class="loader__figure"></div>
                <p class="loader__label">Personas</p>
            </div>
        </div>
        <div id="main-wrapper">         
            <%@include file="/WEB-INF/jsp/header.jsp" %>
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <div class="page-wrapper">
                <!-- ============================================================== -->
                <!-- Container fluid  -->
                <!-- ============================================================== -->
                <div class="container-fluid">
                    <!-- ============================================================== -->
                    <!-- Bread crumb and right sidebar toggle -->
                    <!-- ============================================================== -->
                    <div class="row page-titles">
                        <div class="col-md-5 align-self-center">
                            <h4 class="text-themecolor"></h4>
                        </div>
                        <div class="col-md-7 align-self-center text-right">
                            <div class="d-flex justify-content-end align-items-center">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="javascript:void(0)">Inicio</a></li>
                                    <li class="breadcrumb-item active">Reporte de Personas</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header bg-info">
                                    <h4 class="m-b-0 text-white">Reporte de Personas</h4>
                                </div>
                                <div class="card-body">
                                    <div class="dt-buttons">
                                        <a class="dt-button buttons-excel buttons-html5" tabindex="0" aria-controls="example23" href="javascript:excelexport();"><span>Excel</span></a>
                                        <a class="dt-button buttons-pdf buttons-html5" tabindex="0" aria-controls="example23"  href="javascript:pdfexport();"><span>PDF</span></a>
                                    </div>
                                    <form role="form" id="formReporte" data-toggle="validator" action="${pageContext.request.contextPath}/printReportPerson" target="_blank">
                                        <input type="hidden" id="typeexport" name="typeexport" />
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label class="control-label">Persona</label>
                                                <jf:combobox url="${pageContext.request.contextPath}/cbofilterPerson"   id="idperson"  name="idperson" placeholder="Person"  />
                                            </div>
                                            <div class="col-md-6">
                                             	<label class="control-label">Telefono</label>
                                                 <jf:textbox id="telefono" name="telefono"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label class="control-label">Departamento</label>
                                                <jf:combobox url="${pageContext.request.contextPath}/cbofilterDepartamento"   id="iddepartamento"  name="iddepartamento"/>
                                            </div>
                                            <div class="col-md-6">
                                                <label class="control-label">Municipio</label>
                                                  <label class="control-label">Estado</label>
                                                <jf:combobox url="${pageContext.request.contextPath}/cbofilterMunicipio"   id="idmunicipio"  name="idmunicipio"   />
                                            </div>
                                        </div>
                                         <div class="row">
                                            <div class="col-md-6">
                                            <label class="control-label">Fecha Inicio</label>
                                               <jf:textbox id="fechainicio" name="fechainicio"/>  
											  
                                            </div>
                                            <div class="col-md-6">
                                            <label class="control-label">Fecha Fin</label>
                                            
                                               <jf:textbox id="fechafin" name="fechafin"/>  
                                            
                                            </div>
                                        </div>
                                        </div>
                                        <br>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/WEB-INF/jsp/footer.jsp" %>
        </div>
    </body>
</html>