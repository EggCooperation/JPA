package eggjpa.persistencia;

import eggjpa.entidades.Persona;
import java.util.List;

public class PersonaDAO extends DAO<Persona> {

    @Override
    public void guardar(Persona persona) {
        super.guardar(persona);
    }

    public void eliminar(String dni) {
        Persona persona = buscarPorDNI(dni);
        super.eliminar(persona);
    }

    public List<Persona> listarTodos() {
        conectar();
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p")
                .getResultList();
        desconectar();
        return personas;
    }

    public Persona buscarPorDNI(String dni) {
        conectar();
        Persona persona = (Persona) em.createQuery("SELECT p FROM Persona p WHERE p.dni LIKE :dni")
                .setParameter("dni", dni).getSingleResult();
        desconectar();
        return persona;
    }

    public Persona buscarPorDNIMascota(String dni) {
        conectar();
        Persona persona = (Persona) em.createQuery("SELECT p FROM Persona p, IN(p.mascotas) m WHERE m.dni LIKE :dni")
                .setParameter("dni", dni).getSingleResult();
        desconectar();
        return persona;
    }

    /**
     * Revisar esta pagina para aprender mas de los JOIN en JPQL
     * https://www.baeldung.com/jpa-join-types
     */
    public List<Persona> buscarPorPaisYProvincia(String pais, String provincia) {
        conectar();
        //Opcion 1 sin JOIN
        //        List<Persona> personas = em.createQuery("SELECT p FROM Persona p WHERE p.direccion.pais LIKE :pais AND p.direccion.provincia LIKE :provincia ")
        //                .setParameter("pais", pais).setParameter("provincia", provincia).getResultList();
        //Opcion 2 con JOIN
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p JOIN p.direccion d WHERE d.pais LIKE :pais AND d.provincia LIKE :provincia ")
                .setParameter("pais", pais).setParameter("provincia", provincia).getResultList();
        desconectar();
        return personas;
    }
}
