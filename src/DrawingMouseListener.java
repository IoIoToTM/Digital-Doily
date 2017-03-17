import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by IoIoTo on 15.3.2017 г..
 */

//custom mouse listener for the drawing
public class DrawingMouseListener implements MouseListener, MouseMotionListener{

    private DrawingArea drawingArea;

    public DrawingMouseListener(DrawingArea drawingArea)
    {
        this.drawingArea = drawingArea;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //check if the number of sectors is even or odd and add the point after making some adjustments to it's coordinates
        //to make sure it's where it's supposed to be (because for the drawing I've used translate to the center)
        if (drawingArea.getNumberOfSectors() % 2 == 0) {
            int tempX = (int) (e.getX() - drawingArea.getSize().getWidth() / 2);
            int tempY = (int) (e.getY() - drawingArea.getSize().getHeight()  / 2);
            drawingArea.getPointList().addPoint(new Point(-tempX, -tempY));
        } else {
            int tempX = (int) (e.getX() - drawingArea.getSize().getWidth()/ 2);
            int tempY = (int) (e.getY() - drawingArea.getSize().getHeight()  / 2);
            drawingArea.getPointList().addPoint(new Point(tempX, tempY));
        }
        drawingArea.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        //when you release the mouse button add the current stroke to the arraylist of strokes and create a new current stroke
        drawingArea.getStrokes().add(drawingArea.getPointList());
        drawingArea.setPointList( new Stroke(drawingArea, drawingArea.getDrawingColor(), drawingArea.isReflected(), drawingArea.getDrawingPenSize()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        //first check if the mouse is in the drawing area and if it is do the same as above
        if (checkIfInDrawingArea(new Point(e.getX(),e.getY()))) {
            if (drawingArea.getNumberOfSectors() % 2 == 0) {
                int tempX = (int) (e.getX() - drawingArea.getSize().getWidth() / 2);
                int tempY = (int) (e.getY() - drawingArea.getSize().getHeight()  / 2);
                drawingArea.getPointList().addPoint(new Point(-tempX, -tempY));
            } else {
                int tempX = (int) (e.getX() - drawingArea.getSize().getWidth()/ 2);
                int tempY = (int) (e.getY() - drawingArea.getSize().getHeight()  / 2);
                drawingArea.getPointList().addPoint(new Point(tempX, tempY));
            }
            drawingArea.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //function to check if a point is in the drawing area
    private boolean checkIfInDrawingArea(Point point)
    {
        if (!(point.getX() - drawingArea.getSize().getWidth() / 2 > drawingArea.getSize().getWidth() / 2) && !(point.getX() - drawingArea.getSize().getWidth() / 2 < -drawingArea.getSize().getWidth() / 2) && !(point.getY() - drawingArea.getSize().getHeight() / 2 < -drawingArea.getSize().getHeight()  / 2) &&
                !(point.getY() - drawingArea.getSize().getHeight()  / 2 > drawingArea.getSize().getHeight()  / 2))
        {
            return true;
        }
        else return false;

    }

}
