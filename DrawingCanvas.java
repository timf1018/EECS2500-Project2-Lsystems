import java.awt.*;
import java.awt.geom.Line2D;


public class DrawingCanvas extends Canvas {
    protected String drawString;
    protected double angleIncrement;
    ArrayStack saveStackXY = new ArrayStack(20);
    ArrayStack saveStackAngle = new ArrayStack(20);

    DrawingCanvas() {
        this.setPreferredSize(new Dimension(400, 400));
    }

    public void setDrawString(String s) {
        drawString = s;
    }

    public void setAngleIncrement(double d) {
        angleIncrement = Math.PI * d / 180.0;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int currentPositionX, currentPositionY, position;
        double currentAngle;

        currentPositionX = 200;
        currentPositionY = 0;
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
                try {
                    saveStackXY.push(currentPositionX);
                    saveStackXY.push(currentPositionY);
                    saveStackAngle.push(currentAngle);
                } catch (StackOverflowException ex) {
                    System.out.println("Stack overflow");
                }

            } else if (drawString.charAt(position) == ']') {
                try {
                    currentPositionY = Integer.parseInt(saveStackXY.top().toString());
                    saveStackXY.pop();
                    currentPositionX = Integer.parseInt(saveStackXY.top().toString());
                    saveStackXY.pop();
                    currentAngle = Double.parseDouble(saveStackAngle.top().toString());
                    saveStackAngle.pop();
                } catch (StackUnderflowException ex) {
                    System.out.println("Stack underflow");
                }
            }
        }
    }

}