import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;
/**
 *  @author Tal Ishon
 *  class AbstractArtDrawing.
 *  draw rendom lines.
 */
public class AbstractArtDrawing {
    /**
     * DrawRandomLines method.
     * draw 10 lines.
     * shuffle random number for line's point's x and y values
     * sets the lines according to points
     * finds the middle point of line and draw it
     * draw intersection points between the lines
     */
    public void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Lines"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] linesArray = new Line[10];
        // loop for drawing 10 lines
        for (int i = 0; i < linesArray.length; ++i) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
            linesArray[i] = new Line(x1, y1, x2, y2);
            d.setColor(Color.BLACK);
            d.drawLine(x1, y1, x2, y2);
            Point middle = linesArray[i].middle();
            d.setColor(Color.BLUE);
            d.fillCircle((int) middle.getX(), (int) middle.getY(), 3);
        }
        drawIntersectionPointsInRed(linesArray, d);
        gui.show(d);
    }

    /**
     * DrawIntersectionPointsInRed method.
     * gets array of lines and DrawSurface to draw the points on
     * checks if lines intersect, if they do - draw intersection point,
     * otherwise - check intersection with next line
     *
     * @param arr the array of lines that are checked if intersect
     * @param d   the DrawSurface that the points are been drawn on
     */
    public void drawIntersectionPointsInRed(Line[] arr, DrawSurface d) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].isIntersecting(arr[j])) {
                    Point intersection = arr[i].intersectionWith(arr[j]);
                    d.setColor(Color.RED);
                    d.fillCircle((int) intersection.getX(), (int) intersection.getY(), 3);
                }
            }
        }
    }

    /**
     * The main.
     * runs the program. does not get input.
     * @param args in this case we don't get input. we don't use args
     */
    public static void main(String[] args) {
        AbstractArtDrawing lines = new AbstractArtDrawing();
        lines.drawRandomLines();
    }
}

