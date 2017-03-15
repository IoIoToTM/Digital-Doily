import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
                    drawingArea.setDrawingColor(newColor);
                    drawingArea.getPointList().setColor(newColor);
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
                drawingArea.getStrokes().removeAll(drawingArea.getStrokes());
                System.out.println("dddddd");
                drawingArea.repaint();
            }
        });


        add(clear);

        SpinnerModel penSize = new SpinnerNumberModel(drawingArea.getDrawingPenSize(),1,20,1);

        JSpinner penSizeSpinner = new JSpinner(penSize);
        penSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner test = (JSpinner) e.getSource();
                drawingArea.setDrawingPenSize( (int) test.getValue());
                drawingArea.getPointList().setPenSize((int) test.getValue());
                drawingArea.repaint();


                System.out.println(test.getValue().toString());
            }
        });

        add(penSizeSpinner);

        SpinnerModel numOfSectors= new SpinnerNumberModel(drawingArea.getNumberOfSectors(),1,100,1);
        JSpinner sectorSpinner = new JSpinner(numOfSectors);
        sectorSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner test = (JSpinner) e.getSource();
                drawingArea.setNumberOfSectors((int) test.getValue());

                drawingArea.setAngle( 360/(double)drawingArea.getNumberOfSectors());

                drawingArea.repaint();

            }
        });
        add(sectorSpinner);
        JButton toggleSectors = new JButton("Turn Sector Lines off");
        toggleSectors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingArea.setShowSectorLines(!drawingArea.isShowSectorLines());
                JButton temp = (JButton)e.getSource();
                if(drawingArea.isShowSectorLines())
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
                drawingArea.setReflected(!drawingArea.isReflected());

                JButton temp = (JButton)e.getSource();
                if(drawingArea.isReflected())
                {
                    temp.setText("Turn Reflection off");
                }
                else temp.setText("Turn Reflection on");

                drawingArea.getPointList().setReflected(drawingArea.isReflected());
                drawingArea.repaint();
            }
        });
        add(toggleReflection);


        JButton undo = new JButton("Undo");

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!drawingArea.getStrokes().isEmpty())
                drawingArea.getStrokes().remove(drawingArea.getStrokes().size()-1);
                drawingArea.repaint();
            }
        });

        add(undo);
        JButton saveDoily = new JButton("Save to gallery");
        saveDoily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage temp = drawingArea.copyDrawingArea(drawingArea);
                gallery.addImage(temp);
                gallery.repaint();

            }
        });

        add(saveDoily);


        /*this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println("TYOE");

                if(e.getKeyCode()==KeyEvent.VK_Z && e.isControlDown())
                {

                    System.out.println("CONTROL");
                    if(!DrawingArea.strokes.isEmpty())
                        DrawingArea.strokes.remove(DrawingArea.strokes.size()-1);
                    drawingArea.repaint();
                }
            }
        });*/

    }

}
