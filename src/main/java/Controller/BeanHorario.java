/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Bryan e Isaac
 */
public class BeanHorario {
    private int ID;
    private String inicio;
    private String fin;
    
    private String [] listaDias = {"Lunes", "Martes", "Miércoles",
                                    "Jueves", "Viernes"};
    private String [] listaHoras = {"08:00", "09:00", "10:00", "11:00", "12:00",
                                    "13:00", "14:00", "15:00", "16:00", "17:00",
                                    "18:00"};
    

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
    
    public boolean validaDatosNulos(){
        if(inicio.equals("Seleccione") || fin.equals("Seleccione") || inicio.equals(fin)){
            return false;
        }else{
            return true;
        }
    }
    
    public ArrayList<SelectItem> listaHorarios() throws SNMPExceptions, SQLException {
        ArrayList<SelectItem> lista = new ArrayList<>();
        
        lista.add(new SelectItem("Seleccione"));
        String horario = "";
        
        for (int i = 0; i < listaDias.length; i++) {
            for (int j = 0; j < listaHoras.length; j++) {
                horario = listaDias[i] + " " + listaHoras[j];
                lista.add(new SelectItem(horario));
            }
        }       
        return lista;
    }
}
