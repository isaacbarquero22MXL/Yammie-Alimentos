/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Bryan e Isaac
 */
public class Pedido {
    private String ID;
    private Usuario usuario;
    private Date HoraEntrega;
    private Direccion direccion;
    private EstadoPedido estadoPedido;
    
    private ArrayList<Producto> listaCarrito;

    public Pedido() {
        listaCarrito = new ArrayList<>();
    }

    public Pedido(String ID, Usuario usuario, Date HoraEntrega, Direccion direccion, EstadoPedido estadoPedido) {
        this.ID = ID;
        this.usuario = usuario;
        this.HoraEntrega = HoraEntrega;
        this.direccion = direccion;
        this.estadoPedido = estadoPedido;
    }

    public String getIDentificacion() {
        return ID;
    }

    public void setIDentificacion(String ID) {
        this.ID = ID;
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

    public ArrayList<Producto> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(ArrayList<Producto> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }
    
    
}
