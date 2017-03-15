import jdk.nashorn.internal.runtime.arrays.IteratorAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IoIoTo on 7.3.2017 г..
 */


public class DrawingArea extends JPanel {



    private int drawingPenSize;
    private Color drawingColor;
    private int numberOfSectors;
    private double angle;
    private boolean showSectorLines = true;
    private boolean reflected = false;

    private Stroke pointList;
    private ArrayList<Stroke> strokes;

    public DrawingArea() {

        super();

        strokes = new ArrayList<>();
        this.setBackground(Color.BLACK);

        drawingPenSize = 1;
        drawingColor = Color.WHITE;
        numberOfSectors = 6;
        pointList = new Stroke(this,drawingColor, reflected, drawingPenSize);
        angle = (double) 360 / numberOfSectors;

        DrawingMouseListener mouseListener = new DrawingMouseListener(this);

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);


    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    public BufferedImage copyDrawingArea(JPanel drawingArea) {
        BufferedImage copy = new BufferedImage(drawingArea.getWidth(), drawingArea.getHeight(), BufferedImage.TYPE_INT_ARGB);
        drawingArea.paint(copy.getGraphics());
        return copy;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //g2d.setColor(Color.black);
        g2d.translate(this.getWidth() / 2, this.getHeight() / 2);


        if (showSectorLines)
            for (int i = 0; i < numberOfSectors; i++) {
                g2d.setStroke(new BasicStroke(1));
                g2d.setColor(Color.WHITE);
                g2d.drawLine(0, 0, 0, -300);
                g2d.rotate(Math.toRadians(angle));
            }


        g2d.setStroke(new BasicStroke(drawingPenSize));
        g2d.setColor(Color.white);


        //going through backwards through the stroke arraylist
        for (int i = 0; i < strokes.size(); i++) {
            strokes.get(i).drawStroke(g2d);
        }
        pointList.drawStroke(g2d);

    }

    public int getDrawingPenSize() {
        return drawingPenSize;
    }

    public void setDrawingPenSize(int drawingPenSize) {
        this.drawingPenSize = drawingPenSize;
    }
    public Color getDrawingColor() {
        return drawingColor;
    }

    public void setDrawingColor(Color drawingColor) {
        this.drawingColor = drawingColor;
    }

    public int getNumberOfSectors() {
        return numberOfSectors;
    }

    public void setNumberOfSectors(int numberOfSectors) {
        this.numberOfSectors = numberOfSectors;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isShowSectorLines() {
        return showSectorLines;
    }

    public void setShowSectorLines(boolean showSectorLines) {
        this.showSectorLines = showSectorLines;
    }

    public boolean isReflected() {
        return reflected;
    }

    public void setReflected(boolean reflected) {
        this.reflected = reflected;
    }

    public Stroke getPointList() {
        return pointList;
    }

    public void setPointList(Stroke pointList) {
        this.pointList = pointList;
    }

    public ArrayList<Stroke> getStrokes() {
        return strokes;
    }

    public void setStrokes(ArrayList<Stroke> strokes) {
        this.strokes = strokes;
    }

}
