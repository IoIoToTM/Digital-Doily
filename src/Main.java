import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by IoIoTo on 7.3.2017 г..
 */
public class Main {


    static class DrawPane extends JPanel {

        public int oldX=0, oldY=0, newX=0, newY=0;


        public void paintComponent(Graphics g) {
            g.setColor(Color.black);

           // g.drawLine(oldX, oldY, newX, newY);
            g.drawRect(newX,newY,1,1);


        }
    }

    static public boolean click = false;

    public static void main(String[] args) {


        JFrame window = new JFrame("TEST");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(600, 800));

        DrawPane test = new DrawPane();
        window.setContentPane(test);

        window.setVisible(true);

        window.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {


                test.oldX = test.newX;
                test.oldY = test.newY;

                test.newX = e.getX();
                test.newY = e.getY();
                test.repaint();

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        window.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                test.newX = e.getX();
                test.newY = e.getY();
                click = true;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                click = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }
}
