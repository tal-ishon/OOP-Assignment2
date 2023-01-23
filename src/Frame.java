/**
 *  @author Tal Ishon
 *  class Frame.
 *  creates new frames of balls.
*/
public class Frame {

    private int width;
    private int height;
    private int leftWidth;
    private int topHeight;

    /**
     * constructor of a new ball's Frame.
     *
     * @param width  the width of frame
     * @param height the height of frame
     */
    public Frame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * constructor of a new Frame.
     *
     * @param leftWidth    the left width of frame
     * @param topHeight the bottom height of frame
     * @param width        the width of frame
     * @param height       the height of frame
     */
    public Frame(int leftWidth, int topHeight, int width, int height) {
        this.leftWidth = leftWidth;
        this.topHeight = topHeight;
        this.width = width;
        this.height = height;
    }

    /**
     * GetWidth method.
     *
     * @return the width of frame
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * GetHeight method.
     *
     * @return the height of frame
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * GetLeftWidth method.
     *
     * @return the left width of frame
     */
    public int getLeftWidth() {
        return this.leftWidth;
    }

    /**
     * GetTopHeight method.
     *
     * @return the bottom height of frame
     */
    public int getTopHeight() {
        return this.topHeight;
    }
}
