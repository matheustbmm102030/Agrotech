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
}
