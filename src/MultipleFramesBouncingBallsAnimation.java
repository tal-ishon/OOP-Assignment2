import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;
/**
 *  @author Tal Ishon
 *  class MultipleFramesBouncingBallsAnimation.
 *  receives arguments from user, referring to balls' radiuses
 *  set balls information and draw the balls in frames.
 *
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * StartDraw method.
     * Creates an array of balls, set the balls' information and draw them.
     * @param radius the strings which contains the balls' sizes (radius)
     */
    public static void startDraw(int[] radius) {
        GUI gui = new GUI("Hey look Ma I made it", 800, 600);
        DrawSurface d = gui.getDrawSurface();
        Sleeper sleeper = new Sleeper();
        Frame gray = new Frame(50, 50, 500, 500);
        Frame yellow = new Frame(450, 450, 600, 600);
        int[] radArr1 = new int[radius.length / 2]; // array of balls' sizes - the balls which will be in gray frame
        int[] radArr2 = new int[radius.length / 2]; // array of balls' sizes - the balls which will be in yellow frame
        for (int i = 0, j = radius.length / 2; i < radius.length / 2; i++, j++) { // split array into 2 arrays
            radArr1[i] = radius[i];
            radArr2[i] = radius[j];
        }
        // creating 2 new array of balls - ball1 will bounce in gray frame, ball2 will bounce in yellow frame
        Ball[] ball1 = balls(radArr1, gray.getLeftWidth(), gray.getTopHeight(), gray.getWidth(), gray.getHeight());
        Ball[] ball2 = balls(radArr2, yellow.getLeftWidth(), yellow.getTopHeight(),
                yellow.getWidth(), yellow.getHeight());

        while (true) {
            d = gui.getDrawSurface();
            // draw balls in gray frame
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            for (Ball value : ball1) {
                value.moveOneStep();
                value.drawOn(d);
            }
            // draw balls in yellow frame
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            for (Ball ball : ball2) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * StringsToInts method.
     * Receives array of strings and convert it to array of ints
     * @param string the string contains the balls' sizes
     * @return the array of balls' sizes (radius) in ints
     */
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
     * Receives array of balls' sizes (radius) and balls' frame.
     *
     * @param ballSize     the ball size (radius)
     * @param leftWidth    the left width of frame -  where the ball bounces
     * @param topHeight the bottom height of frame -  where the ball bounces
     * @param width        the width of frame -  where the ball bounces
     * @param height       the height of frame -  where the ball bounces
     * @return the array of the new balls
     */
    public static Ball[] balls(int[] ballSize, int leftWidth, int topHeight, int width, int height) {
        Ball[] ball = new Ball[ballSize.length];
        for (int i = 0; i < ballSize.length; i++) {
            // creating colors for balls
            float color1 = 0.0f; // narrowing selection so colors won't be similar to background
            float color2 = new Random().nextFloat();
            float color3 = new Random().nextFloat();
            Color myColors = new Color(color1, color2, color3);
            Velocity v;
            // make sure radius is valid
            int maxSize; // creating the limit size of a radius
            if (width < height) {
                maxSize = (width - leftWidth) / 4;
            } else {
                maxSize = (height - topHeight) / 4;
            }
            if (ballSize[i] > maxSize || ballSize[i] <= 0) { // if radius too big or if smaller than 1,
                // we create a valid ball instead
                ballSize[i] = maxSize;
            }
            ball[i] = new Ball(getRandomNumber(leftWidth + ballSize[i], width - ballSize[i]),
                        getRandomNumber(topHeight + ballSize[i], height - ballSize[i]), ballSize[i], myColors);

            if (ballSize[i] <= 50) {
                v = Velocity.fromAngleAndSpeed(getRandomNumber(0, 360), (double) 100 / ballSize[i]);
            } else {
                v = Velocity.fromAngleAndSpeed(getRandomNumber(0, 360), 2);
            }
            ball[i].setFrame(leftWidth, topHeight, width, height);
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
        MultipleFramesBouncingBallsAnimation.startDraw(ballSizes);
    }

}
