package JPA_Ejemplo_1;

import JPA_Ejemplo_1.entidades.Direccion;
import JPA_Ejemplo_1.entidades.Mascota;
import JPA_Ejemplo_1.entidades.Persona;
import JPA_Ejemplo_1.enums.Rol;
import JPA_Ejemplo_1.servicios.DireccionServicio;
import JPA_Ejemplo_1.servicios.MascotaServicio;
import JPA_Ejemplo_1.servicios.PersonaServicio;
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

    // este método instancia 2 objetos Direccion, 2 objetos Persona
    // y 3 objetos Mascota. Almacena los objetos Mascotas en dos listas llamada
    // distintas llamadas mascotas1 y mascotas2
    public void ejecucion() {

        // Declaración y asignación de valores a las variables requeridas
        // para instanciar los objetos necesarios.
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

        // Creación y asignación de dos objetos Direccion
        Direccion d1 = direccionServicio.crearDireccion(pais1, provincia1);
        Direccion d2 = direccionServicio.crearDireccion(pais2, provincia2);

        // Creación y asignación de tres objetos Mascota
        Mascota m1 = mascotaServicio.crearMascota(nombreMascota1, dniMascota1);
        Mascota m2 = mascotaServicio.crearMascota(nombreMascota2, dniMascota2);
        Mascota m3 = mascotaServicio.crearMascota(nombreMascota3, dniMascota3);

        // Almacenamiento de las mascotas creadas en dos listas
        List<Mascota> mascotas1 = new ArrayList<>();
        mascotas1.add(m1);
        mascotas1.add(m2);
        List<Mascota> mascotas2 = new ArrayList<>();
        mascotas2.add(m3);

        // Creación y asignación de dos objetos Personas
        Persona p1 = personaServicio.crearPersona(nombrePersona1, apellidoPersona1, dniPersona1, nacimiento, rolUser, d1, mascotas1);
        Persona p2 = personaServicio.crearPersona(nombrePersona2, apellidoPersona2, dniPersona2, nacimiento, rolAdmin, d2, mascotas2);

        // Utilizando un foreach lambda que es simplemente una forma más acortada
        // se iteran las personas y mascotas cargadas y se imprimen por consola.
        personaServicio.listarPersonas().forEach((a) -> System.out.println(a.toString()));
        mascotaServicio.listarMascotas().forEach((a) -> System.out.println(a.toString()));

        // Se hace uso del método 'eliminarPorDNI', eliminando a una persona de 
        personaServicio.eliminarPorDni(dniPersona1);

        // Imprimo los valores de las listas disponibles luego de la eliminación
        System.out.println("Las personas que quedaron en la base de datos son:");
        personaServicio.listarPersonas().forEach((a) -> System.out.println(a.toString()));
        
        // Haciendo uso del método buscarPorPaisYProvincia se muestra por patnalla
        // que personas ingresadas son de Santiago de Chile, Chile.
        System.out.println("Las personas de Santiago de Chile son:");
        System.out.println(personaServicio.buscarPorPaisYProvincia("Chile", "STG"));
    }

}
