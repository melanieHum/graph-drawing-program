// Name: Melanie Hum
// Student Number: 500715410

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by Melanie on 2/26/2016.
 */
public class ShapeComponent extends JComponent {

    private ArrayList<GraphElement> shapes;

    // initialize states
    static final int DRAWRECT = 0;
    static final int DRAWELLIPSE = 1;
    static final int DRAWEDGE = 2;
    static final int DRAWINGEDGE = 3;
    static final int SELECT = 4;

    int state = SELECT;

    // which graph element is selected
    public GraphElement selectedElement;
    public boolean isSelected;


    public static Edge currentEdge;

    public ShapeComponent(ArrayList<GraphElement> shapes1) { // Constructs an array list of type GraphElement

        shapes = shapes1; // Sets this array to the parameter which is the one that was created in Main
        selectedElement = null;
        isSelected = false;

        class MyListener implements MouseListener {

            /**
             * This method checks if the object is in the area where the mouse is clicked.
             * If the mouse-click happens in a shape, that shape will be selected.
             */
            @Override
            public void mouseClicked(MouseEvent event) {
                System.out.println("clicked");

            }

            /**
             * When the mouse is pressed, depending on which state, mousePressed will do something different
             */
            @Override
            public void mousePressed(MouseEvent event) { // on left mouse press, draw shapes
                System.out.println("pressed");

                switch (state) {
                    case DRAWRECT: // for drawing rectangle
                        RectangleNode rectangle = new RectangleNode(event.getX(), event.getY());
                        shapes.add(rectangle);
                        repaint();
                        state = SELECT;
                        break;
                    case DRAWELLIPSE: // for drawing ellipse
                        EllipseNode ellipse = new EllipseNode(event.getX(), event.getY());
                        shapes.add(ellipse);
                        repaint();
                        state = SELECT;
                        break;
                    case DRAWEDGE: // for drawing edge start
                        Edge edge = new Edge(event.getX(), event.getY(), event.getX(), event.getY());
                        currentEdge = edge;
                        shapes.add(edge);
                        repaint();
                        state = DRAWINGEDGE;
                        break;
                    case DRAWINGEDGE: // for drawing edge end
                        repaint();
                        currentEdge = null;
                        state = SELECT;
                        break;
                    default: // select
                        if (event.getButton() == MouseEvent.BUTTON1) { // if the left mouse button is clicked
                            for (int i = 0; i < shapes.size(); i++) {
                                if (shapes.get(i).isClicked(event.getX(), event.getY())) { // checks if the mouse click is in a shape
                                    System.out.println("selecting shit");

                                    if (selectedElement != null) {
                                        selectedElement.setColor(Color.BLACK);
                                        repaint();
                                        selectedElement = null;
                                    }

                                    selectedElement = shapes.get(i);
                                    selectedElement.setColor(Color.BLUE);
                                    repaint();
                                    break;

                                } else if (selectedElement != null) {
                                    System.out.println("deselecting shit");
                                    selectedElement.setColor(Color.BLACK);
                                    repaint();
                                    selectedElement = null;
                                }
                            }
                        }
                        break;
                }

                if (event.getButton() == MouseEvent.BUTTON3 && selectedElement != null) { // on right mouse press shape is DELETED
                    System.out.println("BUTTON TWO ASDFGHJKL;");
                    shapes.remove(selectedElement);
                    selectedElement = null;
                    repaint();
                }



            }

            @Override
            public void mouseReleased(MouseEvent event) {

            }

            @Override
            public void mouseEntered(MouseEvent event) {

            }

            @Override
            public void mouseExited(MouseEvent event) {

            }
        }

            // adds MouseListener
            MouseListener allEarsOnMe = new MyListener();
            this.addMouseListener(allEarsOnMe);

        class MyMotionListener implements MouseMotionListener {

            @Override
            public void mouseDragged(MouseEvent event) {

                if (state == DRAWINGEDGE) { // draws line to where mouse is
                    currentEdge.x2 = event.getX();
                    currentEdge.y2 = event.getY();
                    repaint();
                }
                else if (selectedElement != null) { // if an object is selected, dragging the mouse will move it around
                    selectedElement.moveTo(event.getX(), event.getY());
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent event) {
                if (currentEdge != null) {
                    if (state == DRAWINGEDGE) { // draws line to where mouse is
                        currentEdge.x2 = event.getX();
                        currentEdge.y2 = event.getY();
                        repaint();
                    }
                }
            }
        }

            // adds MouseMotionListener
            MouseMotionListener allEyesOnMouse = new MyMotionListener();
            addMouseMotionListener(allEyesOnMouse);
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g; // Recover Graphics2D
        for (GraphElement shape : shapes) {
            shape.draw(g2); // draw the objects from the array list
        }
    }
}
