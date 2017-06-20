/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.report;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class AbstractBaseReportBean {

    public enum ExportOption {

        PDF, HTML, EXCEL, RTF
    }
    private ExportOption exportOption;
    private final String COMPILE_DIR = "/resources/report/";

    protected JRMapCollectionDataSource jRMapCollectionDataSource;

    protected JRBeanCollectionDataSource jRBeanCollectionDataSource;

    protected String compileFileName;

    protected JasperPrint jasperPrint;

    protected Map<String, Object> reportParameters;

    /**
     *
     * @return
     */
    public AbstractBaseReportBean() {
        super();
    }

    public void prepareReportWithConnection(Connection connection) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), getCompileFileName());

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), getCompileFileName() + ".jasper"));
        System.out.println("file=" + reportFile.getName());
        System.out.println("lien=" + reportFile.getPath());
        if (connection != null) {
            this.jasperPrint = ReportConfigUtil.fillReport(reportFile, getReportParameters(), connection);
            System.err.println("123456789 =" + connection);
        } else {
            this.jasperPrint = ReportConfigUtil.fillReport(reportFile, getReportParameters());
            System.err.println("123456789 =" + connection);
        }
        System.err.println("connnn=" + connection);
        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());
        System.err.println("ok=11");
        FacesContext.getCurrentInstance().responseComplete();
        System.err.println("ok=21");
    }

    public void prepareReportWithConnectionGood(Connection connection) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), getCompileFileName());

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), getCompileFileName() + ".jasper"));

        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, getReportParameters(), connection);

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();
    }

    public StreamedContent prepareReportWithConnectionReady(Connection connection) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), getCompileFileName());

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), getCompileFileName() + ".jasper"));

        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, getReportParameters(), connection);

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());
        FacesContext.getCurrentInstance().responseComplete();
        byte[] bytes = JasperExportManager.exportReportToPdf(this.jasperPrint);

        InputStream ist = new ByteArrayInputStream(bytes);
        StreamedContent download = new DefaultStreamedContent(ist, "application/pdf");
        System.out.println("" + download);

        return download;

    }

    public void prepareReport(JRBeanCollectionDataSource jrbencol) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), getCompileFileName());

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), getCompileFileName() + ".jasper"));

        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, getReportParameters(), jrbencol);
        System.out.println("REEEEEE---" + this.jasperPrint.getName());

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());
        System.out.println("EXPORT++++" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void prepareReport(JRMapCollectionDataSource jrbencol) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), getCompileFileName());

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), getCompileFileName() + ".jasper"));

        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, getReportParameters(), jrbencol);
        System.out.println("REEEEEE---" + this.jasperPrint.getName());

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());
        System.out.println("EXPORT++++" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void prepareReportNew(JRDataSource jrbencol, Map<String, Object> parametres) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), getCompileFileName());

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), getCompileFileName() + ".jasper"));

        System.out.println("Nombre de parametre : " + (parametres == null ? "null" : parametres.size()));
        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, parametres, jrbencol);
        System.out.println("REEEEEE---" + this.jasperPrint.getName());

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());
        System.out.println("EXPORT++++" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void prepareReportWithParam(String compileFileName, Map<String, Object> reportParameters, JRMapCollectionDataSource jrMapCollectionDataSource) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), compileFileName);

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), compileFileName + ".jasper"));

        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, reportParameters, jrMapCollectionDataSource);

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();
    }

    public StreamedContent prepareReportWithParam(String compileFileName, Map<String, Object> reportParameters, JRBeanCollectionDataSource jrBeanCollectionDataSource) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), compileFileName);

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), compileFileName + ".jasper"));

        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, reportParameters, jrBeanCollectionDataSource);

        byte[] bytes = JasperExportManager.exportReportToPdf(this.jasperPrint);

        InputStream ist = new ByteArrayInputStream(bytes);
        StreamedContent download = new DefaultStreamedContent(ist, "application/pdf");
        System.out.println("REPORT " + download);

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();

        return download;
    }

    public void prepareReportWithConnexion(String compileFileName, Map<String, Object> reportParameters,
            Connection connexion) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), compileFileName);

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), compileFileName + ".jasper"));

        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, reportParameters, connexion);

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();

    }

    public ExportOption getExportOption() {
        return exportOption;
    }

    public void setExportOption(ExportOption exportOption) {
        this.exportOption = exportOption;
    }

    protected String getCompileDir() {
        return COMPILE_DIR;
    }

    public JRMapCollectionDataSource getjRMapCollectionDataSource() {
        return jRMapCollectionDataSource;
    }

    public void setjRMapCollectionDataSource(JRMapCollectionDataSource jRMapCollectionDataSource) {
        this.jRMapCollectionDataSource = jRMapCollectionDataSource;
    }

    public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
        return jRBeanCollectionDataSource;
    }

    public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
        this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public String getCompileFileName() {
        return compileFileName;
    }

    public void setCompileFileName(String compileFileName) {
        this.compileFileName = compileFileName;
    }

    public Map<String, Object> getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(Map<String, Object> reportParameters) {
        this.reportParameters = reportParameters;
    }

}
