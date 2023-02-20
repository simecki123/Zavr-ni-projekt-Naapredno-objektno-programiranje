package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ToolBar {
    /**
     * Image.
     */
    private Image image;
    /**
     * Icon.
     */
    private ImageIcon clearRowwIcon;
    /**
     * Icon.
     */
    private ImageIcon clearTableIcon;
    /**
     * Icon.
     */
    private ImageIcon deleteTableIcon;
    /**
     * Icon.
     */
    private ImageIcon undoIcon;
    /**
     * Icon.
     */
    private ImageIcon redoIcon;
    /**
     * Toolbar.
     */
    private JToolBar toolBar;
    /**
     * Clear row.
     */
    private JButton clearRow;
    /**
     * Button
     */
    private JButton clearAll;
    /**
     * Button
     */
    private JButton deleteALL;
    /**
     * Button
     */
    private JButton undo;
    /**
     * Button
     */
    private JButton redo;

    public ToolBar(){
        setIcons();
        clearRow = new JButton();
        clearAll = new JButton();
        deleteALL = new JButton();
        undo = new JButton();
        redo = new JButton();

        toolBar = new JToolBar();
        toolBar.setSize(1000,100);
        toolBar.add(clearRow);
        toolBar.add(clearAll);
        toolBar.add(deleteALL);
        toolBar.add(undo);
        toolBar.add(redo);
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    /**
     * Method that sets icons to out TollBar buttons.
     */
    private void setIcons(){
        try {
            clearRowwIcon = new ImageIcon();
            image = ImageIO.read(new File("Zavrsni_Projekt_RezervacijeMasaze/src/Slike/delete.png"));
            clearRowwIcon.setImage(image.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

            clearTableIcon = new ImageIcon();
            image = ImageIO.read(new File("Zavrsni_Projekt_RezervacijeMasaze/src/Slike/clear.png"));
            clearTableIcon.setImage(image.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

            deleteTableIcon = new ImageIcon();
            image = ImageIO.read(new File("Zavrsni_Projekt_RezervacijeMasaze/src/Slike/deleteTable.png"));
            deleteTableIcon.setImage(image.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

            undoIcon = new ImageIcon();
            image = ImageIO.read(new File("Zavrsni_Projekt_RezervacijeMasaze/src/Slike/undo.png"));
            undoIcon.setImage(image.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

            redoIcon = new ImageIcon();
            image = ImageIO.read(new File("Zavrsni_Projekt_RezervacijeMasaze/src/Slike/redo.png"));
            redoIcon.setImage(image.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
