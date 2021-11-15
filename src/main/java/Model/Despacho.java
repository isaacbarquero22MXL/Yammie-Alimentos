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
    private Date FechaSalida;
    private Date HoraEnvio;
    private MedioEnvio medioenvio;

    public Despacho() {
    }

    public Despacho(String Identificacion, Date FechaSalida, Date HoraEnvio, MedioEnvio medioenvio) {
        this.Identificacion = Identificacion;
        this.FechaSalida = FechaSalida;
        this.HoraEnvio = HoraEnvio;
        this.medioenvio = medioenvio;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String Identificacion) {
        this.Identificacion = Identificacion;
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

    public void setMedioenvio(MedioEnvio medioenvio) {
        this.medioenvio = medioenvio;
    }
    
    
}
