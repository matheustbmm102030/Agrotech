package serviços;

import dados.daos.PrestadoraDeServicoDAO;
import dados.entidades.PrestadoraDeServico;
import java.util.List;

public class PrestadoraDeServicoServico {
    private PrestadoraDeServicoDAO dao = new PrestadoraDeServicoDAO();
    
    public void salvar(PrestadoraDeServico ps){
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
    public List<PrestadoraDeServico> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    public void editar(PrestadoraDeServico ps){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(ps);
        
    }

    public void excluir(PrestadoraDeServico ps) {
        
        dao.excluir(ps);
    }
}
