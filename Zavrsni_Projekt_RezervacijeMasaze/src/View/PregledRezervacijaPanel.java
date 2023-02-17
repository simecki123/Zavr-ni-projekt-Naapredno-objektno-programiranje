package View;

import Model.Rezervation;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class PregledRezervacijaPanel extends JFrame {
    private Tablica tablicaPregleda;
    private JScrollPane scTablePanel;
    private AbstractTableModel dtm;
    private JDateChooser dateChooser;
    private JButton pretrazi;
    private JPanel panelZaDatum;

    private List<Rezervation> rezervations;


    public PregledRezervacijaPanel(){
        super("View ofReservations");
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
        tablicaPregleda = new Tablica();
        scTablePanel = new JScrollPane(tablicaPregleda, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scTablePanel.setBackground(Color.BLACK);
        dateChooser = new JDateChooser();
        pretrazi = new JButton("Search");
        pretrazi.setBackground(Color.CYAN);
        rezervations = new ArrayList<>();
        panelZaDatum = new JPanel();
        panelZaDatum.setBackground(Color.BLACK);
        panelZaDatum.add(dateChooser);
        panelZaDatum.add(pretrazi);
    }






    private void layoutAll() {
        add(panelZaDatum, BorderLayout.NORTH);
        add(scTablePanel, BorderLayout.CENTER);

    }

    private void activateApp() {

    }
}
