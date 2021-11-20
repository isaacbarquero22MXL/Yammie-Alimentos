/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bryan e Isaac
 */
public class ProductoDB {

    public void insertaProducto(Producto producto) throws SNMPExceptions {
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "INSERT INTO Producto VALUES (?,?,?,?,?,?)";

            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, producto.getIdentificacion());
            insert.setString(2, producto.getFoto());
            insert.setInt(3, producto.getCantMinVenta());
            insert.setDouble(4, producto.getPrecio());
            insert.setDouble(5, producto.getTipo().getCodigo());
            insert.setString(6, producto.getDescripcion());

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
    
    public boolean validaIDUsuario(String ID) throws SNMPExceptions {
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
}
