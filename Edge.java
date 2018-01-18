// Name: Melanie Hum
// Student Number: 500715410

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * Created by Melanie on 2/26/2016.
 */
public class Edge extends GraphElement {

    public int x2;
    public int y2;

    public Edge(int x, int y, int x2, int y2) {

        super(x, y); //overloads superclass constructor
        this.x2 = x2;
        this.y2 = y2;

    }

    public void draw(Graphics2D g2) {

        g2.setColor(this.getColour());
        Line2D.Double line = new Line2D.Double(getXPos(), getYPos(), x2, y2);
        g2.draw(line);
        //System.out.println("RE-EDGE-EDGE-EDGE; SO EDGY");
    }
    public boolean isClicked(double x, double y) {

        double run = getXPos() - x2;
        double rise = getYPos() - y2;
        double m = rise/run;
        double b = getYPos() - m * getXPos();
        double upperB = b + 20;
        double lowerB = b - 20;
        double upperY = m * x + upperB;
        double lowerY = m * x + lowerB;
        double maxX = Math.max(getXPos(), x2);
        double minX = Math.min(getXPos(), x2);

        boolean yCheck = y < upperY && y > lowerY;
        boolean xCheck = x < maxX && x > minX;

        if (yCheck && xCheck) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean applyLabel() {
        return true;
    }

    @Override
    public void moveTo(double xLoc, double yLoc) {
        //System.out.println("Raise your kappas");
    }
}
