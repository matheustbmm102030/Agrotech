package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    
    private static EntityManagerFactory fabrica 
            = Persistence.createEntityManagerFactory("agrotech");
    
    public static EntityManager getGerenciador(){
        return fabrica.createEntityManager();
    }
    
}
