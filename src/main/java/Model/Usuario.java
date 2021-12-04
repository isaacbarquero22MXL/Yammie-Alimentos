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
public class Usuario {
    private String Contrasenna;
    private String Electronico;
    private String Cedula;
    private String nombre;
    private String apellido;
    private String apellido2;
    private String telefono;
    private Date fechVenciCOntrasenna;
    private ArrayList<Direccion> listaDirecciones;
    private ArrayList<Horario> listaHorarios;
    private ArrayList<TipoRol> listaRoles;
    private TipoRol rolSeleccionado;
    
    public Usuario() {
    }

    public Usuario(String Contrasenna, String Electronico, String Cedula, String nombre, String apellido, String apellido2, String telefono, Date FechVenciCOntrasenna) {
        this.Contrasenna = Contrasenna;
        this.Electronico = Electronico;
        this.Cedula = Cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.fechVenciCOntrasenna = FechVenciCOntrasenna;
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
        return fechVenciCOntrasenna;
    }

    public void setFechVenciCOntrasenna(Date FechVenciCOntrasenna) {
        this.fechVenciCOntrasenna = FechVenciCOntrasenna;
    }

    public ArrayList<Direccion> getListaDirecciones() {
        return listaDirecciones;
    }

    public void setListaDirecciones(ArrayList<Direccion> listaDirecciones) {
        this.listaDirecciones = listaDirecciones;
    }

    public ArrayList<Horario> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(ArrayList<Horario> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public ArrayList<TipoRol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(ArrayList<TipoRol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public TipoRol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(TipoRol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }
    
    public String retornaNombreCompleto(){
        return this.nombre + " " + this.apellido + " " + apellido2;
    }
    
}
