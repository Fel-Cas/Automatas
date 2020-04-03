/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaautomatas;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author jhon.quitian
 */
public class IngresoHileraController {
    //Varibles Utilizadas en  la entrada de la Expresion Regular
    @FXML
    private Label escribirHilera;
    @FXML
    private Button aceptar,cierraParentesis,abreParentesis,hileraNula,hileraNoNula,concatenar,union;
    static int j=0, contadorParentesis= 0,contadorAbreParentesis=0;
    static String cadena= "";
    @FXML
    private Button button;
    private Stage stage=new Stage();
    @FXML
    private TextField hilera;
    @FXML
    private javafx.scene.control.Button soloNumeros,soloLetras, finSecuencia;
    //Variables Utilizadas para mostrar el Automata y verificar hileras.
    @FXML
    private TextField hileraAReconocer;
    @FXML
    private Button mostrarAutomata,reconocerHilera;
    static AutomataFD automata;
    static ArbolSintactico arbol;
    //Metodos de entrada de la Expresion Regular
    @FXML
    private void IngresarHileraLetras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("IngresoHilera.fxml"));
        j=1;
        Scene scene = new Scene(root);
        System.out.println(j);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Solo Letras");
        stage.setResizable(false);
        Stage stage =(Stage) soloNumeros.getScene().getWindow();
        stage.hide();
    }
    @FXML
    private void IngresarHileraNumeros(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("IngresoHilera.fxml"));
        j=2;
        Scene scene = new Scene(root);
        System.out.println(j);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Solo Números");
        stage.setResizable(false);
        Stage stage =(Stage) soloLetras.getScene().getWindow();
        stage.hide();
    }
    public void aceptarAction (ActionEvent event){
        cadena+=hilera.getText();
        escribirHilera.setText(cadena);
        hilera.setText("");
        hilera.setVisible(false);
        aceptar.setDisable(true);
        hileraNula.setDisable(false);
        hileraNoNula.setDisable(false);
        concatenar.setDisable(false);
        union.setDisable(false);
        abreParentesis.setDisable(true);
        finSecuencia.setDisable(true);
        if(contadorParentesis>0){
           cierraParentesis.setDisable(false);
        }
    }
    public void  hilera (javafx.scene.input.KeyEvent keyEvent) {
        char car = keyEvent.getCharacter().charAt(0);
        
        if(j==1){
            if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') ) {
                keyEvent.consume();
                }
               
                if(hilera.getText().length()>=1){ 
                    keyEvent.consume();
                } 
                    
        }else{
                if ((car < '0' || car > '9') ) {
                    keyEvent.consume();
                }
                if(hilera.getText().length()>=1){ 
                    keyEvent.consume();                     
                }              
            }
           
        if(hilera.getText().length()==0){                          
            abreParentesis.setDisable(false); 
        }
        if(hilera.getText().length()>=0){                          
            abreParentesis.setDisable(true);
            System.out.println("pee");
        }
        if(hilera.getText().length()==0){                          
            abreParentesis.setDisable(false); 
        }
            
         
    }
    public void abreParentesisAction(ActionEvent event){
        cadena+= "(";
        escribirHilera.setText(cadena);
        cierraParentesis.setDisable(true);
        hileraNula.setDisable(true);
        hileraNoNula.setDisable(true);
        concatenar.setDisable(true);
        union.setDisable(true);
        finSecuencia.setDisable(true);
        hilera.setVisible(true);
        aceptar.setDisable(false);
        contadorParentesis+= 1;        
        aceptar.setDisable(false);
    }
    public void cierraParentesisAction(ActionEvent event){
        cadena+=")";
        escribirHilera.setText(cadena);                
        contadorParentesis-= 1;
        hileraNula.setDisable(false);
        concatenar.setDisable(false);
        hileraNoNula.setDisable(false);
        union.setDisable(false);
        finSecuencia.setDisable(true);
        abreParentesis.setDisable(true);
        cierraParentesis.setDisable(true);
    }
    public void hileraNulaAction(ActionEvent event){
        cadena+="*";
        escribirHilera.setText(cadena);
        concatenar.setDisable(false);
        union.setDisable(false);
        finSecuencia.setDisable(true);
        abreParentesis.setDisable(true);
        hileraNula.setDisable(true);
        hileraNoNula.setDisable(true);
    }
    public void hileraNoNulaAction(ActionEvent event){
        cadena+="+";
        escribirHilera.setText(cadena);
        concatenar.setDisable(false);
        union.setDisable(false);
        abreParentesis.setDisable(true);
        hileraNula.setDisable(true);
        hileraNoNula.setDisable(true);
        finSecuencia.setDisable(true);
    }
    public void concatenarAction(ActionEvent event){
        cadena+=".";
        escribirHilera.setText(cadena);
        hilera.setVisible(true);
        aceptar.setDisable(false);
        abreParentesis.setDisable(false);
        if(contadorParentesis==0){
            finSecuencia.setDisable(false);
        }else{
            finSecuencia.setDisable(true);
        }
        cierraParentesis.setDisable(true);
        hileraNula.setDisable(true);
        hileraNoNula.setDisable(true);
        concatenar.setDisable(true);
        union.setDisable(true);        
    }
    public void unionAction(ActionEvent event){
        cadena+="|";
        escribirHilera.setText(cadena);        
        hilera.setVisible(true);
        aceptar.setDisable(false);
        abreParentesis.setDisable(false);
        cierraParentesis.setDisable(true);
        hileraNula.setDisable(true);
        hileraNoNula.setDisable(true);
        concatenar.setDisable(true);
        union.setDisable(true);
        finSecuencia.setDisable(true);
    }
    public void finSecuenciaAction(ActionEvent event) throws IOException{
        cadena+="#";
        escribirHilera.setText(cadena);
        Parent root = FXMLLoader.load(getClass().getResource("Automata.fxml"));
        Scene scene = new Scene(root);
        System.out.println(j);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Automata");
        stage.setResizable(false);
         Stage stage =(Stage) finSecuencia.getScene().getWindow();
         stage.hide();
    }    
    //Metodos Utilizados para el reconocimiento de la hilera
    public void reconocerHileraAction(ActionEvent event){
        System.out.println(hileraAReconocer.getText());
        hileraAReconocer.setText(" ");
    }
    public void mostrarAutomataAction(ActionEvent event){
        reconocerHilera.setDisable(false);
    }
    public void  hileraReconocer (javafx.scene.input.KeyEvent keyEvent) {
        char car = keyEvent.getCharacter().charAt(0);
        
        if(j==1){
            if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') ) {
                keyEvent.consume();
                }   
        }else{
                if ((car < '0' || car > '9') ) {
                    keyEvent.consume();
                }             
            }
                   
    }
}
