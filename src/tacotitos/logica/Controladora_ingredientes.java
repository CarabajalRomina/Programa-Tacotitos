
package tacotitos.logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Controladora_ingredientes {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    
    public Controladora_ingredientes(){
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
    }
    
    public void crearIngrediente( String nombre, float precio, int id_tipo){
        //buscar el id del objeto de tipo ingrediente 
        Tipo_Ingrediente tipo_ingrediente= em.find(Tipo_Ingrediente.class, id_tipo);
       
    }
    
    
    
}
