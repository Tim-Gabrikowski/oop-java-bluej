
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
        c1 = new Stuhl();
        c2 = new Stuhl();
        c3 = new Stuhl();
        c4 = new Stuhl();
        t1 = new Tisch();
    }
}