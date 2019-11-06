package serviços;

import dados.daos.ProdutoDAO;
import dados.entidades.Produto;
import java.util.List;

public class ProdutoServico {
    
    private ProdutoDAO dao = new ProdutoDAO();
    
    public void salvar(Produto p){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(p);
    }
    
    /**
     * Solicita a camada DAO para buscar os atores
     * cadastrados
     * @return 
     */
    public List<Produto> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
}
