/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

/**
 *
 * @author Bryan e Isaac
 */
public class UsuarioDB {

    public boolean validaIngreso(String email, String contrasenna) throws SNMPExceptions, SQLException {
        String select = "";
        boolean exist = false;
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT u.cedula from USUARIO u, EstadoUsuario e where u.CorreoElectronico='"
                    + email + "' and u.contrasenna = '" + contrasenna + "' and u.logActivo = 1 and "
                    + "u.cedula = e.IDUsuario and e.Estado = 'Activo' ";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                exist = true;
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return exist;
    }

    public void insertaUsuario(Usuario usuario) throws SNMPExceptions {
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "INSERT INTO USUARIO VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, usuario.getContrasenna());
            insert.setString(2, usuario.getElectronico());
            insert.setString(3, usuario.getCedula());
            insert.setString(4, usuario.getNombre());
            insert.setString(5, usuario.getApellido());
            insert.setString(6, usuario.getApellido2());
            insert.setString(7, usuario.getTelefono());
            insert.setDate(8, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setDate(9, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setString(10, usuario.getCedula());
            insert.setDate(11, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setBoolean(12, false);

            insert.executeUpdate();
            insert.close();

            strSQL = "INSERT INTO ROLUSUARIO VALUES (?,?)";
            PreparedStatement insert2 = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert2.setString(1, usuario.getRolSeleccionado().toString());
            insert2.setString(2, usuario.getCedula());

            insert2.executeUpdate();
            insert2.close();

            strSQL = "INSERT INTO ESTADOUSUARIO VALUES (?,?,?)";
            PreparedStatement insert3 = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert3.setString(1, usuario.getCedula());
            insert3.setString(2, usuario.getCedula());
            insert3.setString(3, "Pendiente");

            insert3.executeUpdate();
            insert3.close();

            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public boolean validaIDUsuario(String cedula, String correo) throws SNMPExceptions {
        String strSQL = "";
        boolean exist = false;
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT * FROM USUARIO WHERE CEDULA = '" + cedula + "' or CorreoElectronico = '" + correo + "' ";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                exist = true;
                break;
            }
            rsPA.close();
            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return exist;
    }

    public Usuario ObtenerInfoUsuario(String Correo, String Contrasenna) throws SNMPExceptions,
            SQLException {
        String select = "";
        Usuario usuario = new Usuario();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "select * from usuario where CorreoElectronico='" + Correo + "' "
                    + "and contrasenna = '" + Contrasenna + "' ";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String ContrasennaBD = rsPA.getString("Contrasenna");
                String CorreoElectronico = rsPA.getString("CorreoElectronico");
                String Cedula = rsPA.getString("Cedula");
                String Nombre = rsPA.getString("nombre");
                String Apellido = rsPA.getString("apellido");
                String Apellido2 = rsPA.getString("apellido2");
                String telefono = rsPA.getString("telefono");
                Date FechaVenciContrasena = rsPA.getDate("FechVenciContrasena");

                usuario = new Usuario(ContrasennaBD, CorreoElectronico, Cedula, Nombre, Apellido, Apellido2, telefono, FechaVenciContrasena);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return usuario;
    }

    public ArrayList<Usuario> usuariosPendiente() throws SNMPExceptions,
            SQLException {
        String select = "";
        ArrayList<Usuario> listaU = new ArrayList<>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "select * from Usuario, EstadoUsuario where Usuario.logActivo = 0 and \n"
                    + "Usuario.Cedula = EstadoUsuario.IDUsuario and \n"
                    + "EstadoUsuario.Estado = 'Pendiente'";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                Usuario usuario = ObtenerInfoUsuario(rsPA.getString(2), rsPA.getString(1));
                listaU.add(usuario);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return listaU;
    }

    public void actualizaEstadoUsuario(String usuario , Usuario userEdita, String estado, int logActivo) throws SNMPExceptions {
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "Update Usuario set logActivo = "+logActivo+", usuarioEdita = ?, fechaEdita = ? where cedula = ?";
            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, userEdita.getCedula());
            insert.setDate(2, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setString(3, usuario);

            insert.executeUpdate();
            insert.close();
            
            strSQL = "Update EstadoUsuario set Estado = '"+estado+"' where IDUsuario = ?";
            PreparedStatement insert2 = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert2.setString(1, usuario);
            
            insert2.executeUpdate();
            insert2.close();

            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    
    public Usuario obtenerUsuarioPorCedula(String cedula) throws SNMPExceptions{
        String select = "";
        Usuario user = null;
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT * from Usuario where cedula = '"+ cedula +"' and logActivo = 1";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                user = ObtenerInfoUsuario(rsPA.getString(2), rsPA.getString(1));
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return user;
    }
}
