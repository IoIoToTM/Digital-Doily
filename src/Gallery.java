import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by IoIoTo on 12.3.2017 г..
 */
public class Gallery extends JPanel {

    private ArrayList<JLabel> gallery;

    public JLabel getSelected() {
        return selected;
    }
    public void setSelected(JLabel selected)
    {
        this.selected = selected;
    }

    private JLabel selected;
    private int pointer;

    public void setRemoveButton(JButton removeButton) {
        this.removeButton = removeButton;
    }

    private JButton removeButton;

    class MouseGallery implements MouseListener
    {

        private ArrayList<JLabel> gallery;

        public MouseGallery(ArrayList<JLabel> gallery)
        {
            this.gallery = gallery;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

            Border border1 = BorderFactory.createLineBorder(Color.black, 1);
            for(JLabel i: gallery)
            {
                i.setBorder(border1);
            }
            JLabel temp = (JLabel) e.getSource();

            if(temp.getIcon()==null)
            {
                return;
            }

            if(selected==temp)
            {
                temp.setBorder(border1);
                selected = null;
                removeButton.setText("Please select what to remove");
            }
            else {
                Border border = BorderFactory.createLineBorder(Color.red, 2);
                temp.setBorder(border);
                selected = temp;
                removeButton.setText("Remove Selected");
            }
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
    }

    public void updateArray()
    {

        System.out.println("in");
        int size = gallery.size();
        ArrayList<JLabel> temp;

        for(int i = 0; i< size ; i++)
        {
            if(gallery.get(i).getIcon()==null)
            {
                for(int j = i; j< size -1; j++)
                {
                    gallery.get(j).setIcon(gallery.get(j+1).getIcon());
                }
                gallery.get(size-1).setIcon(null);
            }
        }



        /*for(int i =0; i<size-2;i++)
        {
            if(gallery.get(i).getIcon()==null)
            {
                System.out.println(i);
                for(int j = i+1;j<size-1;j++)
                {
                    Collections.swap(gallery,i,j);
                }
            }
        }*/


    }

    public Gallery()
    {
        super();

        selected = null;
        removeButton = null;

        pointer = 0;
        gallery = new ArrayList<JLabel>();
        setLayout(new GridLayout(4, 3));
        for(int i =1 ; i<=12;i++)
        {

            Border border = BorderFactory.createLineBorder(Color.black, 1);

            JLabel temp = new JLabel();
            temp.setBorder(border);
            temp.addMouseListener(new MouseGallery(gallery));

            /*temp.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    JLabel test = (JLabel) e.getSource();
                    test.setIcon(new ImageIcon());
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
            });*/

            gallery.add(temp);
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
        for(JLabel i : this.gallery)
        {

            if(i.getIcon() == null)
            {i.setIcon(new ImageIcon(test));
            break;
        }}
    }
    public static BufferedImage resize(BufferedImage image, double width, double height) {
       /* BufferedImage bi = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0,(int) width, (int)height, null);*/

        Image tmp = image.getScaledInstance((int)width,(int) height,Image.SCALE_SMOOTH);

        BufferedImage dimg = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
        //return bi;

    }

    /*public void addImage(BufferedImage toGallery)
    {
        BufferedImage resized = (BufferedImage) toGallery.getScaledInstance(gallery.get(1).getWidth(),gallery.get(1).getHeight(),Image.SCALE_SMOOTH);
        gallery.get(1).add();
    }*/
}
