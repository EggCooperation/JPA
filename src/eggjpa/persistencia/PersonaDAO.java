package eggjpa.persistencia;

import eggjpa.entidades.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonaDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarPersona(Persona persona) throws Exception {
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
    }

    public void eliminar(String dni) {
        Persona persona = buscarPorDNI(dni);
        em.getTransaction().begin();
        em.remove(persona);
        em.getTransaction().commit();
    }

    public List<Persona> listarTodos() {
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p")
                .getResultList();
        return personas;
    }

    public Persona buscarPorDNI(String dni) {
        Persona persona = (Persona) em.createQuery("SELECT p FROM Persona p WHERE p.dni LIKE :dni")
                .setParameter("dni", dni).getSingleResult();
        return persona;
    }

    public Persona buscarPorDNIMascota(String dni) {
        Persona persona = (Persona) em.createQuery("SELECT p FROM Persona p, IN(p.mascotas) m WHERE m.dni LIKE :dni")
                .setParameter("dni", dni).getSingleResult();
        return persona;
    }

    /**
     * Revisar esta pagina para aprender mas de los JOIN en JPQL
     * https://www.baeldung.com/jpa-join-types
     */
    public List<Persona> buscarPorPaisYProvincia(String pais, String provincia) {
        //Opcion 1 sin JOIN
        //        List<Persona> personas = em.createQuery("SELECT p FROM Persona p WHERE p.direccion.pais LIKE :pais AND p.direccion.provincia LIKE :provincia ")
        //                .setParameter("pais", pais).setParameter("provincia", provincia).getResultList();
        //Opcion 2 con JOIN
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p JOIN p.direccion d WHERE d.pais LIKE :pais AND d.provincia LIKE :provincia ")
                .setParameter("pais", pais).setParameter("provincia", provincia).getResultList();
        return personas;
    }
}
