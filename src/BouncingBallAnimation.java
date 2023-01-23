import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Bouncing ball animation.
 *
 * @author Tal Ishon  class BouncingBallAnimation.  receives 4 values from user - x, y, dx, dy
 */
public class BouncingBallAnimation {
    /**
     * drawAnimation method.
     * receives point start and the velocity of ball (dx, dy)
     * creates a new ball and set it's methods (size, color, velocity, frame etc)
     * draw the ball
     *
     * @param start the point the ball starts
     * @param dx    the x value's direction the ball's velocity
     * @param dy    the y value's direction the ball's velocity
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Hey look Ma I made it", 200, 200);
        DrawSurface d = gui.getDrawSurface();
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        ball.setFrame(0, 0, d.getWidth(), d.getHeight());
        while (true) {
            ball.moveOneStep();
            d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * drawAnimation method.
     * receives point start and the velocity of ball (dx, dy)
     * creates a new ball and set it's methods (size, color, velocity, frame etc)
     * draw the ball
     *
     * @param x  the x value of point the ball starts at
     * @param y  the y value of point the ball starts at
     * @param dx the x value's direction the ball's velocity
     * @param dy the y value's direction the ball's velocity
     */
    public static void drawAnimation(double x, double y, double dx, double dy) {
        GUI gui = new GUI("Hey look Ma I made it", 200, 200);
        Sleeper sleeper = new Sleeper();
        DrawSurface d = gui.getDrawSurface();
        int radius = 30;
        if (x + radius > d.getWidth() || x - radius < d.getWidth()) {
            x = (double) d.getWidth() / 2; // making sure x value within frame's range
        }
        if (y + radius > d.getWidth() || y - radius < d.getWidth()) {
            y = (double) d.getWidth() / 2; // making sure y value within frame's range
        }
        Ball ball = new Ball((int) x, (int) y, radius, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        ball.setFrame(0, 0, d.getWidth(), d.getHeight());
        while (true) {
            ball.moveOneStep();
            d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
            if (gui.getKeyboardSensor().isPressed("space")) {
                return;
            }
        }
    }

    /**
     * stringsToDoubles method.
     * Receives array of strings and converts them to array of doubles
     * @param args the array which contains the balls' coordinates (radius)
     * @return the array of ints which contains the balls' sizes (radius)
     */
    public static double[] stringsToDoubles(String[] args) {
        if (args.length != 4) { // invalid input
            throw new NumberFormatException();
        }
        double[] doubles = new double[args.length];
        for (int i = 0; i < 4; i++) {
            doubles[i] = Double.parseDouble(args[i]);
        }
        return doubles;
    }

    /**
     * The main.
     * Receives 4 arguments from user and send them to method to get the ball bouncing
     * if there are more or less than 4 argument, exit system.
     *
     * @param args the input arguments from user. referring to - x, y, dx, dy
     */
    public static void main(String[] args) {
        double[] doubles;
        try {
            doubles = stringsToDoubles(args);
        } catch (NumberFormatException ex) {
            System.out.println("ERROR");
            return;
        }
        BouncingBallAnimation.drawAnimation(doubles[0], doubles[1], doubles[2], doubles[3]);
    }
}
