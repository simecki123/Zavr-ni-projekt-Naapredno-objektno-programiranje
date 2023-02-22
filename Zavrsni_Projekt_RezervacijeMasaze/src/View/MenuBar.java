package View;


import Controller.AbstractComand;
import Controller.ClearRowCMND;
import Controller.ClearTableMND;
import Controller.DeleteTableCMND;
import Model.Rezervation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Menu Bar class that creates JMenuBar.
 */
public class MenuBar {
    /**
     * MenuBar.
     */
    private JMenuBar menuBar;
    /**
     * JMenu.
     */
    private JMenu server;
    /**
     * Menu item
     */
    private JMenuItem save2Server;
    /**
     * Menu item
     */
    private JMenuItem uploadFromServer;
    /**
     * Menu item
     */
    private JMenuItem Disconect4Server;
    /**
     * JMenu
     */
    private JMenu edit;
    /**
     * Menu item
     */
    private JMenuItem clearRow;
    /**
     * Menu item
     */
    private JMenuItem clearTable;
    /**
     * Menu item
     */
    private JMenuItem deleteTable;
    /**
     * Menu item
     */
    private JMenuItem undo;
    /**
     * Menu item
     */
    private JMenuItem redo;
    /**
     * Data panel listener.
     */
    private DataPanelListener dplSaveToServer;
    /**
     * Data panel listener.
     */
    private DataPanelListener dplUploadFromServer;
    /**
     * Data panel listener.
     */
    private DataPanelListener dplDisconectFromServer;
    /**
     * List of rezervations.
     */
    private List<Rezervation> rezervationList;

    /**
     * ClearRow Command.
     */
    private ClearRowCMND clearRowCMND;
    /**
     * ClearTable Command.
     */
    private ClearTableMND clearTableCmnd;
    /**
     * DeleteTable Command.
     */
    private DeleteTableCMND deleteTableCMND;

    /**
     * Active command.
     */
    private AbstractComand activeComand;

    public MenuBar(){
        initAll();

        activateApp();
    }

    private void initAll() {
        menuBar = new JMenuBar();
        server = new JMenu("Server");
        save2Server = new JMenuItem("Save to server");
        save2Server.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        uploadFromServer = new JMenuItem("Upload from server");
        uploadFromServer.setAccelerator(KeyStroke.getKeyStroke('U', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        Disconect4Server = new JMenuItem("Disconect from server");
        Disconect4Server.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        edit = new JMenu("Edit");
        clearRow = new JMenuItem("Clear Row");
        clearRow.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        clearTable = new JMenuItem("Clear Table");
        clearTable.setAccelerator(KeyStroke.getKeyStroke('T', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        deleteTable = new JMenuItem("Delete Table");
        deleteTable.setAccelerator(KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        server.add(save2Server);
        server.add(uploadFromServer);
        server.addSeparator();
        server.add(Disconect4Server);

        edit.add(clearRow);
        edit.add(clearTable);
        edit.add(deleteTable);
        edit.addSeparator();
        edit.add(undo);
        edit.add(redo);

        menuBar.add(server);
        menuBar.add(edit);

    }

    public void setRezervationList(List<Rezervation> rezervationList) {
        this.rezervationList = rezervationList;
    }

    public void setDplSaveToServer(DataPanelListener dplSaveToServer) {
        this.dplSaveToServer = dplSaveToServer;
    }

    public void setDplUploadFromServer(DataPanelListener dplUploadFromServer) {
        this.dplUploadFromServer = dplUploadFromServer;
    }

    public void setDplDisconectFromServer(DataPanelListener dplDisconectFromServer) {
        this.dplDisconectFromServer = dplDisconectFromServer;
    }

    public void setClearRowCMND(ClearRowCMND clearRowCMND) {
        this.clearRowCMND = clearRowCMND;
    }

    public void setClearTableCmnd(ClearTableMND clearTableCmnd) {
        this.clearTableCmnd = clearTableCmnd;
    }

    public void setDeleteTableCMND(DeleteTableCMND deleteTableCMND) {
        this.deleteTableCMND = deleteTableCMND;
    }

    public void setActiveComand(AbstractComand activeComand) {
        this.activeComand = activeComand;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    private void activateApp() {
        save2Server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataEvent dataEvent = new DataEvent(this, rezervationList);
                dplSaveToServer.dataPanelEventOccured(dataEvent);
            }
        });
        uploadFromServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataEvent dataEvent = new DataEvent(this, rezervationList);
                dplUploadFromServer.dataPanelEventOccured(dataEvent);
            }
        });
        Disconect4Server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataEvent dataEvent = new DataEvent(this, rezervationList);
                dplDisconectFromServer.dataPanelEventOccured(dataEvent);
            }
        });

        clearRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRowCMND.execute();
            }
        });
        clearTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTableCmnd.execute();
            }
        });

        deleteTable.addActionListener(new ActionListener() {
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


}
