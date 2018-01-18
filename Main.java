// Name: Melanie Hum
// Student Number: 500715410

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This program makes a frame and adds buttons to it.
 * It displays three buttons which can be used to create shapes.
 * These shapes can me moved, removed, and labeled.
*/

public class Main {

    // static references to important objects
    public static ShapeComponent theShapeComponent;
    public static JTextField theTextField;

    public static void main(String[] args) {

        // Creates the frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setTitle("Assignment 1");

        // creates new BorderLayout
        frame.setLayout(new BorderLayout());

        // Creates component
        ArrayList<GraphElement> shapes = new ArrayList<>();
        theShapeComponent = new ShapeComponent(shapes);

        // Creates panels in the right places (LOL)
        JPanel panel = new JPanel();
        frame.add(panel,BorderLayout.NORTH); // Adds the panel to top of frame

        // Creates the buttons and text field
        JButton rectangleButton = new JButton("Rectangle");
        JButton ellipseButton = new JButton("Ellipse");
        JButton edgeButton = new JButton("Edge");
        JButton labelButton = new JButton("Label");

        // creates text field and sets preferred size
        theTextField = new JTextField();
        theTextField.setPreferredSize(new Dimension(100,27)); // Sets text field's size

        // Adds buttons to panels.
        panel.add(rectangleButton);
        panel.add(ellipseButton);
        panel.add(edgeButton);
        panel.add(labelButton);
        panel.add(theTextField);

        // Creates listener object
        ActionListener rectangleListener = new RectangleListener();
        ActionListener ellipseListener = new EllipseListener();
        ActionListener edgeListener = new EdgeListener();
        ActionListener labelListener = new LabelListener();

        // Adds the listeners to the buttons so when buttons are pushed, the actionPerformed method is executed
        rectangleButton.addActionListener(rectangleListener);
        ellipseButton.addActionListener(ellipseListener);
        edgeButton.addActionListener(edgeListener);
        labelButton.addActionListener(labelListener);

        // Adds component to the frame
        frame.add(theShapeComponent,BorderLayout.CENTER);

        // makes frame visible
        frame.setVisible(true); // Makes frame visible

    }

    static class RectangleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) { // sets the state to draw rectangle

            //System.out.println("WHY");
            theShapeComponent.state = ShapeComponent.DRAWRECT;

        }
    }
    static class EllipseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) { // sets the state to draw ellipse

            //System.out.println("Ahhhhhh...");
            theShapeComponent.state = ShapeComponent.DRAWELLIPSE;
        }
    }
    static class EdgeListener implements ActionListener { // sets the state to draw edge
        @Override
        public void actionPerformed(ActionEvent event) {

            System.out.println("AHHHHHHH...");
            theShapeComponent.state = ShapeComponent.DRAWEDGE;
        }
    }
    static class LabelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) { // labels the shape

            //System.out.println("H E L P  M E");
            if (theShapeComponent.selectedElement != null) {
                theShapeComponent.selectedElement.setLabel(theTextField.getText());
                theShapeComponent.repaint();
            }



        }
    }

}
