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
 * @author Bryan e Isaac
 */
public class BeanUsuario {

    // Atributos
    private String contrasenna;
    private String contrasennaConfirm;
    private String correo;
    private TipoRol tipoRol;
    private String Cedula;
    private String nombre;
    private String apellido;
    private String apellido2;
    private String telefono;
    private Date FechVenciContrasenna;

    //Bean de direccion
    private BeanDireccion beanDireccion;

    private String ID_Provincia;
    private String ID_Canton;
    private String ID_Distrito;
    private String ID_Barrio;
    private String otrasSenna;

    //Bean de Horario
    private BeanHorario beanHorario;
    private String inicio;
    private String fin;

    // Mensaje de error
    private String mensaje;

    // Validador patrones
    Pattern pattern
            = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    //Constructor
    public BeanUsuario() {
        beanDireccion = new BeanDireccion();
        beanHorario = new BeanHorario();
    }

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

    public String getOtrasSenna() {
        return otrasSenna;
    }

    public void setOtrasSenna(String otrasSenna) {
        this.otrasSenna = otrasSenna;
    }

    public String getContrasennaConfirm() {
        return contrasennaConfirm;
    }

    public void setContrasennaConfirm(String contrasennaConfirm) {
        this.contrasennaConfirm = contrasennaConfirm;
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
            } catch (IOException ex) {
            }
            return "";
        }
    }

    // Si el correo digitado en el index esta mal formateado, resetea la página y 
    // navega entre anclas hasta el div miniForm
    public void redirigirIndex() throws IOException {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect("index.xhtml#miniForm");
        } catch (Exception e) {
            mensaje = e.toString();
        }
    }

    // valida si el correo y contraseña se han digitado
    public String validaCamposIngreso() {
        mensaje = "";
        String retorno = "";
        if (correo.equals("") || contrasenna.equals("")) {
            mensaje = "Por favor digite correo y contraseña para ingresar";
        } else {
            if (validaCorreo().equals("ingreso")) {
                // aquí se llamaría al DB para ejecutar el proceso de login
                retorno = "ingreso";
            }
        }
        return retorno;
    }

    // destruye la sesión actual con los valores del bean
    // y redirige a la página del parámetro
    public String destroySessionAndReturn(String page) {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return page;
    }

    // valida campos nulos
    public boolean validaCamposUsuario() {
        if (Cedula.equals("") || apellido.equals("")
                || apellido2.equals("") || nombre.equals("")
                || correo.equals("") || contrasenna.equals("")
                || telefono.equals("") || contrasennaConfirm.equals("")) {
            mensaje = "Por favor rellene todos los campos de información personal"
                    + " para contirnuar con el registro";
            return false;
        } else {
            return true;
        }
    }

    // valida que el teléfono tenga 8 dígitos y que sea numérico
    public boolean validaTelefono() {
        boolean estado = true;

        for (int i = 0; i < telefono.length(); i++) {
            char digito = telefono.charAt(i);

            if (Character.isLetter(digito)) {
                estado = false;
                mensaje = "El formato de teléfono contiene letras. Asegúrese "
                        + " que sean solo números.";
                break;
            }
        }
        if (estado) {
            if (telefono.length() != 8) {
                estado = false;
                mensaje = "El formato de teléfono debe tener 8 digitos sin guión (-).";
            }
        }
        return estado;
    }

    // valida que las contraseñas sean iguales
    public boolean validaContrasennas() {
        if (contrasenna.equals(contrasennaConfirm)) {
            return true;
        } else {
            mensaje = "Las contraseñas no coinciden. Verifique de nuevo.";
            return false;
        }
    }

    // valida que la cedula sea numérica, de 8 dígitos y sin guiones
    public boolean validaCedula() {
        boolean estado = true;

        for (int i = 0; i < Cedula.length(); i++) {
            char digito = Cedula.charAt(i);

            if (Character.isLetter(digito)) {
                estado = false;
                mensaje = "El formato de cédula contiene letras. Asegúrese "
                        + " que sean solo números sin guiones (-).";
                break;
            }
        }

        if (estado) {
            if (Cedula.length() != 9) {
                estado = false;
                mensaje = "La cédula consta de 9 digitos numéricos sin guión.";
            }
        }
        return estado;
    }

    // asigna los atributos del bean direccion
    public void asignarDireccion() {
        beanDireccion.setID_Barrio(ID_Barrio);
        beanDireccion.setID_Canton(ID_Canton);
        beanDireccion.setID_Distrito(ID_Distrito);
        beanDireccion.setID_Provincia(ID_Provincia);
        beanDireccion.setOtrasSenna(otrasSenna);
    }

    // asigna los atributos al bean horario
    public void asignarHorario() {
        beanHorario.setInicio(inicio);
        beanHorario.setFin(fin);
    }

    public String deshacerBeansComp(){
        beanDireccion.setID_Barrio("");
        beanDireccion.setID_Canton("");
        beanDireccion.setID_Distrito("");
        beanDireccion.setID_Provincia("");
        beanDireccion.setOtrasSenna("");
        beanHorario.setInicio("");
        beanHorario.setFin("");
        return "horarioDirecciones";
    }
    // realiza todas las validaciones del bean usuario, dirección y horario
    // si todo es correcto ira al DB registrará el usuario
    public void validaCamposRegistro() {
        mensaje = "";
        if (validaCamposUsuario() && validaTelefono()
                && validaContrasennas() && validaCedula()) {
            if(validaBeanAdicionales()){
                mensaje = "registro completado";
            }
        }
    }

    // valida los beans externos ajenos a usuarios, pero complementarios
    public boolean validaBeanAdicionales() {
        boolean estado = true;
        asignarDireccion();
        asignarHorario();
        if (!beanDireccion.validaCampos()) {
            estado = false;
            mensaje = "Verifique que haya seleccionado todos lo campos requeridos"
                    + " para la dirección y continuar con el registro.";
        } else {
            if (!beanHorario.validaDatosNulos()) {
                estado = false;
                mensaje = "Verifique haya seleccionado un horario"
                        + " para continuar con el registro";
            }
        }
        return estado;
    }
    
    public void validaUsuarioMante(){
        mensaje = "";
        if(validaBeanAdicionales()){
            // aqui se llama al db
        }
    }
}
