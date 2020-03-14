/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaautomatas;

/**
 *
 * @author andres
 */
public class NodoDoble {
    private String dato, siguientePos, anteriorPos, posicion;
    private NodoDoble ld;
    private NodoDoble li;
    private boolean anulable;
 

    public NodoDoble(String dato) {
        this.dato = dato;
        this.siguientePos = null;
        this.anteriorPos = null;
        this.ld = null;
        this.li = null;
        this.posicion=null;
        this.anulable=false;
    }

    public String getSiguientePos() {
        return siguientePos;
    }

    public void setSiguientePos(String siguientePos) {
        this.siguientePos = siguientePos;
    }

    public String getAnteriorPos() {
        return anteriorPos;
    }

    public void setAnteriorPos(String anteriorPos) {
        this.anteriorPos = anteriorPos;
    }
    
    

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public NodoDoble getLd() {
        return ld;
    }

    public void setLd(NodoDoble ld) {
        this.ld = ld;
    }

    public NodoDoble getLi() {
        return li;
    }

    public void setLi(NodoDoble li) {
        this.li = li;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    
    
}
