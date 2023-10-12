/**
 * The main class of the project
 *
 * @author Tim Gabrikowski
 * @version 05.09.2023
 */
public class Raumplaner
{
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Erstelle leinwand");
        Leinwand canvas = Leinwand.gibLeinwand();
        
        System.out.println("Erstelle Stuhl an G4|4");
        Stuhl s1 = new Stuhl(1, 1);
        
        System.out.println("Zeige Stuhl");
        s1.zeige();
        
        s1.move(3, 3, true);
        s1.inspect();
        
        s1.move(-5, -4, true);
        s1.inspect();        
    }
}
