/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bryan e Isaac
 */
public class Direccion {
    private String ID;
    private float provincia;
    private float canton;
    private float distrito;
    private float barrio;
    private String tipoDireccion;
    private String otrasSennas;
    
    private Provincia ObjetcProv;
    private Canton ObjetcCant;
    private Distrito ObjetcDist;
    private Barrio ObjetcBar;
    
    public Direccion(){
        this.tipoDireccion = "Principal";
    }

    public Direccion(String ID, float provincia, float canton, float distrito, float barrio, String tipoDireccion, String otrasSennas) {
        this.ID = ID;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.barrio = barrio;
        this.tipoDireccion = tipoDireccion;
        this.otrasSennas = otrasSennas;
    }
    

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public float getProvincia() {
        return provincia;
    }

    public void setProvincia(float provincia) {
        this.provincia = provincia;
    }

    public float getCanton() {
        return canton;
    }

    public void setCanton(float canton) {
        this.canton = canton;
    }

    public float getDistrito() {
        return distrito;
    }

    public void setDistrito(float distrito) {
        this.distrito = distrito;
    }

    public float getBarrio() {
        return barrio;
    }

    public void setBarrio(float barrio) {
        this.barrio = barrio;
    }

    public String getOtrasSennas() {
        return otrasSennas;
    }

    public void setOtrasSennas(String otrasSennas) {
        this.otrasSennas = otrasSennas;
    }

    public Provincia getObjetcProv() {
        return ObjetcProv;
    }

    public void setObjetcProv(Provincia ObjetcProv) {
        this.ObjetcProv = ObjetcProv;
    }

    public Canton getObjetcCant() {
        return ObjetcCant;
    }

    public void setObjetcCant(Canton ObjetcCant) {
        this.ObjetcCant = ObjetcCant;
    }

    public Distrito getObjetcDist() {
        return ObjetcDist;
    }

    public void setObjetcDist(Distrito ObjetcDist) {
        this.ObjetcDist = ObjetcDist;
    }

    public Barrio getObjetcBar() {
        return ObjetcBar;
    }

    public void setObjetcBar(Barrio ObjetcBar) {
        this.ObjetcBar = ObjetcBar;
    }
    
    
}
