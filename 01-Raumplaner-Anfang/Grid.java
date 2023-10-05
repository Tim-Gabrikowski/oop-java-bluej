
/**
 * Write a description of class Grid here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Grid
{
    // cHeight und cWidth sind Canvas größe
    private static int cHeight = 100;
    private static int cWidth = 100;
    // die Rastergröße (in Pixeln)
    private static int unitSize = 25;

    public static void setParams(int w, int h, int u) {
        cHeight = h;
        cWidth = w;
        unitSize = u;
    }
    /**
     * @brief Calculate X position in Pixels given a x value in the grid
     * @param uX X Position in Gridspace
     * @return x Position in pixelspace
     */
    public static int getX(int uX) {
        return uX * unitSize;
    }
    /**
     * @brief Calculate Y position in Pixels given a Y value in the grid
     * @param uY Y Position in Gridspace
     * @return y Position in pixelspace
     */
    public static int getY(int uY) {
        return uY * unitSize;
    }
}
