import biuoop.DrawSurface;
/**
 *  @author Tal Ishon
 *  class Ball.
 *  creates new balls.
 *  draw balls.
 */
public class Ball {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private Frame frame;

    /**
     * constructor of a  Ball.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * constructor of a  Ball.
     *
     * @param x     the x value of the center point
     * @param y     the y value of the center point
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }
    /**
     * GetX method.
     *
     * @return the x of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * GetY method.
     *
     * @return the y of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * GetSize method.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * GetColor method.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * SetFrame method.
     * creates a new frame for the ball (means the range the ball exists (moves))
     *
     * @param width  the width of the frame
     * @param height the height of the frame
     */
    public void setFrame(int width, int height) {
        this.frame = new Frame(width, height);
    }

    /**
     * SetFrame method.
     * creates a new frame for the ball (means the range the ball exists (moves))
     *
     * @param leftWidth    the left width of the frame
     * @param topHeight the bottom height of the frame
     * @param width        the width of the frame
     * @param height       the height of the frame
     */
    public void setFrame(int leftWidth, int topHeight, int width, int height) {
        this.frame = new Frame(leftWidth, topHeight, width, height);
    }

    /**
     * DrawOn method.
     * draw the ball on the given DrawSurface
     *
     * @param surface the DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());

    }

    /**
     * SetVelocity method.
     * set the ball's velocity
     *
     * @param v the object velocity we want to set for the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v); // why don't we need new?
    }

    /**
     * SetVelocity method.
     * set the ball's velocity
     *
     * @param dx the x value's direction the ball's velocity
     * @param dy the y value's direction the ball's velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * GetVelocity method.
     *
     * @return the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * MoveOneStep method.
     * moves the ball inside the ball's frame.
     * checks if ball's next step is in or out of frame.
     * if it's inside frame - ball continues in same direction,
     * otherwise, changes the ball's direction (means when ball hits one of the frames borders).
     */
    public void moveOneStep() {
        // checks the value of the next point and the point after
        Point newPoint = this.getVelocity().applyToPoint(this.center);
        Point nextPoint = this.getVelocity().applyToPoint(newPoint);

        // these conditions check if on the next step ball is in frame (newPoint)
        // and check if the point after newPoint (nextPoint) if even farther of frame
        // if both conditions are true - it means ball needs to change it's direction
        if (newPoint.getY() + getSize() > this.frame.getHeight()
                && nextPoint.getY() > newPoint.getY()) { // ball hits bottom axis
            this.setVelocity(this.velocity.getDx(), -1 * (this.velocity.getDy()));
        }
        if (newPoint.getY() - getSize() < this.frame.getTopHeight()
                && nextPoint.getY() < newPoint.getY()) { // ball hits upper axis
            this.setVelocity(this.velocity.getDx(), -1 * (this.velocity.getDy()));
        }
        if (newPoint.getX() + getSize() > this.frame.getWidth()
                && nextPoint.getX() > newPoint.getX()) { // ball hits right axis
            this.setVelocity(-1 * (this.velocity.getDx()), this.velocity.getDy());
        }
        if (newPoint.getX() - getSize() < this.frame.getLeftWidth()
                && nextPoint.getX() < newPoint.getX()) { // ball hits left axis
            this.setVelocity(-1 * (this.velocity.getDx()), this.velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center); // move the ball to next step
    }
}
