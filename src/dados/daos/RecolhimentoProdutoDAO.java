package dados.daos;

import dados.entidades.RecolhimentoProduto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

public class RecolhimentoProdutoDAO {
    public void salvar(RecolhimentoProduto f){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir o ator
        gerenciador.persist(f);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
    /**
     * Retorna uma lista com todos os funcionarios 
     * que estejam cadastrados no banco de dados
     * @return 
     */
    public List<RecolhimentoProduto> listar(){
        
      //Pegando o gerenciador de acesso ao BD
      EntityManager gerenciador = JPAUtil.getGerenciador(); 
      
      //Criando a consulta ao BD
      TypedQuery consulta = gerenciador.createQuery("Select f from RecolhimentoProduto f", RecolhimentoProduto.class);
      
      //Retornar a lista de atores
      return consulta.getResultList();
        
    }
    
    public void editar(RecolhimentoProduto f) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(f);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    public void excluir(RecolhimentoProduto f){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o ator do BD com o ator que foi
        //selecionado na tela
        f = gerenciador.merge(f);

        //Mandar sincronizar as alterações 
        gerenciador.remove(f);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }
    
     public List<RecolhimentoProduto> buscarPeloNome(String nome){

       //Pegando o gerenciador de acesso ao BD
       EntityManager gerenciador = JPAUtil.getGerenciador(); 

       //Criando a consulta ao BD
       TypedQuery<RecolhimentoProduto> consulta = gerenciador.createQuery(
                "Select f from RecolhimentoProduto f where f.nome like :nome", 
               RecolhimentoProduto.class);

       //Substituindo o parametro :nome pelo valor da variavel n
       consulta.setParameter("nome","%" + nome + "%");

       //Retornar os dados
       return consulta.getResultList();

    }
    
}
