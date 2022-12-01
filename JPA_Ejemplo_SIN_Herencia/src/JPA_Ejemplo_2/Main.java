/* en este ejemplo veremos como utilizar una clase DAO padre en la capa de persistencia para reutilizar métodos comunes a 
cada persistencia. De esta manera, no estaremos repitiendo código. */

package JPA_Ejemplo_2;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.ejecucion();
    }
}
