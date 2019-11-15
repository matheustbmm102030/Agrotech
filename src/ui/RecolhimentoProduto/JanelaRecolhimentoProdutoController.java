/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.RecolhimentoProduto;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author User
 */
public class JanelaRecolhimentoProdutoController implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXComboBox<?> cbPrestador;
    @FXML
    private JFXComboBox<?> cbProduto;
    @FXML
    private JFXTextField tfDtEntrega;
    @FXML
    private JFXTextField tfQantidade;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colPrestador;
    @FXML
    private TableColumn<?, ?> corProd;
    @FXML
    private TableColumn<?, ?> colQte;
    @FXML
    private TableColumn<?, ?> colDtEntrega;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bExcluir(ActionEvent event) {
    }

    @FXML
    private void bsalvar(ActionEvent event) {
    }

    @FXML
    private void bEditar(ActionEvent event) {
    }
    
}
