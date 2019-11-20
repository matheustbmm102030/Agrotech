package ui.Equipe;

import com.jfoenix.controls.JFXComboBox;
import dados.entidades.Equipe;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Supervisor;
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
import serviços.EquipeServico;
import serviços.SupervisorServico;


public class JanelaEquipeController implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfTel;
    @FXML
    private JFXTextField tfRg;
    @FXML
    private JFXTextField tfCpf;
    @FXML
    private JFXTextField tfDataN;
    @FXML
    private JFXTextField tfEnd;
    @FXML
    private TableView<Equipe> tabela;
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
    @FXML
    private TableColumn<?, ?> colSupervisor;
    @FXML
    private JFXComboBox<Supervisor> cbSupervisor;
    @FXML
    private JFXTextField tfPesquisar;
    
    //Atributo que representa os dados para tabela
    private ObservableList<Equipe> dados
            = FXCollections.observableArrayList();

    //Atributos para representar os servicos
    private EquipeServico equipeServico = new EquipeServico();
    private SupervisorServico supervisorServico = new SupervisorServico();

    //Atributo para representar o filme selecionado
    //na tabela para editar e excluir
    private Equipe selecionado;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configure a tabela
        configurarTabela();

        //Carregue a lista de atores na tabela
        listarEquipesTabela();

        //Carregar combo de genero
        listarSupervisores();
    }    
    
    @FXML
    private void bSalvar(ActionEvent event) {
        //Verificar se está atualizando ou inserindo
        if(tfID.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário
            //e cria um objeto funcionario
            Equipe ps = new Equipe(tfNome.getText(),tfEnd.getText(),
                tfTel.getText(),cbSupervisor.getValue(),tfRg.getText(),tfCpf.getText(),tfDataN.getText());

            //Mandar o ator para a camada de servico
            equipeServico.salvar(ps);
            
            //Exibindo mensagem
            mensagemSucesso("Integrante da Equipe salvo com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            listarEquipesTabela();
            
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
                equipeServico.editar(selecionado);
                
                //Exibindo mensagem
                mensagemSucesso("Ator atualizado com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 listarEquipesTabela();
            }
            
        }

        
        limparCampos();
    }

        public void mensagemSucesso(String m){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!"); 
        alerta.setHeaderText(null); 
        alerta.setContentText(m);
        alerta.showAndWait(); 
    }
    
    private void configurarTabela(){
        
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
        colSupervisor.setCellValueFactory(
                new PropertyValueFactory("responsavel"));        
    }
    
    private void listarEquipesTabela(){
        //Limpando quaisquer dados anteriores
        dados.clear();
        
        //Solicitando a camada de servico a lista de atores
        List<Equipe> equipes = equipeServico.listar();
        
        //Transformar a lista de funcionario no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(equipes);
        
        //Jogando os dados na tabela
        tabela.setItems(dados);
        
    }
    
    private void listarSupervisores() {

        List<Supervisor> supervisores = supervisorServico.listar();

        cbSupervisor.setItems(FXCollections.observableArrayList(supervisores));

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
        
        //Pegar o Funcionario que foi selecionado na tabela
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
            cbSupervisor.setValue(selecionado.getResponsavel());
                           
        }else{ //não tem ator selecionado na tabela
            mensagemErro("Selecione um Integrante da Equipe.");
        }
    }
    
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
                equipeServico.excluir(selecionado);
                
                //mostrar mensagem de sucesso
                mensagemSucesso("Integrante da Equipe excluído com sucesso");
                
                //Atualizar a tabela
                listarEquipesTabela();              
                
            }
            
            
            
        }else{
            mensagemErro("Selecione um Integrante da Equipe.");
        }
    }
    private void limparCampos() {
        tfID.setText("");
        tfNome.setText("");
        tfEnd.setText("");
        tfRg.setText("");
        tfCpf.setText("");
        tfDataN.setText("");
        tfTel.setText("");
        cbSupervisor.setValue(null);
    }

    @FXML
    private void bPesquisar(ActionEvent event) {
        
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Pegando o nome que a pessoa deseja pesquisar
        String nome = tfPesquisar.getText();

        //Solicitando a camada de servico a lista de atores
        List<Equipe> funcionarios = equipeServico.buscarPeloNome(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(funcionarios);

        //Jogando os dados na tabela
        tabela.setItems(dados);
        
    }
    
    
}