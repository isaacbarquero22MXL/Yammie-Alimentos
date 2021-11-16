/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MedioEnvio;
import Model.TipoCobro;
import java.util.ArrayList;
import javax.faces.model.SelectItem;

/**
 *
 * @author User
 */
public class BeanFactura {
    private String ID;
    private double Descuento;
    private double TotalBruto;
    private double Impuesto;
    private double CostoEnvio;
    private double TotalNeto;
    private TipoCobro cobro;
    
    
    // Mensaje de error
    private String mensaje;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public double getTotalBruto() {
        return TotalBruto;
    }

    public void setTotalBruto(double TotalBruto) {
        this.TotalBruto = TotalBruto;
    }

    public double getImpuesto() {
        return Impuesto;
    }

    public void setImpuesto(double Impuesto) {
        this.Impuesto = Impuesto;
    }

    public double getCostoEnvio() {
        return CostoEnvio;
    }

    public void setCostoEnvio(double CostoEnvio) {
        this.CostoEnvio = CostoEnvio;
    }

    public double getTotalNeto() {
        return TotalNeto;
    }

    public void setTotalNeto(double TotalNeto) {
        this.TotalNeto = TotalNeto;
    }

    public TipoCobro getCobro() {
        return cobro;
    }

    public void setCobro(TipoCobro cobro) {
        this.cobro = cobro;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public ArrayList<SelectItem> listaTipoCobro() {
        ArrayList<SelectItem> lista = new ArrayList<>();

        for (TipoCobro tipo : TipoCobro.values()) {
            lista.add(new SelectItem(tipo));
        }
        return lista;
    }

    
    public boolean validaCampos(){
        boolean estado = true;
        if (this.ID.equals("Seleccione")) {
            mensaje = "Por favor seleccione un pedido para facturar.";
            estado = false;
        }
        return estado;
    }
    
    public void realizarFacturacion(){
        mensaje = "";
        if(validaCampos()){
            // aqui se llama al DB
        }
    }
    
}
