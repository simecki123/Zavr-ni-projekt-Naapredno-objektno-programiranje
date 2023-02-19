package View;

import Controller.Controller;
import Model.Rezervation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NapraviRezervacijeCLS extends JFrame {
    private DataPanel dataPanel;
    private TablePanel tablePanel;
    private Controller controller;


    
    public NapraviRezervacijeCLS(){
        super("Massage Reservation");
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
        dataPanel = new DataPanel();
        tablePanel = new TablePanel();
    }

    private void layoutAll() {
        add(dataPanel);
        add(tablePanel);
        setLayout(new GridLayout());
    }

    public void setRezervations(List<Rezervation> rezervations){
        dataPanel.setRezervationsFromDataBase(rezervations);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }



    private void activateApp() {
        dataPanel.setDplSearchDate(new DataPanelListener() {
            @Override
            public void dataPanelEventOccured(DataEvent dataEvent) {
                controller.addElementsUserIsSearchingFor(dataEvent.getRezervations(), tablePanel.getTablica());
            }
        });

        dataPanel.setDplSubmitRezervation(new DataPanelListener() {
            @Override
            public void dataPanelEventOccured(DataEvent dataEvent) {
                controller.addNewElementsInDatabase(dataEvent.getRezervations());
                controller.addElementsUserIsSearchingFor(dataEvent.getRezervations(), tablePanel.getTablica());
            }
        });
    }


}
