import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;


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
        breite = 120;
        tiefe = 80;
    }
    protected Shape gibAktuelleFigur()
    {
        
        GeneralPath stuhl = new GeneralPath();
        stuhl.moveTo(0 , 0);
        stuhl.lineTo(breite, 0);
        stuhl.lineTo(breite+(breite/20+1), tiefe);
        stuhl.lineTo(-(breite/20+1), tiefe);
        stuhl.lineTo(0 , 0);
        
        stuhl.moveTo(0 , (breite/10+1));
        stuhl.lineTo(breite, (breite/10+1));    
        
        AffineTransform t = new AffineTransform();
        t.translate(xPosition, yPosition);
        Rectangle2D umriss = stuhl.getBounds2D();
        t.rotate(Math.toRadians(rotation),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(stuhl);
    }
}