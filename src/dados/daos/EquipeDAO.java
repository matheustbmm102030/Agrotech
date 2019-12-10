package dados.daos;

import dados.entidades.Equipe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

public class EquipeDAO {
    public void salvar(Equipe ps){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir
        gerenciador.persist(ps);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
    /**
     * Retorna uma lista com todos os funcionarios 
     * que estejam cadastrados no banco de dados
     * @return 
     */
    public List<Equipe> listar(){
        
      //Pegando o gerenciador de acesso ao BD
      EntityManager gerenciador = JPAUtil.getGerenciador(); 
      
      //Criando a consulta ao BD
      TypedQuery consulta = gerenciador.createQuery("Select ps from Equipe ps", Equipe.class);
      
      //Retornar a lista
      return consulta.getResultList();
        
    }
    
    public void editar(Equipe ps) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(ps);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    public void excluir(Equipe ps){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o objeto do BD com o objeto que foi
        //selecionado na tela
        ps = gerenciador.merge(ps);

        //Mandar sincronizar as alterações 
        gerenciador.remove(ps);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }
    
     public List<Equipe> buscarPeloNome(String nome){

       //Pegando o gerenciador de acesso ao BD
       EntityManager gerenciador = JPAUtil.getGerenciador(); 

       //Criando a consulta ao BD
       TypedQuery<Equipe> consulta = gerenciador.createQuery(
                "Select f from Equipe f where f.nome like :nome", 
               Equipe.class);

       //Substituindo o parametro :nome pelo valor da variavel n
       consulta.setParameter("nome","%" + nome + "%");

       //Retornar os dados
       return consulta.getResultList();

    }
    
    
}
