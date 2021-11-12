/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author bryan
 */
public class Usuario {
    private String Contrasenna;
    private String Electronico;
    private TipoRol tipoRol;
    private String Cedula;
    private String nombre;
    private String apellido;
    private String apellido2;
    private String telefono;
    private Date FechVenciCOntrasenna;

    public Usuario() {
    }

    public Usuario(String Contrasenna, String Electronico, TipoRol tipoRol, String Cedula, String nombre, String apellido, String apellido2, String telefono, Date FechVenciCOntrasenna) {
        this.Contrasenna = Contrasenna;
        this.Electronico = Electronico;
        this.tipoRol = tipoRol;
        this.Cedula = Cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.FechVenciCOntrasenna = FechVenciCOntrasenna;
    }

    public String getContrasenna() {
        return Contrasenna;
    }

    public void setContrasenna(String Contrasenna) {
        this.Contrasenna = Contrasenna;
    }

    public String getElectronico() {
        return Electronico;
    }

    public void setElectronico(String Electronico) {
        this.Electronico = Electronico;
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

    public Date getFechVenciCOntrasenna() {
        return FechVenciCOntrasenna;
    }

    public void setFechVenciCOntrasenna(Date FechVenciCOntrasenna) {
        this.FechVenciCOntrasenna = FechVenciCOntrasenna;
    }
    
    
    
}
