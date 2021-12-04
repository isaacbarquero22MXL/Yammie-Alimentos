/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import DAO.AccesoDatos;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Isaac B.
 */
public class ReportManager {

    //formato
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    public void mostrarReportePedido(Date fechaDesde, Date fechaHasta, String filtro) throws Exception {
        try {
            AccesoDatos acceso = new AccesoDatos();
            JasperReport jasperR = null;

            String path = "C:\\Users\\User\\OneDrive\\Documentos\\ReportesProyecto\\reportPedido.jasper";
            jasperR = (JasperReport) JRLoader.loadObjectFromFile(path);

            String fecha = format.format(fechaHasta);
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("fechaDesde", format.format(fechaDesde));
            parameters.put("fechaHasta", format.format(fechaHasta));
            parameters.put("filtro", filtro);

            JasperPrint print = JasperFillManager.fillReport(jasperR, parameters, acceso.getDbConn());

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte Pedido.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(print, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    
    public void mostrarReporteVentas(Date fechaDesde, Date fechaHasta, String filtro) throws Exception {
        try {
            AccesoDatos acceso = new AccesoDatos();
            JasperReport jasperR = null;

            String path = "C:\\Users\\User\\OneDrive\\Documentos\\ReportesProyecto\\reportCobro.jasper";
            jasperR = (JasperReport) JRLoader.loadObjectFromFile(path);

            String fecha = format.format(fechaHasta);
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("fechaDesde", format.format(fechaDesde));
            parameters.put("fechaHasta", format.format(fechaHasta));
            parameters.put("filtro", filtro);

            JasperPrint print = JasperFillManager.fillReport(jasperR, parameters, acceso.getDbConn());

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte Ventas.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(print, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
}
