/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaautomatas;

import java.util.Stack;

/**
 *
 * @author andres
 */
public class AutomataFD {
    private String lenguaje="";
    public AutomataFD() {
    }
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
    public void enumerar(NodoDoble r){
        Stack pila=new Stack();
        nodosHoja(r,pila);
        int i=pila.size();
        NodoDoble p;
        while(!pila.isEmpty()){
            p=(NodoDoble)pila.pop();
            p.setPosicion(Integer.toString(i));
            p.setPrimeraPos(p.getPosicion());
            p.setUltimaPos(p.getPosicion());
            i--;
            System.out.print(p.getDato());
        }
    }
    public void nodosHoja(NodoDoble r,Stack pila){
       if(r!=null){
            nodosHoja((NodoDoble)r.getLi(),pila);
            if(r.getLd()==null && r.getLi()==null){
                pila.push(r);
            }
            nodosHoja((NodoDoble)r.getLd(),pila);
        }
        
    }
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
    public void follows(NodoDoble r){
        Stack pilaHojas=new Stack();
        Stack pila=new Stack();
        nodosHoja(r,pilaHojas);
        nodos(r,pila);
        Object[][] siguientes=new Object[pilaHojas.size()][3];
        int i=pilaHojas.size()-1;
        String  aux;
        NodoDoble p,q;
        int n=pilaHojas.size();
        System.out.println(pilaHojas.size());
        System.out.println(pila.size());
        for(int k=0;k<pilaHojas.size();k++){
            siguientes[k][2]="";
        }
        while(!pilaHojas.isEmpty()){
            p=(NodoDoble)pilaHojas.pop();
            siguientes[i][0]=p.getPosicion();
            siguientes[i][1]=p.getDato();
            i--;
        }
        while(!pila.isEmpty()){
            p=(NodoDoble)pila.pop();
            System.out.println(p.getDato());
            if(p.getDato()=='.'){
                for(int j=0;j<n-1;j++){
                 aux=(String )siguientes[j][0];
                 if(p.getLd()!=null&&p.getLi()!=null){
                     q=p.getLi();
                     if(q.getUltimaPos().indexOf(aux)!=-1);
                     
                     siguientes[j][2]+=p.getLd().getPrimeraPos();
                 }
                }
            }else{
                for(int j=0;j<n-1;j++){
                    aux=(String)siguientes[j][0];
                    if(p.getUltimaPos().indexOf(aux)!=-1){
                        System.out.println(p.getLi().getUltimaPos().indexOf(aux));
                        siguientes[j][2]+=p.getPrimeraPos();
                    }
                }
                
            }
            
        }
        
        for(int j=0;j<n;j++){            
            for(int k=0;k<3;k++){
                System.out.print(siguientes[j][k]);
            }
            System.out.println(j);
        }
        System.out.println("");
    }
    public void nodos(NodoDoble r,Stack pila){
        if(r!=null){
            nodos(r.getLi(),pila);
            if(r.getDato()=='.'||r.getDato()=='*'){
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
}
