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

    private Stroke pointList; //storing the current line you're drawing
    private ArrayList<Stroke> strokes; //arraylist of all the previously drawn lines

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


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //turning antialiasing on
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //translating to the center so drawing is easier
        g2d.translate(this.getWidth() / 2, this.getHeight() / 2);

        //draw sector lines if toggled on
        if (showSectorLines)
        drawSectorLines(g2d);

        //setting the pen size to the current size
        g2d.setStroke(new BasicStroke(drawingPenSize));

        //going through the stroke arraylist and drawing each past one
        for (int i = 0; i < strokes.size(); i++) {
            strokes.get(i).drawStroke(g2d);
        }

        //draw current stroke
        pointList.drawStroke(g2d);

    }

    private void drawSectorLines(Graphics2D g2d)
    {

            for (int i = 0; i <=numberOfSectors; i++) {
                g2d.setStroke(new BasicStroke(1));
                g2d.setColor(Color.WHITE);
                g2d.drawLine(0, 0, 0, -500);
                g2d.rotate(Math.toRadians(angle));
            }

    }

    //function for copying the image on the drawing area and returning a buffered image of it
    public BufferedImage copyDrawingArea() {
        BufferedImage copy = new BufferedImage((int)getSize().getWidth(), (int)getSize().getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        paint(copy.getGraphics());
        return copy;
    }

    //clearing the drawing
    public void clearDrawing()
    {
        strokes.removeAll(strokes);
        repaint();
    }

    //undo last action
    public void undoLastAction()
    {
        if(!strokes.isEmpty()){
            strokes.remove(strokes.size()-1);
            repaint();
        }
    }

    //getters and setters for most of the variables
    public int getDrawingPenSize() {
        return drawingPenSize;
    }

    public void setDrawingPenSize(int drawingPenSize) {

        pointList.setPenSize(drawingPenSize);
        this.drawingPenSize = drawingPenSize;
        repaint();
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
        setAngle( 360/(double)getNumberOfSectors());
        repaint();

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
