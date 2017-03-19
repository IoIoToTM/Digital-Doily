import javax.swing.*;
import javax.swing.border.Border;
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

    public ControlPanel(DrawingArea drawingArea,JFrame window,Gallery gallery)
    {
        super();

        //gray border for components
        Border grayBorder = BorderFactory.createLineBorder(Color.darkGray, 1);

        this.setBorder(grayBorder);
        this.setBackground(Color.lightGray);
        this.drawingArea = drawingArea;
        setLayout(new FlowLayout());

        //making a color chooser with the built in JColorChooser
        JButton chooseColor = new JButton("Choose Color");

        chooseColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(
                        window,
                        "Choose Background Color",
                        window.getBackground());
                if(newColor != null){
                    drawingArea.setDrawingColor(newColor);
                    drawingArea.getPointList().setColor(newColor);
                }
                drawingArea.repaint();
            }
        });

        //clear button that removes all strokes from the arraylist
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               drawingArea.clearDrawing();
            }
        });


        //spinner for the pen size
        SpinnerModel penSize = new SpinnerNumberModel(drawingArea.getDrawingPenSize(),1,20,1);
        JSpinner penSizeSpinner = new JSpinner(penSize);
        penSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner temp = (JSpinner) e.getSource();

                drawingArea.setDrawingPenSize( (int) temp.getValue());

            }
        });

        JLabel penSizeText = new JLabel("Pen Size");
        JPanel penSizePanel = new JPanel(new GridLayout(2,1));
        penSizePanel.setBackground(Color.lightGray);
        penSizePanel.add(penSizeText);
        penSizePanel.add(penSizeSpinner);

        //spinner for the number of sectors
        SpinnerModel numOfSectors = new SpinnerNumberModel(drawingArea.getNumberOfSectors(),1,100,1);
        JSpinner sectorSpinner = new JSpinner(numOfSectors);
        sectorSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner sectorSpinner = (JSpinner) e.getSource();
                drawingArea.setNumberOfSectors((int) sectorSpinner.getValue());
                drawingArea.repaint();
            }
        });

        JLabel numberOfSectorsText = new JLabel("Sectors");
        JPanel sectorPanel = new JPanel(new GridLayout(2,1));
        sectorPanel.setBackground(Color.lightGray);
        sectorPanel.add(numberOfSectorsText);
        sectorPanel.add(sectorSpinner);

        //toggle button for showing the sector lines
        JButton toggleSectors = new JButton("Turn Sector Lines off");
        toggleSectors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingArea.setShowSectorLines(!drawingArea.isShowSectorLines());
                JButton sectorToggle = (JButton)e.getSource();
                if(drawingArea.isShowSectorLines())
                {
                    sectorToggle.setText("Turn Sector Lines off");
                }
                else sectorToggle.setText("Turn Sector Lines on");
                drawingArea.repaint();
            }
        });

        //toggle button for reflecting
        JButton toggleReflection = new JButton("Turn Refection on");
        toggleReflection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingArea.setReflected(!drawingArea.isReflected());

                JButton reflectionToggle = (JButton)e.getSource();
                if(drawingArea.isReflected())
                {
                    reflectionToggle.setText("Turn Reflection off");
                }
                else reflectionToggle.setText("Turn Reflection on");

                drawingArea.getPointList().setReflected(drawingArea.isReflected());
                drawingArea.repaint();
            }
        });

        //undo button that removes the last element from the arraylist of strokes
        JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                drawingArea.undoLastAction();
            }
        });

        //button for saving the current doily on to the gallery
        JButton saveDoily = new JButton("Save to gallery");
        saveDoily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage temp = drawingArea.copyDrawingArea();
                gallery.addImage(temp);
                gallery.repaint();

            }
        });

        //button for removing selected drawing from the gallery
        JButton removeFromGallery = new JButton("Please select what to remove");
        gallery.setRemoveButton(removeFromGallery);
        removeFromGallery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gallery.getSelected()==null)
                {
                    return;
                }
                else
                {
                    Border grayBorder = BorderFactory.createLineBorder(Color.darkGray, 1);
                    JButton removeButton = (JButton)e.getSource();
                    removeButton.setText("Please select what to remove");

                    JLabel selected = gallery.getSelected();
                    selected.setBorder(grayBorder);
                    selected.setIcon(null);
                    gallery.setSelected(null);
                    gallery.updateArray();
            }}
        });

        add(chooseColor);
        add(penSizePanel);
        add(sectorPanel);
        add(undo);
        add(clear);
        add(toggleReflection);
        add(toggleSectors);
        add(saveDoily);
        add(removeFromGallery);
    }

}
