package eggjpa.servicios;

import eggjpa.entidades.Direccion;
import eggjpa.persistencia.DireccionDAO;

public class DireccionServicio {

    private MascotaServicio mascotaServicio;
    private PersonaServicio personaServicio;
    private final DireccionDAO DAO;

    public DireccionServicio() {
        this.DAO = new DireccionDAO();
    }

    public void setServicios(MascotaServicio mascotaServicio, PersonaServicio personaServicio) {
        this.mascotaServicio = mascotaServicio;
        this.personaServicio = personaServicio;
    }

    public Direccion crearDireccion(String pais, String provincia) {
        Direccion direccion = new Direccion();
        try {
            direccion.setPais(pais);
            direccion.setProvincia(provincia);
            DAO.guardar(direccion);
            return direccion;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
