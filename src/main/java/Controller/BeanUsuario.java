/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Barrio;
import Model.Canton;
import Model.Direccion;
import Model.DireccionDB;
import Model.Distrito;
import Model.Horario;
import Model.HorarioDB;
import Model.Provincia;
import Model.TipoRol;
import Model.Usuario;
import Model.UsuarioDB;
import java.io.Console;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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

    private float ID_Provincia;
    private float ID_Canton;
    private float ID_Distrito;
    private float ID_Barrio;
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

    // Usuario Global
    private Usuario usuario;

    //Constructor
    public BeanUsuario() {
        beanDireccion = new BeanDireccion();
        beanHorario = new BeanHorario();
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
                try {
                    // aquí se llamaría al DB para ejecutar el proceso de login
                    UsuarioDB uBD = new UsuarioDB();
                    if (uBD.validaIngreso(this.correo, this.contrasenna)) {
                        retorno = "ingreso";
                    } else {
                        mensaje = "El correo o contraseña no pertencen"
                                + " a ningún usuario registrado. Intenta de nuevo.";
                    }
                } catch (SNMPExceptions ex) {
                    mensaje = ex.toString();
                } catch (SQLException ex) {
                    mensaje = ex.toString();
                }
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

    // asigna los atributos al bean horario
    public String deshacerBeansComp() {
        beanDireccion.setID_Barrio(-1);
        beanDireccion.setID_Canton(-1);
        beanDireccion.setID_Distrito(-1);
        beanDireccion.setID_Provincia(-1);
        beanDireccion.setOtrasSenna("");
        beanHorario.setInicio("");
        beanHorario.setFin("");
        return "horarioDirecciones";
    }

    // realiza todas las validaciones del bean usuario, dirección y horario
    // si todo es correcto ira al DB registrará el usuario
    public String validaCamposRegistro() {
        String resultado = "";
        mensaje = "";
        if (validaCamposUsuario() && validaTelefono()
                && validaContrasennas() && validaCedula()) {
            if (validaBeanAdicionales()) {
                UsuarioDB userDB = new UsuarioDB();
                DireccionDB direccionDB = new DireccionDB();
                HorarioDB horarioDB = new HorarioDB();
                try {
                    if (!userDB.validaIDUsuario(this.Cedula, this.correo)) {
                        Usuario usuario = retornaUsuarioConstruido();
                        Direccion direccion = retornaDirrecionConstruido();
                        Horario horario = retornaHorarioConstruido();

                        userDB.insertaUsuario(usuario);
                        direccionDB.insertaDireccion(direccion, usuario.getCedula());
                        horarioDB.insertaDireccion(horario, usuario.getCedula());
                        contrasenna = "";
                        
                        mensaje = "<p class=\"errorLabel\" style=\"color: #DF9E16\">"
                                + "El registro ha sido completado. Su cuenta está en espera de aprobación.<p>";
                        resultado = "ingreso";
                    } else {
                        mensaje = "Esta cédula o correo de usuario ya se encuentra registrada en el sistema";
                    }
                } catch (SNMPExceptions ex) {
                    mensaje = ex.toString();
                }
            }
        }
        return resultado;
    }

    // valida los beans externos ajenos a usuarios, pero complementarios
    public boolean validaBeanAdicionales() {
        boolean estado = true;
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

    public void validaUsuarioMante() {
        mensaje = "";
        if (validaBeanAdicionales()) {
            // aqui se llama al db
        }
    }

    public Usuario retornaUsuarioConstruido() {
        Usuario user = new Usuario();
        user.setCedula(Cedula);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setApellido2(apellido2);
        user.setContrasenna(contrasenna);
        user.setElectronico(correo);
        user.setTelefono(telefono);
        user.setTipoRol(TipoRol.Cliente);

        return user;
    }

    public Direccion retornaDirrecionConstruido() {
        Direccion direccion = new Direccion();
        direccion.setProvincia(beanDireccion.getID_Provincia());
        direccion.setCanton(beanDireccion.getID_Canton());
        direccion.setDistrito(beanDireccion.getID_Distrito());
        direccion.setBarrio(beanDireccion.getID_Barrio());
        direccion.setOtrasSennas(beanDireccion.getOtrasSenna());

        return direccion;
    }

    public Horario retornaHorarioConstruido() {
        Horario horario = new Horario();
        horario.setInicio(beanHorario.getInicio());
        horario.setFin(beanHorario.getFin());

        return horario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BeanDireccion getBeanDireccion() {
        return beanDireccion;
    }

    public void setBeanDireccion(BeanDireccion beanDireccion) {
        this.beanDireccion = beanDireccion;
    }

    public BeanHorario getBeanHorario() {
        return beanHorario;
    }

    public void setBeanHorario(BeanHorario beanHorario) {
        this.beanHorario = beanHorario;
    }
}
