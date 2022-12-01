package JPA_Ejemplo_1.servicios;

import JPA_Ejemplo_1.entidades.Mascota;
import JPA_Ejemplo_1.persistencia.MascotaDAO;
import java.util.List;

public class MascotaServicio {

    private DireccionServicio direccionServicio;
    private PersonaServicio personaServicio;
    private final MascotaDAO DAO;

    public MascotaServicio() {
        this.DAO = new MascotaDAO();
    }

    public void setServicios(DireccionServicio direccionServicio, PersonaServicio personaServicio) {
        this.direccionServicio = direccionServicio;
        this.personaServicio = personaServicio;
    }

    // este método invoca el método guardar de la clase DAO padre para 
    // persistir un objeto Mascota
    public Mascota crearMascota(String nombre, String dni) {
        Mascota mascota = new Mascota();
        try {
            mascota.setNombre(nombre);
            mascota.setDni(dni);
            DAO.guardar(mascota);
            return mascota;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Mascota buscarPorDni(String dni) {
        try {
            return DAO.buscarPorDNI(dni);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorDni(String dni) {
        try {
            DAO.eliminar(dni);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Mascota> listarMascotas() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
