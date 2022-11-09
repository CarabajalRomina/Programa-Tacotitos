
package tacotitos.logica;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class Taco {
    
    private List<Ingrediente> ingredientes;

    //constructor
    public Taco(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    //getters y setters
    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    //toString
    @Override    
    public String toString() {
        return "Taco{" + "ingredientes=" + ingredientes + '}';
    }

    //hashcode y equals
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.ingredientes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Taco other = (Taco) obj;
        return Objects.equals(this.ingredientes, other.ingredientes);
    }
    
    
    
    
    
    
    
    
}
