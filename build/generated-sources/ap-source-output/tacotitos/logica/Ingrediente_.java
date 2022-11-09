package tacotitos.logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tacotitos.logica.Tipo_Ingrediente;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-11-08T01:35:34")
@StaticMetamodel(Ingrediente.class)
public class Ingrediente_ { 

    public static volatile SingularAttribute<Ingrediente, Float> precio;
    public static volatile SingularAttribute<Ingrediente, Tipo_Ingrediente> tipo;
    public static volatile SingularAttribute<Ingrediente, String> nombre;
    public static volatile SingularAttribute<Ingrediente, Integer> id_ingrediente;
    public static volatile SingularAttribute<Ingrediente, Date> fecha_carga;

}