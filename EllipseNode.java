// Name: Melanie Hum
// Student Number: 500715410

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Created by Melanie on 2/26/2016.
 */
public class EllipseNode extends GraphElement {

    public EllipseNode(int x, int y) {

        super(x, y); //overloads superclass constructor
    }
    public void draw(Graphics2D g2) {

        g2.setColor(this.getColour());
        Ellipse2D.Double ellipse = new Ellipse2D.Double(getXPos(), getYPos(), LENGTH, HEIGHT);
        g2.draw(ellipse);
        int midX = getLabelWidth(g2);
        int midY = (int)getYPos() + 2*HEIGHT/3;
        g2.drawString(this.getLabel(), midX, midY);
        System.out.println("RE-ELLIPSE DAMMIT");
    }

    public boolean isClicked(double x, double y) {

        // for setting boundaries
        boolean lessThanRightEdge = x < (getXPos() + LENGTH);
        boolean greaterThanTheLeftEdge = x > (getXPos());
        boolean lessThanBottomEdge = y < (getYPos() + HEIGHT);
        boolean greaterThanTopEdge = y > (getYPos());

        if (lessThanRightEdge && greaterThanTheLeftEdge && lessThanBottomEdge && greaterThanTopEdge) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean applyLabel() {
        return true;
    }
}
