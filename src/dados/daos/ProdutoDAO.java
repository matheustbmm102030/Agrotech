package dados.daos;

import dados.entidades.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

public class ProdutoDAO {
    public void salvar(Produto f){
        
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
    public List<Produto> listar(){
        
      //Pegando o gerenciador de acesso ao BD
      EntityManager gerenciador = JPAUtil.getGerenciador(); 
      
      //Criando a consulta ao BD
      TypedQuery consulta = gerenciador.createQuery("Select f from Produto f", Produto.class);
      
      //Retornar a lista de atores
      return consulta.getResultList();
        
    }
    
    public void editar(Produto f) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(f);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    public void excluir(Produto f){
        
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
    
     public List<Produto> buscarPeloNome(String nome){

       //Pegando o gerenciador de acesso ao BD
       EntityManager gerenciador = JPAUtil.getGerenciador(); 

       //Criando a consulta ao BD
       TypedQuery<Produto> consulta = gerenciador.createQuery(
                "Select f from Filme f where f.nome like :nome", 
               Produto.class);

       //Substituindo o parametro :nome pelo valor da variavel n
       consulta.setParameter("nome","%" + nome + "%");

       //Retornar os dados
       return consulta.getResultList();

    }
}
