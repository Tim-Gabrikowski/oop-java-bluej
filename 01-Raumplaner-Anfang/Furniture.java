import java.awt.Shape;
/**
 * Write a description of class Furniture here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Furniture
{
    // instance variables - replace the example below with your own
    private int xPosition;
    private int yPosition;
    private int xPosG;
    private int yPosG;
    private int orientierung;
    private String farbe;
    private boolean istSichtbar;
    private int breite;
    private int tiefe;

    protected abstract Shape gibAktuelleFigur();
    
    
}
