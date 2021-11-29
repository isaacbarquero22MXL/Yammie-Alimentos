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
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
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

    private static int index = 0;
    private static int consecutivo = 1;
    private PedidoDB pedidoDB = new PedidoDB();
    //lista carrito
    private ArrayList<Producto> listaCarrito = new ArrayList<>();
    
    private double valorProductos;

    public double getValorProductos() {
        return valorProductos;
    }

    public void setValorProductos(double valorProductos) {
        this.valorProductos = valorProductos;
    }

    
    public BeanPedido() {
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

    public void agregaCarrito(Producto pro) {
        listaCarrito.add(pro);
        addRemoveAnimation();
        PrimeFaces.current().executeScript("addCartImg('" + pro.getFoto() + "')");
        PrimeFaces.current().executeScript("add()");
        calculaCosto();
    }

    public void quitarCarrito(Producto pro) throws InterruptedException {
        Thread.sleep(1500);
        listaCarrito.remove(pro);
        addRemoveAnimation();
        calculaCosto();
    }

    public void addRemoveAnimation() {
        PrimeFaces.current().executeScript("addRemoveAnimation()");
    }

    public void seteaIDPedido() {
        try {
            String ID = "PD" + consecutivo;
            if (pedidoDB.validaIDPedido(ID)) {
                consecutivo++;
                seteaIDPedido();
            } else {
                this.ID = ID;
            }
        } catch (Exception e) {
        }
    }
    
    public void calculaCosto(){
        double costo = 0;
        for (Producto producto : listaCarrito) {
            costo += producto.getPrecio();
        }
        
        this.valorProductos = costo;
    }
}
