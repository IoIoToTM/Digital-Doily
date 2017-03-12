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

    public static int drawingPenSize;
    public static Color drawingColor;
    public static int numberOfSectors;
    public static double angle;
    public static boolean showSectorLines = true;
    public static BufferedImage drawing;

    public ArrayList<Point> pointList;

    public DrawingArea() {
        super();
        this.setBackground(Color.gray);

        drawingPenSize = 1;
        drawingColor = Color.black;
        numberOfSectors = 6;
        pointList = new ArrayList<>();
        angle = (double) 360 / numberOfSectors;
        drawing = new BufferedImage(600,600,BufferedImage.TYPE_4BYTE_ABGR);



        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pointList.add(new Point(e.getX(), e.getY()));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!(e.getX() - getWidth() / 2 > getWidth() / 2) && !(e.getX() - getWidth() / 2 < -getWidth() / 2) && !(e.getY() - getHeight() / 2 < -getHeight() / 2) &&
                        !(e.getY() - getHeight() / 2 > getHeight() / 2)) {
                    int tempX = e.getX() - getWidth() / 2;
                    int tempY = e.getY() - getHeight() / 2;
                    pointList.add(new Point(-tempX * 2, -tempY * 2));
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(Color.gray);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        //Graphics2D g2d = drawing.createGraphics();

        //g2d.clearRect(0,0,getWidth(),getHeight());

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        g2d.setColor(Color.black);
        g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
        //g2d.drawRect(0,0,1,1);

       /* ArrayList<Point> test = new ArrayList<Point>();


        for (int i = 0; i < 200; i++) {
            if (i % 10 == 0)
                test.add(new Point(i, 30));
            else test.add(new Point(i + 10, 30));
        }*/
       /* Iterator<Point> testing = DigitalDoilyWindow.pointList.iterator();
        while(testing.hasNext())
        {
            Point temp = testing.next();
            g2d.drawRect((int)temp.getX(),(int)temp.getY(),drawingSize,drawingSize);
        }*/
       if(showSectorLines)
        for (int i = 0; i <= numberOfSectors; i++) {
            g2d.setStroke(new BasicStroke(1));
            g2d.setColor(Color.black);
            g2d.drawLine(0, 0, 0, -200);
            g2d.rotate(Math.toRadians(angle));
       }



        for (int i = 0; i <= numberOfSectors; i++) {

            g2d.rotate(Math.toRadians(angle));
            g2d.setColor(drawingColor);
            Iterator<Point> testing = pointList.iterator();
            Point first = null;

            if (!pointList.isEmpty()) {
                first = testing.next();
                while (testing.hasNext()) {

                    Point temp1 = testing.next();

                    g2d.setStroke(new BasicStroke(drawingPenSize));
                    g2d.drawLine(-(int) temp1.getX() / 2, -(int) temp1.getY() / 2, -(int) first.getX() / 2, -(int) first.getY() / 2);
                    //g2d.drawRect(-(int)temp1.getX()/2,-(int)temp1.getY()/2,1,1);
                    first = temp1;
                }
            }

            /*g2d.setColor(Color.GREEN);
            g2d.fillRect(30,30,300,500);*/


           // g.drawImage(drawing,0,0,null);


            //System.out.println(DigitalDoilyWindow.angle + " " + DigitalDoilyWindow.numberOfSectors + " " +Math.sin(DigitalDoilyWindow.angle));

        }

    }

}
