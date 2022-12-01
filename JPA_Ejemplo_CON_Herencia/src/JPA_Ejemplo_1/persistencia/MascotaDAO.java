// Esta clase hereda de la clase padre DAO y vemos que
// se especifica la generalización (<T>) como Mascota
// esto permite que los métodos heredados donde se solicita
// una parámetro T, en esta clase serán de tipo Mascota

package JPA_Ejemplo_1.persistencia;

import JPA_Ejemplo_1.entidades.Mascota;
import java.util.List;

public class MascotaDAO extends DAO<Mascota> {

    @Override
    public void guardar(Mascota mascota) {
        super.guardar(mascota);
    }

    public void eliminar(String dni) throws Exception {
        Mascota mascota = buscarPorDNI(dni);
        super.eliminar(mascota);
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
