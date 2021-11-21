/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.lang.model.element.Element;

/**
 *
 * @author Bryan e Isaac
 */
public enum TipoRol {
    Admin(0),Cliente(1),Despacho(2);
    
    public final int label;
    
    private TipoRol(int label){
        this.label=label;
    }
    
    public int getValue(){
        return label;
    }
}
