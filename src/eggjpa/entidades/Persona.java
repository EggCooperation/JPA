package eggjpa.entidades;

import eggjpa.enums.Rol;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Persona {

    @Id
    @GeneratedValue(generator = "uuid")
    private String id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String dni;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Mascota> mascotas;

    @OneToOne
    private Direccion direccion;

    @Temporal(TemporalType.DATE)
    private Date nacimiento;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Persona(String id, String nombre, String apellido, String dni, List<Mascota> mascotas, Direccion direccion, Date nacimiento, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.mascotas = mascotas;
        this.direccion = direccion;
        this.nacimiento = nacimiento;
        this.rol = rol;
    }

    public Persona() {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", mascotas=" + mascotas + ", direccion=" + direccion + ", nacimiento=" + nacimiento + ", rol=" + rol + '}';
    }

}
