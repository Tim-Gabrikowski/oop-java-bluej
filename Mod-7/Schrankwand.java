import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;
import java.util.*;

public class Schrankwand extends Furniture
{
    private LinkedList<Schrank> boards;
    
    public Schrankwand (int x, int y, int o, int a) {
        super(x, y, o);
        
        boards = new LinkedList<Schrank>();
        
        for(int i = 0; i < a; ++i) {
            boards.add(new Schrank(x, y, o));
        }
        
        tiefe = boards.get(0).getDepth();
        breite = boards.get(0).getWidth() * a;
        zeige();
    }
    public GeneralPath gibAktuelleFigur()
    {
        GeneralPath schrankwand = new GeneralPath();
        
        int i = 0;
        for(Iterator<Schrank> it = boards.iterator(); it.hasNext();) {
            Schrank s = it.next();
            AffineTransform t = new AffineTransform();
            t.translate(s.getWidth() * i++, 0);
            schrankwand.append(t.createTransformedShape( s.gibAktuelleFigur() ), false);
        }
    
        return schrankwand;
    }
    public double cost(double hours) {
        double cost = 0;
        for(Schrank s : boards) {
            cost += s.cost(hours);
        }
        return cost;
    }
}
