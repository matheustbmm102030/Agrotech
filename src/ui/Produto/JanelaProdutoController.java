/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Produto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author medei
 */
public class JanelaProdutoController implements Initializable {

    @FXML
    private JFXButton bSalvar;
    @FXML
    private JFXButton bEditar;
    @FXML
    private JFXButton bExcluir;
    @FXML
    private JFXTextField tfId;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfQuanti;
    @FXML
    private JFXTextField tfPreco;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colNome;
    @FXML
    private TableColumn<?, ?> colQuant;
    @FXML
    private TableColumn<?, ?> colPreco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
