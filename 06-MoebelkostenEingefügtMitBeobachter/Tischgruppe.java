import java.util.*;
/**
 * Beschreiben Sie hier die Klasse Tischgruppe.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Tischgruppe 
{
    private Stuhl s1;
    private Stuhl s2;
    private Stuhl s3;
    private Stuhl s4;
    private Tisch t1;
    private ArrayList<Moebel> gruppe;
    
    public Tischgruppe()
    {
        gruppe=new ArrayList<Moebel>();
    s1 = new Stuhl();
    s1.zeige();
    s1.bewegeHorizontal(100);
    s1.bewegeVertikal(80);
    s1.dreheAuf(180);
    gruppe.add(s1);
    s2 = new Stuhl();
    s2.zeige();
    s2.bewegeHorizontal(100);
    s2.bewegeVertikal(-80);
    s2.dreheAuf(0);
    gruppe.add(s2);
    s3 = new Stuhl();
    s3.zeige();
    s3.bewegeHorizontal(200);
    s3.dreheAuf(90);
    gruppe.add(s3);
    s4 = new Stuhl();
    s4.zeige();
    gruppe.add(s4);
    t1=new Tisch();
    t1.zeige();
    gruppe.add(t1);
    }

    public double gesamtKostenTischgruppe(){
        double kosten =0;
        for (Moebel mobis :gruppe){
            kosten = kosten+mobis.kosten();
        }
        //kosten = schraenke.get(0).kosten() * anzahl + 100;
        return kosten;
   }

}
