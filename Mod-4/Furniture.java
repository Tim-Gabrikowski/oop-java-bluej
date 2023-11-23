import java.awt.Shape;
/**
 * Write a description of class Furniture here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Furniture
{
    // instance variables - replace the example below with your own
    protected int xPosition;
    protected int yPosition;
    private int xPosG;
    private int yPosG;
    protected int rotation;
    private String farbe;
    private boolean istSichtbar;
    protected int breite;
    protected int tiefe;
    
    public Furniture(int x, int y, int o) {
        Leinwand lw = Leinwand.gibLeinwand();
        xPosG = x;
        yPosG = y;
        xPosition = Grid.getX(x);
        yPosition = Grid.getY(y);
        farbe = "blau";
        rotation = o;
        istSichtbar = false;
    }

    protected abstract Shape gibAktuelleFigur();
    
    public void inspect() {
        System.out.println("Furniture G" + xPosG + "|" + yPosG + " P" + xPosition + "|" + yPosition);
    }
    
    public void rotateTo(int nO) {
        loesche();
        rotation = nO;
        zeichne();
    }
    
    public void move(int dx, int dy, boolean anim) throws InterruptedException {
        boolean onCanvas = Grid.isOnCanvas(xPosG + dx, yPosG + dy);
        if(!onCanvas) { return; }
        
        if(anim){
            int framerate = 60; // 30 Frames / sekunde
            int frametime = Math.round(1000/framerate); // in milliseconds (thats why 1000)
            float length = 4; // length in seconds
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
}
