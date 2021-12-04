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
public class PedidoDB {

    public void insertaPedido(Pedido pedido) throws SNMPExceptions {
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "INSERT INTO Pedido VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, pedido.getID());
            insert.setString(2, pedido.getUsuario().getCedula());
            insert.setString(3, pedido.getHoraEntrega());
            insert.setString(4, pedido.getEstadoPedido().toString());
            insert.setDate(5, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setDate(6, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setString(7, pedido.getUsuario().getCedula());
            insert.setString(8, pedido.getUsuario().getCedula());
            insert.setInt(9, pedido.getDireccion());

            insert.executeUpdate();
            insert.close();

            strSQL = "INSERT INTO PedidoProducto values (?,?,?)";
            for (Producto productos : pedido.getListaCarrito()) {
                PreparedStatement insert2 = accesoDatos.getDbConn().prepareStatement(strSQL);
                insert2.setString(1, productos.getIdentificacion());
                insert2.setString(2, pedido.getID());
                insert2.setInt(3, 1);

                insert2.executeUpdate();
                insert2.close();
            }
            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public boolean validaIDPedido(String ID) throws SNMPExceptions {
        String strSQL = "";
        boolean exist = false;
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT * FROM PEDIDO WHERE ID = '" + ID + "' ";

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

    public ArrayList<Pedido> listaPedidos(String estado) throws SNMPExceptions {
        String strSQL = "";
        ArrayList<Pedido> lista = new ArrayList<>();
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //OBTENER LOS PRODUCTOS PARA REALIZAR BUSQUEDA
            ProductoDB proDB = new ProductoDB();
            ArrayList<Producto> listaProducto = proDB.listaProductos();

            strSQL = "SELECT * FROM PEDIDO where EstadoPedido = '"+estado+"'";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                Pedido pedido = new Pedido();
                pedido.setID(rsPA.getString(1));
                pedido.setDireccion(rsPA.getInt(9));
                pedido.setHoraEntrega(rsPA.getString(3));

                String strSQL2 = "SELECT * FROM PedidoProducto where IDPedido = '" + pedido.getID() + "' ";
                ResultSet rsPA2 = accesoDatos.ejecutaSQLRetornaRS(strSQL2);

                // lista de productos en el pedido
                ArrayList<Producto> listaProductoPedido = new ArrayList<>();

                while (rsPA2.next()) {
                    String IDProdcuto = rsPA2.getString(2);
                    for (Producto producto : listaProducto) {
                        if (IDProdcuto.equals(producto.getIdentificacion())) {
                            listaProductoPedido.add(producto);
                            break;
                        }
                    }
                }
                // obtener el usuario de dicho pedido
                UsuarioDB uDB = new UsuarioDB();
                pedido.setUsuario(uDB.obtenerUsuarioPorCedula(rsPA.getString(2)));
                
                //obtener la direccion 
                DireccionDB dDB = new DireccionDB();
                pedido.setObjectDireccion(dDB.obtenerDireccion(rsPA.getInt(9)));
                
                pedido.setListaCarrito(listaProductoPedido);
                rsPA2.close();
                lista.add(pedido);
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
    
    public void actualizaEstadoPedido(Pedido pedido, Usuario user, String estado) throws SNMPExceptions{
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "Update Pedido set EstadoPedido = ?, "
                    + "Usuariomodificacion = ?, FechaModificacion = ? "
                    + "where ID = ?";

            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, estado);
            insert.setString(2, user.getCedula());
            insert.setDate(3, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setString(4, pedido.getID());

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
