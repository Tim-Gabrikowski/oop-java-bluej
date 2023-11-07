import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

/**
 * Ein Stuhl, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 * Tim was here
 * And here
 */
public class Stuhl
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


    /**
     * Erzeuge einen neuen Stuhl mit einer Standardfarbe und Standardgroesse
     * an einer Standardposition. (Standardkonstruktor)
     */
    public Stuhl() {
        xPosition = 160;
        yPosition = 80;
        farbe = "blau";
        orientierung = 0;
        istSichtbar = false;
        breite = 40;
        tiefe  = 40;
        
    }
    
    /**
     * Erzeuge einen neuen Stuhl mit einer Standardfarbe und Standardgroesse
     * an einer angegebenen Position im Grid (Aufgabe 3.5)
     */
    public Stuhl(int gx, int gy){
        System.out.println("" + gx + " " + Grid.getX(gx) + " " + gy + " " + Grid.getY(gy));
        xPosition = Grid.getX(gx);
        yPosition = Grid.getY(gy);
        xPosG = gx;
        yPosG = gy;
        farbe = "blau";
        orientierung = 0;
        istSichtbar = false;
        breite = 40;
        tiefe  = 40;        
    }
    
    public Stuhl(int x, int y, int o) {
        xPosition = x;
        yPosition = y;
        farbe = "blau";
        orientierung = o;
        istSichtbar = false;
        breite = 40;
        tiefe  = 40;
    }
    /**
     * Creates a new Chair given the position, orientation and color
     * @param x The X Position to spawn the Chair
     * @param y The Y Position to spawn the Chair
     * @param orientation The Orientation to spawn the Chair in
     * @param color The Color of the Chair
     */
    public Stuhl(int x, int y, int orientation, String color) {
        xPosition = x;
        yPosition = y;
        orientierung = orientation;
        farbe = color;
        istSichtbar = false;
        breite = 120;
        tiefe = 100;
    }
    
    /**
     * Berechnet das zu zeichnende Shape anhand der gegebenen Daten
     * [ Zum Anzeigen der Attributwerte ueber Inspect muss hier die Sichtbarkeit 
     *  auf public gesetzt werden. ]
     */
    private Shape gibAktuelleFigur() {
        // einen GeneralPath definieren
        GeneralPath stuhl = new GeneralPath();
        stuhl.moveTo(0 , 0);
        stuhl.lineTo(breite, 0);
        stuhl.lineTo(breite+(breite/20+1), tiefe);
        stuhl.lineTo(-(breite/20+1), tiefe);
        stuhl.lineTo(0 , 0);
        // Das ist die Umrandung. Das Stuhl bekommt noch eine Lehne:
        stuhl.moveTo(0 , (breite/10+1));
        stuhl.lineTo(breite, (breite/10+1));    
        // transformieren:
        AffineTransform t = new AffineTransform();
        t.translate(xPosition, yPosition);
        Rectangle2D umriss = stuhl.getBounds2D();
        t.rotate(Math.toRadians(orientierung),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(stuhl);
    }
    
    public void inspect() {
        System.out.println("Stuhl bei G" + xPosG + "|" + yPosG + " P" + xPosition + "|" + yPosition);
    }
    
    /**
     * Mache dieses Objekt sichtbar. Wenn es bereits sichtbar ist, tue nichts.
     */
    public void zeige() {
        if (!istSichtbar) {
            istSichtbar = true;
            zeichne();
        }
    }
    
    /**
     * Mache dieses Objekt unsichtbar. Wenn es bereits unsichtbar ist, tue nichts.
     */
    public void verberge() {
        loesche(); // "tue nichts" wird in loesche() abgefangen.
        istSichtbar = false;
    }
    
    /**
     * Drehe auf den angegebenen Winkel
     */
    public void dreheAuf(int neuerWinkel) {
        loesche();
        orientierung = neuerWinkel;
        zeichne();
    }
    
    /**
     * Bewege dieses Objekt horizontal um 'entfernung' Bildschirmpunkte.
     */
    public void bewegeHorizontal(int entfernung) {
        loesche();
        xPosition += entfernung;
        zeichne();
    }
    
    /**
     * Bewege dieses Objekt um dx und dy Rastereinheiten
     * Wenn anim = true, wird Bewegung animiert
     */
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
    
    /**
     * Bewege dieses objekt vertikal um 'entfernung' Bildschirmpunkte.
     */
    public void bewegeVertikal(int entfernung) {
        loesche();
        yPosition += entfernung;
        zeichne();
    }
    
    
    /**
     * Aendere die Farbe dieses Objektes in 'neueFarbe'.
     * Gueltige Angaben sind "rot", "gelb", "blau", "gruen",
     * "lila" und "schwarz".
     */
    public void aendereFarbe(String neueFarbe) {
        loesche();
        farbe = neueFarbe;
        zeichne();
    }
    
    /**
     * Zeichne dieses Objekt mit seinen aktuellen Werten auf den Bildschirm.
     */
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
    
    /**
     * Loesche dieses Objekt vom Bildschirm.
     */
    private void loesche() {
        if (istSichtbar){
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.entferne(this);
        }
    }
}
