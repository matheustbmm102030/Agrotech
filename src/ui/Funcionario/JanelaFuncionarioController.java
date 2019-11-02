package ui.Funcionario;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class JanelaFuncionarioController implements Initializable {

    @FXML
    private JFXTextField Id;
    @FXML
    private JFXTextField Nome;
    @FXML
    private JFXTextField tfTel;
    @FXML
    private JFXTextField tfRg;
    @FXML
    private JFXTextField tfCpf;
    @FXML
    private JFXTextField dataNascimento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
