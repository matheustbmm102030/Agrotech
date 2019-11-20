package dados.daos;

import dados.entidades.Insumo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

public class InsumoDAO {
    public void salvar(Insumo f){
        
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
    public List<Insumo> listar(){
        
      //Pegando o gerenciador de acesso ao BD
      EntityManager gerenciador = JPAUtil.getGerenciador(); 
      
      //Criando a consulta ao BD
      TypedQuery consulta = gerenciador.createQuery("Select mp from Insumo mp", Insumo.class);
      
      //Retornar a lista de atores
      return consulta.getResultList();
        
    }
    
    public void editar(Insumo f) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(f);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    public void excluir(Insumo mp){
        
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
    
     public List<Insumo> buscarPeloNome(String nome){

       //Pegando o gerenciador de acesso ao BD
       EntityManager gerenciador = JPAUtil.getGerenciador(); 

       //Criando a consulta ao BD
       TypedQuery<Insumo> consulta = gerenciador.createQuery(
                "Select f from Filme f where f.nome like :nome", 
               Insumo.class);

       //Substituindo o parametro :nome pelo valor da variavel n
       consulta.setParameter("nome", nome + "%");

       //Retornar os dados
       return consulta.getResultList();

    }
    
}
