package eggjpa;

import eggjpa.entidades.Direccion;
import eggjpa.entidades.Mascota;
import eggjpa.entidades.Persona;
import eggjpa.enums.Rol;
import eggjpa.servicios.DireccionServicio;
import eggjpa.servicios.MascotaServicio;
import eggjpa.servicios.PersonaServicio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    private final MascotaServicio mascotaServicio = new MascotaServicio();
    private final DireccionServicio direccionServicio = new DireccionServicio();
    private final PersonaServicio personaServicio = new PersonaServicio();

    public Menu() {
        mascotaServicio.setServicios(direccionServicio, personaServicio);
        direccionServicio.setServicios(mascotaServicio, personaServicio);
        personaServicio.setServicios(direccionServicio, mascotaServicio);
    }

    public void ejecucion() {

        String pais1 = "Argentina";
        String provincia1 = "Mendoza";

        String pais2 = "Chile";
        String provincia2 = "STG";

        String nombreMascota1 = "Chiquito";
        String dniMascota1 = "35";

        String nombreMascota2 = "Magu";
        String dniMascota2 = "36";

        String nombreMascota3 = "Cartucho";
        String dniMascota3 = "37";

        String nombrePersona1 = "Agustin";
        String apellidoPersona1 = "Fiorde";
        String dniPersona1 = "366";
        Rol rolUser = Rol.USER;

        String nombrePersona2 = "Valen";
        String apellidoPersona2 = "Holy";
        String dniPersona2 = "377";
        Rol rolAdmin = Rol.ADMIN;
        Date nacimiento = null;

        try {
            String inputDate = "07/28/2011 11:06:37 AM";
            nacimiento = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").parse(inputDate);
        } catch (ParseException ex) {
            Logger.getLogger(EggJPA.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        primero creo
         */
        Direccion d1 = direccionServicio.crearDireccion(pais1, provincia1);
        Direccion d2 = direccionServicio.crearDireccion(pais2, provincia2);

        Mascota m1 = mascotaServicio.crearMascota(nombreMascota1, dniMascota1);
        Mascota m2 = mascotaServicio.crearMascota(nombreMascota2, dniMascota2);
        Mascota m3 = mascotaServicio.crearMascota(nombreMascota3, dniMascota3);

        List<Mascota> mascotas1 = new ArrayList<>();
        mascotas1.add(m1);
        mascotas1.add(m2);
        List<Mascota> mascotas2 = new ArrayList<>();
        mascotas2.add(m3);

        Persona p1 = personaServicio.crearPersona(nombrePersona1, apellidoPersona1, dniPersona1, nacimiento, rolUser, d1, mascotas1);
        Persona p2 = personaServicio.crearPersona(nombrePersona2, apellidoPersona2, dniPersona2, nacimiento, rolAdmin, d2, mascotas2);

        /*
        despues muestro individual y todos
         */
        personaServicio.listarPersonas().forEach((a) -> System.out.println(a.toString()));
        mascotaServicio.listarMascotas().forEach((a) -> System.out.println(a.toString()));

        /*
        despues elimino
         */
        personaServicio.eliminarPorDni(dniPersona1);

        /*
        despues muestro muestro individual y todos
         */
        System.out.println("Las personas que quedaron en la base de datos son:");
        personaServicio.listarPersonas().forEach((a) -> System.out.println(a.toString()));
        
        /*
        despues muestro muestro individual y todos
         */
        System.out.println("Las personas de Santiago de Chile son:");
        System.out.println(personaServicio.buscarPorPaisYProvincia("Chile", "STG"));
    }

}
