
package tacotitos.logica;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Pedido {
    private List<Taco> totalTacos;
    @Temporal(TemporalType.DATE)
    private Date fecha_carga;

    //constructor
    public Pedido(List<Taco> totalTacos) {
        this.totalTacos = totalTacos;
    }
    //getters y setters
    
    public List<Taco> getTotalTacos() {
        return totalTacos;
    }

    public void setTotalTacos(List<Taco> totalTacos) {
        this.totalTacos = totalTacos;
    }
    
    //toString

    @Override
    public String toString() {
        return "Pedido{" + "totalTacos=" + totalTacos + '}';
    }
    
    
    //hashcode y equals
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.totalTacos);
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
        final Pedido other = (Pedido) obj;
        return Objects.equals(this.totalTacos, other.totalTacos);
    }
    
    
    
    
    
    
    
}
