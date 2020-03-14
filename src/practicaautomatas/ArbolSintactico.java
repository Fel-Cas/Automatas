/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaautomatas;

import java.util.ArrayList;

/**
 *
 * @author andres
 */
public class ArbolSintactico {
     private NodoDoble raiz;
    /**
     * Este es el constructor del árbol binario
     **/
    public ArbolSintactico() {
    }

    /**
     * Método usado para extraer la raíz del árbol
     **/
    public NodoDoble getRaiz() {
        return raiz;
    }
    public void construirArbol(String hilera){
        this.raiz.setDato(".");
        NodoDoble x=new NodoDoble("#");
        raiz.setLd(x);
        int n=hilera.length();
        String aux1,aux3;
        char aux,aux2;
        ArrayList<String>cadenas=new ArrayList<String>();
        ArrayList<String>padres=new ArrayList<String>();
        for(int i=0;i<=(n-1);i++){
            aux=hilera.charAt(i);
            switch(aux){
                case'(':
                    int j=i+1;
                    boolean bandera=true;
                    while(bandera && j<=n ){
                        aux=hilera.charAt(j);
                        if(aux==')'){
                            bandera=false;
                        }
                        j+=1;
                    }
                    if(hilera.charAt(j)=='*'||hilera.charAt(j)=='+'){
                        aux1=hilera.substring(i+1, j-1);
                        aux2=hilera.charAt(j);
                        aux+=aux2;
                    }else{
                        aux1=hilera.substring(i+1,j-1);
                    }
                    i=j;
                    cadenas.add(aux1);
                    break;
                
            }
        }
        
    }
}
