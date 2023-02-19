package View;

import Model.Rezervation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TablePanel extends JPanel{

    private Tablica tablica;
    private JScrollPane scTablePanel;



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



        rezervations = new ArrayList<>();

    }

    public Tablica getTablica() {
        return tablica;
    }


    //public void setDBData(DataBase db) {
    //    programmers = db.getAll4DB();
    //}

    private void layoutComps() {
        setBackground(Color.BLACK);
        add(scTablePanel, BorderLayout.CENTER);




    }

    private void activateDataPanel() {

    }



}
