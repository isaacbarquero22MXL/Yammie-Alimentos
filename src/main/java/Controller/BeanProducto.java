/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Producto;
import Model.ProductoDB;
import Model.TipoProducto;
import Model.TipoProductoDB;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import Model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Bryan e Isaac
 */
public class BeanProducto {

    private String identificacion;
    private int cantMinVenta;
    private double precio;
    private String descripcion;
    private String resultado;
    private String Mensaje = "";
    private TipoProducto tipoProducto;

    private int ID_Tipo;

    // Imagen por directorio
    private String path;
    private String image;

    //Lista productos
    ArrayList<Producto> listaProductos = new ArrayList<>();
    ArrayList<Producto> listaCarrito = new ArrayList<>();

    // Producto seleccionado
    Producto productoSelected;

    public BeanProducto() {
        refrescaListaProductos();
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String Identificacion) {
        this.identificacion = Identificacion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void cargaImagePath() {
        this.image = "<img class=\"product_added\" alt=\"imagenMante\" src=\"" + this.path + "\">";
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getID_Tipo() {
        return ID_Tipo;
    }

    public void setID_Tipo(int ID_Tipo) {
        this.ID_Tipo = ID_Tipo;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProductoSelected() {
        return productoSelected;
    }

    public void setProductoSelected(Producto productoSelected) {
        this.productoSelected = productoSelected;
    }

    public ArrayList<Producto> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(ArrayList<Producto> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    
   
    // Métodos 
    public boolean VerificarCampos() {
        boolean isValid = true;

        if (this.identificacion.equals("")) {
            Mensaje = "Por favor digite un ID para este producto.";
            isValid = false;
        }

        if (this.cantMinVenta < 0) {
            Mensaje = "La cantidad de venta debe ser mayor a 0";
            isValid = false;
        }

        if (this.precio <= 0) {

            Mensaje = "El precio no puede ser menos a 0";
            isValid = false;
        }

        if (this.ID_Tipo == 0) {
            Mensaje = "Seleccione el tipo de producto.";
            isValid = false;
        }

        if (this.path.equals("")) {
            Mensaje = "La ruta de imágen no puede estar vacía";
            isValid = false;
        }

        if (this.descripcion.equals("")) {
            Mensaje = "Por favor escriba una descripción para el producto";
            isValid = false;
        }

        return isValid;
    }

    public void realizaRegistroProducto() {
        if (VerificarCampos()) {
            Producto producto = retornaProductoConstruido();
            ProductoDB prodDB = new ProductoDB();
            try {
                if (!prodDB.validaIDProducto(producto.getIdentificacion())) {
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    Usuario user = (Usuario) session.getAttribute("usuario");
                    prodDB.insertaProducto(producto, user);
                    Mensaje = "<p class=\"errorLabel\" style=\"color: #00C32C\">"
                            + "El registro de producto se ha completado.<p>";
                    refrescaListaProductos();
                } else {
                    Mensaje = "Alparecer ya existe un producto con este ID registrado";
                }
            } catch (Exception e) {
                Mensaje = e.getMessage();
            }
        }
    }

    public Producto retornaProductoConstruido() {
        Producto producto = new Producto();
        producto.setIdentificacion(this.identificacion);
        producto.setFoto(this.path);
        producto.setCantMinVenta(this.cantMinVenta);
        producto.setPrecio(this.precio);
        producto.setDescripcion(descripcion);

        TipoProductoDB tipoDB = new TipoProductoDB();
        try {
            ArrayList<TipoProducto> listaTipos = tipoDB.listaTipoProducto();
            for (TipoProducto tipo : listaTipos) {
                if (tipo.getCodigo() == this.ID_Tipo) {
                    producto.setTipo(tipo);
                }
            }
        } catch (SNMPExceptions ex) {
            Mensaje = ex.getMensajeParaDesarrollador();
        } catch (SQLException ex) {
            Mensaje = ex.getMessage();
        }

        return producto;
    }

    public ArrayList<SelectItem> getListaTipoProducto() {

        TipoProductoDB tipoDB = new TipoProductoDB();
        ArrayList<SelectItem> resultList = new ArrayList();
        resultList.add(new SelectItem(0, "Seleccione"));
        try {
            ArrayList<TipoProducto> tipoPro = tipoDB.listaTipoProducto();

            for (TipoProducto tipoP : tipoPro) {
                resultList.add(new SelectItem(tipoP.getCodigo(), tipoP.getDescripcion()));
            }
        } catch (SNMPExceptions ex) {
            Mensaje = ex.getMensajeParaDesarrollador();
        } catch (SQLException ex) {
            Mensaje = ex.getMessage();
        }

        return resultList;
    }

    public void refrescaListaProductos() {
        ProductoDB proDB = new ProductoDB();
        try {
            this.listaProductos = proDB.listaProductos();
        } catch (Exception e) {
            Mensaje = e.getMessage();
        }
    }
    
    public void refrescaCantidadProdcutos(Producto prod, String accion){
        ProductoDB proDB = new ProductoDB();
        try {
            proDB.actualizaCantidadProducto(prod, accion);
            this.listaProductos = proDB.listaProductos();
        } catch (Exception e) {
            Mensaje = e.getMessage();
        }
    }

    public void setearProductoSeleccionado(Producto producto) {
        // se setean las propiedades de los input
        this.identificacion = producto.getIdentificacion();
        this.cantMinVenta = producto.getCantMinVenta();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.tipoProducto = producto.getTipo();
        this.ID_Tipo = producto.getTipo().getCodigo();
        this.path = producto.getFoto();
        productoSelected = producto; // para poder eliminarlo si llega el caso 
        PrimeFaces.current().executeScript("showUpdateBtn()");
    }

    public void setearProductoYEjecutarJavaScript(Producto producto) {
        setearProductoSeleccionado(producto);
        PrimeFaces.current().executeScript("showDialog()");
        PrimeFaces.current().executeScript("removeUpdateBtn()");
    }

    public void actualizaProducto() {
        if (VerificarCampos()) {
            Producto producto = retornaProductoConstruido();
            ProductoDB prodDB = new ProductoDB();

            // esto permitirá setear el ID original del producto seleccionado por si el usuario lo trata cambiar.
            producto.setIdentificacion(this.productoSelected.getIdentificacion());
            try {
                if (prodDB.validaIDProducto(producto.getIdentificacion())) {
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    Usuario user = (Usuario) session.getAttribute("usuario");
                    prodDB.actualizaProducto(producto, user);
                    Mensaje = "<p class=\"errorLabel\" style=\"color: #FA7E00\">"
                            + "El producto se ha actualizado correctamente.<p>";
                    refrescaListaProductos();
                    PrimeFaces.current().executeScript("removeUpdateBtn()");
                } else {
                    Mensaje = "No se puede actualizar un producto con ID diferente al registrado.";
                }
            } catch (Exception e) {
                Mensaje = e.getMessage();
            }
        }
    }

    public void eliminaProducto() {
        ProductoDB prodDB = new ProductoDB();
        try {
            if (prodDB.validaIDProducto(this.productoSelected.getIdentificacion())) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                Usuario user = (Usuario) session.getAttribute("usuario");
                prodDB.eliminaProducto(productoSelected, user);
                Mensaje = "<p class=\"errorLabel\" style=\"color: #FA7E00\">"
                        + "El producto se ha eliminado correctamente.<p>";
                refrescaListaProductos();
                cleanProduct();
            } else {
                Mensaje = "Al parecer el producto que intenta eliminar ya fue borrado o no existe.";
            }
        } catch (Exception e) {
            Mensaje = e.getMessage();
        }
    }
    
    public void cleanProduct(){
        this.identificacion = "";
        this.cantMinVenta = 0;
        this.descripcion = "";
        this.precio = 0;
        this.tipoProducto = null;
        this.ID_Tipo = 0;
        this.path = "";
        productoSelected = null;
    }
    
    public String catalogoProductos(){
        String hilera = "";
        
        for (Producto producto : listaProductos) {
            hilera += "<div class=\"product_data\">"
                    + "<img src=\"" + producto.getFoto() + "\" alt=\"papas\" class=\"product_img\">"
                    + "<div class=\"product_data_container\">"
                    + " <h3>" + producto.getDescripcion() + "</h3>"
                    + "<div class=\"product_data-info\">"
                    + "<h2>₡" + producto.getPrecio() + "</h2>"
                    + "<h:commandButton value=\"Agregar\" action=\"#{beanProducto.agregaCarrito("+ producto +")}\" class=\"button addCart\">Agregar<i class='bx bxs-cart-alt'></i></h:commandButton>"
                    + "</div>"
                    + "</div>"
                    + "</div>";
        }
        return hilera;
    }
    
    
}