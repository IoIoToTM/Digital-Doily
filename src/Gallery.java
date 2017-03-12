import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IoIoTo on 12.3.2017 г..
 */
public class Gallery extends JPanel {

    public Gallery()
    {
        super();
        setLayout(new GridLayout(4, 3));
        /*for(int i =1 ; i<=12;i++)
        {
            add(new JButton("number " +i));
        }*/
    }
}
