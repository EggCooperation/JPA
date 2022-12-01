// Esta entidad Mascota contiene 3 atributos: un id o llave primaria, un nombre
// y un dni que es llave foránea y vincula a un objeto Mascota con un determinado
// objeto Persona. En este objeto se almacenará la información de respectiva
// a una Mascota

package JPA_Ejemplo_1.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(generator = "uuid")
    private String id;

    private String nombre;

    @Column(unique = true)
    private String dni;

    public Mascota(String id, String nombre, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
    }

    public Mascota() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Mascota{" + "id=" + id + ", nombre=" + nombre + ", dni=" + dni + '}';
    }

}
