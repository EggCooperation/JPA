package eggjpa.persistencia;

import eggjpa.entidades.Mascota;
import java.util.List;

public class MascotaDAO extends DAO<Mascota> {

    public void guardarMascota(Mascota mascota) throws Exception {
        guardar(mascota);
    }

    public void eliminarMascota(String dni) throws Exception {
        Mascota mascota = buscarPorDNI(dni);
        eliminar(mascota);
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
