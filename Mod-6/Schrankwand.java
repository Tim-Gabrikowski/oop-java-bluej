import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Schrankwand extends Furniture
{
    private ArrayList<Schrank> boards;
    
    public Schrankwand (int x, int y, int o, int a) {
        super(x, y, o);
        
        boards = new ArrayList<Schrank>();
        
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
        for(Schrank s : boards) {
            AffineTransform t = new AffineTransform();
            t.translate(s.getWidth() * i++, 0);
            schrankwand.append(t.createTransformedShape( s.gibAktuelleFigur() ), false);
        }
    
        return schrankwand;
    }
}
