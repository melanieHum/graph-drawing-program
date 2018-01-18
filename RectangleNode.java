// Name: Melanie Hum
// Student Number: 500715410

import java.awt.*;

/**
 * Created by Melanie on 2/26/2016.
 */
public class RectangleNode extends GraphElement {

    public RectangleNode(int x, int y) {

        super(x, y); //overloads superclass constructor
    }
    public void draw(Graphics2D g2) {

        g2.setColor(this.getColour());
        g2.drawRect((int)getXPos(), (int)getYPos(), LENGTH, HEIGHT);
        int midX = getLabelWidth(g2);
        int midY = (int)getYPos() + 2*HEIGHT/3;
        g2.drawString(this.getLabel(), midX, midY);
        System.out.println("RE-RECTANGLE DAMMIT");
    }

    public boolean isClicked(double x, double y) {

        // for checking boundaries
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
