/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Bryan e Isaac
 */
public class TipoProductoDB {
    public ArrayList<TipoProducto> listaTipoProducto() throws SNMPExceptions, SQLException {

        // breakpoint en plublic**
        String select = "";
        ArrayList<TipoProducto> listaTipo = new ArrayList<TipoProducto>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Select * from TipoProducto";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                TipoProducto tipo = new TipoProducto(rsPA.getString(2), rsPA.getInt(1));
                listaTipo.add(tipo);
            }

            rsPA.close(); // cierra conexion
            accesoDatos.cerrarConexion(); //cierra la conexion al DB

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return listaTipo;
    }
}
