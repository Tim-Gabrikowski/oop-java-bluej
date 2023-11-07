/**
 * Write a description of class Grid here.
 *
 * @author Tim Gabrikowski
 * @version 05.10.2023
 */
public class Grid
{
    // cHeight und cWidth sind Canvas größe
    private static int cHeight = 100;
    private static int cWidth = 100;
    // die Rastergröße (in Pixeln)
    private static int unitSizeX = 25;
    private static int unitSizeY = 25;
    
    public static void setParams(int w, int h, int u) {
        cHeight = h;
        cWidth = w;
        unitSizeX = u;
        unitSizeY = u;
    }
    /**
     * @brief Calculate X position in Pixels given a x value in the grid
     * @param uX X Position in Gridspace
     * @return x Position in pixelspace
     */
    public static int getX(int uX) {
        return uX * unitSizeX;
    }
    /**
     * @brief Calculate Y position in Pixels given a Y value in the grid
     * @param uY Y Position in Gridspace
     * @return y Position in pixelspace
     */
    public static int getY(int uY) {
        return uY * unitSizeY;
    }
    
    public static boolean isOnCanvas(int gX, int gY) {
        return (gX*unitSizeX <= cWidth) && (gY*unitSizeY <= cHeight) && (gX >= 0) && (gY >= 0);
    }
    
    public static int lerpGridX(int s, int e, float t) {      
        // calculate the pixel Xs      
        int pS = s * unitSizeX;
        int pE = e * unitSizeX;
        // calculate delta
        int delta = pE-pS;
        // to now get the point we need to find the delta from the start at a given t
        return pS + Math.round(delta * quadr(t));
    }
    public static int lerpGridY(int s, int e, float t) {
        // calculate the pixel Xs      
        int pS = s * unitSizeX;
        int pE = e * unitSizeY;
        // calculate delta
        int delta = pE-pS;
        // to now get the point we need to find the delta from the start at a given t
        return pS + Math.round(delta * quadr(t));
    }
    
    private static float quadr(float x) {
        return -1*x*x + 2*x;
    }
}
