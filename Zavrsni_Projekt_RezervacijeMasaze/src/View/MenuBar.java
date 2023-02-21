package View;

import Controller.Controller;
import Controller.AbstractComand;
import Controller.ClearRowCMND;
import Controller.ClearTable;
import Controller.DeleteTableCMND;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar {
    private JMenuBar menuBar;

    private JMenu server;
    private JMenuItem save2Server;
    private JMenuItem uploadFromServer;
    private JMenuItem exit;

    private JMenu edit;
    private JMenuItem clearRow;
    private JMenuItem clearTable;
    private JMenuItem deleteTable;
    private JMenuItem undo;
    private JMenuItem redo;
    private Controller controller;
    private DataPanelListener dpl;

    /**
     * ClearRow Command.
     */
    private ClearRowCMND clearRowCMND;
    /**
     * ClearTable Command.
     */
    private ClearTable clearTableCmnd;
    /**
     * DeleteTable Command.
     */
    private DeleteTableCMND deleteTableCMND;

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
        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

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
        server.add(exit);

        edit.add(clearRow);
        edit.add(clearTable);
        edit.add(deleteTable);
        edit.addSeparator();
        edit.add(undo);
        edit.add(redo);

        menuBar.add(server);
        menuBar.add(edit);

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setDpl(DataPanelListener dpl) {
        this.dpl = dpl;
    }

    public void setClearRowCMND(ClearRowCMND clearRowCMND) {
        this.clearRowCMND = clearRowCMND;
    }

    public void setClearTableCmnd(ClearTable clearTableCmnd) {
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
