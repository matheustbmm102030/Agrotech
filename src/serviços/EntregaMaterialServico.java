package serviços;

import dados.daos.EntregaMaterialDAO;
import dados.entidades.EntregaMaterial;
import java.util.List;

public class EntregaMaterialServico {
    
    private EntregaMaterialDAO dao = new EntregaMaterialDAO();
    
    public void salvar(EntregaMaterial em){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(em);
    }
    
    /**
     * Solicita a camada DAO para buscar os atores
     * cadastrados
     * @return 
     */
    public List<EntregaMaterial> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    public void editar(EntregaMaterial em){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(em);
        
    }
    
    public void excluir(EntregaMaterial em){
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(em);
    }
    
    public List<EntregaMaterial> buscarPeloNome(String nome){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO buscar os filmes pelo nome
        return dao.buscarPeloNome(nome);
    }
}
