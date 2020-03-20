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
    private String  siguientePos, anteriorPos, posicion;
    private NodoDoble ld;
    private NodoDoble li;
    private boolean anulable;
    private char dato;
 

    public NodoDoble(char dato) {
        this.dato = dato;
        this.siguientePos = null;
        this.anteriorPos = null;
        this.ld = null;
        this.li = null;
        this.posicion=null;
        this.anulable=false;
    }

    public String getPrimeraPos() {
        return siguientePos;
    }

    public void setPrimeraPos(String siguientePos) {
        this.siguientePos = siguientePos;
    }

    public String getUltimaPos() {
        return anteriorPos;
    }

    public void setUltimaPos(String anteriorPos) {
        this.anteriorPos = anteriorPos;
    }
    
    

    public char getDato() {
        return dato;
    }

    public void setDato(char dato) {
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

    public boolean getAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }
    
    
    
}
