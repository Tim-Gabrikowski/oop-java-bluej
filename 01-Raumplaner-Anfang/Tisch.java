import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

/**
 * Ein Tisch, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 */
public class Tisch 
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
    private String name;
    private int preis;
    
    public Tisch()  {
        xPosition = 120;
        yPosition = 150;
        orientierung = 0;
        farbe = "rot";
        istSichtbar = false;
        breite = 120;
        tiefe  = 100;
        name = "Helgoland";
        preis = 199;
    }
    
    /**
     * Erzeuge einen neuen Tisch mit einer Standardfarbe und Standardgroesse
     * an einer angegebenen Position im Grid (Aufgabe 3.5)
     */
    public Tisch(int gx, int gy){
        xPosition = Grid.getX(gx);
        yPosition = Grid.getY(gy);
        xPosG = gx;
        yPosG = gy;
        orientierung = 0;
        farbe = "rot";
        istSichtbar = false;
        breite = 120;
        tiefe  = 100;
        name = "Helgoland";
        preis = 199;      
    }
    
    public Tisch(int x, int y, int o)  {
        xPosition = x;
        yPosition = y;
        orientierung = o;
        farbe = "rot";
        istSichtbar = false;
        breite = 120;
        tiefe  = 100;
        name = "Helgoland";
        preis = 199;
    }
    
    public Tisch(int x, int y, int orientation, String color, String newName) {
        xPosition = x;
        yPosition = y;
        orientierung = orientation;
        farbe = color;
        istSichtbar = false;
        breite = 120;
        tiefe = 100;
        name = newName;
        preis = 199;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    /**
     * Berechnet das zu zeichnende Shape anhand der gegebenen Daten
     * [ Zum Anzeigen der Attributwerte ueber Inspect muss hier die Sichtbarkeit 
     *  auf public gesetzt werden. ]
     */
    private Shape gibAktuelleFigur()
    {
        // definieren
        Shape tisch = new Ellipse2D.Double(0 , 0, breite, tiefe);
        
        // transformieren
        AffineTransform t = new AffineTransform();
        t.translate(xPosition, yPosition);
        Rectangle2D umriss = tisch.getBounds2D();
        t.rotate(Math.toRadians(orientierung),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(tisch);
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
    
    public void bewege (int x, int y) {
        loesche();
        xPosition += x;
        yPosition += y;
        zeichne();
    }
    
    /**
     * Bewege dieses Objekt um dx und dy Rastereinheiten
     */
    public void move(int dx, int dy) {
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
            leinwand.warte(10);
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
