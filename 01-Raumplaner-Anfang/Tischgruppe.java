
/**
 * A collection of a Table and 4 Chairs
 * 
 * @author Tim Gabrikowski
 * @version 05.09.2023
 */
public class Tischgruppe
{
    // instance variables - replace the example below with your own
    private Stuhl c1;
    private Stuhl c2;
    private Stuhl c3;
    private Stuhl c4;
    private Tisch t1;
    
    /**
     * Constructor for objects of class Tischgruppe
     */
    public Tischgruppe()
    {
        // initialise instance variables
        c1 = new Stuhl(50, 130, -90);
        c2 = new Stuhl(140, 50, 0);
        c3 = new Stuhl(230, 130, 90);
        c4 = new Stuhl(140, 210, 180);
        t1 = new Tisch(100, 100, 0);
        
        this.show();
    }
    
    public void show(){
        c1.zeige();
        c2.zeige();
        c3.zeige();
        c4.zeige();
        t1.zeige();
    }
}