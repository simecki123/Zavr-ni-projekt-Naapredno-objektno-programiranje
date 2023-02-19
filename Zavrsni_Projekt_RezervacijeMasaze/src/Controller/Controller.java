package Controller;

import Model.DataBase;
import Model.Rezervation;
import View.NapraviRezervacijeCLS;
import View.PregledRezervacijaPanel;
import View.Tablica;
import View.UrediRezervacije;

import java.util.List;

public class Controller {
    private final DataBase dataBase;
    private UrediRezervacije urediRezervacije;
    private PregledRezervacijaPanel pregledRezervacijaPanel;
    private NapraviRezervacijeCLS napraviRezervacijeCLS;
    public Controller(UrediRezervacije urediRezervacije, PregledRezervacijaPanel pregledRezervacijaPanel, NapraviRezervacijeCLS napraviRezervacijeCLS){
        dataBase = new DataBase();
        this.urediRezervacije = urediRezervacije;
        this.pregledRezervacijaPanel = pregledRezervacijaPanel;
        this.napraviRezervacijeCLS = napraviRezervacijeCLS;
    }

    public void addNewElementsInDatabase(List<Rezervation> rezervations){
        dataBase.addNewRezervations2DB(rezervations);

    }

    public void showDataOnTable(Tablica table){
        table.showDataOnTable( dataBase);
    }


    public void addElementsUserIsSearchingFor(List<Rezervation> rezervations, Tablica table) {
        table.setSearchedTable(rezervations);
    }

    public void updateOtherPanels(List<Rezervation> rezervations) {

        urediRezervacije.setRezervations(rezervations);
        pregledRezervacijaPanel.setRezervations(rezervations);
        napraviRezervacijeCLS.setRezervations(rezervations);

    }


}
