/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaautomatas;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author jhon.quitian
 */
public class IngresoHileraController {
    @FXML
    private TextField hilera;
    public void  hilera (javafx.scene.input.KeyEvent keyEvent) {
        char car = keyEvent.getCharacter().charAt(0);
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                
                 ) {
            keyEvent.consume();
        }
        
        
       
        if(hilera.getText().length()>=1){ 
            keyEvent.consume(); 
        } 
        
    }
}
