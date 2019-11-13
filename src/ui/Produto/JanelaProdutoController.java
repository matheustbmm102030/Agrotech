/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Produto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Produto;
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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author medei
 */
public class JanelaProdutoController implements Initializable {

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
                        
        //Configure a tabela
        configurarTabela();
        
        //Carregue a lista de atores na tabela
        listarProdutoTabela();
    }    

    @FXML
    private void bSalvar(ActionEvent event) {
          //Verificar se está atualizando ou inserindo
        if(tfId.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário
            //e cria um objeto funcionario
            Produto mp = new Produto(tfNome.getText(),
                    Integer.parseInt(tfQuanti.getText()),
                    Double.parseDouble(tfPreco.getText()));

            //Mandar o ator para a camada de servico
            servico.salvar(mp);
            
            //Exibindo mensagem
            mensagemSucesso("Produto salvo com sucesso!");
            
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
                selecionado.setTipo(tfNome.getText());
                selecionado.setQuantidade(Integer.parseInt(tfQuanti.getText()));
                selecionado.setPreco(Double.parseDouble(tfPreco.getText()));
                
                //Mandando pra camada de serviço salvar as alterações
                servico.editar(selecionado);
                
                //Exibindo mensagem
                mensagemSucesso("Produto atualizado com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 listarProdutoTabela();
            }
            
        }

        
        //Limpando o form
        tfId.setText("");
        tfNome.setText("");
        tfQuanti.setText("");
        tfPreco.setText("");
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
            tfQuanti.setText( selecionado.getQuantidade() );
            tfPreco.setText( selecionado.getPreco() );
            
                           
        }else{ //não tem ator selecionado na tabela
            mensagemErro("Selecione um Produto.");
        }
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
                listarProdutosTabela();              
                
            }
            
            
            
        }else{
            mensagemErro("Selecione um Produto.");
        }
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
        colId.setCellValueFactory(
                new PropertyValueFactory("id"));
        colNome.setCellValueFactory(
                new PropertyValueFactory("nome"));
        colQuant.setCellValueFactory(
                new PropertyValueFactory("quanti"));
        colPreco.setCellValueFactory(
                new PropertyValueFactory("preco"));
        
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
    
    private Optional<ButtonType> mensagemDeConfirmacao(String mensagem, String titulo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait();
    }   
    
}
