package dados.daos;

import dados.entidades.EntregaMaterial;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

public class EntregaMaterialDAO {
    public void salvar(EntregaMaterial em){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir o ator
        gerenciador.persist(em);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
    /**
     * Retorna uma lista com todos os funcionarios 
     * que estejam cadastrados no banco de dados
     * @return 
     */
    public List<EntregaMaterial> listar(){
        
      //Pegando o gerenciador de acesso ao BD
      EntityManager gerenciador = JPAUtil.getGerenciador(); 
      
      //Criando a consulta ao BD
      TypedQuery consulta = gerenciador.createQuery(
              "Select em from EntregaMaterial em", EntregaMaterial.class);
      
      //Retornar a lista de atores
      return consulta.getResultList();
        
    }
    
    public void editar(EntregaMaterial em) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(em);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    public void excluir(EntregaMaterial em){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o ator do BD com o ator que foi
        //selecionado na tela
        em = gerenciador.merge(em);

        //Mandar sincronizar as alterações 
        gerenciador.remove(em);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }
    
    public List<EntregaMaterial> buscarPeloNome(String nome){
        
       //Pegando o gerenciador de acesso ao BD
       EntityManager gerenciador = JPAUtil.getGerenciador(); 
       
       //Criando a consulta ao BD
       TypedQuery<EntregaMaterial> consulta = gerenciador.createQuery(
                "Select f from Filme f where f.nome like :nome", 
               EntregaMaterial.class);
       
       //Substituindo o parametro :nome pelo valor da variavel n
       consulta.setParameter("nome", nome + "%");
       
       //Retornar os dados
       return consulta.getResultList();
        
    }
}
