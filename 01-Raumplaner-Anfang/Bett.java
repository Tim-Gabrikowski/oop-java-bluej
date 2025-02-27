import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

/**
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 * Tim was here
 * And here
 */
public class Bett
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


    public Bett(int gx, int gy, int o){
        System.out.println("" + gx + " " + Grid.getX(gx) + " " + gy + " " + Grid.getY(gy));
        xPosition = Grid.getX(gx);
        yPosition = Grid.getY(gy);
        xPosG = gx;
        yPosG = gy;
        farbe = "gruen";
        orientierung = o;
        istSichtbar = false;
        breite = 160;
        tiefe  = 220;        
    }
    
    public void inspect() {
        System.out.println("bett bei G" + xPosG + "|" + yPosG + " P" + xPosition + "|" + yPosition);
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
        // einen GeneralPath definieren
        GeneralPath bett = new GeneralPath();
        // outline
        bett.moveTo(0 , 0);
        bett.lineTo(breite, 0);
        bett.lineTo(breite, tiefe);
        bett.lineTo(0, tiefe);
        // diagonal
        bett.lineTo(0 , 0);  
        bett.lineTo(breite, tiefe);
        // transformieren:
        AffineTransform t = new AffineTransform();
        t.translate(xPosition, yPosition);
        Rectangle2D umriss = bett.getBounds2D();
        t.rotate(Math.toRadians(orientierung), umriss.getX() + umriss.getWidth()/2, umriss.getY() + umriss.getHeight()/2);
        return  t.createTransformedShape(bett);
    }
}
