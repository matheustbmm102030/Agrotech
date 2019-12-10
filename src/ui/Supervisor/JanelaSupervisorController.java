package ui.Supervisor;

import dados.entidades.Supervisor;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import serviços.SupervisorServico;

public class JanelaSupervisorController implements Initializable {

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
    private TableView<Supervisor> tabela;
    @FXML
    private JFXTextField tfEnd;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colNome;
    @FXML
    private TableColumn colEnd;
    @FXML
    private TableColumn colTel;
    @FXML
    private TableColumn colRg;
    @FXML
    private TableColumn colCPF;
    @FXML
    private TableColumn colDataN;

    /**
     * Initializes the controller class.
     */
    private SupervisorServico servico = new SupervisorServico();

    private ObservableList<Supervisor> dados = 
            FXCollections.observableArrayList();
    
    private Supervisor selecionado;
    @FXML
    private JFXTextField tfPesquisa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        //Configure a tabela
        configurarTabela();
        
        //Carregue a lista de atores na tabela
        listarSupervisoresTabela();
    }    

    @FXML
    private void bSalvar(ActionEvent event) {
          //Verificar se está atualizando ou inserindo
        if(tfID.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário
            //e cria um objeto funcionario
            Supervisor f = new Supervisor(tfNome.getText(),tfEnd.getText(),
                tfTel.getText(),tfRg.getText(),tfCpf.getText(),tfDataN.getText());

            //Mandar o ator para a camada de servico
            servico.salvar(f);
            
            //Exibindo mensagem
            mensagemSucesso("Funcionario salvo com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            listarSupervisoresTabela();
            
        }else{ //atualizando o ator
           
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                //Pegar os novos dados do formulário e
                //atualizar o meu ator
                selecionado.setNome(tfNome.getText());
                selecionado.setEndereco(tfEnd.getText());
                selecionado.setTelefone(tfTel.getText());
                selecionado.setRg(tfRg.getText());
                selecionado.setCpf(tfCpf.getText());
                selecionado.setDataNasc(tfDataN.getText());
                
                //Mandando pra camada de serviço salvar as alterações
                servico.editar(selecionado);
                
                //Exibindo mensagem
                mensagemSucesso("Supervisor atualizado com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 listarSupervisoresTabela();
            }
            
        }

        
        //Limpando o form
        tfID.setText("");
        tfEnd.setText("");
        tfTel.setText("");
        tfRg.setText("");
        tfCpf.setText("");
        tfDataN.setText("");
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
                new PropertyValueFactory("endereco"));
        colTel.setCellValueFactory(
                new PropertyValueFactory("telefone"));
        colRg.setCellValueFactory(
                new PropertyValueFactory("rg"));
        colCPF.setCellValueFactory(
                new PropertyValueFactory("cpf"));
        colDataN.setCellValueFactory(
                new PropertyValueFactory("dataNasc"));
        
    }//configurarTabela
    
    /**
     * Responsável por carregar a lista de atores 
     * na tabela
     */
    private void listarSupervisoresTabela(){
        //Limpando quaisquer dados anteriores
        dados.clear();
        
        //Solicitando a camada de servico a lista de atores
        List<Supervisor> supervisores = servico.listar();
        
        //Transformar a lista de funcionario no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(supervisores);
        
        //Jogando os dados na tabela
        tabela.setItems(dados);
        
    }
    
    public void mensagemErro(String m) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

    @FXML
    private void bEditar(ActionEvent event) {
        
        //Pegar o Supervisor que foi selecionado na tabela
        selecionado = tabela.getSelectionModel()
                .getSelectedItem();

        //Se tem algum funcionario selecionado
        if (selecionado != null) { //tem funcionario selecionado
            //Pegar os dados do funcionario e jogar nos campos do
            //formulario
            tfID.setText(String.valueOf( selecionado.getId() ) );
            tfNome.setText( selecionado.getNome() ); 
            tfEnd.setText( selecionado.getEndereco() );
            tfTel.setText( selecionado.getTelefone() );
            tfRg.setText( selecionado.getRg() );
            tfCpf.setText( selecionado.getCpf() );
            tfDataN.setText( selecionado.getDataNasc() );
                           
        }else{ //não tem ator selecionado na tabela
            mensagemErro("Selecione um Supervisor.");
        }
    }
    
        /**
     * Mostra uma caixa com uma mensagem de confirmação
     * onde a pessoa vai poder responder se deseja realizar
     * uma ação
     */
    private Optional<ButtonType> mensagemDeConfirmacao(String mensagem, String titulo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait();
    }

    @FXML
    private void bExcluir(ActionEvent event) {
        
        //Pegar o ator que foi selecionado na tabela
        selecionado = tabela.getSelectionModel()
                .getSelectedItem();
        
        //Verifico se tem ator selecionado
        if(selecionado != null){ //existe ator selecionado
            
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                mensagemDeConfirmacao("Deseja mesmo excluir?",
                      "EXCLUIR");
            
            //Verificando se apertou o OK
            if(btn.get() == ButtonType.OK){
                
                //Manda para a camada de serviço excluir
                servico.excluir(selecionado);
                
                //mostrar mensagem de sucesso
                mensagemSucesso("Supervisor excluído com sucesso");
                
                //Atualizar a tabela
                listarSupervisoresTabela();              
                
            }
            
            
            
        }else{
            mensagemErro("Selecione um Supervisor.");
        }
        
    }

    @FXML
    private void btnPesquisa(ActionEvent event) {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Pegando o valor  para pesquisar
        String nome = tfPesquisa.getText();
        
        //Solicitando a camada de servico a lista dos objetos
        List<Supervisor> entregas = servico.buscarPeloNome(nome);

        //Transformar a lista de objetos no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(entregas);

        //Jogando os dados na tabela
        tabela.setItems(dados);
    }
    
    
}