// en este ejemplo veremos como utilizar una clase DAO padre
// en la capa de persistencia para reutilizar métodos comunes a 
// cada persistencia. De esta manera, no estaremos repitiendo código.

package JPA_Ejemplo_1;

public class EggJPA {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.ejecucion();
    }
}
