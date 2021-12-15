package eggjpa.entidades;

import eggjpa.entidades.Direccion;
import eggjpa.entidades.Mascota;
import eggjpa.enums.Rol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-09T14:46:37")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellido;
    public static volatile SingularAttribute<Persona, Direccion> direccion;
    public static volatile ListAttribute<Persona, Mascota> mascotas;
    public static volatile SingularAttribute<Persona, String> id;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, String> dni;
    public static volatile SingularAttribute<Persona, Rol> rol;
    public static volatile SingularAttribute<Persona, Date> nacimiento;

}