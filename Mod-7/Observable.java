
/**
 * @author Tim Gabrikowski 
 * @version 20.01.2024
 */

public interface Observable
{
    public abstract void notifyObservers();
    public abstract void addObserver(Observer o);
    public abstract void removeObserver(Observer o);
    public abstract Observable getInstance();
}
