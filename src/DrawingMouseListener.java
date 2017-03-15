import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by IoIoTo on 15.3.2017 г..
 */
public class DrawingMouseListener implements MouseListener, MouseMotionListener{

    private DrawingArea drawingArea;

    public DrawingMouseListener(DrawingArea drawingArea)
    {
        this.drawingArea = drawingArea;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

        if (drawingArea.getNumberOfSectors() % 2 == 0) {
            int tempX = (int) (e.getX() - drawingArea.getSize().getHeight() / 2);
            int tempY = (int) (e.getY() - drawingArea.getSize().getHeight() / 2);
            drawingArea.getPointList().addPoint(new Point(-tempX, -tempY));
            //pointList.add();
        } else {
            int tempX = (int) (e.getX() - drawingArea.getSize().getHeight() / 2);
            int tempY = (int) (e.getY() - drawingArea.getSize().getHeight() / 2);
            drawingArea.getPointList().addPoint(new Point(tempX, tempY));
        }
        drawingArea.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
        if (checkIfInDrawingArea(new Point(e.getX(),e.getY()))) {
            if (drawingArea.getNumberOfSectors() % 2 == 0) {
                int tempX = (int) (e.getX() - drawingArea.getSize().getWidth() / 2);
                int tempY = (int) (e.getY() - drawingArea.getSize().getHeight()  / 2);
                drawingArea.getPointList().addPoint(new Point(-tempX, -tempY));
                // pointList.add();
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
