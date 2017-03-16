import javax.swing.*;

/**
 * Created by IoIoTo on 7.3.2017 г..
 */
public class DigitalDolly {

    public DigitalDolly()
    {
       DigitalDoilyWindow test =  new DigitalDoilyWindow(1024,600,"DigitalDoily");

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DigitalDolly test = new DigitalDolly();

            }
        });


    }
}
