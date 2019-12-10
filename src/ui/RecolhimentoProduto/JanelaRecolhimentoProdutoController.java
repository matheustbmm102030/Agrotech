/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.RecolhimentoProduto;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Equipe;
import dados.entidades.Produto;
import dados.entidades.RecolhimentoProduto;
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
import serviços.ProdutoServico;
import serviços.RecolhimentoProdutoServico;



public class JanelaRecolhimentoProdutoController implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXComboBox<Equipe> cbPrestador;
    @FXML
    private JFXComboBox<Produto> cbProduto;
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
    @FXML
    private TableView<RecolhimentoProduto> tabela;
    
    
    private ObservableList<RecolhimentoProduto> dados
            = FXCollections.observableArrayList();

    //Atributos para representar os servicos
    private RecolhimentoProdutoServico Servico = new RecolhimentoProdutoServico();
    private EquipeServico equipeServico = new EquipeServico();
    private ProdutoServico produtoServico = new ProdutoServico();
    
    //Atributo para representar o filme selecionado
    //na tabela para editar e excluir
    private RecolhimentoProduto selecionado;
    @FXML
    private JFXTextField tfPesquisar;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configure a tabela
        configurarTabela();

        //Carregue a lista de atores na tabela
        listarRecolhimentoProdutoTabela();

        //Carregar combo de genero
        listarEquipes();
        
        listarProdutos();
    }    
    @FXML
    private void bsalvar(ActionEvent event) {
         if(tfID.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário
            //e cria um objeto funcionario
            RecolhimentoProduto ps = new RecolhimentoProduto(Integer.parseInt(tfQantidade.getText()),
                    cbPrestador.getValue(),cbProduto.getValue(),
                    tfDtEntrega.getText());

            //Mandar o ator para a camada de servico
            Servico.salvar(ps);
            
            //Exibindo mensagem
            mensagemSucesso("Coleta Salva com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            listarRecolhimentoProdutoTabela();
            
        }else{ //atualizando o ator
           
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                //Pegar os novos dados do formulário e
                //atualizar o meu ator
                selecionado.setPrestadora(cbPrestador.getValue());
                selecionado.setProdutos(cbProduto.getValue());
                selecionado.setDataEntrega(tfDtEntrega.getText());
                selecionado.setQuantidade(Integer.parseInt(tfQantidade.getText()));
                
                //Mandando pra camada de serviço salvar as alterações
                Servico.editar(selecionado);
                
                //Exibindo mensagem
                mensagemSucesso("Recolhimento atualizado com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 listarRecolhimentoProdutoTabela();
            }
            
        }

        
        limparCampos();
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
                Servico.excluir(selecionado);
                
                //mostrar mensagem de sucesso
                mensagemSucesso("Recolhimento excluído com sucesso");
                
                //Atualizar a tabela
                listarRecolhimentoProdutoTabela();              
                
            }
            
            
            
        }else{
            mensagemErro("Selecione um Integrante da Equipe.");
        }
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
            
            tfDtEntrega.setText( selecionado.getDataEntrega());
            cbPrestador.setValue(selecionado.getPrestadora());
            cbProduto.setValue(selecionado.getProdutos());
            tfQantidade.setText(String.valueOf(selecionado.getQuantidade()));               
        }else{ //não tem ator selecionado na tabela
            mensagemErro("Selecione um Item.");
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
        
        colID.setCellValueFactory(
                new PropertyValueFactory("id"));
        colQte.setCellValueFactory(
                new PropertyValueFactory("quantidade"));
        colPrestador.setCellValueFactory(
                new PropertyValueFactory("prestadora"));
        corProd.setCellValueFactory(
                new PropertyValueFactory("produtos"));
        colDtEntrega.setCellValueFactory(
                new PropertyValueFactory("dataEntrega"));
       
    }
    
    private void listarRecolhimentoProdutoTabela(){
        //Limpando quaisquer dados anteriores
        dados.clear();
        
        //Solicitando a camada de servico a lista de atores
        List<RecolhimentoProduto> recolhimentoProdutos = Servico.listar();
        
        //Transformar a lista de funcionario no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(recolhimentoProdutos);
        
        //Jogando os dados na tabela
        tabela.setItems(dados);
        
    }
    
    private void listarEquipes() {

        List<Equipe> equipes = equipeServico.listar();

        cbPrestador.setItems(FXCollections.observableArrayList(equipes));

    }
    private void listarProdutos() {

        List<Produto> produtos = produtoServico.listar();

        cbProduto.setItems(FXCollections.observableArrayList(produtos));

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

    private void limparCampos() {
        tfID.setText("");
        cbPrestador.setValue(null);
        cbProduto.setValue(null);
        tfDtEntrega.setText("");
        tfQantidade.setText("");
        
    }

    @FXML
    private void bPesquisar(ActionEvent event) {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Pegando o valor  para pesquisar
        String nome = tfPesquisar.getText();
        
        //Solicitando a camada de servico a lista dos objetos
        List<RecolhimentoProduto> recProd = Servico.buscarPeloNome(nome);

        //Transformar a lista de objetos no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(recProd);

        //Jogando os dados na tabela
        tabela.setItems(dados);
    }

}
