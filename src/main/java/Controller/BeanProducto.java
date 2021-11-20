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
  

    // Imagen por directorio
    private String path;
    private String image;

    public BeanProducto() {}

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
    

    public boolean VerificarCampos() {
        boolean isValid = true;
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        Usuario user = (Usuario) session.getAttribute("usuario");
        
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
        
        if (tipoProducto == null || tipoProducto.getDescripcion().equals("Seleccione")) {
            Mensaje = "Debe escoger un tipo de producto";
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

    public String realizaRegistroProducto() {
        if (VerificarCampos()) {
            Producto producto = retornaProductoConstruido();
            ProductoDB prodDB = new ProductoDB();
            try {
                if (!prodDB.validaIDUsuario(producto.getIdentificacion())) {
                    prodDB.insertaProducto(producto);
                    Mensaje = "<p class=\"errorLabel\" style=\"color: #00C32C\">"
                                + "El registro de producto se ha completado.<p>";
                }
            } catch (Exception e) {
                Mensaje = e.getMessage();
            }
        }
        return "ingreso";
    }

    public Producto retornaProductoConstruido() {
        Producto producto = new Producto();
        producto.setIdentificacion(this.identificacion);
        producto.setFoto(this.path);
        producto.setCantMinVenta(this.cantMinVenta);
        producto.setPrecio(this.precio);
        producto.setTipo(this.tipoProducto);
        producto.setDescripcion(descripcion);
        
        return producto;
    }

    public LinkedList<SelectItem> getListaTipoProducto() {

        TipoProductoDB tipoDB = new TipoProductoDB();
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(new TipoProducto("Seleccione", 0)));
        try {
            LinkedList<TipoProducto> tipoPro = tipoDB.listaTipoProducto();

            for (TipoProducto tipoP : tipoPro) {
                resultList.add(new SelectItem(tipoP));
            }
        } catch (SNMPExceptions ex) {
            Mensaje = ex.getMensajeParaDesarrollador();
        } catch (SQLException ex) {
            Mensaje = ex.getMessage();
        }

        return resultList;
    }
}
