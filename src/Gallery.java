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

    private ArrayList<JLabel> gallery;
    private int pointer;

    public Gallery()
    {
        super();

        pointer = 0;
        gallery = new ArrayList<JLabel>();
        setLayout(new GridLayout(3, 4));
        for(int i =1 ; i<=12;i++)
        {

            gallery.add(new JLabel());
        }

        for(JLabel i : gallery)
        {
            add(i);
        }
    }

    public void addImage(BufferedImage gallery)
    {

        JLabel current = this.gallery.get(pointer);
        BufferedImage test = resize(gallery,current.getWidth(),current.getHeight());

        this.gallery.get(pointer).setIcon(new ImageIcon(test));
        pointer++;
        if(pointer>11)
        {
            pointer=0;
        }
    }
    public static BufferedImage resize(BufferedImage image, double width, double height) {
        BufferedImage bi = new BufferedImage((int)width, (int)height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0,(int) width, (int)height, null);
        g2d.dispose();
        return bi;
    }

    /*public void addImage(BufferedImage toGallery)
    {
        BufferedImage resized = (BufferedImage) toGallery.getScaledInstance(gallery.get(1).getWidth(),gallery.get(1).getHeight(),Image.SCALE_SMOOTH);
        gallery.get(1).add();
    }*/
}
