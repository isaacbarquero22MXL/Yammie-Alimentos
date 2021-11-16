/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.EstadoUsuario;
import Model.MedioEnvio;
import java.util.ArrayList;
import javax.faces.model.SelectItem;

/**
 *
 * @author User
 */
public class BeanEstadoUsuarios {
    private EstadoUsuario estado;
    private String mensajeEstado;
    private String IDUsuario;
    
    // mensaje error
    private String mensaje;

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public String getMensajeEstado() {
        return mensajeEstado;
    }

    public void setMensajeEstado(String mensajeEstado) {
        this.mensajeEstado = mensajeEstado;
    }

    public String getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(String IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    public ArrayList<SelectItem> listaEstado() {
        ArrayList<SelectItem> lista = new ArrayList<>();

        for (EstadoUsuario eu : EstadoUsuario.values()) {
            lista.add(new SelectItem(eu));
        }
        return lista;
    }
    
    public boolean validaCampos(){
        boolean valido = true;
        
        if(IDUsuario.equals("Seleccione")){
            mensaje = "Por favor seleccione un usuario para realizar la acción correspondiente.";
            valido = false;
        }else{
            if(estado == EstadoUsuario.Rechazar){
                if(mensajeEstado.equals("")){
                    valido = false;
                    mensaje = "Si vas a rechazar a este usuario por favor especifica el por qué.";
                }
            }
        }
        return valido;
    }
    
    public void realizaAccion(){
        mensaje = "";
        if(validaCampos()){
            // aqui va el DB
        }
    }
}
