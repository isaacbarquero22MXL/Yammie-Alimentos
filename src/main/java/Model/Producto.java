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
public class Producto {
    private String Identificacion;
    private String foto;
    private int cantMinVenta;
    private double precio;
    private TipoProducto tipo;
    private String descripcion;

    public Producto() {
    }

    public Producto(String Identificacion, String foto, int cantMinVenta, double precio) {
        this.Identificacion = Identificacion;
        this.foto = foto;
        this.cantMinVenta = cantMinVenta;
        this.precio = precio;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String Identificacion) {
        this.Identificacion = Identificacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getCantMinVenta() {
        return cantMinVenta;
    }

    public void setCantMinVenta(int cantMinVenta) {
        this.cantMinVenta = cantMinVenta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
