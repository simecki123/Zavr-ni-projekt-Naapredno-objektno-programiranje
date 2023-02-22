package View;

import Controller.Controller;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Main Frame class.
 */
public class MainFrame extends JFrame {
    /**
     * Frame for view above all reservations.
     */
    private PregledRezervacijaPanel pregledRezervacijaPanel;
    /**
     * Frame that allows as to register reservations.
     */
    private UrediRezervacije urediRezervacije;
    /**
     * Frame that makes new reservations for some date.
     */
    private NapraviRezervacijeCLS napraviRezervacijeCLS;

    /**
     * Panel that holds some variables(Buttons).
     */
    private JPanel panelBottuns;
    /**
     * Button
     */
    private JButton botunUrediRezervacijeZaDatum;
    /**
     * Button
     */
    private JButton napraviRezervaciju;
    /**
     * Button
     */
    private JButton pregledRasporeda;

    /**
     * Our controller.
     */
    private Controller controller;


    public MainFrame(){
        super("MassageApp");
        setLayout(new BorderLayout());
        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initAll();
        layoutAll();
        activateApp();
    }



    private void initAll() {

        pregledRezervacijaPanel = new PregledRezervacijaPanel();
        urediRezervacije = new UrediRezervacije();
        napraviRezervacijeCLS = new NapraviRezervacijeCLS();

        panelBottuns = new JPanel();
        panelBottuns.setSize(600,100);
        botunUrediRezervacijeZaDatum = new JButton("Uredi Rezervacije");
        botunUrediRezervacijeZaDatum.setBackground(Color.CYAN);
        napraviRezervaciju = new JButton("Napravi Rezervacijju");
        napraviRezervaciju.setBackground(Color.CYAN);
        pregledRasporeda = new JButton("Pregled rezervacija");
        pregledRasporeda.setBackground(Color.CYAN);
        panelBottuns.add(botunUrediRezervacijeZaDatum);
        panelBottuns.add(napraviRezervaciju);
        panelBottuns.add(pregledRasporeda);
        panelBottuns.setBackground(Color.BLACK);

        controller = new Controller(urediRezervacije,pregledRezervacijaPanel, napraviRezervacijeCLS);
        napraviRezervacijeCLS.setController(controller);


    }

    private void layoutAll() {
        add(panelBottuns, BorderLayout.NORTH);


    }

    private void activateApp() {
        pregledRasporeda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pregledRezervacijaPanel.setVisible(true);
            }
        });

        botunUrediRezervacijeZaDatum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urediRezervacije.setVisible(true);
            }
        });
        napraviRezervaciju.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                napraviRezervacijeCLS.setVisible(true);
            }
        });

        urediRezervacije.setDataPanelListenerCreate(new DataPanelListener() {
            @Override
            public void dataPanelEventOccured(DataEvent dataEvent) {
                controller.addNewElementsInDatabase(dataEvent.getRezervations());
                controller.showDataOnTable(urediRezervacije.getTablica());
                controller.updateOtherPanels(dataEvent.getRezervations());

            }
        });
        urediRezervacije.setDataPanelSearch(new DataPanelListener() {
            @Override
            public void dataPanelEventOccured(DataEvent dataEvent) {
                controller.addElementsUserIsSearchingFor(dataEvent.getRezervations(), urediRezervacije.getTablica());
            }
        });

        urediRezervacije.setDplSave2Server(new DataPanelListener() {
            @Override
            public void dataPanelEventOccured(DataEvent dataEvent) {
                controller.connect2DB();
                controller.saveData2Server();
                controller.resetComands();

            }
        });
        urediRezervacije.setDplUploadDataFromServer(new DataPanelListener() {
            @Override
            public void dataPanelEventOccured(DataEvent dataEvent) {
                controller.connect2DB();
                controller.uploadDataFromServer();
                controller.resetComands();
            }
        });
        urediRezervacije.setDplDisonectFromServer(new DataPanelListener() {
            @Override
            public void dataPanelEventOccured(DataEvent dataEvent) {
                controller.disconect();
            }
        });

        pregledRezervacijaPanel.setPanelListenerSearch(new DataPanelListener() {
            @Override
            public void dataPanelEventOccured(DataEvent dataEvent) {
                controller.addElementsUserIsSearchingFor(dataEvent.getRezervations(), pregledRezervacijaPanel.getTable());
            }
        });






    }


}
