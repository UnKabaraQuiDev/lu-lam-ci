
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Ball {

    public static final double GRAVITY = 0.981;

    public double x, y; // position
    private int radius;
    private double dX = 5 + Math.random() * 5, dY = -Math.random() * 10; // velocity
    private double friction = 0.65 + Math.random() * (0.80 - 0.65);
    private boolean toDelete = false;
    private Color color;
    private int neighborCount;

    public Ball(double x, double y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public void move(int width, int height) {
        // we should multiply acceleration & velocity with $\Delta time$
        // so that we can use more appropriated units (gravity: px/s^2 instead of px/frame)
        x += dX;
        dY += GRAVITY;
        y += dY;

        // the demo version doesn't seem to do this correctly when the frame is narrower than its starting width
        if (x - radius > width) {
            toDelete = true;
            return;
        }

        if (y + radius > height) {
            y = height - radius;
            dY = -dY * friction;
        }
    }

    // Changed to Graphics2D
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        Ellipse2D.Double oval = new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2);
        g2d.fill(oval);

        g2d.setColor(Color.BLACK);
        g2d.draw(oval);

        g2d.setColor(color);
        oval = new Ellipse2D.Double(x - radius / 2, y - radius / 2, radius, radius);
        g2d.fill(oval);

        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(neighborCount), (int) (x - g2d.getFontMetrics().stringWidth(Integer.toString(neighborCount)) / 2), (int) (y + g2d.getFontMetrics().getHeight() / 2));
    }

    public boolean isInside(int x, int y) {
        return distance(x, y) < this.radius;
    }

    // How about isColliding or isIntersecting
    public boolean isTouching(Ball ball) {
        return distance(ball.x, ball.y) <= (ball.radius + this.radius);
    }

    public void setCentre(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setNeighborCount(int neighborCount) {
        this.neighborCount = neighborCount;
    }

    public int getNeighborCount() {
        return neighborCount;
    }

    public boolean isToDelete() {
        return toDelete;
    }

    public Color getColor() {
        return color;
    }

    // Added helper method
    private double distance(double x, double y) {
        return Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2));
    }

}
