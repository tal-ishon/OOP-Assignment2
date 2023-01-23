/**
 *  @author Tal Ishon
 *  class Velocity.
 *  creates new Velocity of balls.
 */
public class Velocity {

    private double dx;
    private double dy;
    private Velocity v;

    /**
     * constructor of a new ball's Velocity.
     *
     * @param dx the x value's direction the ball's velocity
     * @param dy the y value's direction the ball's velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructor of a new ball's Velocity.
     *
     * @param v the velocity of the ball
     */
    public Velocity(Velocity v) {
        this.dx = v.dx;
        this.dy = v.dy;
    }

    /**
     * GetDx method.
     *
     * @return the dx of the ball's velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * GetDy method.
     *
     * @return the dy of the ball's velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * FromAngleAndSpeed method.
     * calculates the dx and dy of the ball's velocity
     * according to the angle and the speed method receives
     *
     * @param angle the angle of the vector's direction
     * @param speed the speed of the ball
     * @return the velocity of the ball
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        if (angle <= 180) {
            double radian = Math.toRadians(angle);
            double dx = Math.sin(radian) * speed;
            double dy = Math.cos(radian) * speed;
            return new Velocity(dx, dy);
        }
        if (angle > 180 && angle <= 270) {
            double radian = Math.toRadians(angle - 180);
            double dx = Math.sin(radian) * speed;
            double dy = Math.cos(radian) * speed;
            return new Velocity(dx, dy);
        }
        // if we got here angle > 270 && angle <= 360
        double radian = Math.toRadians(angle - 270);
        double dx = Math.sin(radian) * speed;
        double dy = Math.cos(radian) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * ApplyToPoint method.
     * Take a point with position (x,y)
     * and return a new point with position (x+dx, y+dy)
     *
     * @param p the point the ball is at right now
     * @return the point on the next step
     */
// Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}

