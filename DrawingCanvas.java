import java.awt.Canvas;
import java.awt.*;
import java.awt.geom.*;

/**
 *
 */

/**
 * @author Gerald Heuring
 */
public class DrawingCanvas extends Canvas {
    protected String drawString;
    protected double angleIncrement;


    DrawingCanvas() {
        this.setPreferredSize(new Dimension(400, 400));
    }

    public void setDrawString(String s) {
        drawString = s;
    }

    public void setAngleIncrement(double d) {
        angleIncrement = Math.PI * d / 180.0;
    }

    /**
     * Paint routine for our canvas.  The upper Left hand corner
     * is 0, 0 and the lower right hand corner is 399, 399.
     * <p>
     * Our initial Position can be either 200, 0 (probably easier)
     * or 200, 399 (need to draw in the "negative" direction).
     * <p>
     * We will also talk about how to scale this and modify it.
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int currentPositionX, currentPositionY, position;
        double currentAngle;

        currentPositionX = 200;
        currentPositionY = 200;
        currentAngle = 0.0;

        for (position = 0; position < drawString.length(); position++) {
            if (drawString.charAt(position) == 'F') { // Draw 5 units along current direction
                Line2D line = new Line2D.Double(currentPositionX, currentPositionY,
                        currentPositionX + 5.0 * Math.sin(currentAngle),
                        currentPositionY + 5.0 * Math.cos(currentAngle));
                g2.draw(line);
                currentPositionX = (int) (currentPositionX + 5.0 * Math.sin(currentAngle));
                currentPositionY = (int) (currentPositionY + 5.0 * Math.cos(currentAngle));
            } else if (drawString.charAt(position) == '-') {
                currentAngle -= angleIncrement;
            } else if (drawString.charAt(position) == '+') {
                currentAngle += angleIncrement;
            } else if (drawString.charAt(position) == '[') {

            } else if (drawString.charAt(position) == ']') {

            }
        }
    }

}