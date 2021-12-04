/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Factura;
import Model.FacturaDB;
import Model.MedioEnvio;
import Model.Pedido;
import Model.PedidoDB;
import Model.TipoCobro;
import Model.Usuario;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class BeanFactura {

    private String ID;
    private double Descuento;
    private double TotalBruto;
    private double Impuesto;
    private double CostoEnvio;
    private double TotalNeto;
    private TipoCobro cobro;

    // parte pedido
    private String IDPedido;
    private Pedido pedidoSeleccionado = new Pedido();

    // Lista Pedidos
    private ArrayList<SelectItem> itemPedidos = new ArrayList<>();
    private ArrayList<Pedido> listaPedido = new ArrayList<>();

    //consecutivo factura 
    private static int consecutivo = 1;

    // Usuario actual de facturacion
    private Usuario user;
    
    private FacturaDB fDB = new FacturaDB();

    public BeanFactura() {
        seteaIDFactura();
        refrescaPedidos();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (Usuario) session.getAttribute("usuario");
    }

    // Mensaje de error
    private String mensaje;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public double getTotalBruto() {
        return TotalBruto;
    }

    public void setTotalBruto(double TotalBruto) {
        this.TotalBruto = TotalBruto;
    }

    public double getImpuesto() {
        return Impuesto;
    }

    public void setImpuesto(double Impuesto) {
        this.Impuesto = Impuesto;
    }

    public double getCostoEnvio() {
        return CostoEnvio;
    }

    public void setCostoEnvio(double CostoEnvio) {
        this.CostoEnvio = CostoEnvio;
    }

    public double getTotalNeto() {
        return TotalNeto;
    }

    public void setTotalNeto(double TotalNeto) {
        this.TotalNeto = TotalNeto;
    }

    public TipoCobro getCobro() {
        return cobro;
    }

    public void setCobro(TipoCobro cobro) {
        this.cobro = cobro;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Pedido getPedido() {
        return pedidoSeleccionado;
    }

    public void setPedido(Pedido pedido) {
        this.pedidoSeleccionado = pedido;
    }

    public ArrayList<SelectItem> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(ArrayList<SelectItem> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

    public ArrayList<Pedido> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(ArrayList<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }

    public String getIDPedido() {
        return IDPedido;
    }

    public void setIDPedido(String IDPedido) {
        this.IDPedido = IDPedido;
    }

    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    
    public ArrayList<SelectItem> listaTipoCobro() {
        ArrayList<SelectItem> lista = new ArrayList<>();

        for (TipoCobro tipo : TipoCobro.values()) {
            lista.add(new SelectItem(tipo));
        }
        return lista;
    }

    public boolean validaCampos() {
        boolean estado = true;
        if (this.IDPedido.equals("0")) {
            mensaje = "Por favor seleccione un pedido para facturar.";
            estado = false;
        }
        if(Descuento < 0){
            mensaje = "Por favor digite un descuento válido mayor o igual a 0.";
            estado = false;
        }
        return estado;
    }

    public void realizarFacturacion() {
        mensaje = "";
        if (validaCampos()) {
            Factura factura = construyeFactura();
            
            FacturaDB fDB = new FacturaDB();
            try {
                fDB.insertaFactura(factura, user);
                mensaje = "<p class=\"errorLabel\" style=\"color: #00C32C\">"
                        + "Se ha realizado la factura para el pedido.<p>";
                seteaIDFactura();
                refrescaPedidos();
                cleanFields();
            } catch (Exception e) {
                mensaje = e.getMessage() + e.toString();
            }
        }
    }

    public void seteaIDFactura() {
        try {
            String ID = "F" + consecutivo;
            if (fDB.seteaFactura(ID)) {
                consecutivo++;
                seteaIDFactura();
            } else {
                this.ID = ID;
            }
        } catch (Exception e) {
            mensaje = e.toString();
        }
    }

    public void refrescaPedidos() {
        try {
            PedidoDB pDB = new PedidoDB();
            this.listaPedido = pDB.listaPedidos("Pendientes");
            this.itemPedidos = new ArrayList<>();
            itemPedidos.add(new SelectItem("0", "Seleccione"));
            for (Pedido pedido : listaPedido) {
                itemPedidos.add(new SelectItem(pedido.getID()));
            }
        } catch (Exception e) {
            mensaje = e.toString();
        }
    }
    
    public void seteaPedidoSeleccionado(){
        for (Pedido pedido : listaPedido) {
            if(this.IDPedido.equals(pedido.getID())){
                this.pedidoSeleccionado = pedido;
                break;
            }
        }
    }
    
    public Factura construyeFactura(){
        Factura factura = new Factura();
        factura.setID(ID);
        factura.setDescuento(Descuento);
        factura.setPedido(pedidoSeleccionado);
        factura.setTotalBruto(pedidoSeleccionado.calculaCosto());
        factura.setImpuesto(pedidoSeleccionado.calculaCostoImpuesto());
        factura.setTotalNeto(pedidoSeleccionado.total());
        factura.setMetodo(cobro);
        return factura;
    }
    
    public void cleanFields(){
        this.Descuento = 0;
        this.IDPedido = "0";
        this.cobro = TipoCobro.Contado;
    }
}
