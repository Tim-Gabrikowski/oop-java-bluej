import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.AffineTransform;

/**
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 * Tim was here
 * And here
 */
public class Waschmaschine
{
    private int xPosition;
    private int yPosition;
    private int xPosG;
    private int yPosG;
    private int orientierung;
    private String farbe;
    private boolean istSichtbar;
    private int breite;
    private int tiefe;


    public Waschmaschine(int gx, int gy, int o){
        xPosition = Grid.getX(gx);
        yPosition = Grid.getY(gy);
        xPosG = gx;
        yPosG = gy;
        farbe = "gruen";
        orientierung = o;
        istSichtbar = false;
        breite = 60;
        tiefe  = 60;        
    }
    
    public void inspect() {
        System.out.println("waschmaschine bei G" + xPosG + "|" + yPosG + " P" + xPosition + "|" + yPosition);
    }

    public void zeige() {
        if (!istSichtbar) {
            istSichtbar = true;
            zeichne();
        }
    }

    public void verberge() {
        loesche(); // "tue nichts" wird in loesche() abgefangen.
        istSichtbar = false;
    }
    
    public void dreheAuf(int neuerWinkel) {
        loesche();
        orientierung = neuerWinkel;
        zeichne();
    }
    
    public void move(int dx, int dy, boolean anim) throws InterruptedException {
        boolean onCanvas = Grid.isOnCanvas(xPosG + dx, yPosG + dy);
        if(!onCanvas) { return; }
        
        if(anim){
            int framerate = 60; // 30 Frames / sekunde
            int frametime = Math.round(1000/framerate); // in milliseconds (thats why 1000)
            float length = 2; // length in seconds
            int totalFrames = Math.round(framerate * length);
            
            for( int framesDone = 0; framesDone < totalFrames; framesDone++) {
                // value from 0 to 1 (progress)
                float factor = (float)framesDone / (float)totalFrames;
                // lerp pixel functions
                int px = Grid.lerpGridX(xPosG, xPosG + dx, factor);
                int py = Grid.lerpGridY(yPosG, yPosG + dy, factor);
                // move chair and draw
                xPosition = px;
                yPosition = py;
                zeichne();
                
                // sleep for frametime
                Thread.sleep(frametime);
            }
        }
        
        
        xPosG += dx;
        yPosG += dy;
        xPosition = Grid.getX(xPosG);
        yPosition = Grid.getY(yPosG);
        zeichne();
    }    
    
    private void zeichne() {
        if (istSichtbar) {
            Shape figur = gibAktuelleFigur();
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.zeichne (
              this,           // leinwand kennt das Objekt
              farbe,          // definiert seine Zeichenfarbe
              figur);         // definiert seinen grafischen Aspekt
        }
    }
    
    private void loesche() {
        if (istSichtbar){
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.entferne(this);
        }
    }
    
    private Shape gibAktuelleFigur() {
        int bigDia = 50;
        int smallDia = 5;
        // einen GeneralPath definieren
        GeneralPath waschmaschine = new GeneralPath();
        
        // outline
        Rectangle2D outline = new Rectangle2D.Double(0, 0, breite, tiefe);
        waschmaschine.append(outline, false);
                
        // bigC
        Ellipse2D bigC = new Ellipse2D.Double((breite - bigDia) / 2, (tiefe - bigDia) / 2, bigDia, bigDia);
        waschmaschine.append(bigC, false);
        
        // smallC
        Ellipse2D smallC = new Ellipse2D.Double((breite - smallDia) / 2, (tiefe - smallDia) / 2, smallDia, smallDia);
        waschmaschine.append(smallC, false);
        
        // transformieren:
        AffineTransform t = new AffineTransform();
        t.translate(xPosition, yPosition);
        Rectangle2D umriss = waschmaschine.getBounds2D();
        t.rotate(Math.toRadians(orientierung), umriss.getX() + umriss.getWidth()/2, umriss.getY() + umriss.getHeight()/2);
        return  t.createTransformedShape(waschmaschine);
    }
}
