/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Direccion;
import Model.EstadoPedido;
import Model.PedidoDB;
import Model.Producto;
import Model.Usuario;
import java.sql.Date;
import java.util.ArrayList;
import org.primefaces.PrimeFaces;

/**
 *
 * @author User
 */


public class BeanPedido {
    private String ID;
    private Usuario usuario;
    private Date HoraEntrega;
    private Direccion direccion;
    private EstadoPedido estadoPedido;
    
    private static int consecutivo = 1;
    private PedidoDB pedidoDB = new PedidoDB();
    //lista carrito
    private ArrayList<Producto> listaCarrito = new ArrayList<>();

    
    public BeanPedido(){
        seteaIDPedido();
    }
    
    public ArrayList<Producto> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(ArrayList<Producto> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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
    
    public void agregaCarrito(Producto pro){
        listaCarrito.add(pro);
        PrimeFaces.current().executeScript("addCartImg('" + pro.getFoto() + "')");
        PrimeFaces.current().executeScript("add()");
    }
    
    public void quitarCarrito(Producto pro){
        listaCarrito.remove(pro);
    }
    
    public void seteaIDPedido(){
        try {
            String ID = "PD" + consecutivo;
            if(pedidoDB.validaIDPedido(ID)){
                consecutivo++;
                seteaIDPedido();
            }else{
                this.ID = ID;
            }
        } catch (Exception e) {}
    }
}
