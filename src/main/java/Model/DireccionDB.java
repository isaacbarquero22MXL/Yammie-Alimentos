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
import java.util.LinkedList;

/**
 *
 * @author Bryan e Isaac
 */
public class DireccionDB {

    public LinkedList<Provincia> listaProvincias() throws SNMPExceptions, SQLException {

        // breakpoint en plublic**
        String select = "";
        LinkedList<Provincia> listaPro = new LinkedList<Provincia>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            select = "SELECT COD_PROVINCIA,DSC_CORTA_PROVINCIA,DSC_PROVINCIA,LOG_ACTIVO "
                    + "FROM PROVINCIA WHERE LOG_ACTIVO=1;";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                float codigoProvincia = rsPA.getFloat("COD_PROVINCIA");
                String dscCortaProvincia = rsPA.getString("" + "DSC_CORTA_PROVINCIA");
                String dscProvincia = rsPA.getString("DSC_PROVINCIA");
                float logActivo = rsPA.getFloat("LOG_ACTIVO");

                Provincia perProvincia = new Provincia(codigoProvincia, dscCortaProvincia, dscProvincia, logActivo);
                listaPro.add(perProvincia);

            }

            rsPA.close(); // cierra conexion

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return listaPro;
    }

    public LinkedList<Canton> SeleccionarCantonPorProvincia(float codigoProv) throws SNMPExceptions, SQLException {

        String strSQL = "";
        LinkedList<Canton> listCant = new LinkedList<Canton>();

        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT COD_PROVINCIA,COD_CANTON,DSC_CANTON,LOG_ACTIVO\n "
                    + "FROM CANTON\n "
                    + "WHERE LOG_ACTIVO=1 AND COD_PROVINCIA=" + codigoProv + ";";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            while (rsPA.next()) {
                int codProvincia = rsPA.getInt("COD_PROVINCIA");
                int codCanton = rsPA.getInt("COD_CANTON");
                String descCanton = rsPA.getString("DSC_CANTON");
                int logActivo = rsPA.getInt("LOG_ACTIVO");

                Canton canton = new Canton(codProvincia,
                        codCanton, descCanton, logActivo);

                listCant.add(canton);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listCant;
    }

    public LinkedList<Distrito> SeleccionarDistritoporCanton(float codigoProv,float codigoCanton)
            throws SNMPExceptions, SQLException {
        
        String strSQL = "";
        LinkedList<Distrito> listDist = new LinkedList<Distrito>();

        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT COD_PROVINCIA,COD_CANTON,COD_DISTRITO,DSC_DISTRITO,LOG_ACTIVO "
                    + "FROM DISTRITO "
                    + "WHERE LOG_ACTIVO=1 AND COD_PROVINCIA="+ codigoProv
                    + " AND COD_CANTON=" + codigoCanton  + ";";
                    
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            while (rsPA.next()) {
                int codProvincia = rsPA.getInt("COD_PROVINCIA");
                int codCanton = rsPA.getInt("COD_CANTON");
                int codDistrito = rsPA.getInt("COD_DISTRITO");
                String descDistrito = rsPA.getString("DSC_DISTRITO");
                int logActivo = rsPA.getInt("LOG_ACTIVO");

                Distrito distrito = new Distrito(codProvincia,
                        codCanton, codDistrito,descDistrito, logActivo);

                listDist.add(distrito);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listDist;
    
    }
    
    public LinkedList<Barrio> SeleccionarBarrioporDistrito(float codigoProv,float codigoCanton, float codigoDistrito)
            throws SNMPExceptions, SQLException {
        
        String strSQL = "";
        LinkedList<Barrio> listBarrio = new LinkedList<Barrio>();

        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT COD_PROVINCIA,COD_CANTON,COD_DISTRITO,COD_BARRIO,DSC_BARRIO,LOG_ACTIVO\n " +
                     "FROM BARRIO\n " +
                     "WHERE LOG_ACTIVO=1 AND COD_PROVINCIA="+ codigoProv +"\n " +
                     "AND COD_CANTON="+ codigoCanton +" AND COD_DISTRITO="+ codigoDistrito +";";
                    
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            while (rsPA.next()) {
                int codProvincia = rsPA.getInt("COD_PROVINCIA");
                int codCanton = rsPA.getInt("COD_CANTON");
                int codDistrito = rsPA.getInt("COD_DISTRITO");
                float codBarrio = rsPA.getFloat("COD_BARRIO");
                String descBarrio = rsPA.getString("DSC_BARRIO");
                int logActivo = rsPA.getInt("LOG_ACTIVO");

                Barrio barrio = new Barrio(codProvincia,
                        codCanton, codDistrito,codBarrio ,descBarrio, logActivo);

                listBarrio.add(barrio);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listBarrio;
    }
    
    public void insertaDireccion(Direccion direccion, String usuario) throws SNMPExceptions{
         String strSQL = "";     
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();
            
            strSQL = "INSERT INTO DIRECCION VALUES (?,?,?,?,?,?,?)";
            
            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, usuario);
            insert.setFloat(2, direccion.getProvincia());
            insert.setFloat(3, direccion.getCanton());
            insert.setFloat(4, direccion.getDistrito());
            insert.setFloat(5, direccion.getBarrio());
            insert.setString(6, direccion.getOtrasSennas());
            insert.setString(7, direccion.getTipoDireccion());
            
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
