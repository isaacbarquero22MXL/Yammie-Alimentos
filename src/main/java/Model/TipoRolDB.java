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

/**
 *
 * @author bryan
 */
public class TipoRolDB {
    
    public  ArrayList<TipoRol> ObtenerTipoRol(String cedula) throws SNMPExceptions, 
            SQLException {
      String select = "";
         ArrayList<TipoRol> ListaRoles= new ArrayList<TipoRol>();
          
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = 
                      "select * from RolUsuario where IDUsuario ='"+ cedula +"' ";
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {
                  for (TipoRol rol : TipoRol.values()) {
                      if(rol.toString().endsWith(rsPA.getString("Descripcion"))){
                          ListaRoles.add(rol);
                      }
                  }
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
          return ListaRoles;
      }
}
