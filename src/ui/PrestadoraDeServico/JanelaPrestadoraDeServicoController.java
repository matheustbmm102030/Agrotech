/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.PrestadoraDeServico;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.PrestadoraDeServico;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import serviços.PrestadoraDeServicoServico;

/**
 * FXML Controller class
 *
 * @author medei
 */
public class JanelaPrestadoraDeServicoController implements Initializable {

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
    private TableView<?> tabela;
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
    private JFXComboBox<?> cbSupervisor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
/*
    @FXML
    private void bSalvar(ActionEvent event) {
        //Verificar se está atualizando ou inserindo
        if(tfID.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário
            //e cria um objeto funcionario
            PrestadoraDeServico ps = new PrestadoraDeServico(tfNome.getText(),tfEnd.getText(),
                tfTel.getText(),cbSupervisor.getClass(),tfRg.getText(),tfCpf.getText(),tfDataN.getText());

            //Mandar o ator para a camada de servico
            servico.salvar(ps);
            
            //Exibindo mensagem
            mensagemSucesso("Prestadora de servico salvo com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            listarPrestadoraDeServicoTabela();
            
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
                mensagemSucesso("Ator atualizado com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 listarPrestadoraDeServicoTabela();
            }
            
        }

        
        //Limpando o form
        tfID.setText("");
        tfNome.setText("");
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
        
    }
    
    private void listarPrestadoraDeServicoTabela(){
        //Limpando quaisquer dados anteriores
        dados.clear();
        
        //Solicitando a camada de servico a lista de atores
        List<PrestadoraDeServico> prestadoraDeServicos = servico.listar();
        
        //Transformar a lista de funcionario no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(prestadoraDeServicos);
        
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
                           
        }else{ //não tem ator selecionado na tabela
            mensagemErro("Selecione um funcionario.");
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
                servico.excluir(selecionado);
                
                //mostrar mensagem de sucesso
                mensagemSucesso("Funcionario excluído com sucesso");
                
                //Atualizar a tabela
                listarPrestadoraDeServicoTabela();              
                
            }
            
            
            
        }else{
            mensagemErro("Selecione um ator.");
        }
    }
    */
}
