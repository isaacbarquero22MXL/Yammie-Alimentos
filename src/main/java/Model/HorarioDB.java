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
import java.util.ArrayList;

/**
 *
 * @author Bryan e Isaac
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
      public  ArrayList<Horario> ObtenerHorarios(String cedula) throws SNMPExceptions, 
            SQLException {
      String select = "";
         ArrayList<Horario> ListaHorarios= new ArrayList<Horario>();
          
          try {
                                                            
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = 
                      "select * from HorarioEntrega where IDUsuario ='"+cedula+"'";
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

               String IDRol=rsPA.getString("Descripcion");
                int ID=rsPA.getInt("ID");
                String inicio=rsPA.getString("Inicio");
                String fin=rsPA.getString("Fin");
                ListaHorarios.add(new Horario(ID, inicio, fin));
                
              }
              rsPA.close();
              
          } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          }catch (Exception e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());
          } finally {
              
          }
          return ListaHorarios;
      }
}


