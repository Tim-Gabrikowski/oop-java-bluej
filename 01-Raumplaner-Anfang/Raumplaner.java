
/**
 * The main class of the project
 *
 * @author Tim Gabrikowski
 * @version 05.09.2023
 */
public class Raumplaner
{
    public static void main(String[] args) {
        Stuhl c1 = new Stuhl();
        Stuhl c2 = new Stuhl();
        Stuhl c3 = new Stuhl();
        Tisch t1 = new Tisch();
        
        c1.zeige();
        c2.zeige();
        c3.zeige();
        t1.zeige();
    }
}
