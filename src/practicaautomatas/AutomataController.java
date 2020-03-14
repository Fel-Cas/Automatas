/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaautomatas;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 *
 * @author jhon.quitian
 */
public class AutomataController {
    public void reconocerAction(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);        
        alert.showAndWait();
    }
}
