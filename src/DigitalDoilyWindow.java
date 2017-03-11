import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by IoIoTo on 7.3.2017 г..
 */
public class DigitalDoilyWindow extends JFrame {

    private static int WIDTH;
    private static int HEIGHT;
    private static String NAME;

    public static ArrayList<Point> pointList;
    public static ArrayList<ArrayList<Point>> drawSteps;
    private boolean mouseClicked = false;

    public static int numberOfSectors = 1;
    public static double angle = 360 / numberOfSectors;

    public static final Font MAIN_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final Dimension DEFAULT_SIZE = new Dimension(1024, 600);

    public DigitalDoilyWindow(int width, int height, String name) {

        super(name);
        System.out.println(angle);
        pointList = new ArrayList<Point>();

        this.WIDTH = width;
        this.HEIGHT = height;

        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        //main panel we add to
        JPanel window = new JPanel();
        window.setVisible(true);
        window.setBackground(Color.black);
        window.setLayout(new BorderLayout());

        //panel for the drawing area
        JPanel drawingArea = new DrawingArea();
        //drawingArea.setBackground(Color.gray);

        drawingArea.setPreferredSize(new Dimension(600, this.HEIGHT));

        drawingArea.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                //e.translatePoint(drawingArea.getWidth()/2,drawingArea.getHeight()/2);

                //record point only if in drawing area
                if (!(e.getX() - drawingArea.getWidth() / 2 > drawingArea.getWidth() / 2) && !(e.getX() - drawingArea.getWidth() / 2 < -drawingArea.getWidth() / 2) && !(e.getY() - drawingArea.getHeight() / 2 < -drawingArea.getHeight() / 2) &&
                        !(e.getY() - drawingArea.getHeight() / 2 > drawingArea.getHeight() / 2)) {
                    int tempX = e.getX() - drawingArea.getWidth() / 2;
                    int tempY = e.getY() - drawingArea.getHeight() / 2;
                    System.out.println(tempX + " " + tempY);
                    pointList.add(new Point(-tempX * 2, -tempY * 2));
                    drawingArea.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        drawingArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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


        //panel for the options and gallery
        JPanel tools = new JPanel();
        tools.setPreferredSize(new Dimension(300, 300));
        tools.setLayout(new GridLayout(2, 1));
        tools.setBackground(Color.GREEN);

        //panel for the control panel
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        controls.setBackground(Color.red);


        //panel for the gallery
        JPanel gallery = new JPanel();
        gallery.setLayout(new GridLayout(4, 3));
        for (int i = 1; i <= 12; i++) {
            JButton test = new JButton("Picture " + i);
            test.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DigitalDoilyWindow.numberOfSectors++;
                    angle = 360 / DigitalDoilyWindow.numberOfSectors;
                    drawingArea.repaint();
                }
            });
            gallery.add(test);
        }
        gallery.setBackground(Color.YELLOW);


        tools.add(controls);
        tools.add(gallery);

        window.add(drawingArea, BorderLayout.CENTER);
        window.add(tools, BorderLayout.EAST);


        this.add(window);
        //for starting in center of screen
        //this.setLocationRelativeTo(null);


        this.pack();

    }
}
