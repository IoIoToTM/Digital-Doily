import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IoIoTo on 14.3.2017 г..
 */
public class Stroke {




    private ArrayList<Point> pointList;
    private Color color;
    private boolean reflected;

    public void setPenSize(int penSize) {
        this.penSize = penSize;
    }

    private int penSize;

    public Stroke(ArrayList<Point> arr, Color color, boolean reflected, int penSize)
    {
        pointList = arr;
        this.color = color;
        this.reflected = reflected;
        this.penSize = penSize;

    }

    public Stroke( Color color, boolean reflected, int penSize)
    {
        pointList = new ArrayList<>();
        this.color = color;
        this.reflected = reflected;
        this.penSize = penSize;

    }
    public void drawStroke(Graphics g)
    {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(color);

        for(int i = 0; i<= DrawingArea.numberOfSectors; i++)
        {
            g2d.setStroke(new BasicStroke(penSize));
            Iterator<Point> iter = pointList.iterator();
            Point first = null,second = null;

            if(pointList.size()==1)
            {
                first = pointList.get(0);
                g2d.drawOval((int)first.getX(),(int)first.getY(),DrawingArea.drawingPenSize,DrawingArea.drawingPenSize);
                if(reflected)
                {
                    g2d.drawOval(-(int)first.getX(),(int)first.getY(),DrawingArea.drawingPenSize,DrawingArea.drawingPenSize);
                }
                g2d.rotate(Math.toRadians(DrawingArea.angle));
            }
            else if(pointList.size()>1)
            {
                first = iter.next();

                while(iter.hasNext())
                {
                    second = iter.next();

                    g2d.drawLine((int)first.getX(),(int)first.getY(),(int)second.getX(),(int)second.getY());

                    if(reflected)
                    {
                        g2d.drawLine(-(int)first.getX(),(int)first.getY(),-(int)second.getX(),(int)second.getY());
                    }

                    first=second;
                }
                g2d.rotate(Math.toRadians(DrawingArea.angle));
            }
        }


    }



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
