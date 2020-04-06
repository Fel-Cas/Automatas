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
public class AutomataFD {
    private String lenguaje="";
    private Object[][]siguientes;
    private Object [][] transicion;
    private ArrayList <String> letras = new ArrayList();
    private Object [][] tabla;
    public AutomataFD() {
        
    }
    /****
       Este método es el encargado de encontrar las letras o números diferentes 
        que se encontraron en la ER.
    ****/
    public void encontrarLenguaje(NodoDoble r){
        Stack pila=new Stack();
        NodoDoble p;
        nodosHoja(r,pila);
        while(!pila.isEmpty()){
            p=(NodoDoble)pila.pop();
            if(lenguaje.indexOf(p.getDato())==-1 && p.getDato()!='#'){
                lenguaje+=p.getDato();
            }
        }
    }
    /****
        Método encargado de poner verdadero el item de anulable a cada 
        nodo del arbol, si este es correcto.
    ****/
    public void anulable(NodoDoble r){
        if(r!=null){
            anulable((NodoDoble)r.getLi());           
            anulable((NodoDoble)r.getLd());
            if(r.getDato()=='.'||r.getDato()=='|'||r.getDato()=='*'){
                switch(r.getDato()){
                    case'.':
                        if(r.getLd().getAnulable()==true && r.getLi().getAnulable()==true){
                            r.setAnulable(true);
                        }
                        break;
                    case '|':
                        if(r.getLd().getAnulable()==true || r.getLi().getAnulable()==true){
                            r.setAnulable(true);
                        }
                        break;
                    case'*':
                        r.setAnulable(true);
                        break;
                }
            }
            System.out.print(r.getDato());
            
        }
    }
    /****
        Metodo encargado de enumerar de 1 hasta n las hojas del árbol.
    ****/
    public void enumerar(NodoDoble r){
        Stack pila=new Stack();
        String letra;
        nodosHoja(r,pila);
        letras.add("A");letras.add("B");letras.add("C");letras.add("D");letras.add("E");letras.add("F");letras.add("G");letras.add("H");letras.add("I");letras.add("J");
        letras.add("K");letras.add("L");letras.add("M");letras.add("N");letras.add("O");letras.add("P");letras.add("Q");letras.add("R");letras.add("S");letras.add("T");
        letras.add("U");letras.add("V");letras.add("W");letras.add("X");letras.add("Y");letras.add("Z");
        
        int i=pila.size(), j;
        NodoDoble p;
        while(!pila.empty() && i>9){
            j=i-10;
            p=(NodoDoble) pila.pop();
            letra = letras.get(j);
            p.setPosicion(letra);
            p.setPrimeraPos(p.getPosicion());
            p.setUltimaPos(p.getPosicion());
            i--;
            System.out.print(p.getDato());            
        }
        while(!pila.isEmpty()){
            p=(NodoDoble)pila.pop();
            p.setPosicion(Integer.toString(i));
            p.setPrimeraPos(p.getPosicion());
            p.setUltimaPos(p.getPosicion());
            i--;
            System.out.print(p.getDato());
        }
    }
    /****
        Método encargado de encontrar todos los nodos hoja del árbol y ponerlos en una pila.
    ****/
    public void nodosHoja(NodoDoble r,Stack pila){
       if(r!=null){
            nodosHoja((NodoDoble)r.getLi(),pila);
            if(r.getLd()==null && r.getLi()==null){
                pila.push(r);
            }
            nodosHoja((NodoDoble)r.getLd(),pila);
        }
        
    }
    /****
        Método encargado de encontrar Primera y Ultima Posicion de cada nodo del árbol.
    ****/
    public void calcularPosiciones(NodoDoble r){
        if(r!=null){            
            calcularPosiciones(r.getLi());
            calcularPosiciones(r.getLd());
            switch(r.getDato()){
                case'.':
                    if(r.getLi().getAnulable()==true){
                        r.setPrimeraPos(r.getLi().getPrimeraPos()+r.getLd().getUltimaPos());
                    }else{
                        r.setPrimeraPos(r.getLi().getPrimeraPos());
                    }
                    if(r.getLd().getAnulable()==true){
                        r.setUltimaPos(r.getLi().getUltimaPos()+r.getLd().getPrimeraPos());
                    }else{
                        r.setUltimaPos(r.getLd().getUltimaPos());
                    }
                    break;
                case'|':
                    r.setPrimeraPos(r.getLi().getPrimeraPos()+r.getLd().getPrimeraPos());
                    r.setUltimaPos(r.getLd().getUltimaPos()+r.getLi().getUltimaPos());
                    break;
                case'*':
                    r.setPrimeraPos(r.getLi().getPrimeraPos());
                    r.setUltimaPos(r.getLi().getUltimaPos());
                    break;
                case'+':
                    r.setPrimeraPos(r.getLi().getPrimeraPos());
                    r.setUltimaPos(r.getLi().getUltimaPos());
                    break;
            }
            System.out.print(r.getDato());
        }
    }
    /****
        Método encargado crear la tabla de siguientes para crear el automata.
    ****/
    public void follows(NodoDoble r){
         Stack pilaHojas=new Stack();
        Stack pila=new Stack();
        nodosHoja(r,pilaHojas);
        nodos(r,pila);
        siguientes =new Object[pilaHojas.size()][3];
        int i=pilaHojas.size()-1;
        String  aux;
        NodoDoble p,q;
        int n=pilaHojas.size();
        //System.out.println(pilaHojas.size());
        //System.out.println(pila.size());
        for(int k=0;k<pilaHojas.size();k++){
            siguientes[k][2]="";
        }
        while(!pilaHojas.empty()){
            p=(NodoDoble)pilaHojas.pop();
            siguientes[i][0]=p.getPosicion();
            siguientes[i][1]=p.getDato();
            i--;
        }
        while(!pila.empty()){
            p=(NodoDoble)pila.pop();
            //System.out.println(p.getDato());
            switch (p.getDato()) {
                 case '.':
                     for(int j=0;j<n-1;j++){
                         aux=(String )siguientes[j][0];
                         if(p.getLd()!=null&&p.getLi()!=null){
                             q=p.getLi();
                             if(q.getUltimaPos().contains(aux)){
                                 siguientes[j][2]+=p.getLd().getPrimeraPos();
                             }
                         }
                     }    break;
                 case '*':
                     for(int j=0;j<n-1;j++){
                         aux=(String)siguientes[j][0];
                         if(p.getUltimaPos().contains(aux)){
                             //System.out.println(p.getLi().getUltimaPos().indexOf(aux));
                             siguientes[j][2]+=p.getPrimeraPos();
                         }
                     }    break;
                 case '+':
                     for(int j=0;j<n-1;j++){
                         aux=(String)siguientes[j][0];
                         if(p.getUltimaPos().contains(aux)){
                             //System.out.println(p.getLi().getUltimaPos().indexOf(aux));
                             siguientes[j][2]+=p.getPrimeraPos();
                         }
                     }    break; 
                 default:
                     break;
             }          
        }
       
        for(int j=0;j<n;j++){            
            for(int k=0;k<3;k++){
                System.out.print(siguientes[j][k]+" ");
            }
            System.out.println("");
        }
    }
    /****
        Método encargado de construir los el AutomataFD a partir 
        de la tabla de siguientes.
    ****/
    public void construirAutomata(NodoDoble r){
        ArrayList<String> estadosD = new ArrayList();
        int fila= 2;
        NodoDoble q;
        String estadoTransicional = "", estado, estadoDeAceptacion, estados, estadoDeAceptacionLetra, verificacion;
        Stack pilaHojas=new Stack();
        nodosHoja(r,pilaHojas);
        pilaHojas.pop(); //para quitar el #
        estadosD.add(r.getPrimeraPos());
        int k=1, h=0, estadoAceptacion, l, numeroEstado;
        ArrayList <Object> simbolos = new ArrayList();
        for(int i=0;i<=simbolos.size();i++){
            if(!pilaHojas.empty()){
                q =(NodoDoble) pilaHojas.pop();
                char aux = q.getDato();
                if(!simbolos.contains(aux)){
                    simbolos.add(aux);
                }
            }            
        }
        transicion = new Object [200][simbolos.size()+2];
        transicion [0][0] = "estado";
        transicion [1][0] = estadosD.get(0);
        while(h<simbolos.size()){ //se le asigna los símbolos utilizados a la tabla de transiciones
            char aux =(char) simbolos.get(h);
            transicion[0][k] = aux;
            h++; k++;                           
        }       
        for(int i=0;i<transicion.length;i++){
            if(transicion[i][transicion[0].length-1]==null){
                transicion[i][transicion[0].length-1] = "";
            }
        }
        for(int i=1;i<transicion[0].length;i++){
            System.out.println(transicion[0][i]);
        }
        
        
        for(int i=0;i<estadosD.size();i++){ // Se crea la tabla de transiciones con cada uno de los estados
            String transicionEstados = estadosD.get(i);
            System.out.println(estadosD.get(i));
            k=0;
            for(int j=1;j<transicion[0].length;j++){
                k=0;                
                estadoTransicional="";
                while(k<transicionEstados.length()){
                    if(letras.contains(transicionEstados.substring(k, k+1))){
                        l = letras.indexOf(transicionEstados.substring(k, k+1));
                        estados= (String) siguientes[l+9][2];
                        numeroEstado=l+10;
                    }else{
                        numeroEstado=Integer.parseInt(transicionEstados.substring(k,k+1));
                        estados= (String) siguientes[numeroEstado-1][2];
                    }                    
                    if(!estadoTransicional.equals(estados) && transicion[0][j]==siguientes[numeroEstado-1][1] && !estadoTransicional.contains(estados)){
                        for(h=0;h<estados.length();h++){
                            verificacion = estados.substring(h,h+1);
                            if(!estadoTransicional.contains(verificacion)){
                                estadoTransicional += verificacion;
                            }
                        }
                        //estadoTransicional += estados;
                    }                    
                    k++;
                }
                if(!estadosD.contains(estadoTransicional) && !"".equals(estadoTransicional)){                    
                    estadosD.add(estadoTransicional);
                    transicion[fila][0]=estadoTransicional;                       
                    fila++;
                }
                if(!"".equals(estadoTransicional)){
                    transicion[i+1][j]=estadoTransicional;      
                }
            }
        }     
        estadoAceptacion = siguientes.length;
        estadoDeAceptacion = String.valueOf(estadoAceptacion);
        estadoDeAceptacionLetra = (String) siguientes[siguientes.length-1][0];
        h=0;
        for(int i=1;i<transicion.length;i++){ //Se colocan los 1 y 0 a los estados de aceptación
            if(h<estadosD.size()){
                estado =(String) estadosD.get(h);
                if(estado.contains(estadoDeAceptacion) || estado.contains(estadoDeAceptacionLetra)){
                    transicion[i][transicion[0].length-1]= 1;
                }else{
                    transicion[i][transicion[0].length-1] =0;
                }
                h++;
            }                
        }        
        for(int i=0;i<=estadosD.size();i++){
            for(int j=0;j<transicion[0].length;j++){
                if(transicion[i][j]==null){
                    transicion[i][j]="ERROR";
                }
            }
        }
        tabla=new Object[estadosD.size()+1][transicion[0].length];
        for(int i=0; i<tabla.length;i++){
            for(int j=0;j<tabla[0].length;j++){
                tabla[i][j]=transicion[i][j];                
            }
        }
        System.out.println("");
        for(int i=0;i<estadosD.size();i++){
            System.out.println(estadosD.get(i)+" = "+"estado "+(i+1));  
        }        
    }
    
    /**
     * Método que recibe una hilera de caracteres y verifica si es correcta.
     **/
    public String reconocerHilera(String hilera){
        char recorreTransicion=' '/*, simbolo=""*/;
        char simbolo=' ';
        String respuesta;
        int letra=0, i=1, j=1, aceptacion=1, contadorLetras=0;
        String estadoColumna="", estadoFila="";
        if(hilera.length()>0){
            while(letra<hilera.length()){
                simbolo=' ';
                estadoColumna=""; estadoFila="";
                recorreTransicion=hilera.charAt(letra);
                while(j<tabla[0].length-1 && recorreTransicion!=simbolo){
                    simbolo=(char) tabla[0][j];
                    if(recorreTransicion==simbolo){
                        contadorLetras++;
                        estadoColumna =(String) tabla[aceptacion][j];
                        if(estadoColumna.equals("ERROR")){
                            return "Hilera "+hilera+" "+" no reconocida";
                        }
                    }
                    j++;
                }
                //System.out.print(i+" "+estadoFila+" "+estadoColumna);
                while(i<tabla.length && !estadoFila.equals(estadoColumna)){
                    estadoFila = (String) tabla[i][0];
                    if(estadoFila.equals(estadoColumna)){
                        aceptacion=i;
                    }
                    i++;
                }
                letra++;
                j=1; i=1;
            }
            if(contadorLetras==hilera.length()){
                int aux =(int) tabla[aceptacion][tabla[0].length-1];
                if(aux==1){
                    respuesta= "Hilera "+hilera+" "+" reconocida";
                    return respuesta;
                }else{
                    respuesta = "Hilera "+hilera+" "+" no reconocida";
                    return respuesta;
                }                
            }else{
                respuesta = "Hilera "+hilera+" "+" no reconocida";
                return respuesta;
            }
        }else{
            int aux =(int) tabla[aceptacion][tabla[0].length-1];
            if(aux==1){
                respuesta = "Hilera "+hilera+" "+" reconocida";
                return respuesta;
            }else{
                respuesta = "Hilera "+hilera+" "+" no reconocida";
                return respuesta;
            }                
        }            
    }
    /****
        Método encargado de encontrar todos los nodos que contengan como dato a 
    *, . y +, los cuales son guardados en una pila.
    ****/
    public void nodos(NodoDoble r,Stack pila){
        if(r!=null){
            nodos(r.getLi(),pila);
            if(r.getDato()=='.'||r.getDato()=='*' || r.getDato()=='+'){
                pila.push(r);
            }
            nodos(r.getLd(),pila);
        }
    }
    
    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Object[][] getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(Object[][] siguientes) {
        this.siguientes = siguientes;
    }

    public Object[][] getTransicion() {
        return transicion;
    }

    public void setTransicion(Object[][] transicion) {
        this.transicion = transicion;
    }

    public ArrayList <String> getLetras() {
        return letras;
    }

    public void setLetras(ArrayList <String> letras) {
        this.letras = letras;
    }

    public Object[][] getTabla() {
        return tabla;
    }

    public void setTabla(Object[][] tabla) {
        this.tabla = tabla;
    }
    
    
}
