package Controller;

import Model.DataBase;
import Model.Rezervation;
import View.Tablica;

import java.util.List;

public class Controller {
    private final DataBase dataBase;
    public Controller(){
        dataBase = new DataBase();
    }

    public void addNewElementsInDatabase(List<Rezervation> rezervations, Tablica table){
        dataBase.addNewRezervations2DB(rezervations);
        table.showDataOnTable( dataBase);
    }


    public void addElementsUserIsSearchingFor(List<Rezervation> rezervations, Tablica table) {
        table.setSearchedTable(rezervations);
    }
}
