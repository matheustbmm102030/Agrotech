
package serviços;

import dados.daos.SupervisorDAO;
import dados.entidades.Supervisor;
import java.util.List;

public class SupervisorServico {
        
    //Atributo para representar a camada de dados
    private SupervisorDAO dao = new SupervisorDAO();
    
    public void salvar(Supervisor f){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(f);
    }
    
    /**
     * Solicita a camada DAO para buscar os atores
     * cadastrados
     * @return 
     */
    public List<Supervisor> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    public void editar(Supervisor f){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(f);
        
    }

    public void excluir(Supervisor f) {
        
        dao.excluir(f);
    }
    
    public List<Supervisor> buscarPeloNome(String nome){

        //Qualquer regra de negócio (se aplicável)

        //Mandar para a DAO buscar os filmes pelo nome
        return dao.buscarPeloNome(nome);
    }
    
}
