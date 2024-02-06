
/**
 * Entwickelt einen Iterator f�r eine ArrayList.
 *
 * @author Tim Gabrikowski
 * @version 06.02.2024
 */
import java.util.ArrayList;
class ArrayListIterator {
    // Exemplarvariablen (erg�nzt eigene)
    private ArrayList<Object> list;

    // Konstruktor
    public ArrayListIterator(ArrayList<Object> aList) {
        list = aList;
        // initialisiere weitere Variablen
    }
    public void start() {
        // Springe zum ersten Element der Liste (Index 0)
    }
    public void next () {
        // springe zum N�chsten Objekt der Liste (wenn schon beim letzten, mache nichts)
    }
    public boolean hasNext() {
        // gibt es noch ein weiteres Element?
        return true; // muss ge�ndert werden!
    }
    public Object getCur() {
        // gebe das Aktuelle Element zur�ck
        return new Object(); // muss ge�ndert werden!
    }
}
