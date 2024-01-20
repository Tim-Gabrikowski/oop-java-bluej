
/**
 * @author Tim Gabrikowski
 * @version 20.01.2024
 */

public interface Observer
{
    public abstract void updateObservable (Observable o);
    public abstract void addObservable (Observable o);
    public abstract void removeObservable (Observable o);
}
