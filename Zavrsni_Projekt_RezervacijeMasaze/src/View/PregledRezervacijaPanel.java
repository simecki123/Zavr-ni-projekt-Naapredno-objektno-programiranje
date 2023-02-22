package View;

import Model.Rezervation;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class PregledRezervacijaPanel extends JFrame {
    /**
     * Our table.
     */
    private Tablica tablicaPregleda;
    /**
     * Scroll table that contains table.
     */
    private JScrollPane scTablePanel;
    /**
     * Date Chooser.
     */
    private JDateChooser dateChooser;
    /**
     * Button.
     */
    private JButton pretrazi;
    /**
     * Panel that holds date and button.
     */
    private JPanel panelZaDatum;

    /**
     * All objects from dataBase.
     */
    private List<Rezervation> rezervations;
    /**
     * DataPanelListener.
     */
    private DataPanelListener panelListenerSearch;
    /**
     * Simple date format.
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

    public void setRezervations(List<Rezervation> rezervations) {
        this.rezervations = rezervations;
    }

    public void setPanelListenerSearch(DataPanelListener panelListenerSearch) {
        this.panelListenerSearch = panelListenerSearch;
    }

    private void layoutAll() {
        add(panelZaDatum, BorderLayout.NORTH);
        add(scTablePanel, BorderLayout.CENTER);

    }

    public Tablica getTable() {
        return tablicaPregleda;
    }

    private void activateApp() {
        pretrazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dateChooser.getDate() != null) {
                    List<Rezervation> odabraneRezervacije = new ArrayList<>();
                    for(Rezervation rez : rezervations){
                        if(rez.getDay().equals(simpleDateFormat.format(dateChooser.getDate()))){
                            odabraneRezervacije.add(rez);
                        }
                    }
                    DataEvent dataEvent = new DataEvent(this,odabraneRezervacije);
                    panelListenerSearch.dataPanelEventOccured(dataEvent);
                    dateChooser.setDate(null);

                } else{
                    JOptionPane.showMessageDialog(new JFrame(), "Date is not set!!!", "Warning",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }


}
