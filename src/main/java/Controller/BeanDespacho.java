/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MedioEnvio;
import java.sql.Date;
import java.util.ArrayList;
import javax.faces.model.SelectItem;

/**
 *
 * @author User
 */
public class BeanDespacho {

    private String IDFactura;
    private Date FechaSalida;
    private Date HoraEnvio;
    private MedioEnvio medioenvio;

    // mensaje de error
    private String mensaje;

    public String getIDFactura() {
        return IDFactura;
    }

    public void setIDFactura(String IDFactura) {
        this.IDFactura = IDFactura;
    }

    public Date getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(Date FechaSalida) {
        this.FechaSalida = FechaSalida;
    }

    public Date getHoraEnvio() {
        return HoraEnvio;
    }

    public void setHoraEnvio(Date HoraEnvio) {
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

    public ArrayList<SelectItem> listaMedios() {
        ArrayList<SelectItem> lista = new ArrayList<>();

        for (MedioEnvio md : MedioEnvio.values()) {
            lista.add(new SelectItem(md));
        }
        return lista;
    }

    public boolean validaCampos() {
        boolean estado = true;
        if (medioenvio == null || IDFactura.equals("Seleccione")) {
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
}
