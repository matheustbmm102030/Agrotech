package dados.daos;

import dados.entidades.MateriaPrima;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

public class MateriaPrimaDAO {
    public void salvar(MateriaPrima f){
        
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
    public List<MateriaPrima> listar(){
        
      //Pegando o gerenciador de acesso ao BD
      EntityManager gerenciador = JPAUtil.getGerenciador(); 
      
      //Criando a consulta ao BD
      TypedQuery consulta = gerenciador.createQuery(
              "Select mp from MateriaPrima mp", MateriaPrima.class);
      
      //Retornar a lista de atores
      return consulta.getResultList();
        
    }
    
    public void editar(MateriaPrima f) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(f);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    public void excluir(MateriaPrima mp){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o ator do BD com o ator que foi
        //selecionado na tela
        mp = gerenciador.merge(mp);

        //Mandar sincronizar as alterações 
        gerenciador.remove(mp);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }
    
}
