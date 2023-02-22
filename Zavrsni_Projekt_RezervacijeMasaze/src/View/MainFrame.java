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
     * Panel for picture.
     */
    private JPanel slikaPanel;
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

        controller = new Controller(urediRezervacije,pregledRezervacijaPanel, napraviRezervacijeCLS);
        napraviRezervacijeCLS.setController(controller);


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
