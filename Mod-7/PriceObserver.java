import java.util.ArrayList;
/**
 * Beschreiben Sie hier die Klasse PriceObserver.
 * 
 * @author Tim Gabrikowski 
 * @version 20.01.2024
 */
public class PriceObserver implements Observer
{
    private ArrayList<Observable> observables;
    private double totalCost = 0;
    public PriceObserver() {
        observables = new ArrayList<Observable>();
    }
    
    @Override
    public void addObservable (Observable o) {
        observables.add(o);
        o.addObserver(this);
        calculateCost();
    }
    @Override
    public void updateObservable (Observable o) {
        
    }
    @Override
    public void removeObservable (Observable o) {
        observables.remove(o);
        o.removeObserver(this);
        calculateCost();
    }
    
    private void calculateCost() {
        double c = 0;
        for(Observable o : observables) {
            c += ((Furniture)o).cost(1);
        }
        totalCost = c;
        System.out.println("total: " + c);
    }
    
}
