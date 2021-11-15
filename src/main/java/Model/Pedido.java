/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Bryan e Isaac
 */
public class Pedido {
    private String IDentificacion;
    private Usuario usuario;
    private Date HoraEntrega;
    private Direccion direccion;
    private EstadoPedido estadoPedido;
    private Despacho despacho;

    public Pedido() {
    }

    public Pedido(String IDentificacion, Usuario usuario, Date HoraEntrega, Direccion direccion, EstadoPedido estadoPedido, Despacho despacho) {
        this.IDentificacion = IDentificacion;
        this.usuario = usuario;
        this.HoraEntrega = HoraEntrega;
        this.direccion = direccion;
        this.estadoPedido = estadoPedido;
        this.despacho = despacho;
    }

    public String getIDentificacion() {
        return IDentificacion;
    }

    public void setIDentificacion(String IDentificacion) {
        this.IDentificacion = IDentificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getHoraEntrega() {
        return HoraEntrega;
    }

    public void setHoraEntrega(Date HoraEntrega) {
        this.HoraEntrega = HoraEntrega;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Despacho getDespacho() {
        return despacho;
    }

    public void setDespacho(Despacho despacho) {
        this.despacho = despacho;
    }
    
    
}
