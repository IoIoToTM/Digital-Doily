import javax.swing.*;
import javax.swing.border.Border;
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

    public static final Font MAIN_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final Dimension DEFAULT_SIZE = new Dimension(1024, 600);

    public DigitalDoilyWindow(int width, int height, String name) {

        super(name);

        this.WIDTH = width;
        this.HEIGHT = height;

        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void init()
    {
        //main panel we add to
        JPanel window = new JPanel();
        window.setVisible(true);
        window.setBackground(Color.black);
        window.setLayout(new BorderLayout());

        //panel for the drawing area
        DrawingArea drawingArea = new DrawingArea();

        drawingArea.setPreferredSize(new Dimension(800, this.HEIGHT));
        drawingArea.setMinimumSize(new Dimension(700,this.HEIGHT));
        drawingArea.setMaximumSize(new Dimension(700,this.HEIGHT));

        //panel for the options and gallery
        JPanel tools = new JPanel();
        tools.setPreferredSize(new Dimension(310, this.HEIGHT));
        Border b =  BorderFactory.createLineBorder(Color.blue,1);
        tools.setBorder(b);
        tools.setLayout(new GridLayout(2,1));
        tools.setBackground(Color.BLACK);






        //panel for the gallery
        Gallery gallery = new Gallery();
        gallery.setPreferredSize(new Dimension(600,this.HEIGHT/2));


        ControlPanel controls = new ControlPanel(drawingArea,this,gallery);
        controls.setPreferredSize(new Dimension(200,this.HEIGHT/2));

        //tool panel
        tools.add(controls);
        tools.add(gallery);

        window.add(drawingArea, BorderLayout.CENTER);
        window.add(tools, BorderLayout.EAST);


        this.add(window);

        this.pack();

        //drawingArea.repaint();
    }
}
