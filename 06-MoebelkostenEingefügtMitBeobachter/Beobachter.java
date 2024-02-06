import java.util.*;

public abstract class Beobachter
{
    private static ArrayList<Moebel> alleMoebel = new ArrayList<Moebel>();
    private static double gesamtKosten = 0;
    
    public static void hinzufuegen(Moebel moebel){
      alleMoebel.add(moebel);  
    }
    public static void entfernen(Moebel moebel){
      alleMoebel.remove(moebel); 
    }
    
    public static double gesamtKosten(){
        gesamtKosten = 0;
        for (Moebel aktMoebel : alleMoebel) {
            gesamtKosten = gesamtKosten + aktMoebel.kosten();
        }
        return gesamtKosten;
    }
}
