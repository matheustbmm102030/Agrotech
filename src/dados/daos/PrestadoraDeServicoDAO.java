package dados.daos;

import dados.entidades.PrestadoraDeServico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

public class PrestadoraDeServicoDAO {
    public void salvar(PrestadoraDeServico ps){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir o ator
        gerenciador.persist(ps);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
    /**
     * Retorna uma lista com todos os funcionarios 
     * que estejam cadastrados no banco de dados
     * @return 
     */
    public List<PrestadoraDeServico> listar(){
        
      //Pegando o gerenciador de acesso ao BD
      EntityManager gerenciador = JPAUtil.getGerenciador(); 
      
      //Criando a consulta ao BD
      TypedQuery consulta = gerenciador.createQuery(
              "Select ps from PrestadoraDeServico ps", PrestadoraDeServico.class);
      
      //Retornar a lista de atores
      return consulta.getResultList();
        
    }
    
    public void editar(PrestadoraDeServico ps) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(ps);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    public void excluir(PrestadoraDeServico ps){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o ator do BD com o ator que foi
        //selecionado na tela
        ps = gerenciador.merge(ps);

        //Mandar sincronizar as alterações 
        gerenciador.remove(ps);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }
}
