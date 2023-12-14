import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;

public class Schrankwand extends Furniture
{
    private Schrank s1;
    private Schrank s2;
    private Schrank s3;
    
    public Schrankwand (int x, int y, int o) {
        super(x, y, o);
        
        s1 = new Schrank(x, y, o);
        s2 = new Schrank(x, y, o);
        s3 = new Schrank(x, y, o);   
        
        tiefe = s1.getDepth();
        breite = s1.getWidth()*3;
        zeige();
    }
    public GeneralPath gibAktuelleFigur()
    {
        GeneralPath schrankwand = new GeneralPath();
        
        AffineTransform t1 = new AffineTransform();
        t1.translate(0, 0);
        schrankwand.append(t1.createTransformedShape( s1.gibAktuelleFigur() ), false);
        
        AffineTransform t2 = new AffineTransform();
        t2.translate(s2.getWidth(), 0);
        schrankwand.append(t2.createTransformedShape( s2.gibAktuelleFigur() ), false);
        
        AffineTransform t3 = new AffineTransform();
        t3.translate(s2.getWidth()*2, 0);
        schrankwand.append(t3.createTransformedShape( s3.gibAktuelleFigur() ), false);
        
        return schrankwand;
    }
}
