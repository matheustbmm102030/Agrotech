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
    
}
