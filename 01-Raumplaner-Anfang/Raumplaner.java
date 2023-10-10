
/**
 * The main class of the project
 *
 * @author Tim Gabrikowski
 * @version 05.09.2023
 */
public class Raumplaner
{
    public static void main(String[] args) {
        System.out.println("Erstelle leinwand");
        Leinwand canvas = Leinwand.gibLeinwand();
        
        System.out.println("Erstelle Stuhl an G4|4");
        Stuhl s1 = new Stuhl(4, 4);
        
        System.out.println("Zeige Stuhl");
        s1.zeige();
        
        System.out.println("Bewege Stuhl um G3|3 (ePos: G7|7)");
        s1.move(3, 3);
        s1.inspect();
        
        System.out.println("Bewege Stuhl um G1|1 (ePos: G8|8)");
        s1.move(1, 1);
        s1.inspect();
        
        System.out.println("Bewege Stuhl um G1|1 (ePos: G8|8)");
        s1.move(1, 1);
        s1.inspect();
        
    }
}
