import javax.swing.*;

/**
 * Created by IoIoTo on 7.3.2017 г..
 */
public class DigitalDoilly {

    DigitalDoilyWindow mainWindow;
    public DigitalDoilly()
    {
       mainWindow = new DigitalDoilyWindow(1067,800,"Digital Doily");
    }

    public void init()
    {
        mainWindow.init();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DigitalDoilly().init();
            }
        });


    }
}
