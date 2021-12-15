package eggjpa.servicios;

import eggjpa.entidades.Direccion;
import eggjpa.entidades.Mascota;
import eggjpa.entidades.Persona;
import eggjpa.enums.Rol;
import eggjpa.persistencia.PersonaDAO;
import java.util.Date;
import java.util.List;

public class PersonaServicio {

    private PersonaDAO personaDAO;
    
    public PersonaServicio(){
        this.personaDAO = new PersonaDAO(); 
    }

    public Persona crearPersona(String nombre, String apellido, String dni, Date nacimiento, Rol rol, Direccion direccion, List<Mascota> mascotas) {

        Persona persona = new Persona();
        try {
            persona.setApellido(apellido);
            persona.setNombre(nombre);
            persona.setDni(dni);
            persona.setNacimiento(nacimiento);
            persona.setRol(rol);
            persona.setMascotas(mascotas);
            persona.setDireccion(direccion);

            personaDAO.guardarPersona(persona);
            return persona;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Persona buscarPorDni(String dni) {
        try {
            return personaDAO.buscarPorDNI(dni);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorDni(String dni) {
        try {
            personaDAO.eliminar(dni);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Persona> listarPersonas() {
        try {
            return personaDAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<Persona> buscarPorPaisYProvincia(String pais, String provincia) {
        try {
            return personaDAO.buscarPorPaisYProvincia(pais, provincia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
