/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bryan e Isaac
 */
public class Factura {
    private String ID;
    private double Descuento;
    private double TotalBruto;
    private double Impuesto;
    private double CostoEnvio;
    private double TotalNeto;
    
    private Pedido pedido;
    private TipoCobro Metodo;
    

    public Factura() {
    }

    public Factura(String ID, double Descuento, double TotalBruto, double Impuesto, double CostoEnvio, double TotalNeto, TipoCobro Metodo) {
        this.ID = ID;
        this.Descuento = Descuento;
        this.TotalBruto = TotalBruto;
        this.Impuesto = Impuesto;
        this.CostoEnvio = CostoEnvio;
        this.TotalNeto = TotalNeto;
        this.Metodo = Metodo;
    }

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

    public TipoCobro getMetodo() {
        return Metodo;
    }

    public void setMetodo(TipoCobro Metodo) {
        this.Metodo = Metodo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    public double aplicaDescuento(){
        return pedido.calculaCosto() * (Descuento / 100.0);
    }
    
    public double costoTotal(){
        return this.TotalNeto = TotalBruto + Impuesto - aplicaDescuento();
    }
}
