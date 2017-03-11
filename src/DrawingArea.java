import jdk.nashorn.internal.runtime.arrays.IteratorAction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IoIoTo on 7.3.2017 г..
 */


public class DrawingArea extends JPanel {

    public static int drawingSize;

    public DrawingArea() {
        this.setBackground(Color.gray);
        drawingSize = 1;

    }

    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(Color.gray);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        g2d.setColor(Color.black);
        g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
        //g2d.drawRect(0,0,1,1);

        ArrayList<Point> test = new ArrayList<Point>();


        for (int i = 0; i < 200; i++) {
            if (i % 10 == 0)
                test.add(new Point(i, 30));
            else test.add(new Point(i + 10, 30));
        }
       /* Iterator<Point> testing = DigitalDoilyWindow.pointList.iterator();
        while(testing.hasNext())
        {
            Point temp = testing.next();
            g2d.drawRect((int)temp.getX(),(int)temp.getY(),drawingSize,drawingSize);
        }*/

        for (int i = 0; i <= DigitalDoilyWindow.numberOfSectors; i++) {

            g2d.setColor(Color.black);
            g2d.drawLine(0, 0, 0, -200);
            g2d.rotate(Math.toRadians(DigitalDoilyWindow.angle));
            g2d.setColor(Color.GREEN);
            Iterator<Point> testing = DigitalDoilyWindow.pointList.iterator();
            Point first = null;

            if (!DigitalDoilyWindow.pointList.isEmpty()) {
                first = testing.next();
                while (testing.hasNext()) {

                    Point temp1 = testing.next();


                    g2d.drawLine(-(int) temp1.getX() / 2, -(int) temp1.getY() / 2, -(int) first.getX() / 2, -(int) first.getY() / 2);
                    //g2d.drawRect(-(int)temp1.getX()/2,-(int)temp1.getY()/2,1,1);
                    first = temp1;
                }
            }


            //System.out.println(DigitalDoilyWindow.angle + " " + DigitalDoilyWindow.numberOfSectors + " " +Math.sin(DigitalDoilyWindow.angle));

        }

    }

}
