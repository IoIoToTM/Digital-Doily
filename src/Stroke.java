import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IoIoTo on 14.3.2017 г..
 */
public class Stroke {

    //a stroke has an array list of points, color, boolean for if it's reflected, size of pen and a drawing area to which it is drawn to

    private ArrayList<Point> pointList;
    private Color color;
    private boolean reflected;
    private DrawingArea drawingArea;

    public void setPenSize(int penSize) {
        this.penSize = penSize;
    }

    private int penSize;

    //few different constructors

    public Stroke(DrawingArea drawingArea,ArrayList<Point> arr, Color color, boolean reflected, int penSize)
    {
        this.drawingArea = drawingArea;
        pointList = arr;
        this.color = color;
        this.reflected = reflected;
        this.penSize = penSize;

    }

    public Stroke(DrawingArea drawingArea, Color color, boolean reflected, int penSize)
    {
        this.drawingArea = drawingArea;
        pointList = new ArrayList<>();
        this.color = color;
        this.reflected = reflected;
        this.penSize = penSize;

    }

    public Stroke(DrawingArea drawingArea)
    {
        this.drawingArea = drawingArea;
    }

    //drawing function with which you draw the stroke
    public void drawStroke(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        //set the draw color to the stroke color
        g2d.setColor(color);

        //go through each sector and draw the points using an iterator
        for(int i = 0; i< drawingArea.getNumberOfSectors(); i++)
        {

            g2d.setStroke(new BasicStroke(penSize));
            Iterator<Point> iter = pointList.iterator();
            Point first = null,second = null;

            //if there's only one point then just draw a dot
            if(pointList.size()==1)
            {
                first = pointList.get(0);
                g2d.drawOval((int)first.getX(),(int)first.getY(),drawingArea.getDrawingPenSize(),drawingArea.getDrawingPenSize());
                //if it's reflected draw it mirrored by the Y axis ( the x coordinate is "-" the normal one
                if(reflected)
                {
                    g2d.drawOval(-(int)first.getX(),(int)first.getY(),drawingArea.getDrawingPenSize(),drawingArea.getDrawingPenSize());
                }
                //rotate by the current angle and draw again
                g2d.rotate(Math.toRadians(drawingArea.getAngle()));
            }

            //if the points are more than one
            else if(pointList.size()>1)
            {

                //set the first one to the first element of the arraylist
                first = iter.next();

                //iterating through the array
                while(iter.hasNext())
                {
                    second = iter.next();

                    //draw a line between the first and second points
                    g2d.drawLine((int)first.getX(),(int)first.getY(),(int)second.getX(),(int)second.getY());

                    //if it's reflected draw it mirrored by the Y axis as above
                    if(reflected)
                    {
                        g2d.drawLine(-(int)first.getX(),(int)first.getY(),-(int)second.getX(),(int)second.getY());
                    }

                    //first element becomes the second
                    first=second;
                }
                //rotate by the current angle
                g2d.rotate(Math.toRadians(drawingArea.getAngle()));
            }
        }


    }



    //getters and setters

    public ArrayList<Point> getPointList() {
        return pointList;
    }

    public Color getColor() {
        return color;
    }
    public void addPoint(Point point) {
        this.pointList.add(point);
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void setReflected(boolean reflected)
    {
        this.reflected = reflected;
    }

}
