// Esta clase hereda de la clase padre DAO y vemos que
// se especifica la generalización (<T>) como Mascota
// esto permite que los métodos heredados donde se solicita
// una parámetro T, en esta clase serán de tipo Mascota
package JPA_Ejemplo_2.persistencia;


import JPA_Ejemplo_2.entidades.Mascota;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MascotaDAO {

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

    // este método nos permite persistir un objeto en la base de datos.
    // Toma como parámetro el objeto de tipo Mascota que se quiere persistir
    public void guardar(Mascota mascota) {
        conectar();
        em.getTransaction().begin();
        em.persist(mascota);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite eliminar un registro de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminar(Mascota mascota) {
        conectar();
        em.getTransaction().begin();
        em.remove(mascota);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editar(Mascota mascota) {
        conectar();
        em.getTransaction().begin();
        em.merge(mascota);
        em.getTransaction().commit();
        desconectar();
    }

    public List<Mascota> listarTodos() throws Exception {
        conectar();
        List<Mascota> mascotas = em.createQuery("SELECT m FROM Mascota m ").getResultList();
        desconectar();
        return mascotas;
    }

    public Mascota buscarPorDNI(String dni) throws Exception {
        conectar();
        Mascota mascota = (Mascota) em.createQuery("SELECT m FROM Mascota m WHERE m.dni LIKE :dni").setParameter("dni", dni).getSingleResult();
        desconectar();
        return mascota;
    }
}
