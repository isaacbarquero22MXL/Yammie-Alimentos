/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Bryan e Isaac
 */
public class DespachoBD implements Serializable {

    public boolean seteaIDDepacho(String ID) throws SNMPExceptions {
        String strSQL = "";
        boolean exist = false;
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT * FROM Despacho WHERE ID = '" + ID + "' ";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                exist = true;
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

    public void insertaDespacho(Despacho despacho, Usuario user) throws SNMPExceptions {
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "INSERT INTO Despacho VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, despacho.getIdentificacion());
            insert.setString(2, despacho.getFechaSalida());
            insert.setString(3, despacho.getHoraEnvio());
            insert.setDate(4, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setDate(5, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setString(6, user.getCedula());
            insert.setString(7, user.getCedula());
            insert.setString(8, despacho.getFactura().getID());
            insert.setString(9, despacho.getMedioenvio().toString());

            insert.executeUpdate();
            insert.close();

            
            FacturaDB fDB = new FacturaDB();
            fDB.finalizaFactura(despacho.getFactura(), user);
            
            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
}
