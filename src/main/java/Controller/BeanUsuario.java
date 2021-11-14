/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TipoRol;
import java.io.Console;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author bryan
 */
public class BeanUsuario {

    // Atributos
    private String contrasenna;
    private String correo;
    private TipoRol tipoRol;
    private String Cedula;
    private String nombre;
    private String apellido;
    private String apellido2;
    private String telefono;
    private Date FechVenciContrasenna;

    // Mensaje de error
    private String mensaje;

    // Validador patrones
    Pattern pattern
            = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    // Getters and Setters
    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public TipoRol getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(TipoRol tipoRol) {
        this.tipoRol = tipoRol;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechVenciContrasenna() {
        return FechVenciContrasenna;
    }

    public void setFechVenciContrasenna(Date FechVenciContrasenna) {
        this.FechVenciContrasenna = FechVenciContrasenna;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    // Métodos
    public String validaCorreo() {
        Matcher match = pattern.matcher(correo);
        mensaje = "";
        
        if (match.find()) {
            return "ingreso";
        } else {
            mensaje = "El formato ingresado no corresponde a un correo electrónico. Intente de nuevo.";
            try {
                
                redirigirIndex();
            } catch (IOException ex) {}
            return "";
        }
    }

    public void redirigirIndex() throws IOException {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.getFlash().put("msg", "Something was done successfully");
            ec.redirect("index.xhtml#miniForm");
        } catch (Exception e) {
            mensaje = e.toString();
        }
    }
}
