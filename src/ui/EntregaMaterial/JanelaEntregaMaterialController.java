/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.EntregaMaterial;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class JanelaEntregaMaterialController implements Initializable {

    @FXML
    private ComboBox<?> cbPrestadora;
    @FXML
    private ComboBox<?> cbinsumo1;
    @FXML
    private ComboBox<?> cbinsumo2;
    @FXML
    private ComboBox<?> cbinsumo3;
    @FXML
    private ComboBox<?> cbinsumo4;
    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfDataEntrega;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bSalvar(ActionEvent event) {
    }

    @FXML
    private void bEditar(ActionEvent event) {
    }

    @FXML
    private void bExcluir(ActionEvent event) {
    }
    
}
