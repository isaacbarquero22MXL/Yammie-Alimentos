/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author bryan
 */
public class TipoProducto {
     String Descripcion;
     int codigo;

    public TipoProducto() {
    }

    public TipoProducto(String Descripcion, int codigo) {
        this.Descripcion = Descripcion;
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String toString(){
        return this.Descripcion;
    }
   
}
