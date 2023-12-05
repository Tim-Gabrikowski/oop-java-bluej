import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;


/**
 * Ein Stuhl, einfach nur ein Stuhl, kein Sessel, kein Sofa. Ein Stuhl! Kein Schemel, ein Stuhl!!!
 * 
 * @author Tim Gabrikowski
 * @version 1 (23.11.2023)
 * Tim was here
 */
public class Stuhl extends Furniture
{
    public Stuhl (int x, int y, int o) {
        super(x, y, o);
        breite = 60;
        tiefe = 60;
    }
    public GeneralPath gibAktuelleFigur()
    {
        
        GeneralPath stuhl = new GeneralPath();
        stuhl.moveTo(0 , 0);
        stuhl.lineTo(breite, 0);
        stuhl.lineTo(breite+(breite/20+1), tiefe);
        stuhl.lineTo(-(breite/20+1), tiefe);
        stuhl.lineTo(0 , 0);
        
        stuhl.moveTo(0 , (breite/10+1));
        stuhl.lineTo(breite, (breite/10+1));    
        
        return stuhl;
    }
}