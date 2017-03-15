import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
        JButton chooseColor = new JButton("Choose Color");

        chooseColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(
                        test,
                        "Choose Background Color",
                        test.getBackground());
                if(newColor != null){
                    System.out.println(newColor.getBlue());
                    drawingArea.drawingColor = newColor;
                    drawingArea.pointList.setColor(newColor);
                }
                drawingArea.repaint();
            }
        });

        add(chooseColor);


        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // DigitalDoilyWindow.pointList.removeAll(DigitalDoilyWindow.pointList);
                DrawingArea.strokes.removeAll(DrawingArea.strokes);
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
                drawingArea.pointList.setPenSize((int) test.getValue());
                drawingArea.repaint();


                System.out.println(test.getValue().toString());
            }
        });

        add(penSizeSpinner);

        SpinnerModel numOfSectors= new SpinnerNumberModel(drawingArea.numberOfSectors,1,100,1);
        JSpinner sectorSpinner = new JSpinner(numOfSectors);
        sectorSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner test = (JSpinner) e.getSource();
                drawingArea.numberOfSectors= (int) test.getValue();

                drawingArea.angle = 360/(double)drawingArea.numberOfSectors;

                drawingArea.repaint();

            }
        });
        add(sectorSpinner);
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

        JButton toggleReflection = new JButton("Turn Refection on");
        toggleReflection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawingArea.reflected = !DrawingArea.reflected;

                JButton temp = (JButton)e.getSource();
                if(DrawingArea.reflected)
                {
                    temp.setText("Turn Reflection off");
                }
                else temp.setText("Turn Reflection on");

                drawingArea.pointList.setReflected(DrawingArea.reflected);
                drawingArea.repaint();
            }
        });
        add(toggleReflection);


        JButton undo = new JButton("Undo");

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!DrawingArea.strokes.isEmpty())
                DrawingArea.strokes.remove(DrawingArea.strokes.size()-1);
                drawingArea.repaint();
            }
        });

        add(undo);
        JButton saveDoily = new JButton("Save to gallery");
        saveDoily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage temp = drawingArea.getScreenShot(drawingArea);
                BufferedImage test = resize(temp,100,100);

                gallery.add(new JLabel(new ImageIcon(temp)));
                gallery.repaint();

            }
        });

        add(saveDoily);

    }
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
}
