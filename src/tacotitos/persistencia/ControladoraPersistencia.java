
package tacotitos.persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tacotitos.logica.Ingrediente;
import tacotitos.logica.Tipo_Ingrediente;
import tacotitos.persistencia.exceptions.NonexistentEntityException;


public class ControladoraPersistencia {
    
    //instacio a los jpacontroller
    Tipo_IngredienteJpaController tipoJpa = new Tipo_IngredienteJpaController();
    IngredienteJpaController ingredienteJpa = new IngredienteJpaController();
    
    //metodo para guardar el objeto de tipo ingrediente en la bd, para persistir el objeto se llama al metodo del jpa controller
    
    public void guardarTipo(Tipo_Ingrediente tipo) {
        //utilizo el jpa controller para crear el tipo de ingrediente en la base de datos 
        tipoJpa.crear(tipo);
    }

    public List<Tipo_Ingrediente> traerTipo_Ingredientes() {
        //este metodo busca todos los registro que tenga la tabla tipo_ingrediente y nos los trae 
        return tipoJpa.buscarTipo_IngredienteEntities();
    }

    public void borrarTipo(int id_tipo) {
        // en caso que no pueda borrar el objeto con ese id se produce una exepcion
        try {
            tipoJpa.eliminar(id_tipo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tipo_Ingrediente traerTipo_Ingrediente(int id) {
        return tipoJpa.buscarTipo_Ingrediente(id);    
    }

    public void modificarTipo_ingrediente(Tipo_Ingrediente tipo) {
       // si no encuentra el objeto, muestra una exepcion 
        try {
            tipoJpa.editar(tipo);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    //metodos de ingrediente
    
    public void guardarIngrediente(Ingrediente ingre) {
        ingredienteJpa.crear(ingre);
    }

    public List<Ingrediente> traerIngredientes() {
     return ingredienteJpa.buscarIngredienteEntities();
    }

    public void borrarIngrediente(int id_ingre) {
        try {
            ingredienteJpa.eliminar(id_ingre);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Ingrediente traerIngrediente(int id) {
        return ingredienteJpa.buscarIngrediente(id);
    }

    public void modificarIngrediente(Ingrediente ingre) {
        try {
            ingredienteJpa.editar(ingre);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

 
      
}
