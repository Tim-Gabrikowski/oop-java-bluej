
/**
 * Write a description of class Grid here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Grid
{
    // instance variables - replace the example below with your own
    private int cHeight;
    private int cWidth;
    private int unitSize;

    /**
     * Constructor for objects of class Grid
     */
    public Grid(int w, int h, int u)
    {
        cWidth = w;
        cHeight = h;
        unitSize = u;
    }
    public int getX(int uX) {
        return uX * unitSize;
    }
    public int getY(int uY) {
        return uY * unitSize;
    }
}
