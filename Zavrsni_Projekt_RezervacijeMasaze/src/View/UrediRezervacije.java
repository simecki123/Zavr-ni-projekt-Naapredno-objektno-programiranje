package View;


import Model.Rezervation;
import Model.TimeEnum;
import com.toedter.calendar.JDateChooser;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class UrediRezervacije extends JFrame {
    /**
     * ToolBar class.
     */
    private ToolBar toolBar;
    /**
     * Panel.
     */
    private JPanel stvoriPanel;

    /**
     * Table.
     */
    private Tablica tablica;

    /**
     * ScrollPane that holds table.
     */
    private JScrollPane jScrollPane;

    /**
     * Date Chooser.
     */
    private JDateChooser dateChooser;
    /**
     * Button
     */
    private JButton stvori;
    /**
     * Button
     */
    private JButton trazi;


    /**
     * Reservations from dataBase and new that ve create.
     */
    private List<Rezervation> rezervations;
    /**
     * DataPanelListener.
     */
    private DataPanelListener dataPanelListenerCreate;
    /**
     * DataPanelListener.
     */
    private DataPanelListener dataPanelSearch;
    /**
     * SimpleDataFormat.
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

        toolBar = new ToolBar();


        stvoriPanel = new JPanel();
        stvoriPanel.setBackground(Color.BLACK);
        stvoriPanel.setSize(1000,100);
        stvoriPanel.add(dateChooser);
        stvoriPanel.add(stvori);
        stvoriPanel.add(trazi);



        rezervations = new ArrayList<>();
    }


    private void layoutAll() {
        add(toolBar.getToolBar(),BorderLayout.NORTH);
        add(jScrollPane, BorderLayout.CENTER);
        add(stvoriPanel, BorderLayout.SOUTH);
    }


    private void activateApp() {
        stvori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dateChooser.getDate() != null) {
                    boolean vr = provjeriValiditivnost();
                    if (vr) {
                        for (int x = 0; x < TimeEnum.values().length; x++) {
                            Rezervation rezervation = new Rezervation("", "", "", "",
                                    1, "", false, false, String.valueOf(TimeEnum.values()[x]),
                                    simpleDateFormat.format(dateChooser.getDate()), null);
                            rezervations.add(rezervation);
                        }

                        DataEvent dataEvent = new DataEvent(this, rezervations);
                        dataPanelListenerCreate.dataPanelEventOccured(dataEvent);
                        dateChooser.setDate(null);
                    } else{
                        JOptionPane.showMessageDialog(new JFrame(), "Rezervations with that date already exist!!!", "Warning",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                    JOptionPane.showMessageDialog(new JFrame(), "Date is not set!!!", "Warning",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        trazi.addActionListener(new ActionListener() {
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
                    dataPanelSearch.dataPanelEventOccured(dataEvent);
                    dateChooser.setDate(null);

                } else{
                    JOptionPane.showMessageDialog(new JFrame(), "Date is not set!!!", "Warning",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Method that checks if reseravtions with defined date already exist.
     * @return boolean value.
     */
    private boolean provjeriValiditivnost(){
        boolean vr = true;
        for(int x = 0; x < rezervations.size(); x++){
            Rezervation rezervation = rezervations.get(x);
            if(rezervation.getDay().equals(simpleDateFormat.format(dateChooser.getDate()))){
                vr = false;
            }
        }

        return vr;
    }

    public void setDataPanelListenerCreate(DataPanelListener dataPanelListenerCreate) {
        this.dataPanelListenerCreate = dataPanelListenerCreate;
    }

    public void setDataPanelSearch(DataPanelListener dataPanelSearch) {
        this.dataPanelSearch = dataPanelSearch;
    }

    public Tablica getTablica() {
        return tablica;
    }




    public void setRezervations(List<Rezervation> rezervations) {
        this.rezervations = rezervations;
    }
}
