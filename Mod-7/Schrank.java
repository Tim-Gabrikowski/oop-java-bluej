import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;

public class Schrank extends Furniture
{
    public Schrank (int x, int y, int o) {
        super(x, y, o);
        breite = 100;
        tiefe = 60;
    }
    public GeneralPath gibAktuelleFigur()
    {
        GeneralPath schrank = new GeneralPath();
        schrank.append(new Rectangle2D.Double(0, 0, breite, tiefe), false);
        schrank.append(new Line2D.Double(0 , 0, breite, tiefe), false);
        schrank.append(new Line2D.Double(0 , tiefe, breite, 0), false);
        return schrank;
    }
}
