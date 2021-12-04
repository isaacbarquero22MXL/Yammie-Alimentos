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
public class FacturaDB {

    public boolean seteaFactura(String ID) throws SNMPExceptions {
        String strSQL = "";
        boolean exist = false;
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT * FROM Factura WHERE ID = '" + ID + "' ";

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

    public void insertaFactura(Factura factura, Usuario user) throws SNMPExceptions {
        String strSQL = "";
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "INSERT INTO Factura VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement insert = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert.setString(1, factura.getID());
            insert.setString(2, factura.getPedido().getID());
            insert.setFloat(3, (float) factura.aplicaDescuento());
            insert.setString(4, factura.getMetodo().toString());
            insert.setFloat(5, (float) factura.getTotalBruto());
            insert.setFloat(6, (float) factura.getImpuesto());
            insert.setFloat(7, (float) factura.costoTotal());
            insert.setDate(8, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setDate(9, new Date(Calendar.getInstance().getTime().getTime()));
            insert.setString(10, user.getCedula());
            insert.setString(11, user.getCedula());
            insert.setFloat(12, (float) factura.getDescuento());

            insert.executeUpdate();
            insert.close();
            
            strSQL = "insert into EstadoFactura values (?,?)";
            PreparedStatement insert2 = accesoDatos.getDbConn().prepareStatement(strSQL);
            insert2.setString(1, factura.getID());
            insert2.setString(2, "Procesada");
            
            insert2.executeUpdate();
            insert2.close();

            PedidoDB pDB = new PedidoDB();
            pDB.actualizaEstadoPedido(factura.getPedido(), user, "En Proceso");
            accesoDatos.cerrarConexion();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public ArrayList<Factura> listaFacturas() throws SNMPExceptions {
        String strSQL = "";
        ArrayList<Factura> lista = new ArrayList<>();
        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "select * from Factura, EstadoFactura where "
                    + "Factura.ID = EstadoFactura.IDFactura"
                    + " and EstadoFactura.Estado = 'Procesada'";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                Factura factura = new Factura();
                factura.setID(rsPA.getString(1));
                factura.setDescuento(rsPA.getFloat(12));
                factura.setTotalBruto(rsPA.getFloat(5));
                factura.setImpuesto(rsPA.getFloat(6));
                factura.setTotalNeto(rsPA.getFloat(7));
                PedidoDB pDB = new PedidoDB();

                for (TipoCobro cobro : TipoCobro.values()) {
                    if (cobro.toString().equals(rsPA.getString(4))) {
                        factura.setMetodo(cobro);
                    }
                }

                for (Pedido pedido : pDB.listaPedidos("En Proceso")) {
                    if (pedido.getID().equals(rsPA.getString(2))) {
                        factura.setPedido(pedido);
                        break;
                    }
                }
                lista.add(factura);
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
}
