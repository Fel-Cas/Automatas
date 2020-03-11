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
    private String dato, siguientePos, anteriorPos;
    private NodoDoble ld;
    private NodoDoble li;

    public NodoDoble(String dato, String siguientePos, String anteriorPos, NodoDoble ld, NodoDoble li) {
        this.dato = dato;
        this.siguientePos = siguientePos;
        this.anteriorPos = anteriorPos;
        this.ld = ld;
        this.li = li;
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
    
}
