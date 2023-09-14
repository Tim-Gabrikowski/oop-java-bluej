import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Leinwand ist eine Klasse, die einfache Zeichenoperationen auf einer
 * leinwandartigen Zeichenflaeche ermoeglicht.
 * Sie ist eine vereinfachte Version der Klasse Canvas (englisch fuer 
 * Leinwand) des JDK und wurde speziell fuer das Projekt "Figuren"
 * geschrieben.
 * 
 *
 * @author: Bruce Quig
 * @author: Michael Koelling (mik)
 * @author: Axel Schmolitzky
 * 
 * @author: Aenderungen von
 * @Java-MS Groupies
 * @hier: Uwe Debacher
 *
 * @version: 1.7 (5.12.2003)
 */
public class Leinwand
{
  private static Leinwand leinwandSingleton;

  /**
   * Fabrikmethode, die eine Referenz auf das einzige Exemplar
   * dieser Klasse zur�ckliefert. Wenn es von einer Klasse nur
   * genau ein Exemplar gibt, wird dieses als 'Singleton'
   * bezeichnet.
   */
  public static Leinwand gibLeinwand()
  {
    if (leinwandSingleton == null)
    {
      leinwandSingleton =
        new Leinwand("M�belprojekt Grafik", 800, 800, Color.white, 100);
    }
    leinwandSingleton.setzeSichtbarkeit(true);
    return leinwandSingleton;
  }

  //  ----- Exemplarvariablen -----

  private JFrame fenster;
  private Zeichenflaeche zeichenflaeche;
  private Graphics2D graphic;
  private Color hintergrundfarbe;
  private Image leinwandImage;
  private List figuren;
  private Map figurZuShape;
  private Grid grid;// Abbildung von Figuren zu Shapes

  public Grid getGrid(){
      return grid;
  }
  
  private Leinwand(String titel, int breite, int hoehe, Color grundfarbe, int units)
  {
    fenster = new JFrame();
    zeichenflaeche = new Zeichenflaeche();
    fenster.setContentPane(zeichenflaeche);
    fenster.setTitle(titel);
    zeichenflaeche.setPreferredSize(new Dimension(breite, hoehe));
    hintergrundfarbe = grundfarbe;
    fenster.pack();
    figuren = new ArrayList();
    figurZuShape = new HashMap();
    grid = new Grid(breite, hoehe, units);

}


  public void setzeSichtbarkeit(boolean sichtbar)
  {
    if (graphic == null)
    {
      // erstmaliger Aufruf: erzeuge das Bildschirm-Image und f�lle
      // es mit der Hintergrundfarbe
      Dimension size = zeichenflaeche.getSize();
      leinwandImage = zeichenflaeche.createImage(size.width, size.height);
      graphic = (Graphics2D) leinwandImage.getGraphics();
      graphic.setColor(hintergrundfarbe);
      graphic.fillRect(0, 0, size.width, size.height);
      graphic.setColor(Color.black);
    }
    fenster.setVisible(sichtbar);
  }

  
  public void zeichne(Object figur, String farbe, Shape shape)
  {
    figuren.remove(figur); // entfernen, falls schon eingetragen
    figuren.add(figur); // am Ende hinzuf�gen
    figurZuShape.put(figur, new ShapeMitFarbe(shape, farbe));
    erneutZeichnen();
  }

  public void entferne(Object figur)
  {
    figuren.remove(figur); // entfernen,falls schon eingetragen
    figurZuShape.remove(figur);
    erneutZeichnen();
  }

  public void setzeZeichenfarbe(String farbname)
  {
    if (farbname.equals("rot"))
      graphic.setColor(Color.red);
    else if (farbname.equals("schwarz"))
      graphic.setColor(Color.black);
    else if (farbname.equals("blau"))
      graphic.setColor(Color.blue);
    else if (farbname.equals("gelb"))
      graphic.setColor(Color.yellow);
    else if (farbname.equals("gruen"))
      graphic.setColor(Color.green);
    else if (farbname.equals("lila"))
      graphic.setColor(Color.magenta);
    else if (farbname.equals("weiss"))
      graphic.setColor(Color.white);
    else
      graphic.setColor(Color.black);
  }

  public void warte(int millisekunden)
  {
    try
    {
      Thread.sleep(millisekunden);
    }
    catch (Exception e)
    {
      // Exception ignorieren
    }
  }

  private void erneutZeichnen()
  {
    loeschen();
    for (Iterator i = figuren.iterator(); i.hasNext();)
    {
      ((ShapeMitFarbe) figurZuShape.get(i.next())).draw(graphic);
    }
    zeichenflaeche.repaint();
  }

  private void loeschen()
  {
    Color original = graphic.getColor();
    graphic.setColor(hintergrundfarbe);
    Dimension size = zeichenflaeche.getSize();
    graphic.fill(new Rectangle(0, 0, size.width, size.height));
    graphic.setColor(original);
  }

  /************************************************************************
   * Interne Klasse Zeichenflaeche - die Klasse f�r die GUI-Komponente,
   * die tats�chlich im Leinwand-Fenster angezeigt wird. Diese Klasse
   * definiert ein JPanel mit der zus�tzlichen M�glichkeit, das auf ihm
   * gezeichnet Image aufzufrischen (erneut zu zeichnen).
   */
  private class Zeichenflaeche extends JPanel
  {
    public void paint(Graphics g)
    {
      g.drawImage(leinwandImage, 0, 0, null);
    }
  }

  /************************************************************************
   * Interne Klasse ShapeMitFarbe - Da die Klasse Shape des JDK nicht auch
   * eine Farbe mitverwalten kann, muss mit dieser Klasse die Verkn�pfung
   * modelliert werden.
   * graphic.fill() durch graphic.draw() ersetzt von Uwe Debacher am 5.12.2003
   */
  private class ShapeMitFarbe
  {
    private Shape shape;
    private String farbe;

    public ShapeMitFarbe(Shape shape, String farbe)
    {
      this.shape = shape;
      this.farbe = farbe;
    }

    public void draw(Graphics2D graphic)
    {
      setzeZeichenfarbe(farbe);
      graphic.draw(shape);
    }
  }

}
