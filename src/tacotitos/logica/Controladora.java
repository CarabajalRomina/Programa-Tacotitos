
package tacotitos.logica;

import java.util.List;
import tacotitos.persistencia.ControladoraPersistencia;

public class Controladora {
    //instancio la controladora de la persistencia
    //cuando nosotros referenciamos a controlPersis  va a crear un obj de controladora de persistencia para que
    //nosotros podamos llamar a cada uno de los metodos que nosotros declaremos en controladora de persistencia que al mismo
    //tiempo va a llamar a los metodos de los jpa controller 
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    //metodo guardar que recibe por parametro los datos que se envian en el jform de tipo ingrediente, pasamos los datos de la ui a la logica
    //logica recibio los datos de la ui, creo los objetos y le asigno lo valores
    
    public void guardarTipo(String txtNombre, int spnCantMax) {
       //creo la instancia de tipo ingrediente y le asigno los valores
       Tipo_Ingrediente tipo = new Tipo_Ingrediente();
       tipo.setDescripcion(txtNombre);
       tipo.setCantMax(spnCantMax);
       
       //guardo los objetos en la base de datos, a traves de la controladora de persistencia que es la encargada
       //de crear a tipo de ingredientes en la bd y asosiarlos
       
       controlPersis.guardarTipo(tipo);
    }
    
    //metodo para traer todos los registros que tengo en la tabla
    public List<Tipo_Ingrediente> traerTipo_Ingredientes() {
        //le pido a la controladora de persistencia que me pase la lista de tipos de ingrediente
        return controlPersis.traerTipo_Ingredientes();
    }

    public void borrarTipo(int id_tipo) {
        //llamo a la controladora de la persistencia 
        controlPersis.borrarTipo(id_tipo);
    }

    public Tipo_Ingrediente traerTipo_ingrediente(int id) {
        return controlPersis.traerTipo_Ingrediente(id);
    }

    public void modificarTipo_ingrediente(Tipo_Ingrediente tipo, String descripcion, int cantMax) {
      //la tarea de la logica es setearle los nuevos valores al objeto 
      tipo.setDescripcion(descripcion);
      tipo.setCantMax(cantMax);
      
      //llamo a la controladora de persistencia y le paso el objeto con los nuevos valores seteados 
      controlPersis.modificarTipo_ingrediente(tipo);
    }

    
    
    //metodos de ingrediente
    
    public void guardarIngrediente(String nombre, Float precio, int id) {
        
        Tipo_Ingrediente tipo = controlPersis.traerTipo_Ingrediente(id);
        Ingrediente ingre = new Ingrediente(nombre,precio,tipo);
        controlPersis.guardarIngrediente(ingre);
        
    }

    public List<Ingrediente> traerIngredientes() {
       return controlPersis.traerIngredientes();
    }

    public void borrarIngrediente(int id_ingre) {
       controlPersis.borrarIngrediente(id_ingre);
    }

    public Ingrediente traerIngrediente(int id) {
       return controlPersis.traerIngrediente(id);
    }

    public void modificarIngrediente(Ingrediente ingre, String nombre, Float precio, int id) {
      //traigo el tipo de ingrediente segun el id
      Tipo_Ingrediente tipo = controlPersis.traerTipo_Ingrediente(id);
      //la tarea de la logica es setearle los nuevos valores al objeto 
      ingre.setNombre(nombre);
      ingre.setPrecio(precio);
      ingre.setTipo(tipo);
      
      //llamo a la controladora de persistencia y le paso el objeto con los nuevos valores seteados 
      controlPersis.modificarIngrediente(ingre);
    
    }
    
  

    
    
}
