package View;

import Controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class MainFrame extends JFrame {
    private PregledRezervacijaPanel pregledRezervacijaPanel;
    private UrediRezervacije urediRezervacije;
    private NapraviRezervacijeCLS napraviRezervacijeCLS;

    private JPanel panelBottuns;
    private JButton botunUrediRezervacijeZaDatum;
    private JButton napraviRezervaciju;
    private JButton pregledRasporeda;
    private JPanel slikaPanel;
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

        slikaPanel = new JPanel();
        slikaPanel.setSize(600,400);
        slikaPanel.setBackground(Color.CYAN);

        File file = new File("Zavrsni_Projekt_RezervacijeMasaze/src/Slike/za projekt _Marin.jpeg");
        Image img;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not load \"" + file + "\"", e);
        }

        BufferedImage scaledImage = new BufferedImage(
                ((BufferedImage) img).getColorModel(),
                ((BufferedImage) img).getRaster().createCompatibleWritableRaster(600, 400),
                false, new Properties());

        Graphics g = scaledImage.createGraphics();
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_SPEED);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        g.drawImage(img, 0, 0, 600, 400, null);
        g.dispose();


        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        slikaPanel.add(picLabel);

        controller = new Controller();


    }

    private void layoutAll() {
        add(panelBottuns, BorderLayout.NORTH);
        add(slikaPanel, BorderLayout.CENTER);

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
                controller.addNewElementsInDatabase(dataEvent.getRezervations(),urediRezervacije.getTablica());

            }
        });
        urediRezervacije.setDataPanelSearch(new DataPanelListener() {
            @Override
            public void dataPanelEventOccured(DataEvent dataEvent) {
                controller.addElementsUserIsSearchingFor(dataEvent.getRezervations(), urediRezervacije.getTablica());
            }
        });
    }


}
