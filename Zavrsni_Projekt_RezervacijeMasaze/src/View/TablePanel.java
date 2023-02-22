package View;



import javax.swing.*;
import java.awt.*;

/**
 * Panel that creates table.
 */
public class TablePanel extends JPanel{

    /**
     * Our table.
     */
    private Tablica tablica;
    /**
     * Scroll pane that contains table.
     */
    private JScrollPane scTablePanel;





    public TablePanel(){
        Dimension dim = getPreferredSize();
        dim.height = 210;
        setPreferredSize(dim);
        initAll();
        layoutComps();

    }



    private void initAll() {
        this.tablica = new Tablica();
        scTablePanel = new JScrollPane(tablica, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


    }

    public Tablica getTablica() {
        return tablica;
    }


    private void layoutComps() {
        setBackground(Color.BLACK);
        add(scTablePanel, BorderLayout.CENTER);




    }





}
