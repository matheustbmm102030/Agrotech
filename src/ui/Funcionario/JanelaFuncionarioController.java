package ui.Funcionario;

import dados.entidades.Funcionario;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import serviços.FuncionarioServico;

public class JanelaFuncionarioController implements Initializable {

    @FXML
    private JFXTextField tfTel;
    @FXML
    private JFXTextField tfRg;
    @FXML
    private JFXTextField tfCpf;
    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfDataN;
    @FXML
    private TableView<?> tabela;

    /**
     * Initializes the controller class.
     */
    private FuncionarioServico servico = new FuncionarioServico();
    

    @FXML
    private JFXTextField tfEnd;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colNome;
    @FXML
    private TableColumn<?, ?> colEnd;
    @FXML
    private TableColumn<?, ?> colTel;
    @FXML
    private TableColumn<?, ?> colRg;
    @FXML
    private TableColumn<?, ?> colCPF;
    @FXML
    private TableColumn<?, ?> colDataN;
    
    private ObservableList<Funcionario> dados = 
            FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        //Configure a tabela
        configurarTabela();
        
        //Carregue a lista de atores na tabela
        listarFuncionariosTabela();
    }    

    @FXML
    private void bSalvar(ActionEvent event) {
                
        //Pega os dados do fomulário
        //e cria um objeto ator
        Funcionario f = new Funcionario(tfNome.getText(),tfEnd.getText(),
                tfTel.getText(),tfRg.getText(),tfCpf.getText(),tfDataN.getText());
        
        //Mandar o ator para a camada de servico
        servico.salvar(f);
        //Exibindo mensagem
        mensagemSucesso("Funcionario salvo!");
        //Limpando o form
        tfNome.setText("");
    }
        public void mensagemSucesso(String m){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!"); 
        alerta.setHeaderText(null); 
        alerta.setContentText(m);
        alerta.showAndWait(); 
    }
        /**
     * Fazendo configuração das colunas da
     * tabeça
     */
    private void configurarTabela(){
        
        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        colID.setCellValueFactory(
                new PropertyValueFactory("id"));
        colNome.setCellValueFactory(
                new PropertyValueFactory("nome"));
        colEnd.setCellValueFactory(
                new PropertyValueFactory("Endereço"));
        colTel.setCellValueFactory(
                new PropertyValueFactory("Telefone"));
        colRg.setCellValueFactory(
                new PropertyValueFactory("RG"));
        colCPF.setCellValueFactory(
                new PropertyValueFactory("CPF"));
        colDataN.setCellValueFactory(
                new PropertyValueFactory("Data Nascimento"));
        
    }//configurarTabela
    
    /**
     * Responsável por carregar a lista de atores 
     * na tabela
     */
    private void listarFuncionariosTabela(){
        //Limpando quaisquer dados anteriores
        dados.clear();
        
        //Solicitando a camada de servico a lista de atores
        List<Funcionario> Funcionarios = servico.listar();
        
        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(Funcionarios);
        
        //Jogando os dados na tabela
        tabela.setItems(dados);
        
    }
    
}
