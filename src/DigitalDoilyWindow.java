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


    public DigitalDoilyWindow(int width, int height, String name) {

        super(name);

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

        //panel for the gallery
        Gallery gallery = new Gallery();
        gallery.setPreferredSize(new Dimension(100,this.HEIGHT/2));

        //control panel
        ControlPanel controls = new ControlPanel(drawingArea,this,gallery);
        controls.setPreferredSize(new Dimension(200,50));

        //add the drawing area, control panel and gallery to the window
        window.add(drawingArea, BorderLayout.CENTER);
        window.add(gallery,BorderLayout.EAST);
        window.add(controls,BorderLayout.NORTH);

        //add the main window JPanel to the JFrame
        this.add(window);


    }
}
