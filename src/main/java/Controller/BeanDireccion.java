/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Barrio;
import Model.Canton;
import Model.DireccionDB;
import Model.Distrito;
import Model.Provincia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Bryan e Isaac
 */
public class BeanDireccion {

//    private Provincia provincia;
//    private Canton canton;
//    private Distrito Distrito;
//    private Barrio Barrio;
    private String ID;
    private float ID_Provincia;
    private float ID_Canton;
    private float ID_Distrito;
    private float ID_Barrio;
    private String otrasSenna;

    // Direccion DB
    DireccionDB dDB = new DireccionDB();

    // Getters and SettersS
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOtrasSenna() {
        return otrasSenna;
    }

    public void setOtrasSenna(String otrasSenna) {
        this.otrasSenna = otrasSenna;
    }

    public float getID_Provincia() {
        return ID_Provincia;
    }

    public void setID_Provincia(float ID_Provincia) {
        this.ID_Provincia = ID_Provincia;
    }

    public float getID_Canton() {
        return ID_Canton;
    }

    public void setID_Canton(float ID_Canton) {
        this.ID_Canton = ID_Canton;
    }

    public float getID_Distrito() {
        return ID_Distrito;
    }

    public void setID_Distrito(float ID_Distrito) {
        this.ID_Distrito = ID_Distrito;
    }

    public float getID_Barrio() {
        return ID_Barrio;
    }

    public void setID_Barrio(float ID_Barrio) {
        this.ID_Barrio = ID_Barrio;
    }

    
    public boolean validaCampos() {
        if (this.ID_Barrio == -1 || this.ID_Canton == -1
                || this.ID_Distrito == -1 || this.ID_Provincia == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<SelectItem> listaProvincias() throws SNMPExceptions, SQLException {
        ArrayList<SelectItem> lista = new ArrayList<>();
        LinkedList<Provincia> listaPro = new LinkedList<>();

        listaPro = dDB.listaProvincias();
        lista.add(new SelectItem(-1, "Seleccione"));
        for (Provincia provincia : listaPro) {
            lista.add(new SelectItem(provincia.getCod_provincia(), provincia.getDsc_provincia()));
        }
        return lista;
    }

    public ArrayList<SelectItem> listaCantonesPorProvincia() throws SNMPExceptions, SQLException {
        ArrayList<SelectItem> lista = new ArrayList<>();
        LinkedList<Canton> listaCant = new LinkedList<>();

        listaCant = dDB.SeleccionarCantonPorProvincia(this.ID_Provincia);
        lista.add(new SelectItem(-1, "Seleccione"));

        for (Canton canton : listaCant) {
            lista.add(new SelectItem(canton.getCod_canton(), canton.getDsc_canton()));
        }
        return lista;
    }

    public ArrayList<SelectItem> listaDistritosPorCanton() throws SNMPExceptions, SQLException {
        ArrayList<SelectItem> lista = new ArrayList<>();
        LinkedList<Distrito> listaDist = new LinkedList<>();

        listaDist = dDB.SeleccionarDistritoporCanton(ID_Provincia, ID_Canton);
        lista.add(new SelectItem(-1, "Seleccione"));

        for (Distrito distrito : listaDist) {
            lista.add(new SelectItem(distrito.getCod_distrito(), distrito.getDsc_Distrito()));
        }
        return lista;
    }

    public ArrayList<SelectItem> listaBarriosPorDistrito() throws SNMPExceptions, SQLException {
        ArrayList<SelectItem> lista = new ArrayList<>();
        LinkedList<Barrio> listaBarr = new LinkedList<>();

        listaBarr = dDB.SeleccionarBarrioporDistrito(ID_Provincia, ID_Canton, ID_Distrito);
        lista.add(new SelectItem(-1, "Seleccione"));

        for (Barrio barrio : listaBarr) {
            lista.add(new SelectItem(barrio.getCod_barrio(), barrio.getDsc_barrio()));
        }
        return lista;
    }

}
