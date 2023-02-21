package View;


import Controller.AbstractComand;
import Controller.ClearRowCMND;
import Controller.ClearTable;
import Controller.DeleteTableCMND;
import Model.Rezervation;
import Model.TimeEnum;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class UrediRezervacije extends JFrame {
    /**
     * MenuBar.
     */
    private MenuBar menuBar;
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

    private final ClearRowCMND clearRowCMND = new ClearRowCMND(this);
    private final ClearTable clearTable = new ClearTable(this);
    private final DeleteTableCMND deleteTableCMND = new DeleteTableCMND(this);
    private AbstractComand activeComand;

    private Stack<AbstractComand> undoCommand;
    private Stack<AbstractComand> redoCommand;
    private Stack<List<Rezervation>> undoList;
    private Stack<List<Rezervation>> redoList;

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

        menuBar = new MenuBar();

        toolBar = new ToolBar();


        stvoriPanel = new JPanel();
        stvoriPanel.setBackground(Color.BLACK);
        stvoriPanel.setSize(1000,100);
        stvoriPanel.add(dateChooser);
        stvoriPanel.add(stvori);
        stvoriPanel.add(trazi);



        rezervations = new ArrayList<>();

        menuBar.setClearRowCMND(clearRowCMND);
        menuBar.setClearTableCmnd(clearTable);
        menuBar.setDeleteTableCMND(deleteTableCMND);

        toolBar.setClearRowCMND(clearRowCMND);
        toolBar.setClearTable(clearTable);
        toolBar.setDeleteTableCMND(deleteTableCMND);

        activeComand = clearRowCMND;
        undoCommand = new Stack<>();
        redoCommand = new Stack<>();
        undoList = new Stack<>();
        redoList = new Stack<>();
    }


    private void layoutAll() {
        setJMenuBar(menuBar.getMenuBar());
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

    /**
     * Undo comand.
     */
    public void undo(){
        if(!(undoList.isEmpty() & undoCommand.isEmpty())){
            redoList.add(rezervations);
            redoCommand.add(activeComand);

            activeComand = undoCommand.pop();
            toolBar.setActiveComand(activeComand);
            menuBar.setActiveComand(activeComand);
            rezervations = undoList.pop();

            DataEvent dataEvent = new DataEvent(this,rezervations);
            dataPanelListenerCreate.dataPanelEventOccured(dataEvent);
        } else{
            JOptionPane.showMessageDialog(this, "Nothing to undo");
        }
    }

    /**
     * RedoComand.
     */
    public void redo(){
        if(!(redoList.isEmpty() & redoCommand.isEmpty())){
            undoList.add(rezervations);
            undoCommand.add(activeComand);

            activeComand = redoCommand.pop();
            toolBar.setActiveComand(activeComand);
            menuBar.setActiveComand(activeComand);
            rezervations = redoList.pop();

            DataEvent dataEvent = new DataEvent(this,rezervations);
            dataPanelListenerCreate.dataPanelEventOccured(dataEvent);
        }else{
            JOptionPane.showMessageDialog(this, "Nothing to redo!!!");
        }
    }

    /**
     * Method to delete everything from table.
     */
    public void deleteTable() {
        if(! rezervations.isEmpty()) {
            undoList.add(rezervations);
            undoCommand.add(activeComand);
            rezervations = new ArrayList<>();
            activeComand = deleteTableCMND;
            toolBar.setActiveComand(activeComand);
            menuBar.setActiveComand(activeComand);

            DataEvent dataEvent = new DataEvent(this, rezervations);
            dataPanelListenerCreate.dataPanelEventOccured(dataEvent);
        }else{
            JOptionPane.showMessageDialog(this, "Table is empty!!!");
        }
    }

    /**
     * Method to clearReserved massages.
     */
    public void clearTable() {
        if(! rezervations.isEmpty()) {
            List<Rezervation> clearedRezervations = new ArrayList<>();
            for (int x = 0; x < rezervations.size(); x++) {
                Rezervation rez4Table = rezervations.get(x);
                String date = rez4Table.getDay();
                Rezervation rezervation = new Rezervation("", "", "", "",
                        1, "", false, false, String.valueOf(TimeEnum.values()[x]),
                        date, null);
                clearedRezervations.add(rezervation);
            }

            undoList.add(rezervations);
            rezervations = clearedRezervations;
            undoCommand.add(activeComand);
            activeComand = clearTable;
            toolBar.setActiveComand(activeComand);
            menuBar.setActiveComand(activeComand);

            DataEvent dataEvent = new DataEvent(this, rezervations);
            dataPanelListenerCreate.dataPanelEventOccured(dataEvent);
        }else{
            JOptionPane.showMessageDialog(this, "Table is empty!!!");
        }
    }

    /**
     * Method that clears that one reservation.
     */
    public void clearRow() {
        if(! rezervations.isEmpty()) {
            List<Rezervation> clearedRezervations = new ArrayList<>();
            int row = tablica.getSelectedRow();
            if(row != -1) {
                for (int x = 0; x < rezervations.size(); x++) {
                    if (x == row) {
                        Rezervation rez4Table = rezervations.get(x);
                        String date = rez4Table.getDay();
                        Rezervation rezervation = new Rezervation("", "", "", "",
                                1, "", false, false, String.valueOf(TimeEnum.values()[x]),
                                date, null);
                        clearedRezervations.add(rezervation);
                    } else{
                        clearedRezervations.add(rezervations.get(x));
                    }
                }
                undoList.add(rezervations);
                rezervations = clearedRezervations;
                undoCommand.add(activeComand);
                activeComand = clearRowCMND;
                toolBar.setActiveComand(activeComand);
                menuBar.setActiveComand(activeComand);

                DataEvent dataEvent = new DataEvent(this, rezervations);
                dataPanelListenerCreate.dataPanelEventOccured(dataEvent);
            }else{
                JOptionPane.showMessageDialog(this, "Please select row to clearRow!!!");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Table is empty!!!");
        }
    }
}
