package View;

import Controller.AbstractComand;
import Controller.ClearRowCMND;
import Controller.ClearTableMND;
import Controller.DeleteTableCMND;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    /**
     * ClearRow Command.
     */
    private ClearRowCMND clearRowCMND;
    /**
     * ClearTable Command.
     */
    private ClearTableMND clearTable;
    /**
     * DeleteTable Command.
     */
    private DeleteTableCMND deleteTableCMND;
    /**
     * Active comand.
     */
    private AbstractComand activeComand;

    public ToolBar(){
        innitAll();
        activateAll();
    }
    public void innitAll(){
        setIcons();
        clearRow = new JButton(clearRowwIcon);
        clearAll = new JButton(clearTableIcon);
        deleteALL = new JButton(deleteTableIcon);
        undo = new JButton(undoIcon);
        redo = new JButton(redoIcon);

        toolBar = new JToolBar();
        toolBar.setSize(1000,100);
        toolBar.add(clearRow);
        toolBar.add(clearAll);
        toolBar.add(deleteALL);
        toolBar.add(undo);
        toolBar.add(redo);


    }

    public void activateAll(){
        clearRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRowCMND.execute();
            }
        });

        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable.execute();
            }
        });

        deleteALL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTableCMND.execute();
            }
        });

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(activeComand == null){
                    JOptionPane.showMessageDialog(new JFrame(), "Nothing to undo!!!");
                } else{
                    activeComand.undo();
                }
            }
        });
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(activeComand == null){
                    JOptionPane.showMessageDialog(new JFrame(), "Nothing to redo!!!");
                } else{
                    activeComand.redo();
                }
            }
        });
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

    public void setClearRowCMND(ClearRowCMND clearRowCMND) {
        this.clearRowCMND = clearRowCMND;
    }

    public void setClearTable(ClearTableMND clearTable) {
        this.clearTable = clearTable;
    }

    public void setDeleteTableCMND(DeleteTableCMND deleteTableCMND) {
        this.deleteTableCMND = deleteTableCMND;
    }

    public void setActiveComand(AbstractComand activeComand) {
        this.activeComand = activeComand;
    }
}
