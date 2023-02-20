package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

    private JMenu server;
    private JMenuItem save2Server;
    private JMenuItem uploadFromServer;
    private JMenuItem exit;
    private Controller controller;
    private DataPanelListener dpl;

    public MenuBar(){
        initAll();

        activateApp();
    }

    private void initAll() {
        server = new JMenu("Server");
        save2Server = new JMenuItem("Save to server");
        uploadFromServer = new JMenuItem("Upload from server");
        exit = new JMenuItem("Exit");

        server.add(save2Server);
        server.add(uploadFromServer);
        server.addSeparator();
        server.add(exit);

        add(server);

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setDpl(DataPanelListener dpl) {
        this.dpl = dpl;
    }

    private void activateApp() {
        
    }


}
