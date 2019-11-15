/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrotech;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author medei
 */
public class PrincipalController implements Initializable {
    
    @FXML
    private Label label;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void abrirJanelaSupervisores(ActionEvent event) throws IOException {
        //Código para abrir uma nova Janela
        //Ler o FXML que representa a nova janela
        //(adicionar o throws)
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/Supervisor/JanelaSupervisor.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Funcionarios");
        //Adicionando a cena na janela
        stage.setScene(scene);
        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);
        //Mostrando a nova janela
        
        stage.show();
    }

    @FXML
    private void abrirJanelaEquipe(ActionEvent event) throws IOException {
        //Código para abrir uma nova Janela
        //Ler o FXML que representa a nova janela
        //(adicionar o throws)
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/Equipe/JanelaEquipe.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Prestadoras De Serviços");
        //Adicionando a cena na janela
        stage.setScene(scene);
        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);
        //Mostrando a nova janela
        
        stage.show();
    }

    @FXML
    private void abrirJanelaInsumo(ActionEvent event) throws IOException {
        //Código para abrir uma nova Janela
        //Ler o FXML que representa a nova janela
        //(adicionar o throws)
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/Insumo/JanelaInsumo.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Insumos");
        //Adicionando a cena na janela
        stage.setScene(scene);
        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);
        //Mostrando a nova janela
        
        stage.show();
    }
    
    @FXML
    private void abrirJanelaProduto(ActionEvent event) throws IOException {
        //Código para abrir uma nova Janela
        //Ler o FXML que representa a nova janela
        //(adicionar o throws)
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/Produto/JanelaProduto.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Produtos");
        //Adicionando a cena na janela
        stage.setScene(scene);
        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);
        //Mostrando a nova janela
        
        stage.show();
    }
}