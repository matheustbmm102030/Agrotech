package teste;

import dados.entidades.Funcionario;
import javax.persistence.*;
import util.JPAUtil;

public class TesteFuncionario {
    public static void main(String[] args) {
        
        //Criando um objeto ator
        Funcionario a1 = new Funcionario("Raduken","mora ali","9999999","Rg2934","cpf282374","20.98.3823");
       // a1.setNome();
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandando persistir o objeto
        gerenciador.persist(a1);
        
        //Finalizo a transação
        gerenciador.getTransaction().commit();
        
        //Fechar o gerenciador
        gerenciador.close();
        
    }
    
}


