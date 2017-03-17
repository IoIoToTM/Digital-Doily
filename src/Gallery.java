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

    private JLabel selected;

    private JButton removeButton;

    //inner class for the custom mouse listener
    class MouseGallery implements MouseListener
    {
       // private ArrayList<JLabel> gallery;

        public MouseGallery(ArrayList<JLabel> gallery)
        {
            //this.gallery = gallery;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
            //go through the whole array of gallery images (JLabels) and set their borders to black

            Border blackBorder = BorderFactory.createLineBorder(Color.black, 1);
            for(JLabel i: gallery)
            {
                i.setBorder(blackBorder);
            }
            
            //get the selected label
            JLabel clickedJLabel = (JLabel) e.getSource();

            //if it's an empty one (no image) then just return
            if(clickedJLabel.getIcon()==null)
            {
                return;
            }

            //if the selected is the same as the clicked, then unselect it
            if(selected==clickedJLabel)
            {
                clickedJLabel.setBorder(blackBorder);
                selected = null;
                removeButton.setText("Please select what to remove");
            }
            else {
                //else make it's border red and set the selected JLabel to the clicked one
                Border selectedBorder = BorderFactory.createLineBorder(Color.red, 2);
                clickedJLabel.setBorder(selectedBorder);
                selected = clickedJLabel;
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

    //function for updating the array of JLabels used to store them
    public void updateArray()
    {
        int size = gallery.size();

        //go through each element
        for(int i = 0; i< size ; i++)
        {
            //if the current element's icon is null start a loop to shift all elements to the left
            if(gallery.get(i).getIcon()==null)
            {
                for(int j = i; j< size -1; j++)
                {
                    //set the current icon to the next one
                    gallery.get(j).setIcon(gallery.get(j+1).getIcon());
                }
                //set the last one to null because it can't do it in the loop (it would throw index out of bounds exception
                gallery.get(size-1).setIcon(null);
            }
        }
    }

    public Gallery()
    {
        super();

        selected = null;
        removeButton = null;

        gallery = new ArrayList<JLabel>();
        //set the gallery to grid layout 4*3 so it shows 12 elements
        setLayout(new GridLayout(4, 3));

        //add 12 empty JLabels to the gallery
        for(int i =1 ; i<=12;i++)
        {

            Border blackBorder = BorderFactory.createLineBorder(Color.black, 1);

            //create a temporary JLabel, set a few parameters and add it to the gallery
            JLabel temp = new JLabel();
            temp.setBorder(blackBorder);
            temp.addMouseListener(new MouseGallery(gallery));
            gallery.add(temp);
        }

        //go through the gallery arraylist and add each JLabel to the JPanel
        for(JLabel i : gallery)
        {
            add(i);
        }
    }

    //add an image to the array
    public void addImage(BufferedImage toGallery)
    {

        //resize the passed buffered image using one of the JLabels sizes (in this case the first one)
        BufferedImage imageToAdd = resize(toGallery,gallery.get(0).getWidth(),gallery.get(0).getHeight());

        //iterating through the gallery arraylist and adding the image on the first instance of an empty image icon
        for(JLabel i : this.gallery)
        {

            if(i.getIcon() == null)
            {i.setIcon(new ImageIcon(imageToAdd));
            break;
        }}
    }
    public static BufferedImage resize(BufferedImage image, double width, double height) {

        //scale the buffered image to the given dimensions
        Image tempImage = image.getScaledInstance((int)width,(int) height,Image.SCALE_SMOOTH);


        BufferedImage resizedBI = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_ARGB);

        //get graphics and draw to them the image
        Graphics2D g2d = resizedBI.createGraphics();
        g2d.drawImage(tempImage, 0, 0, null);
        g2d.dispose();

        return resizedBI;
    }



    public void setRemoveButton(JButton removeButton) {
        this.removeButton = removeButton;
    }
    public JLabel getSelected() {
        return selected;
    }
    public void setSelected(JLabel selected)
    {
        this.selected = selected;
    }

}
