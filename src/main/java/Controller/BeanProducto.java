/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Paths;

import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author bryan
 */
@SessionScoped
public class BeanProducto implements Serializable {

    private String Identificacion = "";
    private byte[] foto;
    private int cantMinVenta;
    private double precio;
    private String resultado;
    private String Mensaje = "";
    private int TipoProducto;
    private String graphicImage;
    private StreamedContent sc;
    
    private Part uploadedFile;
    private String fileName;

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
        if (TipoProducto == 0) {
            Mensaje = "Debe ingresar un tipo de producto";
            return false;
        }
        if (this.foto == null) {
            Mensaje = "La foto no puede estar vacia";
            return false;
        }
        Mensaje = "entro ";
        return true;
    }

    public void RealizarRegistroProducto() {
        if (VerificarCampos()) {
            //codigo productoDB
        }
    }

    public LinkedList<SelectItem> getListaTipoProducto() {
//           throws SNMPExceptions, SQLException {
//        String dscCortaProvincia = "";
//        float codigoProvincia = 0;
//
//        LinkedList<Provincia> lista = new LinkedList<Provincia>();
//        ProvinciaDB pDB = new ProvinciaDB();
//
//        lista = pDB.moTodo();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Ingrese su Tipo de Producto"));
        resultList.add(new SelectItem(1, "Frituras"));
        resultList.add(new SelectItem(2, "Bebidas"));
        resultList.add(new SelectItem(3, "Postres"));
        resultList.add(new SelectItem(4, "Pastas"));
        resultList.add(new SelectItem(5, "Almuerzos"));
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public String getGraphicImage() {
        return graphicImage;
    }

    public void setGraphicImage(String graphicImage) {
        this.graphicImage = graphicImage;
    }

    public StreamedContent getSc() {
        return sc;
    }

    public void setSc(StreamedContent sc) {
        this.sc = sc;
    }
    
    public void setUploadedFile(Part uploadedFile) throws IOException {
        this.uploadedFile = uploadedFile;
//        if(this.uploadedFile != null){
//            upload();
//        }
    }

    public void upload() throws IOException {
        fileName = Paths.get(uploadedFile.getSubmittedFileName()).getFileName().toString();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (InputStream input = uploadedFile.getInputStream()) {
            int nRead;
            byte[] data = new byte[(int) uploadedFile.getSize()];

            while ((nRead = input.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
        } catch (IOException e) {
            Mensaje = "Algo salio mal cargar el archivo";
        }
        foto = buffer.toByteArray();
        setSc(new DefaultStreamedContent(new ByteArrayInputStream(foto)));
    }

//    public StreamedContent getMyImage() {
//        byte[] buffer;
//        StreamedContent stream = null;
//       // FacesContext fc = FacesContext.getCurrentInstance();
//        if (foto != null) {
//            buffer = foto;
//            InputStream input = new ByteArrayInputStream(buffer);
//            stream = new DefaultStreamedContent(input,
//                    "image/jpeg");
//        }
//        return stream;
//    }
//    
//    public void cargaIamgen(){
//        this.graphicImage = "<p:graphicImage class=\"product_added\" value=\"#{beanProducto.myImage}\"></p:graphicImage>";
//    }
}
