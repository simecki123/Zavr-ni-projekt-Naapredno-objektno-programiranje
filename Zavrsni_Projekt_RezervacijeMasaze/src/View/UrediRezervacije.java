package View;


import com.toedter.calendar.JDateChooser;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class UrediRezervacije extends JFrame {
    private Image image;
    private ImageIcon clearRowwIcon;
    private ImageIcon clearTableIcon;
    private ImageIcon deleteTableIcon;
    private ImageIcon undoIcon;
    private ImageIcon redoIcon;

    private JPanel stvoriPanel;
    private JToolBar editajPanel;

    private Tablica tablica;

    private JScrollPane jScrollPane;


    private JDateChooser dateChooser;
    private JButton stvori;
    private JButton trazi;

    private JButton clearRow;
    private JButton clearAll;
    private JButton deleteALL;
    private JButton undo;
    private JButton redo;

    public UrediRezervacije(){
        super("Edit Dates");
        setLayout(new BorderLayout());
        setSize(1000, 600);
        setVisible(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        initAll();
        layoutAll();
        activateApp();
    }

    private void initAll() {
        tablica = new Tablica();
        jScrollPane = new JScrollPane(tablica, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        dateChooser = new JDateChooser();
        stvori = new JButton("Create");
        stvori.setBackground(Color.CYAN);
        trazi = new JButton("Search");
        trazi.setBackground(Color.CYAN);

        setIcons();
        clearRow = new JButton(clearRowwIcon);
        clearAll = new JButton(clearTableIcon);
        deleteALL = new JButton(deleteTableIcon);
        undo = new JButton(undoIcon);
        redo = new JButton(redoIcon);


        stvoriPanel = new JPanel();
        stvoriPanel.setBackground(Color.BLACK);
        stvoriPanel.setSize(1000,100);
        stvoriPanel.add(dateChooser);
        stvoriPanel.add(stvori);
        stvoriPanel.add(trazi);

        editajPanel = new JToolBar();
        editajPanel.setSize(1000,100);
        editajPanel.add(clearRow);
        editajPanel.add(clearAll);
        editajPanel.add(deleteALL);
        editajPanel.add(undo);
        editajPanel.add(redo);
    }


    private void layoutAll() {
        add(editajPanel,BorderLayout.NORTH);
        add(jScrollPane, BorderLayout.CENTER);
        add(stvoriPanel, BorderLayout.SOUTH);
    }

    private void activateApp() {
    }

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
