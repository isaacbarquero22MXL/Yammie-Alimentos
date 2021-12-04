/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Bryan e Isaac
 */
public class Despacho {
    private String Identificacion ;
    private String FechaSalida;
    private String HoraEnvio;
    private MedioEnvio medioenvio;
    private Factura factura;

    public Despacho() {
    }

    public Despacho(String Identificacion, String FechaSalida, String HoraEnvio, MedioEnvio medioenvio, Factura factura) {
        this.Identificacion = Identificacion;
        this.FechaSalida = FechaSalida;
        this.HoraEnvio = HoraEnvio;
        this.medioenvio = medioenvio;
        this.factura = factura;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String Identificacion) {
        this.Identificacion = Identificacion;
    }

    public String getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(String FechaSalida) {
        this.FechaSalida = FechaSalida;
    }

    public String getHoraEnvio() {
        return HoraEnvio;
    }

    public void setHoraEnvio(String HoraEnvio) {
        this.HoraEnvio = HoraEnvio;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public MedioEnvio getMedioenvio() {
        return medioenvio;
    }

    public void setMedioenvio(MedioEnvio medioenvio) {
        this.medioenvio = medioenvio;
    }
    
    
}
