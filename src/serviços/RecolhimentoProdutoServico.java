package serviços;

import dados.daos.RecolhimentoProdutoDAO;
import dados.entidades.RecolhimentoProduto;
import java.util.List;

public class RecolhimentoProdutoServico {
    
    private RecolhimentoProdutoDAO dao = new RecolhimentoProdutoDAO();
    
    public void salvar(RecolhimentoProduto rp){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(rp);
    }
    
    /**
     * Solicita a camada DAO para buscar os atores
     * cadastrados
     * @return 
     */
    public List<RecolhimentoProduto> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
}
