// Esta clase hereda de la clase padre DAO y vemos que
// se especifica la generalización (<T>) como Persona
// esto permite que los métodos heredados donde se solicita
// una parámetro T, en esta clase serán de tipo Persona
package JPA_Ejemplo_2.persistencia;

import JPA_Ejemplo_2.entidades.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonaDAO {

    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("JPAPU");
    private EntityManager em = EMF.createEntityManager();

    // Este método nos permite conectar con la base de datos
    // se verifica si la conexión está realizada, en caso que
    // no esté realizada, se realiza.
    public void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    // Este método nos permite desconectarnos de la base de datos
    // Se verifica si existe una conexión, y de ser el caso, se
    // cierra la conexión y se desconecta el programa con la base de datos.
    public void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }

    // este método nos permite persistir un objeto Persona en la base de datos.
    // Toma como parámetro el objeto de tipo Direccion que se quiere persistir
    public void guardar(Persona persona) {
        conectar();
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite eliminar un registro de tipo Persona de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminar(Persona persona) {
        conectar();
        em.getTransaction().begin();
        em.remove(persona);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editar(Persona persona) {
        conectar();
        em.getTransaction().begin();
        em.merge(persona);
        em.getTransaction().commit();
        desconectar();
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
     * Revisar esta pagina para aprender mas de los JOIN en JPQL https://www.baeldung.com/jpa-join-types
     * @param pais
     * @param provincia
     * @return 
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
