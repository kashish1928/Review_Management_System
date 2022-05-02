package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

// REFERENCE:
// https://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java
// Class for representing the graph
public class Graph extends JPanel {
    private static final int MAXIMUM_VALUE = 20;                                 // the maximum value
    private static final int X_WIDTH = 800;                                      // x-axis width
    private static final int Y_HEIGHT = 650;                                     // y-axis width
    private static final int BORDER_SIZE = 30;                                   // size of the border
    private static Color COLOR = Color.RED;                                      // color of the graph
    private static Color POINT_COLOR = new Color(24, 22, 22, 180);   // point color
    private static Stroke STROKE = new BasicStroke(3f);                    // stroke of the graph
    private static final int POINT_WIDTH = 12;                                   // point width
    private static final int Y_HATCH = 10;                                       // y-axis hatch
    private static List<Integer> information;                                    // list of data
    private static UserGUI userGUI = new UserGUI();                              // an UserGUI object
    private static JFrame frame;                                                 // a JFrame object

    // MODIFIES: this
    // EFFECTS: creates a list of records
    public Graph(List<Integer> information) {
        this.information = information;
    }

    // MODIFIES: this
    // EFFECTS: handles the graphical that is display part of the graph
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xscale = ((double) getWidth() - 2 * BORDER_SIZE) / (information.size() - 1);
        double yscale = ((double) getHeight() - 2 * BORDER_SIZE) / (MAXIMUM_VALUE - 1);

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < information.size(); i++) {
            int x1 = (int) (i * xscale + BORDER_SIZE);
            int y1 = (int) ((MAXIMUM_VALUE - information.get(i)) * yscale + BORDER_SIZE);
            graphPoints.add(new Point(x1, y1));
        }

        graphics2D.drawLine(BORDER_SIZE, getHeight() - BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
        graphics2D.drawLine(BORDER_SIZE, getHeight() - BORDER_SIZE, getWidth()
                - BORDER_SIZE, getHeight() - BORDER_SIZE);

        axisFunction(graphics2D);

        graphSize(graphPoints,graphics2D);
    }

    // MODIFIES: this
    // EFFECTS: creates the axis of the graphs
    public void axisFunction(Graphics2D graphics2D) {

        for (int i = 0; i < Y_HATCH; i++) {
            int x0 = BORDER_SIZE;
            int x1 = POINT_WIDTH + BORDER_SIZE;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_SIZE * 2)) / Y_HATCH + BORDER_SIZE);
            int y1 = y0;
            graphics2D.drawLine(x0, y0, x1, y1);
        }


        for (int i = 0; i < information.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_SIZE * 2) / (information.size() - 1) + BORDER_SIZE;
            int x1 = x0;
            int y0 = getHeight() - BORDER_SIZE;
            int y1 = y0 - POINT_WIDTH;
            graphics2D.drawLine(x0, y0, x1, y1);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the graph size
    public void graphSize(List<Point> graphPoints,Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        graphics2D.setColor(COLOR);
        graphics2D.setStroke(STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            graphics2D.drawLine(x1, y1, x2, y2);
        }

        graphics2D.setStroke(oldStroke);
        graphics2D.setColor(POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - POINT_WIDTH / 2;
            int ovalW = POINT_WIDTH;
            int ovalH = POINT_WIDTH;
            graphics2D.fillOval(x, y, ovalW, ovalH);
        }
    }

    // EFFECTS: sets the required size of the graph
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(X_WIDTH, Y_HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: creates a graph by adding info and displaying them when called
    public void createAndShowGui() {
        List<Integer> scores = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 16;
        int maxScore = 20;
        for (int i = 0; i < maxDataPoints;i++) {
            for (int index : userGUI.getRating()) {
                scores.add(index);
            }
        }
    }

    // MODIFIES: this, JFrame
    // EFFECTS: creates a frame for displaying the graph
    public JFrame getFrame() {
        Graph mainPanel = new Graph(information);
        frame = new JFrame("RATING VS NUMBER OF PEOPLE");
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(false);
        return frame;
    }

}