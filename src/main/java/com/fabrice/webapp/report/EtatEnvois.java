/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.report;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

/**
 *
 * @author pellage
 */
public class EtatEnvois extends AbstractBaseReportBean {

    private List<?> etats;
    private String compileFileName;
    private List<Map<String, ?>> maps;

    public EtatEnvois(List<?> etatsP) {
        this.etats = etatsP;
    }

    public EtatEnvois(List<Map<String, ?>> maps, boolean b) {
        this.maps = maps;
    }

    //@Override
    public JRMapCollectionDataSource getJRMapCollectionDataSource() {
        return new JRMapCollectionDataSource(this.maps);
    }

    // @Override
    public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
        return new JRBeanCollectionDataSource(this.etats);
    }

    @Override
    public String getCompileFileName() {
        return this.compileFileName;
    }

    @Override
    public Map<String, Object> getReportParameters() {
        return this.reportParameters;
    }

    @Override
    public void setReportParameters(Map<String, Object> param) {
        this.reportParameters = param;
    }

    public List<?> getEtats() {
        return etats;
    }

    public List<Map<String, ?>> getMaps() {
        return maps;
    }

    public void setMaps(List<Map<String, ?>> maps) {
        this.maps = maps;
    }
//    public void setEtats(List<Etat> etats) {
//        this.etats = etats;
//    }
    public void exporter() throws JRException, IOException {
        this.prepareReport(this.getJRBeanCollectionDataSource());
    }

    public void exporterMap() throws JRException, IOException {
        this.prepareReport(this.getJRMapCollectionDataSource());
    }

    public void exporterMapAvecParam() throws JRException, IOException {
        this.prepareReportNew(this.getJRMapCollectionDataSource(), this.getReportParameters());
    }

    public void exporterNew(Map<String, Object> parametres) throws JRException, IOException {
        this.prepareReportNew(this.getJRBeanCollectionDataSource(), parametres);
    }

    public void exporter(Map<String, Object> parametres) throws JRException, IOException {
        this.prepareReportWithParam(this.getCompileFileName(), parametres, new JRBeanCollectionDataSource(this.etats));
    }

    public void setCompileFileName(String name) {
        this.compileFileName = name;
    }

    public void changerFormat(String format) {
        if (format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.PDF.toString())) {
            this.setExportOption(AbstractBaseReportBean.ExportOption.PDF);
        } else if (format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.HTML.toString())) {
            this.setExportOption(AbstractBaseReportBean.ExportOption.HTML);
        } else if (format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.EXCEL.toString())) {
            this.setExportOption(AbstractBaseReportBean.ExportOption.EXCEL);
        } else if (format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.RTF.toString())) {
            this.setExportOption(AbstractBaseReportBean.ExportOption.RTF);
        }
    }
}
