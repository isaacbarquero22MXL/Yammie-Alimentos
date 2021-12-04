/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DespachoBD;
import Model.Factura;
import Model.FacturaDB;
import Model.MedioEnvio;
import Model.Pedido;
import Model.PedidoDB;
import Model.Usuario;
import java.sql.Date;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class BeanDespacho {

    private String ID;
    private String IDFactura;
    private String FechaSalida = "0";
    private String HoraEnvio = "0";
    private MedioEnvio medioenvio;
    
    //listas y factura
    private ArrayList<SelectItem> itemFactura = new ArrayList<>();
    private ArrayList<Factura> listaFacturas = new ArrayList<>();
    
    private Factura facturaSeleccionada = new Factura();
    
    //consecutivo
    private static int consecutivo = 1;

    // DB
    private DespachoBD dBD = new DespachoBD();
    
    //user
    private Usuario user;
    
    // mensaje de error
    private String mensaje;

    public BeanDespacho(){
        seteaIDDespacho();
        refrescaFactura();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (Usuario) session.getAttribute("usuario");
    }
    
    public String getIDFactura() {
        return IDFactura;
    }

    public void setIDFactura(String IDFactura) {
        this.IDFactura = IDFactura;
    }

    public String getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(String FechaSalida) {
       FechaSalida = FechaSalida.substring(6, FechaSalida.length());
        this.FechaSalida = FechaSalida;
    }

    public String getHoraEnvio() {
        return HoraEnvio;
    }

    public void setHoraEnvio(String HoraEnvio) {
        HoraEnvio = HoraEnvio.substring(5, HoraEnvio.length());
        this.HoraEnvio = HoraEnvio;
    }   

    public MedioEnvio getMedioenvio() {
        return medioenvio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setMedioenvio(MedioEnvio medioenvio) {
        this.medioenvio = medioenvio;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<SelectItem> getItemFactura() {
        return itemFactura;
    }

    public void setItemFactura(ArrayList<SelectItem> itemFactura) {
        this.itemFactura = itemFactura;
    }

    public ArrayList<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(ArrayList<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public Factura getFacturaSeleccionada() {
        return facturaSeleccionada;
    }

    public void setFacturaSeleccionada(Factura facturaSeleccionada) {
        this.facturaSeleccionada = facturaSeleccionada;
    }

    public DespachoBD getdBD() {
        return dBD;
    }

    public void setdBD(DespachoBD dBD) {
        this.dBD = dBD;
    }

    
    public ArrayList<SelectItem> listaMedios() {
        ArrayList<SelectItem> lista = new ArrayList<>();

        for (MedioEnvio md : MedioEnvio.values()) {
            lista.add(new SelectItem(md));
        }
        return lista;
    }

    public boolean validaCampos() {
        boolean estado = true;
        if (medioenvio == null || IDFactura.equals("0")) {
            estado = false;
            mensaje = "Verifique que haya seleccionado una factura"
                    + " y el medio de envío de entrega.";
        }
        return estado;
    }

    public void realizaDespacho() {
        mensaje = "";
        if (validaCampos()) {
            // aqui se llama la DB;
        }
    }
    
    public void refrescaFactura() {
        try {
            FacturaDB fDB = new FacturaDB();
            this.listaFacturas = fDB.listaFacturas();
            this.itemFactura = new ArrayList<>();
            itemFactura.add(new SelectItem("0", "Seleccione"));
            for (Factura factura : listaFacturas) {
                itemFactura.add(new SelectItem(factura.getID()));
            }
        } catch (Exception e) {
            mensaje = e.toString();
        }
    }
    
    public void seteaIDDespacho() {
        try {
            String ID = "DESP" + consecutivo;
            if (dBD.seteaIDDepacho(ID)) {
                consecutivo++;
                seteaIDDespacho();
            } else {
                this.ID = ID;
            }
        } catch (Exception e) {
            mensaje = e.toString();
        }
    }
    
    public void seteaFacturaSeleccionado(){
        for (Factura factura : listaFacturas) {
            if(this.IDFactura.equals(factura.getID())){
                this.facturaSeleccionada = factura;
                break;
            }
        }
    }
}
