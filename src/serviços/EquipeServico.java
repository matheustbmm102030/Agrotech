package serviços;

import dados.daos.EquipeDAO;
import dados.entidades.Equipe;
import java.util.List;

public class EquipeServico {
    private EquipeDAO dao = new EquipeDAO();
    
    public void salvar(Equipe ps){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(ps);
    }
    
    /**
     * Solicita a camada DAO para buscar os atores
     * cadastrados
     * @return 
     */
    public List<Equipe> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    public void editar(Equipe ps){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(ps);
        
    }

    public void excluir(Equipe ps) {
        
        dao.excluir(ps);
    }
    
    public List<Equipe> buscarPeloNome(String nome){

        //Qualquer regra de negócio (se aplicável)

        //Mandar para a DAO buscar os filmes pelo nome
        return dao.buscarPeloNome(nome);
    }
}
