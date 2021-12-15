package eggjpa.servicios;

import eggjpa.entidades.Direccion;
import eggjpa.persistencia.DireccionDAO;

public class DireccionServicio {

    private DireccionDAO direccionDAO;
    
    public DireccionServicio(){
        this.direccionDAO = new DireccionDAO();
    }
    
    public Direccion crearDireccion(String pais, String provincia) {
        Direccion direccion = new Direccion();
        try {
            direccion.setPais(pais);
            direccion.setProvincia(provincia);
            direccionDAO.guardarDireccion(direccion);
            return direccion;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } 
    }

}
