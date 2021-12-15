package eggjpa.persistencia;

import eggjpa.entidades.Direccion;
import java.util.List;

public class DireccionDAO extends DAO<Direccion> {

    public void guardarDireccion(Direccion direccion) throws Exception {
        guardar(direccion);
    }
    
    public void eliminar(String id) throws Exception {
        Direccion direccion = buscarPorId(id);
        eliminar(direccion);
    }

    public Direccion buscarPorId(String id) throws Exception {
        Direccion direccion = em.find(Direccion.class, id);
        return direccion;
    }

    public List<Direccion> listarTodos() throws Exception {
        List<Direccion> direcciones = em.createQuery("SELECT d FROM Direccion d")
                .getResultList();
        return direcciones;
    }
}
