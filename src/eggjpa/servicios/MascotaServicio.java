package eggjpa.servicios;

import eggjpa.entidades.Mascota;
import eggjpa.persistencia.MascotaDAO;
import java.util.List;

public class MascotaServicio {

    private MascotaDAO mascotaDAO = new MascotaDAO();
    
    public MascotaServicio(){
        this.mascotaDAO = new MascotaDAO(); 
    }

    public Mascota crearMascota(String nombre, String dni) {
        Mascota mascota = new Mascota();
        try {
            mascota.setNombre(nombre);
            mascota.setDni(dni);
            mascotaDAO.guardarMascota(mascota);
            return mascota;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } 
    }

    public Mascota buscarPorDni(String dni) {
        try {
            return mascotaDAO.buscarPorDNI(dni);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorDni(String dni) {
        try {
            mascotaDAO.eliminarMascota(dni);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Mascota> listarMascotas() {
        try {
            return mascotaDAO.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
