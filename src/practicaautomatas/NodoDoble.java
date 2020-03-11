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
    private Object dato;
    private NodoDoble ld;
    private NodoDoble li;

    public NodoDoble(Object dato, NodoDoble ld, NodoDoble li) {
        this.dato = dato;
        this.ld = ld;
        this.li = li;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
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
