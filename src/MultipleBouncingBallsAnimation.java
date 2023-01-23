import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;
/**
 *  @author Tal Ishon
 *  class MultipleBouncingBallsAnimation.
 *  receives arguments from user, referring to balls' radiuses
 *  set balls information and draw the balls.
 *
 */
public class MultipleBouncingBallsAnimation {
    /**
     * StartDraw method.
     * Creates an array of balls, set the balls' information and draw them.
     * @param ballSizes the strings which contains the balls' sizes (radius)
     */
    public static void startDraw(int[] ballSizes) {
        GUI gui = new GUI("Hey look Ma I made it", 800, 600);
        DrawSurface d = gui.getDrawSurface();
        Sleeper sleeper = new Sleeper();
        Ball[] balls = balls(ballSizes, d.getWidth(), d.getHeight());
        while (true) {
            d = gui.getDrawSurface();
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
                gui.show(d);
                sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * StringsToInts method.
     * Receives array of strings and converts them to array of ints
     * @param string the array which contains the balls' sizes (radius)
     * @return the array of ints which contains the balls' sizes (radius)
     */
// create array of balls' size
    public static int[] stringsToInts(String[] string) {
        if (string.length == 0) { // invalid input
            throw new NumberFormatException();
        }
           int[] array = new int[string.length];
           for (int i = 0; i < string.length; i++) {
               array[i] = Integer.parseInt(string[i]);
           }
           return array;
    }

    /**
     * Balls method.
     * Receives the array of ball sizes (radius), and the range the ball bounces (width and height)
     * Creates array of balls.
     * Set each ball's information and return the array of balls.
     *
     * @param ballSize the radius of the ball
     * @param width    the width of frame - where the ball bounces
     * @param height   the height of frame - where the ball bounces
     * @return the array of balls
     */
    public static Ball[] balls(int[] ballSize, int width, int height) {

        Ball[] ball = new Ball[ballSize.length];
        for (int i = 0; i < ballSize.length; i++) {
            // creating colors for balls
            float color1 = new Random().nextFloat();
            float color2 = new Random().nextFloat();
            float color3 = new Random().nextFloat();
            Color myColors = new Color(color1, color2, color3);
            Velocity v;
            // set ball's information. make sure random ball starts in frame.
            int maxSize = 200;
            if (ballSize[i] > maxSize || ballSize[i] <= 0) { // make sure we have a valid size of radius
                ballSize[i] = maxSize;
            }
            ball[i] = new Ball(getRandomNumber(ballSize[i], width - ballSize[i]),
                    getRandomNumber(ballSize[i], height - ballSize[i]), ballSize[i], myColors);
            if (ballSize[i] <= 50) {
                v = Velocity.fromAngleAndSpeed(getRandomNumber(0, 360), (double) 100 / ballSize[i]);
            } else {
                v = Velocity.fromAngleAndSpeed(getRandomNumber(0, 360), 2);
            }
            ball[i].setFrame(width, height);
            ball[i].setVelocity(v);
        }
        return ball;
    }

    /**
     * GetRandomNumber method.
     * Receives the wanted range of number.
     *
     * @param min the minimum number of wanted range
     * @param max the maximum number of wanted range
     * @return the random number in the wanted range
     */
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * The main.
     * Receives arguments (the balls' radius) and send them to method to draw bouncing balls
     *
     * @param args the input arguments - radius
     */
    public static void main(String[] args) {
        int[] ballSizes;
        try {
            ballSizes = stringsToInts(args);
        } catch (NumberFormatException ex) {
            System.out.println("ERROR");
            return;
        }
        MultipleBouncingBallsAnimation.startDraw(ballSizes);
    }
}

