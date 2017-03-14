import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by IoIoTo on 12.3.2017 г..
 */
public class Gallery extends JPanel {

    ArrayList<JLabel> gallery;

    public Gallery()
    {
        super();
        gallery = new ArrayList<JLabel>();
        setLayout(new GridLayout(3, 4));
        /*for(int i =1 ; i<=12;i++)
        {
           gallery.add(new JLabel());
        }*/
    }

    /*public void addImage(BufferedImage toGallery)
    {
        BufferedImage resized = (BufferedImage) toGallery.getScaledInstance(gallery.get(1).getWidth(),gallery.get(1).getHeight(),Image.SCALE_SMOOTH);
        gallery.get(1).add();
    }*/
}
