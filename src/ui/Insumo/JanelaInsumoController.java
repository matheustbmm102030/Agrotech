package ui.Insumo;

import dados.entidades.Insumo;
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
import serviços.InsumoServico;

public class JanelaInsumoController implements Initializable {
    
    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfTipo;
    @FXML
    private JFXTextField tfQuant;
    @FXML
    private TableView<Insumo> tabela;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colTipo;
    @FXML
    private TableColumn colQuant;
    
     /**
     * Initializes the controller class.
     */
    private InsumoServico servico = new InsumoServico();

    private ObservableList<Insumo> dados = 
            FXCollections.observableArrayList();
   
    private Insumo selecionado;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        //Configure a tabela
        configurarTabela();
        
        //Carregue a lista de atores na tabela
        listarInsumoTabela();
    }    

    @FXML
    private void bSalvar(ActionEvent event) {
          //Verificar se está atualizando ou inserindo
        if(tfID.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário
            //e cria um objeto funcionario
            Insumo mp = new Insumo(tfTipo.getText(),
                    Integer.parseInt(tfQuant.getText()));

            //Mandar o ator para a camada de servico
            servico.salvar(mp);
            
            //Exibindo mensagem
            mensagemSucesso("Insumo salva com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            listarInsumoTabela();
            
        }else{ //atualizando o ator
           
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                //Pegar os novos dados do formulário e
                //atualizar o meu ator
                selecionado.setTipo(tfTipo.getText());
                selecionado.setQuantidade(Integer.parseInt(tfQuant.getText()));

                
                //Mandando pra camada de serviço salvar as alterações
                servico.editar(selecionado);
                
                //Exibindo mensagem
                mensagemSucesso("Insumo atualizado com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 listarInsumoTabela();
            }
            
        }

        
        //Limpando o form
        tfID.setText("");
        tfTipo.setText("");
        tfQuant.setText("");
    }
    
    
    public void mensagemSucesso(String m){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!"); 
        alerta.setHeaderText(null); 
        alerta.setContentText(m);
        alerta.showAndWait(); 
    }
    
    private void configurarTabela(){
        
        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        colID.setCellValueFactory(
                new PropertyValueFactory("id"));
        colTipo.setCellValueFactory(
                new PropertyValueFactory("tipo"));
        colQuant.setCellValueFactory(
                new PropertyValueFactory("quantidade"));
        
    }
    
    private void listarInsumoTabela(){
        //Limpando quaisquer dados anteriores
        dados.clear();
        
        //Solicitando a camada de servico a lista de atores
        List<Insumo> insumos = servico.listar();
        
        //Transformar a lista de funcionario no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(insumos);
        
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
            tfTipo.setText( selecionado.getTipo() ); 
            tfQuant.setText(String.valueOf(selecionado.getQuantidade()));
                           
        }else{ //não tem ator selecionado na tabela
            mensagemErro("Selecione uma Insumo.");
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
                mensagemSucesso("Insumo excluído com sucesso");
                
                //Atualizar a tabela
                listarInsumoTabela();              
                
            }
            
            
             
        }else{
            mensagemErro("Selecione um Insumo.");
        }
    }
    
}