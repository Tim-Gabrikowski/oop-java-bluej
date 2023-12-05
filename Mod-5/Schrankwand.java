import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;

public class Schrankwand extends Furniture
{
    public Schrankwand (int x, int y, int o) {
        super(x, y, o);
        breite = 300;
        tiefe = 60;
    }
    protected GeneralPath gibAktuelleFigur()
    {
        GeneralPath schrank = new GeneralPath();
        
        schrank.append(new Rectangle2D.Double(0, 0, breite/3, tiefe), false);
        schrank.append(new Line2D.Double(0, 0, breite/3, tiefe), false);
        schrank.append(new Line2D.Double(0, tiefe, breite/3, 0), false);
        
        schrank.append(new Rectangle2D.Double(breite/3, 0, breite/3, tiefe), false);
        schrank.append(new Line2D.Double(breite/3, 0, (breite*2)/3, tiefe), false);
        schrank.append(new Line2D.Double(breite/3, tiefe, (breite*2)/3, 0), false);
        
        schrank.append(new Rectangle2D.Double((breite*2)/3, 0, breite/3, tiefe), false);
        schrank.append(new Line2D.Double((breite*2)/3, 0, breite, tiefe), false);
        schrank.append(new Line2D.Double((breite*2)/3, tiefe, breite, 0), false);
        return schrank;
    }
}
