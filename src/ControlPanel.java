import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Created by IoIoTo on 12.3.2017 г..
 */
public class ControlPanel extends JPanel {

    private DrawingArea drawingArea;

    public ControlPanel(DrawingArea drawingArea,JFrame test,Gallery gallery)
    {
        super();

        this.drawingArea = drawingArea;
        setLayout(new FlowLayout());
        setBackground(Color.YELLOW);
        JButton btn1 = new JButton("Choose Color");

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(
                        test,
                        "Choose Background Color",
                        test.getBackground());
                if(newColor != null){
                    System.out.println(newColor.getBlue());
                    drawingArea.drawingColor = newColor;
                }
            }
        });

        add(btn1);


        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // DigitalDoilyWindow.pointList.removeAll(DigitalDoilyWindow.pointList);
                drawingArea.pointList.removeAll(drawingArea.pointList);
                System.out.println("dddddd");
                drawingArea.repaint();
            }
        });


        add(clear);

        SpinnerModel penSize = new SpinnerNumberModel(drawingArea.drawingPenSize,1,20,1);

        JSpinner penSizeSpinner = new JSpinner(penSize);
        penSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner test = (JSpinner) e.getSource();
                drawingArea.drawingPenSize = (int) test.getValue();
                drawingArea.repaint();

                System.out.println(test.getValue().toString());
            }
        });

        add(penSizeSpinner);

        JButton toggleSectors = new JButton("Turn Sector Lines off");
        toggleSectors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingArea.showSectorLines = !drawingArea.showSectorLines;
                JButton temp = (JButton)e.getSource();
                if(drawingArea.showSectorLines)
                {
                    temp.setText("Turn Sector Lines off");
                }
                else temp.setText("Turn Sector Lines on");
                drawingArea.repaint();
            }
        });
        add(toggleSectors);

        JButton saveDoily = new JButton("Save to gallery");
        saveDoily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage temp = new BufferedImage(drawingArea.getWidth(),drawingArea.getHeight(),BufferedImage.TYPE_3BYTE_BGR);

                drawingArea.getGraphics().drawImage(temp,0,0,null);
                JLabel test = new JLabel(new ImageIcon(temp));
                add(test);
            }
        });

    }
}
