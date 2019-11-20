/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.EntregaMaterial;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.EntregaMaterial;
import dados.entidades.Equipe;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import serviços.EntregaMaterialServico;
import serviços.SupervisorServico;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class JanelaEntregaMaterialController implements Initializable {

    private ComboBox<?> cbinsumo1;
    private ComboBox<?> cbinsumo2;
    private ComboBox<?> cbinsumo3;
    private ComboBox<?> cbinsumo4;
    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfDataEntrega;
    @FXML
    private JFXComboBox<?> cbPrestador;
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
    
        //Atributo que representa os dados para tabela
    private ObservableList<EntregaMaterial> dados
            = FXCollections.observableArrayList();

    //Atributos para representar os servicos
    private EntregaMaterialServico entregaMaterialServico = new EntregaMaterialServico();
    private SupervisorServico supervisorServico = new SupervisorServico();

    //Atributo para representar o filme selecionado
    //na tabela para editar e excluir
    private EntregaMaterial selecionado;
    
    @FXML
    private JFXComboBox<?> cbInsumo4;
    @FXML
    private JFXComboBox<?> cbInsumo1;
    @FXML
    private JFXComboBox<?> cbInsumo2;
    @FXML
    private JFXComboBox<?> cbInsumo3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bSalvar(ActionEvent event) {
         //Verificar se está atualizando ou inserindo
        if(tfID.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário
            //e cria um objeto funcionario
            EntregaMaterial em = new EntregaMaterial (tfDataEntrega.getText(),
                cbPrestador.getValue(),cbinsumo1.getValue(),cbinsumo2.getValue(),
                    cbinsumo3.getValue(),cbinsumo4.getValue());

            //Mandar o ator para a camada de servico
            entregaMaterialServico.salvar(em);
            
            //Exibindo mensagem
            mensagemSucesso("Entrega material salvo com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            listarEntregaMaterialTabela();
            
        }else{ //atualizando o ator
           
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                //Pegar os novos dados do formulário e
                //atualizar o meu ator
                selecionado.setDataEntrega(tfDataEntrega.getText());
                
                //Mandando pra camada de serviço salvar as alterações
                entregaMaterialServico.editar(selecionado);
                
                //Exibindo mensagem
                mensagemSucesso("Entrega material atualizado com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 listarEntregaMaterialTabela();
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
        colPrestador.setCellValueFactory(
                new PropertyValueFactory("prestadora"));
        colInsumo1.setCellValueFactory(
                new PropertyValueFactory("insumo1"));
        colInsumo2.setCellValueFactory(
                new PropertyValueFactory("insumo2"));
        colInsumo3.setCellValueFactory(
                new PropertyValueFactory("insumo3"));
        colInsumo4.setCellValueFactory(
                new PropertyValueFactory("insumo4"));
        colDtEntrega.setCellValueFactory(
                new PropertyValueFactory("dataEntrega"));     
    }
        private void listarEntregaMaterialTabela(){
        //Limpando quaisquer dados anteriores
        dados.clear();
        
        //Solicitando a camada de servico a lista de atores
        List<EntregaMaterial> entregas = entregaMaterialServico.listar();
        
        //Transformar a lista de funcionario no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(entregas);
        
        //Jogando os dados na tabela
        tabela.setItems(dados);
        
    }
    
    private void listarEquipe() {

        List<Equipe> prestadores = equipeServico.listar();

        cbPrestador.setItems(FXCollections.observableArrayList(prestadores));

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
            tfDataEntrega.setText( selecionado.getDataEntrega() ); 
            cbPrestador.setValue( selecionado.getPrestadora() );
            cbinsumo1.setValue( selecionado.getInsumo1() );
            cbinsumo2.setValue( selecionado.getInsumo2() );
            cbinsumo3.setValue( selecionado.getInsumo3() );
            cbinsumo4.setValue( selecionado.getInsumo4() );
                       
                           
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
                entregaMaterialServico.excluir(selecionado);
                
                //mostrar mensagem de sucesso
                mensagemSucesso("Integrante da Equipe excluído com sucesso");
                
                //Atualizar a tabela
                private void listarEntregaMaterialTabela();              
                
            }
            
            
            
        }else{
            mensagemErro("Selecione um Integrante da Equipe.");
        }        
    }

    private void limparCampos(){
        tfID.setText("");
        tfDataEntrega.setText("");
        cbinsumo1.setValue(null);
        cbinsumo2.setValue(null);
        cbinsumo3.setValue(null);
        cbinsumo4.setValue(null);
        cbPrestador.setValue(null);
    }    
}
