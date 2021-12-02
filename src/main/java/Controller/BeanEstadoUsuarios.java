/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import MailMaster.Mail;
import Model.EstadoUsuario;
import Model.MedioEnvio;
import Model.Usuario;
import Model.UsuarioDB;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

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

    // lista usuario pendientes
    private ArrayList<SelectItem> listaPendientes = new ArrayList<>();

    public BeanEstadoUsuarios() {
        usuariosPendiente();
    }

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

    public ArrayList<SelectItem> getListaPendientes() {
        return listaPendientes;
    }

    public void setListaPendientes(ArrayList<SelectItem> listaPendientes) {
        this.listaPendientes = listaPendientes;
    }

    public ArrayList<SelectItem> listaEstado() {
        ArrayList<SelectItem> lista = new ArrayList<>();

        for (EstadoUsuario eu : EstadoUsuario.values()) {
            lista.add(new SelectItem(eu));
        }
        return lista;
    }

    public boolean validaCampos() {
        boolean valido = true;

        if (IDUsuario == null) {
            mensaje = "Por favor seleccione un usuario para realizar la acción correspondiente.";
            valido = false;
        } else {
            if (estado == EstadoUsuario.Rechazar) {
                if (mensajeEstado.equals("")) {
                    valido = false;
                    mensaje = "Si vas a rechazar a este usuario por favor especifica el por qué.";
                }
            }
        }
        return valido;
    }

    public void realizaAccion() {
        mensaje = "";
        if (validaCampos()) {
            Usuario user = usuarioSeleccionado(this.IDUsuario);
            try {

                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                Usuario userEdita = (Usuario) session.getAttribute("usuario");
                UsuarioDB uDB = new UsuarioDB();
                String estadoUser = "";
                int logActivo = 0;
                String subject = "";
                String message = "";

                if (this.estado == EstadoUsuario.Aceptar) {
                    subject = "Cuenta aceptada de cliente para Yammie Alimentos";
                    message = "Hola. Este correo es para informarte que tu cuenta ha sido activada. \n\n\n Yammie.";
                    logActivo = 1;
                    estadoUser = "Activo";
                } else {
                    subject = "Cuenta rechazada de cliente para Yammie Alimentos";
                    message = this.mensajeEstado;
                    estadoUser = "Rechazado";
                }

                uDB.actualizaEstadoUsuario(IDUsuario, userEdita, estadoUser, logActivo);
                Mail mail = new Mail();
                mail.setTo(user.getElectronico());
                mail.setSubject(subject);
                mail.setDescrp(message);
                mail.sendEmail();
                usuariosPendiente();
                cleanInputs();
                mensaje = "<p class=\"errorLabel\" style=\"color: #00FB3C\">"
                        + "Usuario modificado.<p>";
            } catch (Exception e) {
                mensaje = e.toString();
            }
        }
    }

    public Usuario usuarioSeleccionado(String cedula) {
        UsuarioDB uDB = new UsuarioDB();
        Usuario usuario = null;
        try {
            for (Usuario user : uDB.usuariosPendiente()) {
                if (user.getCedula().equals(cedula)) {
                    usuario = user;
                    break;
                }
            }

        } catch (Exception e) {
            mensaje = e.toString();
        }
        return usuario;
    }

    public void usuariosPendiente() {
        UsuarioDB uDB = new UsuarioDB();
        ArrayList<SelectItem> lista = new ArrayList<>();
        try {
            for (Usuario user : uDB.usuariosPendiente()) {
                String nombre = user.getNombre() + " " + user.getApellido() + " " + user.getApellido2();
                String info = user.getCedula() + " - " + nombre;
                lista.add(new SelectItem(user.getCedula(), info));
            }

        } catch (Exception e) {
            mensaje = e.toString();
        }

        this.listaPendientes = lista;
    }
    
    public void cleanInputs(){
        setIDUsuario("");
        setMensaje("");
        this.estado = EstadoUsuario.Aceptar;
    }
}
