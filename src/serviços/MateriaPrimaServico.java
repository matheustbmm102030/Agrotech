package serviços;

import dados.daos.MateriaPrimaDAO;
import dados.entidades.MateriaPrima;
import java.util.List;

public class MateriaPrimaServico {
       //Atributo para representar a camada de dados
    private MateriaPrimaDAO dao = new MateriaPrimaDAO();
    
    
    public void salvar(MateriaPrima mp){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(mp);
    }
    
    /**
     * Solicita a camada DAO para buscar os atores
     * cadastrados
     * @return 
     */
    public List<MateriaPrima> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    public void editar(MateriaPrima mp){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(mp);
        
    }

    public void excluir(MateriaPrima mp) {
        
        dao.excluir(mp);
    }
    
}
