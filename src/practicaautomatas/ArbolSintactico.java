/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaautomatas;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author andres
 */
public class ArbolSintactico {
      private NodoDoble raiz;
    /**
     * Este es el constructor del árbol binario
     **/
     /*
      Metodo constructor
      */
     public ArbolSintactico() {
    }
    /*
     Metodo encargado de inicialzar el arbol con el punto de cocatencion en la raiz y el 
     simbolo de secuencia nula(#) como hijo derecho. Ademas es el encargado de verificar los casos
     del resto de la gramatica, si tiene union(|), cocatenacion(.) o solo parentesis.
     */
    public void crearArbol(String hilera){
        String auxHilera=null;
        char padre,hijo,aux;
        StringBuilder invertir=new StringBuilder(hilera);
        auxHilera=invertir.reverse().toString();
        int i=0;
        hijo=auxHilera.charAt(i);
        i++;
        padre=auxHilera.charAt(i);
        NodoDoble x=new NodoDoble(padre);
        raiz=x;
        NodoDoble y=new NodoDoble(hijo);
        x.setLd(y);
        NodoDoble p=raiz;
        if(soloParentesis(hilera)){
            int contador=0;
            for(i=i+1;i<auxHilera.length();i++){
            aux=auxHilera.charAt(i);
            switch(aux){
                case'+':
                    x=new NodoDoble(aux);
                    p.setLi(x);
                    p=x;
                    break;
                case'*':
                    x=new NodoDoble(aux);
                    x=new NodoDoble(aux);
                    p.setLi(x);
                    p=x;
                    break;
                case')':
                    contador++;
                    boolean bandera=true;
                    String hilera2="";
                    while(bandera){
                        if(contador!=0){
                           i++;
                           aux=auxHilera.charAt(i);
                           
                           if(aux==')'){
                               contador++;
                            }else{
                               if(aux=='('){
                                   contador--;
                               }
                           }
                           hilera2+=aux;
                        }else{
                        bandera=false;
                        }
                    }
                    hilera2=hilera2.substring(0, hilera2.length()-1);
                    StringBuilder n=new StringBuilder(hilera2);
                    String copia=n.reverse().toString();
                    if(tieneUnion(copia)){ 
                        dosHileras(p,hilera2,2);
                
                    }else if(tieneConcatenacion(copia)){
                        concatenacion(hilera2,p,2);
                    }
                    break;
            }
            
           }
        }else if(tieneUnion(hilera)){
            dosHileras(p,auxHilera,2); 
        }else if(tieneConcatenacion(hilera)){
            concatenacion(auxHilera,p,2);
        }else{
            NodoDoble z;
            char hijo1=' ',hijo2, padre1;
            for(i=i+1;i<auxHilera.length();i++){
            aux=auxHilera.charAt(i);
            switch(aux){
                case'+':
                    x=new NodoDoble(aux);
                    p.setLi(x);
                    p=x;
                    break;
                case'*':
                    x=new NodoDoble(aux);
                    x=new NodoDoble(aux);
                    p.setLi(x);
                    p=x;
                    break;
                case')':
                    boolean bandera=true;
                    String hilera2="";
                    while(bandera){
                        if(aux!='('){
                           i++;
                           aux=auxHilera.charAt(i);
                           hilera2+=aux;
                        }else{
                        bandera=false;
                        }
                    }
                    hilera2=hilera2.substring(0, hilera2.length()-1);
                    StringBuilder n=new StringBuilder(hilera2);
                    String copia=n.reverse().toString();
                    if(tieneUnion(copia)){ 
                        dosHileras(p,hilera2,2);
                
                    }else if(tieneConcatenacion(copia)){
                        concatenacion(hilera2,p,2);
                    }
                    break;
                    case'.':
                        x=new NodoDoble(aux);
                        p.setLi(x);
                        y=new NodoDoble(hijo1);
                        x.setLd(y);
                        p=x;
                        
                    break;
                default:
                    if((auxHilera.length()-i)==3|| auxHilera.length()==3){
                                    hijo1=aux;
                                    i++;
                                    padre1=auxHilera.charAt(i);
                                    i++;
                                    hijo2=auxHilera.charAt(i);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    z=new NodoDoble(hijo2);
                                    x.setLd(y);
                                    x.setLi(z);
                                    p.setLi(x);
                                    p=x;
                                    
                                }else if((auxHilera.length()-i)==2){
                                    padre=aux;
                                    i++;
                                    hijo1=auxHilera.charAt(i);
                                    x=new NodoDoble(padre);
                                    y=new NodoDoble(hijo1);
                                    x.setLi(y);
                                    p.setLi(x);
                                }else if((auxHilera.length()-i)==1){
                                    y=new NodoDoble(aux);
                                    p.setLi(y);
                                }else{
                                    hijo1=aux;
                                }
                            }
            
                        }
                    }
        
    }
    /*
    Metodo utilizado para ver el recorido inOrden del arbol
    */
    public String inOrden(NodoDoble r){        
       String hilera="";
        if(r!=null){
            hilera= hilera+inOrden((NodoDoble)r.getLi());
            hilera+=r.getDato();
            hilera=hilera+inOrden((NodoDoble)r.getLd());
        }
       return hilera;        
    }
    /*
    Metodo que retorna verdadero o falso, dependiendo si el simbolo con mayor
    pioridad es la union. Osea que si en una hilera o la otra
    */
    public  boolean tieneUnion(String hilera){
        boolean condicion=false;
        int n=hilera.length(),i;
        char aux;
        for(i=0;i<n;i++){
            aux=hilera.charAt(i);
            if(aux=='('){
                i+=1;
                boolean bandera=true;
                while(bandera){                        
                if(aux!=')'){
                        aux=hilera.charAt(i);
                        i+=1;
                    }else{
                        bandera=false;
                    }
                
                }
                i--;
            }
            if(aux=='|'){
                condicion=true;
            }
        }
        return condicion;
    }
    /*
    Metodo encargado de devolver la posicion donde se encuentra la union con 
    mayor prioridad
    */
    public int posicionUnion(String hilera){
        int pos=0;
        int n=hilera.length(),i;
        char aux;
        for(i=0;i<n;i++){
            aux=hilera.charAt(i);
            if(aux==')'){
                i+=1;
                boolean bandera=true;
                while(bandera){                        
                if(aux!='('){
                        aux=hilera.charAt(i);
                        i+=1;
                    }else{
                        bandera=false;
                    }
                }
                aux=hilera.charAt(i);
            }
            if(aux=='|'){
                pos=i;
                return i;
            }
        }
        return pos;
    }
    /*
    Metodo que retorna verdadero o falso, dependiendo si el lenguaje entrado esta entre
    parentesis.
    */
    public boolean soloParentesis(String hilera){
        boolean condicion=false;
        int contador=0;
        char aux;
        for(int i=0;i<hilera.length();i++){
            aux=hilera.charAt(i);
            switch(aux){
                case'(':
                    contador++;
                    boolean bandera=true;
                    while(bandera){
                        i++;
                        aux=hilera.charAt(i);
                        if(aux=='('){
                            contador++;
                        }
                        if(aux==')'){
                            contador--;
                            if(contador==0){
                                bandera=false;
                            }
                        }
                    }
                   
                    break;
                case'*':
                    i++;
                    aux=hilera.charAt(i);
                    if(aux=='.'){
                        i++;
                        aux=hilera.charAt(i);
                        if(aux=='#'){
                            return true;
                        }
                        
                    }
                    i--;
                    break;
                case'+':
                    i++;
                    aux=hilera.charAt(i);
                    if(aux=='.'){
                        i++;
                        aux=hilera.charAt(i);
                        if(aux=='#'){
                            return true;
                        }
                        
                    }
                    i--;
                    break;
                case'.':
                    i++;
                    if(hilera.charAt(i)=='#'){
                        return true;
                    }
                    break;
                default:
                    return false;
            }
        }
        return condicion;
    }
    /*
    Metodo encargado de separar en dos partes la hilera, una parte desde el inicio hasta 
   una posicion antes de la union y otra una posicion despues hasta el final de la hilera.
    pone a la union como padre y le da el trtamiento correspondiente a las otras dos hileras.
    */
    public void dosHileras(NodoDoble padre,String auxHilera,int desicion){
        NodoDoble x,y,z,w,padreU,pad;
        int pos=posicionUnion(auxHilera);
        Stack pila=new Stack();
        pila.push(auxHilera.substring(pos+1,auxHilera.length()));
        if(auxHilera.charAt(0)=='#'){
            pila.push(auxHilera.substring(2,pos));
        }else{
            pila.push(auxHilera.substring(0,pos));
        }
        x=new NodoDoble(auxHilera.charAt(pos));
        if(desicion==1){
            padre.setLd(x);
            pad=x;
        }else{
            padre.setLi(x);
            pad=x;
        }
        padreU=pad;
        int contador=0,j=0;
        String parte;
        char aux,padre1=' ',hijo1=' ',hijo2=' ';
        while(!pila.empty()){
            parte=(String)pila.pop();
            pad=padreU;
            contador++;
            StringBuilder n=new StringBuilder(parte);
            String copia=n.reverse().toString();
            if(tieneUnion(copia)){ 
                dosHileras(pad,parte,contador);
                
            }else if(tieneConcatenacion(copia)){
                concatenacion(parte,pad,contador);
                 
            }else{
                
                if(contador==1){            
                    for(j=0;j<parte.length();j++){
                        aux=parte.charAt(j);
                        switch(aux){
                            case'*':
                                if(pad==padreU){
                                    x=new NodoDoble(aux);
                                    pad.setLd(x);
                                    pad=x;
                                }else{
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                }
                                if(parte.charAt(j+1)==')'){
                                    contador=2;
                                }
                                break;
                            case'+':
                                if(pad==padreU){
                                    x=new NodoDoble(aux);
                                    pad.setLd(x);
                                    pad=x;
                                }else{
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                }
                                if(parte.charAt(j+1)==')'){
                                    contador=2;
                                }
                                break;
                            case')':
                                String hilera2="";
                                boolean bandera=true;
                                j++;
                                while(bandera){
                                    if(aux!='('){
                                        aux=parte.charAt(j);
                                        hilera2+=aux;
                                        j+=1;
                                        aux=parte.charAt(j);

                                    }else{
                                        bandera=false;
                                    }
                                }
                                    n=new StringBuilder(hilera2);
                                    copia=n.reverse().toString();
                                    if(tieneUnion(copia)){
                                        dosHileras(pad,hilera2,contador);
                                        
                                    }else{
                                        concatenacionDosHileras(contador,padreU,pad,hilera2);
                                    }
                                    contador=1;
                                                                       
                            break;
                            case'.':
                                if(pad==padreU){
                                    x=new NodoDoble(aux);
                                    pad.setLd(x);
                                }else{
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                } 
                                y=new NodoDoble(hijo1);
                                x.setLd(y);
                                pad=x;
                            break;
                            default:
                                if((parte.length()-j)==3|| parte.length()==3){
                                    hijo1=aux;
                                    j++;
                                    padre1=parte.charAt(j);
                                    j++;
                                    hijo2=parte.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    z=new NodoDoble(hijo2);
                                    x.setLd(y);
                                    x.setLi(z);
                                    if(pad==padreU){
                                        
                                        pad.setLd(x);
                                        pad=x;
                                    }else{
                                        pad.setLi(x);
                                        pad=x;
                                    } 
                                    
                                }else if((parte.length()-j)==2||parte.length()==2){
                                    padre1=aux;
                                    j++;
                                    hijo1=parte.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    x.setLi(y);
                                    if(pad==padreU){
                                        pad.setLd(x);
                                        pad=x;
                                    }else{
                                        pad.setLi(x);
                                        pad=x;
                                    } 
                                }else if((parte.length()-j)==1||parte.length()==1){
                                    y=new NodoDoble(aux);
                                    pad.setLd(y);
                                }else{
                                    hijo1=aux;
                                }
                        }
                     }
                }else{
                    for(j=0;j<parte.length();j++){
                        aux=parte.charAt(j);
                        switch(aux){
                            case'*':
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                                          
                                break;
                            case'+':
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                break;
                            case')':
                                String hilera2="";
                                boolean bandera=true;
                                j++;
                                while(bandera){
                                    if(aux!='('){
                                        aux=parte.charAt(j);
                                        hilera2+=aux;
                                        j+=1;
                                        aux=parte.charAt(j);

                                    }else{
                                        bandera=false;
                                    }                                  
                                }
                                n=new StringBuilder(hilera2);
                                copia=n.reverse().toString();
                                if(tieneUnion(copia)){
                                        dosHileras(pad,hilera2,contador);
                                    }else{
                                        concatenacionDosHileras(contador,padreU,pad,hilera2);
                                }
                            break;
                            case'.':
                                x=new NodoDoble(aux);
                                pad.setLi(x);
                                y=new NodoDoble(hijo1);
                                x.setLd(y);
                                pad=x;
                            break;
                            default:
                                if((parte.length()-j)==3|| parte.length()==3){
                                    hijo1=aux;
                                    j++;
                                    padre1=parte.charAt(j);
                                    j++;
                                    hijo2=parte.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    z=new NodoDoble(hijo2);
                                    x.setLd(y);
                                    x.setLi(z);
                                    pad.setLi(x);
                                    pad=x;
                                    
                                }else if((parte.length()-j)==2||parte.length()==2){
                                    padre1=aux;
                                    j++;
                                    hijo1=parte.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    x.setLi(y);
                                    pad.setLi(x);
                                    pad=x;
                                }else if((parte.length()-j)==1||parte.length()==1){
                                    y=new NodoDoble(aux);
                                    pad.setLi(y);
                                }else{
                                    hijo1=aux;
                                }
                        }
                     }
                }
            }
                
        }
    }
    /*
    Metodo encargado de tratar una parte de caracteres concatenados que aparecen dentro de un parentesis
    */
    public void concatenacionDosHileras(int contador,NodoDoble padre, NodoDoble pad, String hilera2){
        char aux;
        NodoDoble x,y,z;
        char padre1=' ',hijo1=' ',hijo2=' ';
        if(contador==1){
            for(int j=0;j<hilera2.length();j++){
                aux=hilera2.charAt(j);
                switch(aux){
                            case'*':
                                if(pad==padre){
                                    x=new NodoDoble(aux);
                                    pad.setLd(x);
                                    pad=x;
                                }else{
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                }                          
                                break;
                            case'+':
                                if(pad==padre){
                                    x=new NodoDoble(aux);
                                    pad.setLd(x);
                                    pad=x;
                                }else{
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                }  
                                break;
                            case'.':
                                if(pad==padre){
                                    x=new NodoDoble(aux);
                                    pad.setLd(x);
                                }else{
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                } 
                                y=new NodoDoble(hijo1);
                                x.setLd(y);
                                pad=x;
                            break;
                            default:
                                if((hilera2.length()-j)==3|| hilera2.length()==3){
                                    hijo1=aux;
                                    j++;
                                    padre1=hilera2.charAt(j);
                                    j++;
                                    hijo2=hilera2.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    z=new NodoDoble(hijo2);
                                    x.setLd(y);
                                    x.setLi(z);
                                    if(pad==padre){;
                                        pad.setLd(x);
                                        pad=x;
                                    }else{
                                        pad.setLi(x);
                                        pad=x;
                                    } 
                                    
                                }else if((hilera2.length()-j)==2||hilera2.length()==2){
                                    padre1=aux;
                                    j++;
                                    hijo1=hilera2.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    x.setLi(y);
                                    if(pad==padre){
                                        pad.setLd(x);
                                        pad=x;
                                    }else{
                                        pad.setLi(x);
                                        pad=x;
                                    } 
                                }else if((hilera2.length()-j)==1||hilera2.length()==1){
                                    y=new NodoDoble(aux);
                                    pad.setLd(y);
                                }else{
                                    hijo1=aux;
                                }
                        }
            }
        }else{
            for(int j=0;j<hilera2.length();j++){
                aux=hilera2.charAt(j);
                switch(aux){
                            case'*':
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                                          
                                break;
                            case'+':
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                break;
                            case'.':
                                x=new NodoDoble(aux);
                                pad.setLi(x);
                                y=new NodoDoble(hijo1);
                                x.setLd(y);
                                pad=x;
                            break;
                            default:
                                if((hilera2.length()-j)==3|| hilera2.length()==3){
                                    hijo1=aux;
                                    j++;
                                    padre1=hilera2.charAt(j);
                                    j++;
                                    hijo2=hilera2.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    z=new NodoDoble(hijo2);
                                    x.setLd(y);
                                    x.setLi(z);
                                    pad.setLi(x);
                                    pad=x;
                                    
                                }else if((hilera2.length()-j)==2||hilera2.length()==2){
                                    padre1=aux;
                                    j++;
                                    hijo1=hilera2.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    x.setLi(y);
                                    pad.setLi(x);
                                    pad=x;
                                }else if((hilera2.length()-j)==1||hilera2.length()==1){
                                    y=new NodoDoble(aux);
                                    pad.setLi(y);
                                }else{
                                    hijo1=aux;
                                }
                        }
            }
            
        }
        
    }
    /*
    Metodo encargado de separar en dos partes la hilera, una parte desde el inicio hasta 
   una posicion antes de la concatenacion y otra una posicion despues hasta el final de la hilera.
    pone a la concatenacion como padre y le da el trtamiento correspondiente a las otras dos hileras.
    */
    public void concatenacion(String auxHilera,NodoDoble padre,int liga){
        NodoDoble x,y,z,padreC,pad;
        pad=padre;
        padreC=pad;
        int pos=posicionConcatenacion(auxHilera);
        Stack pila=new Stack();
        pila.push(auxHilera.substring(pos+1,auxHilera.length()));
        if(auxHilera.charAt(0)=='#'){
            pila.push(auxHilera.substring(2,pos));
        }else{
            pila.push(auxHilera.substring(0,pos));
        }
        x=new NodoDoble(auxHilera.charAt(pos));
        if(liga==1){
            padre.setLd(x);
            pad=x;
        }else{
            padre.setLi(x);
            pad=x;
        }   
 
        padreC=pad;
        int contador=0,j=0;
        String parte;
        char aux,padre1=' ',hijo1=' ',hijo2=' ';
        while(!pila.isEmpty()){
            parte=(String)pila.pop();
            pad=padreC;
            contador++;
            StringBuilder n=new StringBuilder(parte);
            String copia=n.reverse().toString();
            if(tieneUnion(copia)){ 
                dosHileras(pad,parte,contador);
                
            }else if(tieneConcatenacion(copia)){
                concatenacion(parte,pad,contador);
                 
            }else{
                
                if(contador==1){            
                    for(j=0;j<parte.length();j++){
                        aux=parte.charAt(j);
                        switch(aux){
                            case'*':
                                if(pad==padreC){
                                    x=new NodoDoble(aux);
                                    pad.setLd(x);
                                    pad=x;
                                }else{
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                }
                                if(parte.charAt(j+1)==')'){
                                    contador=2;
                                }
                                break;
                            case'+':
                                if(pad==padreC){
                                    x=new NodoDoble(aux);
                                    pad.setLd(x);
                                    pad=x;
                                }else{
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                }
                                if(parte.charAt(j+1)==')'){
                                    contador=2;
                                }
                                break;
                            case')':
                                String hilera2="";
                                boolean bandera=true;
                                j++;
                                while(bandera){
                                    if(aux!='('){
                                        aux=parte.charAt(j);
                                        hilera2+=aux;
                                        j+=1;
                                        aux=parte.charAt(j);

                                    }else{
                                        bandera=false;
                                    }
                                }
                                    n=new StringBuilder(hilera2);
                                    copia=n.reverse().toString();
                                    if(tieneUnion(copia)){
                                        dosHileras(pad,hilera2,contador);
                                    }else{
                                        concatenacionDosHileras(contador,padreC,pad,hilera2);
                                    }
                                    contador=1;                                   
                            break;
                            case'.':
                                if(pad==padreC){
                                    x=new NodoDoble(aux);
                                    pad.setLd(x);
                                }else{
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                } 
                                y=new NodoDoble(hijo1);
                                x.setLd(y);
                                pad=x;
                            break;
                            default:
                                if((parte.length()-j)==3|| parte.length()==3){
                                    hijo1=aux;
                                    j++;
                                    padre1=parte.charAt(j);
                                    j++;
                                    hijo2=parte.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    z=new NodoDoble(hijo2);
                                    x.setLd(y);
                                    x.setLi(z);
                                    if(pad==padreC){
                                        pad.setLd(x);
                                        pad=x;
                                    }else{
                                        pad.setLi(x);
                                        pad=x;
                                    } 
                                    
                                }else if((parte.length()-j)==2||parte.length()==2){
                                    padre1=aux;
                                    j++;
                                    hijo1=parte.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    x.setLi(y);
                                    if(pad==padreC){
                                        x=new NodoDoble(aux);
                                        pad.setLd(x);
                                    }else{
                                        pad.setLi(x);
                                        pad=x;
                                    } 
                                }else if((parte.length()-j)==1||parte.length()==1){
                                    y=new NodoDoble(aux);
                                    pad.setLd(y);
                                }else{
                                    hijo1=aux;
                                }
                        }
                     }
                }else{
                    for(j=0;j<parte.length();j++){
                        aux=parte.charAt(j);
                         switch(aux){
                            case'*':
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                                          
                                break;
                            case'+':
                                    x=new NodoDoble(aux);
                                    pad.setLi(x);
                                    pad=x;
                                break;
                            case')':
                                String hilera2="";
                                boolean bandera=true;
                                j++;
                                while(bandera){
                                    if(aux!='('){
                                        aux=parte.charAt(j);
                                        hilera2+=aux;
                                        j+=1;
                                        aux=parte.charAt(j);

                                    }else{
                                        bandera=false;
                                    }                                  
                                }
                                n=new StringBuilder(hilera2);
                                copia=n.reverse().toString();
                                if(tieneUnion(copia)){
                                        dosHileras(pad,hilera2,contador);
                                    }else{
                                        concatenacionDosHileras(contador,padreC,pad,hilera2);
                                }
                                
                            break;
                            case'.':
                                x=new NodoDoble(aux);
                                pad.setLi(x);
                                y=new NodoDoble(hijo1);
                                x.setLd(y);
                                pad=x;
                            break;
                            default:
                                if((parte.length()-j)==3|| parte.length()==3){
                                    hijo1=aux;
                                    j++;
                                    padre1=parte.charAt(j);
                                    j++;
                                    hijo2=parte.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    z=new NodoDoble(hijo2);
                                    x.setLd(y);
                                    x.setLi(z);
                                    pad.setLi(x);
                                    pad=x;
                                    
                                }else if((parte.length()-j)==2||parte.length()==2){
                                    padre1=aux;
                                    j++;
                                    hijo1=parte.charAt(j);
                                    x=new NodoDoble(padre1);
                                    y=new NodoDoble(hijo1);
                                    x.setLi(y);
                                    pad.setLi(x);
                                    pad=x;
                                }else if((parte.length()-j)==1||parte.length()==1){
                                    y=new NodoDoble(aux);
                                    pad.setLi(y);
                                }else{
                                    hijo1=aux;
                                }
                        }
                     }
                }
            }
        }
    }
     /*
    Metodo que retorna verdadero o falso, dependiendo si el simbolo con mayor
    pioridad es la concatenacion.
    */
    public boolean tieneConcatenacion(String hilera){
        int n= hilera.length();
        boolean condicion= false;
        char aux;
        System.out.println(hilera);
        for(int i=0;i<n;i++){
            aux=hilera.charAt(i);
            switch(aux){
                case'(':
                    boolean bandera=true;
                    while(bandera){
                        if(aux!=')'){
                            i++;
                            aux=hilera.charAt(i);
                            
                        }else{
                            bandera=false;
                        }
                        
                    }
                    break;
                case'.':
                    i++;
                    aux=hilera.charAt(i);
                    if(aux=='('){
                        return true;
                    }
                    break;
                case'+':
                    if((hilera.length()-i)!=1){
                        i++;
                        aux=hilera.charAt(i);
                        if(aux=='.'){
                            return false;
                        }
                    }
                    break;
                case'*':
                    if((hilera.length()-i)!=1){
                        i++;
                        aux=hilera.charAt(i);
                        if(aux=='.'){
                            return false;
                        }
                    }
                    break;
            
            }
        }
        return condicion;
    }
    /*
    Metodo encargado de devolver la posicion donde se encuentra la concatenacion con 
    mayor prioridad
    */
    public int posicionConcatenacion(String hilera){
        int pos=0;
        int n=hilera.length(),i;
        char aux;
        for(i=0;i<n;i++){
            aux=hilera.charAt(i);
            if(aux==')'){
                i+=1;
                while(aux!='('){
                    aux=hilera.charAt(i);
                    i++;
                }
                i--;
            }
            if(aux=='('){
                i+=1;
                aux=hilera.charAt(i);
                if(aux=='.'){
                    pos=i;
                    return i;
                }
            }
            if(aux=='.'){
                i++;
                aux=hilera.charAt(i);
                if(aux=='*'||aux=='+'){
                    i--;
                    pos=i;
                    return i;
                }
            }
        }
        return pos;
    }
    
    public NodoDoble getRaiz() {
        return raiz;
    }
    public void setRaiz(NodoDoble raiz) {
        this.raiz = raiz;
    }
    
}