package eggjpa.persistencia;

import eggjpa.entidades.Mascota;
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
