/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TipoCobro;
import Reportes.ReportManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import javax.faces.model.SelectItem;

/**
 *
 * @author User
 */

// aqui no hace falta validar beans ya que hay botones para reporte y el filtrado
// aparece directamente como primera opcion a escoger
public class BeanReporte {
    
    private String filtroPedido;
    private String filtroCobro;
    private Date fechaDesde;
    private Date fechaHasta;
    
    private static String consejo = "<p class=\"errorLabel\" style=\"color: #FA7E00\">"
                            + "Recuerda que el formato de fecha debe"
                            + " ser día, mes y año (dd-MM-yyyy).<p>";
    private String mensaje = consejo;

    public String getFiltroPedido() {
        return filtroPedido;
    }

    public void setFiltroPedido(String filtroPedido) {
        this.filtroPedido = filtroPedido;
    }

    public String getFiltroCobro() {
        return filtroCobro;
    }

    public void setFiltroCobro(String filtroCobro) {
        this.filtroCobro = filtroCobro;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void mostrarReportePedido(){
        mensaje = "";
        if(validaCampos()){
            try {
                ReportManager report = new ReportManager();
                report.mostrarReportePedido(fechaDesde, fechaHasta, filtroPedido);
            } catch (Exception e) {
                mensaje = e.toString();
            }
        }
    }
    
    public void mostrarReporteCobro(){
        mensaje = "";
        if(validaCampos()){
            try {
                ReportManager report = new ReportManager();
                report.mostrarReporteVentas(fechaDesde, fechaHasta, filtroCobro);
            } catch (Exception e) {
                mensaje = e.toString() + "\n" + consejo;
            }
        }
    }
    
    public boolean validaCampos(){
        boolean isValid = true;
        if(fechaDesde == null || fechaHasta == null){
            mensaje = "Seleccione el rango de fechas para el reporte.\n" + consejo;
            isValid = false;
        }
        return isValid;              
    }
    
    public ArrayList<SelectItem> listaCobro(){
        ArrayList<SelectItem> lista = new ArrayList<>();
        for (TipoCobro cobro : TipoCobro.values()) {
            lista.add(new SelectItem(cobro.toString()));
        }
        return lista;
    }
}
