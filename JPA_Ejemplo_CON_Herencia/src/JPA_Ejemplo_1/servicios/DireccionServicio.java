package JPA_Ejemplo_1.servicios;

import JPA_Ejemplo_1.entidades.Direccion;
import JPA_Ejemplo_1.persistencia.DireccionDAO;

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

    // este método recibe dos parámetros: pais y provincia y los utiliza
    //  para inicializar un objeto Direccion con estos valores. Luego se
    //  invoca al método guardar de la clase DAO padre y se pasa el objeto 
    //  a persistir.
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
