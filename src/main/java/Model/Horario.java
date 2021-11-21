/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Horario {
    private int ID;
    private String inicio;
    private String fin;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Horario(int ID, String inicio, String fin) {
        this.ID = ID;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Horario() {
    }
    
    
    
}
