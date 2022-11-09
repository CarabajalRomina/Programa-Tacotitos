
package tacotitos.logica;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
//libreria asociada al tipo de annotations para mapeo
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//anotations, especifica la creacion de una entidad(para BD)

@Entity (name = "Ingredientes")
public class Ingrediente implements Serializable {
    //atributos
    //mencionamos la primary key de la entidad 
    @Id
    //establece que la ID se va a generar de forma automatica y secuencial
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ingrediente")
    private int id_ingrediente;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio")
    private float precio;
    //anotacion para indicar la relacion
    //relacion de 1 a n 
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo_Ingrediente tipo;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_carga")
    private Date fecha_carga;
    
    //constructor vacio 
    public Ingrediente(){
        
    }
    
    //constructor
    public Ingrediente( String nombre, float precio, Tipo_Ingrediente tipo){
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }
    
    //getter y setter de nombre
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    //getter y setter de precio
    
    public float getPrecio(){
        return precio;
    }
    
    public void setPrecio(float precio){
        this.precio=precio;
    }
    
    //getter y setter de id

    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }
    
    //getter y setter de Fecha_carga
    public Date getFecha_carga() {
        return fecha_carga;
    }

    public void setFecha_carga(Date fecha_carga) {
        this.fecha_carga = fecha_carga;
    }
    
    //getter y setter de o.Tipo
    public Tipo_Ingrediente getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_Ingrediente tipo) {
        this.tipo = tipo;
    }
    
    //toString
    @Override    
    public String toString() {
        return "Ingrediente{" +
                "id_ingrediente=" + id_ingrediente +
                ", nombre=" + nombre + 
                ", precio=" + precio +
                ", fecha_carga=" + fecha_carga + 
                ", tipo=" + tipo + 
                '}';
    }

    //equals y hashcode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id_ingrediente;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        hash = 17 * hash + Float.floatToIntBits(this.precio);
        hash = 17 * hash + Objects.hashCode(this.fecha_carga);
        hash = 17 * hash + Objects.hashCode(this.tipo);
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
        final Ingrediente other = (Ingrediente) obj;
        if (this.id_ingrediente != other.id_ingrediente) {
            return false;
        }
        if (Float.floatToIntBits(this.precio) != Float.floatToIntBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.fecha_carga, other.fecha_carga)) {
            return false;
        }
        return Objects.equals(this.tipo, other.tipo);
    }
    
    
   

}
