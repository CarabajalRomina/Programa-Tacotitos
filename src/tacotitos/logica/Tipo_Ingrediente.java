
package tacotitos.logica;
//anotacion para que me cree una tabla en la base de datos

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name = "Tipo_ingredientes")
public class Tipo_Ingrediente implements Serializable {
    //atributos
    //mencionamos la primary key de la entidad 
    @Id
    //establece que la ID se va a generar de forma automatica
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipo_ingrediente")
    private int id_tipo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantMax")
    private int cantMax;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_carga")
    private Date fecha_carga;
    
    // constructor vacio
    public Tipo_Ingrediente(){    
    }
    
    
   //constructor 
    public Tipo_Ingrediente( String descripcion, int cantMax){
         this.descripcion = descripcion;
         this.cantMax = cantMax;
    }
    
    //getter y setter de id_tipo_de_ingrediente

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }
    public Date getFecha_carga(){
        return fecha_carga;
    }

    //getter y setter de fecha_carga
    public void setFecha_carga(Date fecha_carga) {
        this.fecha_carga = fecha_carga;
    }

    //getter y setter de cant max
    public int getCantMax() {
        return cantMax;
    }
    
    public void setCantMax(int cantMaxima){
        this.cantMax=cantMaxima;
    }
    
    //getter y setter de descripcion
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    
   //toString

    @Override
    public String toString() {
        return "Tipo_Ingrediente{" +
                "id_tipo=" + id_tipo + ","
                + " descripcion=" + descripcion + 
                ", cantMax=" + cantMax + 
                ", fecha_carga=" + fecha_carga + '}';
    }
    
    
 // equal y hashCode

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id_tipo;
        hash = 23 * hash + Objects.hashCode(this.descripcion);
        hash = 23 * hash + this.cantMax;
        hash = 23 * hash + Objects.hashCode(this.fecha_carga);
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
        final Tipo_Ingrediente other = (Tipo_Ingrediente) obj;
        if (this.id_tipo != other.id_tipo) {
            return false;
        }
        if (this.cantMax != other.cantMax) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return Objects.equals(this.fecha_carga, other.fecha_carga);
    }
    

    
}