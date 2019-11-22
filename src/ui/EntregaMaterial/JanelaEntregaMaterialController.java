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
import dados.entidades.Insumo;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import serviços.EntregaMaterialServico;
import serviços.EquipeServico;
import serviços.InsumoServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class JanelaEntregaMaterialController implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private TableView<EntregaMaterial> tabela;
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
    @FXML
    private JFXTextField tfDataEntrega;
    @FXML
    private JFXComboBox<Equipe> cbPrestador;
    @FXML
    private JFXComboBox<Insumo> cbInsumo4;
    @FXML
    private JFXComboBox<Insumo> cbInsumo1;
    @FXML
    private JFXComboBox<Insumo> cbInsumo2;
    @FXML
    private JFXComboBox<Insumo> cbInsumo3;
    @FXML
    private JFXTextField tfPesquisar;
    
    private ObservableList<EntregaMaterial> dados
            = FXCollections.observableArrayList();
    
    //Atributos para representar os servicos
    private EntregaMaterialServico entregaMaterialServico = new EntregaMaterialServico();
    private EquipeServico equipeServico = new EquipeServico();
    private InsumoServico insumoServico = new InsumoServico();
    
    private EntregaMaterial selecionado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configure a tabela
        configurarTabela();

        //Carregue a lista de atores na tabela
        listarEntregaMaterialTabela();

        //Carregar combo de genero
        listarEquipe();
        
        listarInsumos1();
                
        listarInsumos2();
                
        listarInsumos3();
                
        listarInsumos4();
                
                
    }   
    
    private void configurarTabela() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
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

    }//configurarTabela
    
    private void listarEntregaMaterialTabela() {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Solicitando a camada de servico a lista de atores
        List<EntregaMaterial> entregas = entregaMaterialServico.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(entregas);

        //Jogando os dados na tabela
        tabela.setItems(dados);

    }
    
    private void listarEquipe() {

        List<Equipe> prestadores = equipeServico.listar();

        cbPrestador.setItems(FXCollections.observableArrayList(prestadores));

    }
    
    private void listarInsumos1() {

        List<Insumo> insumos = insumoServico.listar();

        cbInsumo1.setItems(FXCollections.observableArrayList(insumos));

    }
    
    private void listarInsumos2() {

        List<Insumo> insumos = insumoServico.listar();

        cbInsumo2.setItems(FXCollections.observableArrayList(insumos));

    }
    
    private void listarInsumos3() {

        List<Insumo> insumos = insumoServico.listar();

        cbInsumo3.setItems(FXCollections.observableArrayList(insumos));

    }
    
    private void listarInsumos4() {

        List<Insumo> insumos = insumoServico.listar();

        cbInsumo4.setItems(FXCollections.observableArrayList(insumos));

    }

    @FXML
    private void bSalvar(ActionEvent event) {
        
        //Verificar se está atualizando ou inserindo
        if (tfID.getText().isEmpty()) { //inserindo

            //Criando o objeto filme
            EntregaMaterial em = new EntregaMaterial(
                    cbPrestador.getValue(),
                    cbInsumo1.getValue(),cbInsumo2.getValue(),cbInsumo3.getValue(),cbInsumo4.getValue(),
                    tfDataEntrega.getText()
            );

            //Mandando para a camada de serviço salvar
            entregaMaterialServico.salvar(em);

            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Entrega de material salva com sucesso!");

            //Carregando lista de filmes
            listarEntregaMaterialTabela();

        } else { //atualizando

            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                
                //Pegar os novos dados do formulário e
                //atualizar o filme
                selecionado.setDataEntrega(tfDataEntrega.getText());
                selecionado.setPrestadora(cbPrestador.getValue());
                selecionado.setInsumo1(cbInsumo1.getValue());
                selecionado.setInsumo2(cbInsumo2.getValue());
                selecionado.setInsumo3(cbInsumo3.getValue());
                selecionado.setInsumo4(cbInsumo4.getValue());
                
                //Mandando para a camada de serviço salvar as alterações
                entregaMaterialServico.editar(selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Entrega de material atualizada com sucesso!");
                
                //Carregando lista de filmes
                listarEntregaMaterialTabela();
                
            }
            
        }

        //Limpando o form
        limparCampos();
    }

    @FXML
    private void bEditar(ActionEvent event) {

        //Pegar o filme que foi selecionado na tabela 
        selecionado = tabela.getSelectionModel().getSelectedItem();

        //Se tem algum filme selecionado
        if (selecionado != null) {
            
            //Pega os dados do filme e joga no formulário
            tfID.setText(String.valueOf(selecionado.getId()));
            cbPrestador.setValue(selecionado.getPrestadora());
            cbInsumo1.setValue(selecionado.getInsumo1());
            cbInsumo2.setValue(selecionado.getInsumo2());
            cbInsumo3.setValue(selecionado.getInsumo3());
            cbInsumo4.setValue(selecionado.getInsumo4());
            
        }else{//não selecionou filme na tabela
            AlertaUtil.mensagemErro("Selecione uma Entrega.");
        }

    }

    @FXML
    private void bExcluir(ActionEvent event) {
        
        //Pegar o filme que foi selecionado na tabela 
        selecionado = tabela.getSelectionModel().getSelectedItem();
        
        //Se tem algum filme selecionado
        if (selecionado != null) {
            
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir?",
                      "EXCLUIR");
            
             //Verificando se apertou o OK
            if(btn.get() == ButtonType.OK){
                
                //Manda para a camada de serviço excluir
                entregaMaterialServico.excluir(selecionado);
                
                //mostrar mensagem de sucesso
                AlertaUtil.mensagemSucesso("Entrega de material excluída com sucesso");
                
                //Carregando lista de filmes
                listarEntregaMaterialTabela();
            }
            
        }
        
    }

    @FXML
    private void pesquisar(ActionEvent event) {
        
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Pegando o nome que a pessoa deseja pesquisar
        String nome = tfPesquisar.getText();
        
        //Solicitando a camada de servico a lista de atores
        List<EntregaMaterial> entregas = entregaMaterialServico.buscarPeloNome(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(entregas);

        //Jogando os dados na tabela
        tabela.setItems(dados);
        
    }

    private void limparCampos() {
        tfID.setText("");
        cbPrestador.setValue(null);
        cbInsumo1.setValue(null);
        cbInsumo2.setValue(null);
        cbInsumo3.setValue(null);
        cbInsumo4.setValue(null);
    }
    
}
