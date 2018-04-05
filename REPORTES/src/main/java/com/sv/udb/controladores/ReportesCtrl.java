/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;


import com.sv.udb.modelos.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

/**
 *
 * @author luisedu
 */
public class ReportesCtrl {
    
    public static int PDF = 1;
    public static int WORD=2;
    public static int EXCEL=3;
    String jrxmlFileName="";
    String jasperFileName="";
    String fileName="";

    public void Report(int number,int type) {
        HashMap map;
        
        try {
            //Rutas de archivos
            jrxmlFileName = new File("src/main/java/com/sv/udb/reportes/JugadoresEquipo.jrxml").getAbsolutePath();
            jasperFileName = new File("src/main/java/com/sv/udb/reportes/JugadoresEquipo.jasper").getAbsolutePath();

            //Compilando jasperreport
            net.sf.jasperreports.engine.JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
            //Conexion
            Connection conn = new Conexion().getConn();
            
            map = new HashMap();
            map.put("equipo",number);
            JasperPrint print = (JasperPrint)JasperFillManager.fillReport(jasperFileName, map,conn);
            
            if(type == PDF){
            //guardando
                fileName = new File("reportes/pdf/JugadoresParam.pdf").getAbsolutePath();
                JasperExportManager.exportReportToPdfFile(print, fileName);
                
                //mostrar en el jasperviewer
                JasperViewer.viewReport(print, false);
                //si se desea que el reporte se imprima tambien en el visor por defecto quitar la siguiente linea
                return;
            }
            if(type == WORD){
                fileName = new File("reportes/word/JugadoresParam.docx").getAbsolutePath();
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
                exporter.exportReport();
            }
            
            if(type == EXCEL){
                fileName = new File("reportes/excel/JugadoresParam.xls").getAbsolutePath();
                JRXlsExporter exporter = new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT,print);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,fileName);
                exporter.exportReport();
            }
            
            //mostrando
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(new File(fileName));
                } catch (IOException ex) {
                    System.out.println("No fue posible abrir el archivo" + ex);
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void ReportDos(int type) {
        HashMap map;
        
        try {
            //Rutas de archivos
            jrxmlFileName = new File("src/main/java/com/sv/udb/reportes/Equipos.jrxml").getAbsolutePath();
            jasperFileName = new File("src/main/java/com/sv/udb/reportes/Equipos.jasper").getAbsolutePath();

            //Compilando jasperreport
            net.sf.jasperreports.engine.JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
            //Conexion
            Connection conn = new Conexion().getConn();
            
            map = new HashMap();
            //map.put("order",order);
            JasperPrint print = (JasperPrint)JasperFillManager.fillReport(jasperFileName, map,conn);
            
            if(type == PDF){
            //guardando
                fileName = new File("reportes/pdf/EquiposDetalle.pdf").getAbsolutePath();
                JasperExportManager.exportReportToPdfFile(print, fileName);
                
                //mostrar en el jasperviewer
                JasperViewer.viewReport(print, false);
                //si se desea que el reporte se imprima tambien en el visor por defecto quitar la siguiente linea
                return;
            }
            if(type == WORD){
                fileName = new File("reportes/word/EquiposDetalle.docx").getAbsolutePath();
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
                exporter.exportReport();
            }
            
            if(type == EXCEL){
                fileName = new File("reportes/excel/EquiposDetalle.xls").getAbsolutePath();
                JRXlsExporter exporter = new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT,print);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,fileName);
                exporter.exportReport();
            }
            
            //mostrando
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(new File(fileName));
                } catch (IOException ex) {
                    System.out.println("No se pudo abrir el archivo " + ex);
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
