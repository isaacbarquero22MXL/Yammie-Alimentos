/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Canton;
import Model.Provincia;
import Model.TipoProducto;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author bryan
 */
public class BeanProducto {
     private String Identificacion="";
    private byte[] foto;
    private int cantMinVenta;
    private double precio;
    private String resultado;
    private String Mensaje="";
    private int TipoProducto;

     public BeanProducto() {
         
    }
    
    
    public boolean VerificarCampos() {
       
        for (int i = 0; i < Identificacion.length(); i++) {
            char digito = Identificacion.charAt(i);

            if (Character.isLetter(digito)) {
                Mensaje = "El ID debe contener unicamente numeros";
                                return false;
            }
        }
       
        if (this.cantMinVenta < 0) {
            Mensaje = "La cantidad de venta debe ser mayor a 0";
            return false;

        }
       
        if (this.precio <= 0) {

            Mensaje = "El precio no puede ser menos a 0";
            return false;
        }
        if (TipoProducto==0) {
            Mensaje = "Debe ingresar un tipo de producto";
            return false;
        }
         if (this.foto == null) {
            Mensaje = "La foto no puede estar vacia";
            return false;
        }
        Mensaje="entro ";
        return true;
    }
    
    public void RealizarRegistroProducto(){
        if(VerificarCampos()){
            //codigo productoDB
        }
    }

    public LinkedList<SelectItem>getListaTipoProducto(){
//           throws SNMPExceptions, SQLException {
//        String dscCortaProvincia = "";
//        float codigoProvincia = 0;
//
//        LinkedList<Provincia> lista = new LinkedList<Provincia>();
//        ProvinciaDB pDB = new ProvinciaDB();
//
//        lista = pDB.moTodo();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0,"Ingrese su Tipo de Producto" ));
        resultList.add(new SelectItem(1,"Frituras" ));
        resultList.add(new SelectItem(2,"Bebidas"));
        resultList.add(new SelectItem(3, "Postres"));
        resultList.add(new SelectItem(4,"Pastas"));
        resultList.add(new SelectItem(5,"Almuerzos"));
//         for (Canton can : lista) {
//            
//            
//            dscCortaCanton = can.getDsc_Canton();
//            cod_Canton = can.getCod_Canton();
//            resultList.add(new SelectItem(codigoCanton,
//                    dscCortaCanton));
//         
//        }
        return resultList;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String Identificacion) {
        this.Identificacion = Identificacion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getEstado() {
        return Mensaje;
    }

    public void setEstado(String estado) {
        this.Mensaje = estado;
    }

 

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public int getTipoProducto() {
        return TipoProducto;
    }

    public void setTipoProducto(int TipoProducto) {
        this.TipoProducto = TipoProducto;
    }
    

}
