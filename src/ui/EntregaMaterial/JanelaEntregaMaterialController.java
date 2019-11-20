/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.EntregaMaterial;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class JanelaEntregaMaterialController implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private TableView<?> tabela;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colPrestador;
    @FXML
    private TableColumn<?, ?> colInsumo1;
    @FXML
    private TableColumn<?, ?> colInsumo2;
    @FXML
    private TableColumn<?, ?> colInsumo3;
    @FXML
    private TableColumn<?, ?> colInsumo4;
    @FXML
    private TableColumn<?, ?> colDtEntrega;
    @FXML
    private JFXTextField tfDataEntrega;
    @FXML
    private JFXComboBox<?> cbPrestador;
    @FXML
    private JFXComboBox<?> cbInsumo4;
    @FXML
    private JFXComboBox<?> cbInsumo1;
    @FXML
    private JFXComboBox<?> cbInsumo2;
    @FXML
    private JFXComboBox<?> cbInsumo3;
    @FXML
    private JFXTextField tfPesquisar;

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

    @FXML
    private void pesquisar(ActionEvent event) {
    }
    
}
