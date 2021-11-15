/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Barrio;
import Model.Canton;
import Model.Distrito;
import Model.Provincia;

/**
 *
 * @author Bryan e Isaac
 */
public class BeanDireccion {

    private String ID;
    private String ID_Provincia;
    private String ID_Canton;
    private String ID_Distrito;
    private String ID_Barrio;
    private String otrasSenna;

    public String getID() {
        return ID;
    }

    // Getters and SettersS
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_Provincia() {
        return ID_Provincia;
    }

    public void setID_Provincia(String ID_Provincia) {
        this.ID_Provincia = ID_Provincia;
    }

    public String getID_Canton() {
        return ID_Canton;
    }

    public void setID_Canton(String ID_Canton) {
        this.ID_Canton = ID_Canton;
    }

    public String getID_Distrito() {
        return ID_Distrito;
    }

    public void setID_Distrito(String ID_Distrito) {
        this.ID_Distrito = ID_Distrito;
    }

    public String getID_Barrio() {
        return ID_Barrio;
    }

    public void setID_Barrio(String ID_Barrio) {
        this.ID_Barrio = ID_Barrio;
    }

    public String getOtrasSenna() {
        return otrasSenna;
    }

    public void setOtrasSenna(String otrasSenna) {
        this.otrasSenna = otrasSenna;
    }
    
    public boolean validaCampos() {
        if (this.ID_Barrio.equals("Seleccione") || this.ID_Canton.equals("Seleccione")
                || this.ID_Distrito.equals("Seleccione") || this.ID_Provincia.equals("Seleccione")) {
            return false;
        } else {
            return true;
        }
    }
}
