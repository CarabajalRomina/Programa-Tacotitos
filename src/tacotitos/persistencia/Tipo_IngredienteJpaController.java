
package tacotitos.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import tacotitos.logica.Tipo_Ingrediente;
import tacotitos.persistencia.exceptions.NonexistentEntityException;


public class Tipo_IngredienteJpaController implements Serializable {

  
   /* public IngredienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
   */
    //declaro atributo entitymanagerfactory
    private EntityManagerFactory emf = null;
    
    //constructor de jpacontroller 
    
    public Tipo_IngredienteJpaController() {
        //Persistence le digo a mi gestor de pesistenicia (jpa) que me cree un entitymanager factory de acuerdo a PU(persistence unit)
        //crear clase que va a gestionar las conexiones con la bd (PU)
        emf = Persistence.createEntityManagerFactory("PU");
    }
    
    //metodo que devuelve un entityManager que se va a encargar de guardar, recuperar, listar todo 
    public EntityManager getEntityManager() {
        //entityManager es la conexion en si con la bd es el equivalente al dao, no hago un new entityManager  
        //sino que se lo pido a la persistencia, por que el entityManager factory es singleton,
        //puedo abrir todas las conexiones que quiero pero lo va a gestionar el EntityManager Factory
        return emf.createEntityManager();
    }

    public void crear(Tipo_Ingrediente tipo_Ingrediente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipo_Ingrediente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Tipo_Ingrediente tipo_Ingrediente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipo_Ingrediente = em.merge(tipo_Ingrediente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipo_Ingrediente.getId_tipo();
                if (buscarTipo_Ingrediente(id) == null) {
                    throw new NonexistentEntityException("The tipo_Ingrediente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipo_Ingrediente tipo_Ingrediente;
            try {
                tipo_Ingrediente = em.getReference(Tipo_Ingrediente.class, id);
                tipo_Ingrediente.getId_tipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipo_Ingrediente with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipo_Ingrediente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipo_Ingrediente> buscarTipo_IngredienteEntities() {
        return buscarTipo_IngredienteEntities(true, -1, -1);
    }

    public List<Tipo_Ingrediente> buscarTipo_IngredienteEntities(int maxResults, int firstResult) {
        return buscarTipo_IngredienteEntities(false, maxResults, firstResult);
    }
    
     //metodo privado para buscar todos los registros de una tabla en la base de da
    private List<Tipo_Ingrediente> buscarTipo_IngredienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipo_Ingrediente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    /*
    *este metodo del jpa controller busca un tipo de ingrediente en la bd por su id
    *
    */
    public Tipo_Ingrediente buscarTipo_Ingrediente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipo_Ingrediente.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipo_IngredienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipo_Ingrediente> rt = cq.from(Tipo_Ingrediente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
