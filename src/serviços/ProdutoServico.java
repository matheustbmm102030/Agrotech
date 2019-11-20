package serviços;

import dados.daos.ProdutoDAO;
import dados.entidades.Produto;
import java.util.List;

public class ProdutoServico {
       
    //Atributo para representar a camada de dados
    private ProdutoDAO dao = new ProdutoDAO();
    
    public void salvar(Produto f){
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
    public List<Produto> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    public void editar(Produto f){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(f);
        
    }

    public void excluir(Produto f) {
        
        dao.excluir(f);
    }
    
    public List<Produto> buscarPeloNome(String nome){

        //Qualquer regra de negócio (se aplicável)

        //Mandar para a DAO buscar os filmes pelo nome
        return dao.buscarPeloNome(nome);
    }
}
