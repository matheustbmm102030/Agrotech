/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviços;

import dados.daos.MateriaPrimaDAO;
import dados.entidades.MateriaPrima;
import java.util.List;

/**
 *
 * @author medei
 */
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
}
