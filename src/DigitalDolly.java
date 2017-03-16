import javax.swing.*;

/**
 * Created by IoIoTo on 7.3.2017 г..
 */
public class DigitalDolly {

    DigitalDoilyWindow mainWindow;
    public DigitalDolly()
    {
       mainWindow = new DigitalDoilyWindow(1024,600,"Digital Doily");
    }

    public void init()
    {
        mainWindow.init();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DigitalDolly digitalDolly = new DigitalDolly();
                digitalDolly.init();
            }
        });


    }
}
