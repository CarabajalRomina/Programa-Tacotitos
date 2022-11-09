
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
import tacotitos.logica.Ingrediente;
import tacotitos.persistencia.exceptions.NonexistentEntityException;

public class IngredienteJpaController implements Serializable {

  
   /* public IngredienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
   */
    //declaro atributo entitymanagerfactory
    private EntityManagerFactory emf = null;
    
    //constructor de jpacontroller 
    
    public IngredienteJpaController() {
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

    public void crear(Ingrediente ingrediente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ingrediente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Ingrediente ingrediente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ingrediente = em.merge(ingrediente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = ingrediente.getId_ingrediente();
                if (buscarIngrediente(id) == null) {
                    throw new NonexistentEntityException("The ingrediente with id " + id + " no longer exists.");
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
            Ingrediente ingrediente;
            try {
                ingrediente = em.getReference(Ingrediente.class, id);
                ingrediente.getId_ingrediente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ingrediente with id " + id + " no longer exists.", enfe);
            }
            em.remove(ingrediente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    //busca todo los registos de la tabla en la bd
    public List<Ingrediente> buscarIngredienteEntities() {
        return findIngredienteEntities(true, -1, -1);
    }

    public List<Ingrediente> findIngredienteEntities(int maxResults, int firstResult) {
        return findIngredienteEntities(false, maxResults, firstResult);
    }

    private List<Ingrediente> findIngredienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ingrediente.class));
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

    //busca un solo ingrediente por el id 
    public Ingrediente buscarIngrediente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ingrediente.class, id);
        } finally {
            em.close();
        }
    }

    public int getIngredienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ingrediente> rt = cq.from(Ingrediente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
