/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class HorarioDB {
     public void insertaDireccion(Horario horario, String usuario) throws SNMPExceptions{
         String strSQL = "";     
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();
            
            strSQL = "INSERT INTO HorarioEntrega VALUES (?,?,?)";
            
            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, horario.getInicio());
            insert.setString(2, horario.getFin());
            insert.setString(3, usuario);
            
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


