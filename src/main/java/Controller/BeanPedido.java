/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Barrio;
import Model.Canton;
import Model.Direccion;
import Model.DireccionDB;
import Model.Distrito;
import Model.EstadoPedido;
import Model.Horario;
import Model.Pedido;
import Model.PedidoDB;
import Model.Producto;
import Model.ProductoDB;
import Model.Provincia;
import Model.Usuario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

/**
 *
 * @author User
 */
public class BeanPedido {

    private String ID;
    private Date HoraEntrega;
    private Direccion direccion;
    private EstadoPedido estadoPedido;

    private static int index = 0;
    private static int consecutivo = 1;
    private PedidoDB pedidoDB = new PedidoDB();
    //lista carrito
    private ArrayList<Producto> listaCarrito = new ArrayList<>();

    private double valorProductos;
    private double impuesto;
    private double total;

    private String confirmItems;

    private Usuario user;

    private ArrayList<SelectItem> listaHorarios = new ArrayList<>();
    private ArrayList<SelectItem> listaDirecciones = new ArrayList<>();

    private int IDDireccion;
    private String horarioSeleccionado;

    public BeanPedido() {
        seteaIDPedido();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (Usuario) session.getAttribute("usuario");
        setearHorarios();
        setearDirecciones();
    }

    public ArrayList<SelectItem> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(ArrayList<SelectItem> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public ArrayList<SelectItem> getListaDirecciones() {
        return listaDirecciones;
    }

    public void setListaDirecciones(ArrayList<SelectItem> listaDirecciones) {
        this.listaDirecciones = listaDirecciones;
    }

    public int getIDDireccion() {
        return IDDireccion;
    }

    public void setIDDireccion(int IDDireccion) {
        this.IDDireccion = IDDireccion;
    }

    public String getHorarioSeleccionado() {
        return horarioSeleccionado;
    }

    public void setHorarioSeleccionado(String horarioSeleccionado) {
        this.horarioSeleccionado = horarioSeleccionado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getConfirmItems() {
        return confirmItems;
    }

    public void setConfirmItems(String confirmItems) {
        this.confirmItems = confirmItems;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getValorProductos() {
        return valorProductos;
    }

    public void setValorProductos(double valorProductos) {
        this.valorProductos = valorProductos;
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
        if (pro.getCantMinVenta() > 0) {
            listaCarrito.add(pro);
            addRemoveAnimation();
            PrimeFaces.current().executeScript("addCartImg('" + pro.getFoto() + "')");
            PrimeFaces.current().executeScript("add()");
            PrimeFaces.current().executeScript("addFullCart()");
            total();
        } else {
            PrimeFaces.current().executeScript("alert(\"Ya no hay de este producto.\")");
        }
    }

    public void quitarCarrito(Producto pro) throws InterruptedException {
        Thread.sleep(1500);
        listaCarrito.remove(pro);
        addRemoveAnimation();
        total();
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

    public double calculaCosto() {
        double costo = 0;
        for (Producto producto : listaCarrito) {
            costo += producto.getPrecio();
        }

        this.valorProductos = costo;
        return costo;
    }

    public double calculaCostoImpuesto() {
        this.impuesto = calculaCosto() * 0.15;
        return impuesto;
    }

    public double total() {
        this.total = calculaCosto() + calculaCostoImpuesto();
        return total;
    }

    public void showConfirmItems() {
        String hilera = "<div class=\"confirm_content swiper-slide\">\n"
                + "<img src=\"images/YammieLogoIcon.png\" alt=\"\" class=\"confirm_img\">\n"
                + "</div>";

        for (Producto producto : listaCarrito) {
            hilera += "<div class=\"confirm_content swiper-slide\">"
                    + "<img src=\"" + producto.getFoto() + "\" alt=\"\" class=\"confirm_img\">"
                    + "</div>";
        }
        this.confirmItems = hilera;
        PrimeFaces.current().executeScript("showConfirmPanel()");
    }

    public void aceptarPedido() {
        if (listaCarrito.size() != 0) {
            Pedido pedido = construyePedido();
            try {
                pedidoDB.insertaPedido(pedido);
                listaCarrito = new ArrayList<>();
                clearFields();
                PrimeFaces.current().executeScript("showConfirmPanel()");
                PrimeFaces.current().executeScript("confirmAnimations()");
                PrimeFaces.current().executeScript("removeFullCart()");
            } catch (Exception e) {
                PrimeFaces.current().executeScript("alert(\"" + e.toString() + "\")");
            }
        }else{
            PrimeFaces.current().executeScript("alert(\"No se puede aceptar un pedido "
                    + "el cual no tenga productos a comprar.\")");
        }
    }

    public Pedido construyePedido() {
        Pedido pedido = new Pedido();
        pedido.setID(ID);
        pedido.setDireccion(IDDireccion);
        pedido.setEstadoPedido(EstadoPedido.Pendientes);
        pedido.setHoraEntrega(horarioSeleccionado);
        pedido.setListaCarrito(listaCarrito);
        pedido.setUsuario(user);
        return pedido;
    }

    public void setearHorarios() {
        ArrayList<SelectItem> lista = new ArrayList<>();

        for (Horario horario : user.getListaHorarios()) {
            String descripcion = horario.getInicio() + " - " + horario.getFin();
            lista.add(new SelectItem(descripcion));
        }

        this.listaHorarios = lista;
    }

    public void setearDirecciones() {
        ArrayList<SelectItem> lista = new ArrayList<>();

        DireccionDB dDB = new DireccionDB();

        LinkedList<Provincia> listaPro = null;
        LinkedList<Canton> listaCant = null;
        LinkedList<Distrito> listaDist = null;
        LinkedList<Barrio> listaBar = null;

        try {
            listaPro = dDB.listaProvincias();

            for (Direccion direccion : user.getListaDirecciones()) {
                Provincia pro = null;
                Canton cant = null;
                Distrito dist = null;
                Barrio bar = null;

                for (Provincia provincia : listaPro) {
                    if (provincia.getCod_provincia() == direccion.getProvincia()) {
                        pro = provincia;
                        break;
                    }
                }

                listaCant = dDB.SeleccionarCantonPorProvincia(pro.getCod_provincia());

                for (Canton canton : listaCant) {
                    if (canton.getCod_canton() == direccion.getCanton()) {
                        cant = canton;
                        break;
                    }
                }

                listaDist = dDB.SeleccionarDistritoporCanton(pro.getCod_provincia(), cant.getCod_canton());

                for (Distrito distrito : listaDist) {
                    if (distrito.getCod_distrito() == direccion.getDistrito()) {
                        dist = distrito;
                        break;
                    }
                }

                listaBar = dDB.SeleccionarBarrioporDistrito(pro.getCod_provincia(),
                        cant.getCod_canton(),
                        dist.getCod_distrito());

                for (Barrio barrio : listaBar) {
                    if (barrio.getCod_barrio() == direccion.getBarrio()) {
                        bar = barrio;
                        break;
                    }
                }

                String direccionFinal = pro.getDsc_provincia() + ", "
                        + cant.getDsc_canton() + ", " + dist.getDsc_Distrito()
                        + ", " + bar.getDsc_barrio();
                lista.add(new SelectItem(Integer.parseInt(direccion.getID()), direccionFinal));
            }
        } catch (Exception e) {
        }
        this.listaDirecciones = lista;
    }

    public void devolverProductosYCerrar() {
        ProductoDB pDB = new ProductoDB();
        try {
            for (Producto producto : listaCarrito) {
                pDB.actualizaCantidadProducto(producto, "+1");
            }
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (Exception e) {
        }
    }
    
    public void clearFields(){
        this.impuesto = 0;
        this.total = 0;
        this.valorProductos = 0;
    }
}
