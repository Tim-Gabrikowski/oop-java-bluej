import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

/**
 * Ein Tisch, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 */
public class Tisch extends Furniture
{
    public Tisch (int x, int y, int o) {
        super(x, y, o);
        breite = 120;
        tiefe = 80;
        cost = 199.99;
        costHour = 20;
    }
    public GeneralPath gibAktuelleFigur()
    {
        GeneralPath tisch = new GeneralPath();
        tisch.append(new Ellipse2D.Double(0 , 0, breite, tiefe), false);
        return tisch;
    }
    public double cost(double hours) {
        return cost + (hours * costHour);
    }
}
