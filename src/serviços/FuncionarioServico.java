
package serviços;

import dados.daos.FuncionarioDAO;
import dados.entidades.Funcionario;
import java.util.List;

public class FuncionarioServico {
        
    //Atributo para representar a camada de dados
    private FuncionarioDAO dao = new FuncionarioDAO();
    
    public void salvar(Funcionario f){
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
    public List<Funcionario> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
}
