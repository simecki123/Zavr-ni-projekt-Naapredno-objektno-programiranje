package View;

import javax.swing.*;
import java.awt.*;

public class NapraviRezervacijeCLS extends JFrame {
    private DataPanel dataPanel;
    private TablePanel tablePanel;
    
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

    private void activateApp() {
    }
}
