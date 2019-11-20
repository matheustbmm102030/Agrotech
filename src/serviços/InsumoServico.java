package serviços;

import dados.daos.InsumoDAO;
import dados.entidades.Insumo;
import java.util.List;

public class InsumoServico {
       //Atributo para representar a camada de dados
    private InsumoDAO dao = new InsumoDAO();
    
    
    public void salvar(Insumo mp){
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
    public List<Insumo> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    public void editar(Insumo mp){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(mp);
        
    }

    public void excluir(Insumo mp) {
        
        dao.excluir(mp);
    }
    
    public List<Insumo> buscarPeloNome(String nome){

        //Qualquer regra de negócio (se aplicável)

        //Mandar para a DAO buscar os filmes pelo nome
        return dao.buscarPeloNome(nome);
    }
    
}
