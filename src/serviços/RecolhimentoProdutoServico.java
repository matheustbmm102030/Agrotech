package serviços;

import dados.daos.RecolhimentoProdutoDAO;
import dados.entidades.RecolhimentoProduto;
import java.util.List;

public class RecolhimentoProdutoServico {
    
   //Atributo para representar a camada de dados
    private RecolhimentoProdutoDAO dao = new RecolhimentoProdutoDAO();
    
    public void salvar(RecolhimentoProduto f){
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
    public List<RecolhimentoProduto> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    public void editar(RecolhimentoProduto f){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(f);
        
    }

    public void excluir(RecolhimentoProduto f) {
        
        dao.excluir(f);
    }
    
}
