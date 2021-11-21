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

/**
 *
 * @author Bryan e Isaac
 */
public class ProductoDB {

    public void insertaProducto(Producto producto, Usuario usuario) throws SNMPExceptions {
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "INSERT INTO Producto VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, producto.getIdentificacion());
            insert.setString(2, producto.getFoto());
            insert.setInt(3, producto.getCantMinVenta());
            insert.setDouble(4, producto.getPrecio());
            insert.setDouble(5, producto.getTipo().getCodigo());
            insert.setString(6, producto.getDescripcion());
            insert.setString(7, usuario.getCedula());
            insert.setString(8, usuario.getCedula());
            insert.setDate(9, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setDate(10, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setBoolean(11, true);

            insert.executeUpdate();
            insert.close();
            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public boolean validaIDProducto(String ID) throws SNMPExceptions {
        String strSQL = "";
        boolean exist = false;
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT * FROM Producto where ID = '" + ID + "' ";

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

    public ArrayList<Producto> listaProductos() throws SNMPExceptions {
        String strSQL = "";
        ArrayList<Producto> lista = new ArrayList<>();
        TipoProductoDB tipoDB = new TipoProductoDB();
        try {

            ArrayList<TipoProducto> listaTipo = tipoDB.listaTipoProducto();

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT * FROM Producto where logActivo = 1";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                Producto producto = new Producto();
                producto.setIdentificacion(rsPA.getString(1));
                producto.setFoto(rsPA.getString(2));
                producto.setCantMinVenta(rsPA.getInt(3));
                producto.setPrecio(rsPA.getFloat(4));

                for (TipoProducto tipo : listaTipo) {
                    if(tipo.getCodigo() == rsPA.getInt(5)){
                        producto.setTipo(tipo);
                        break;
                    }
                }
                
                producto.setDescripcion(rsPA.getString(6));
                lista.add(producto);
            }
            rsPA.close();
            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return lista;
    }
    
    public void actualizaProducto(Producto producto, Usuario usuario) throws SNMPExceptions {
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "UPDATE PRODUCTO SET FOTOGRAFIA = ?, "
                    + "CANTMINVENTA = ?, PRECIO = ?, "
                    + "TIPOPRODUCTO = ?, DESCRIPCION = ?, "
                    + "USUARIOEDITA = ?, FECHAEDITA = ? "
                    + "WHERE ID = ?";

            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, producto.getFoto());
            insert.setInt(2, producto.getCantMinVenta());
            insert.setDouble(3, producto.getPrecio());
            insert.setDouble(4, producto.getTipo().getCodigo());
            insert.setString(5, producto.getDescripcion());
            insert.setString(6, usuario.getCedula());
            insert.setDate(7, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setString(8, producto.getIdentificacion());

            insert.executeUpdate();
            insert.close();
            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    
    public void eliminaProducto(Producto producto, Usuario usuario) throws SNMPExceptions {
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "UPDATE PRODUCTO SET LOGACTIVO = 0, USUARIOEDITA = ?, "
                    + "FECHAEDITA = ? "
                    + "WHERE ID = ?";

            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, usuario.getCedula());
            insert.setDate(2, new Date(Calendar.getInstance().getTime().getTime()));
             insert.setString(3, producto.getIdentificacion());

            insert.executeUpdate();
            insert.close();
            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
}
