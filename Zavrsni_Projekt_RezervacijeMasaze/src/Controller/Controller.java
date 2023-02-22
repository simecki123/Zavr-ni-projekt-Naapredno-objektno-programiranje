package Controller;

import Model.DataBase;
import Model.Rezervation;
import View.NapraviRezervacijeCLS;
import View.PregledRezervacijaPanel;
import View.Tablica;
import View.UrediRezervacije;

import java.util.List;

/**
 * Class that represents controller of this project.
 */
public class Controller {
    /**
     * Variable that contains all saved objects.
     */
    private final DataBase dataBase;
    /**
     * Variable that represents frame that needs to be updated.
     */
    private UrediRezervacije urediRezervacije;
    /**
     * Variable that represents frame that needs to be updated.
     */
    private PregledRezervacijaPanel pregledRezervacijaPanel;
    /**
     * Variable that represents frame that needs to be updated.
     */
    private NapraviRezervacijeCLS napraviRezervacijeCLS;

    public Controller(UrediRezervacije urediRezervacije, PregledRezervacijaPanel pregledRezervacijaPanel, NapraviRezervacijeCLS napraviRezervacijeCLS){
        dataBase = new DataBase();
        this.urediRezervacije = urediRezervacije;
        this.pregledRezervacijaPanel = pregledRezervacijaPanel;
        this.napraviRezervacijeCLS = napraviRezervacijeCLS;
    }

    /**
     * Method that adds new elements to DataBase.
     * @param rezervations List that contains Rezervation objects.
     */
    public void addNewElementsInDatabase(List<Rezervation> rezervations){
        dataBase.addNewRezervations2DB(rezervations);

    }

    /**
     * Method that shows database Objects on some defined table.
     * @param table Table that will contain DataBase objects.
     */
    public void showDataOnTable(Tablica table){
        table.showDataOnTable( dataBase);
    }


    /**
     * Method that presents on table only selected objects that are in DataBase.
     * @param rezervations List of wanted objects.
     * @param table Table that will contain wanted objects.
     */
    public void addElementsUserIsSearchingFor(List<Rezervation> rezervations, Tablica table) {
        table.setSearchedTable(rezervations);
    }

    /**
     * Method that updates all Classes, so they can hold newest DataBase objects.
     * @param rezervations
     */
    public void updateOtherPanels(List<Rezervation> rezervations) {

        urediRezervacije.setRezervations(rezervations);
        pregledRezervacijaPanel.setRezervations(rezervations);
        napraviRezervacijeCLS.setRezervations(rezervations);
        showDataOnTable(urediRezervacije.getTablica());
        addElementsUserIsSearchingFor(dataBase.getAllFromDB(),pregledRezervacijaPanel.getTable());
        addElementsUserIsSearchingFor(dataBase.getAllFromDB(), napraviRezervacijeCLS.getTable());

    }

    /**
     * Method for connecting to Data Base.
     */
    public void connect2DB(){
        dataBase.connect();
    }

    /**
     * method for disconnection from Data Base.
     */
    public void disconect(){
        dataBase.disconnect();
    }

    /**
     * Method for downloading data from server.
     */
    public void uploadDataFromServer(){
        dataBase.uploadFromDB();
        updateOtherPanels(dataBase.getAllFromDB());
    }

    /**
     * Method to reset commands from ToolBar.
     */
    public void resetComands(){
        urediRezervacije.resetComands();
    }

    /**
     * Method to save current table to DataBase.
     */
    public void saveData2Server() {
        dataBase.save2Server();
    }
}
