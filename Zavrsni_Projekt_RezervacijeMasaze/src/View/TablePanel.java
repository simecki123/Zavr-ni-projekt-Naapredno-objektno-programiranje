package View;

import Model.Rezervation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TablePanel extends JPanel{
    private JPanel toolBar;
    private Tablica tablica;
    private JScrollPane scTablePanel;

    private JButton clearRow;
    private JButton clearTable;
    private JButton undo;
    private JButton redo;

    private List<Rezervation> rezervations;

    public TablePanel(){
        Dimension dim = getPreferredSize();
        dim.height = 210;
        setPreferredSize(dim);
        initAll();
        layoutComps();
        activateDataPanel();
    }



    private void initAll() {
        this.tablica = new Tablica();
        scTablePanel = new JScrollPane(tablica, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        clearRow = new JButton("Clear Row");
        clearTable = new JButton("ClearTable");
        undo = new JButton("Undo");
        redo = new JButton("Redo");
        toolBar = new JPanel();
        toolBar.add(clearRow);
        toolBar.add(clearTable);
        toolBar.add(undo);
        toolBar.add(redo);

        rezervations = new ArrayList<>();

    }









    //public void setDBData(DataBase db) {
    //    programmers = db.getAll4DB();
    //}

    private void layoutComps() {

        add(scTablePanel, BorderLayout.CENTER);
        add(toolBar, BorderLayout.SOUTH);



    }

    private void activateDataPanel() {

    }



}
