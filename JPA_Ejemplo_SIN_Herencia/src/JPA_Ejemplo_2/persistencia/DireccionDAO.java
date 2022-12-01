// Esta clase se encarga de la persistencia de los registros
// de tipo Direccion
package JPA_Ejemplo_2.persistencia;

import JPA_Ejemplo_2.entidades.Direccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DireccionDAO {

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
    // Toma como parámetro el objeto de tipo Direccion que se quiere persistir
    public void guardar(Direccion direccion){
        conectar();
        em.getTransaction().begin();
        em.persist(direccion);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite eliminar un registro de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminar(Direccion direccion){
        conectar();
        em.getTransaction().begin();
        em.remove(direccion);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editar(Direccion direccion){
        conectar();
        em.getTransaction().begin();
        em.merge(direccion);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Direccion buscarPorId(String id) throws Exception {
        conectar();
        Direccion direccion = em.find(Direccion.class, id);
        desconectar();
        return direccion;
    }

    public List<Direccion> listarTodos() throws Exception {
        conectar();
        List<Direccion> direcciones = em.createQuery("SELECT d FROM Direccion d")
                .getResultList();
        desconectar();
        return direcciones;
    }
}
