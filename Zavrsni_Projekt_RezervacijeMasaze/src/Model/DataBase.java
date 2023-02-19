package Model;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
    /**
     * List that contains all Objects that are in DataBase.
     */
    private List<Rezervation> rezervationList;
    private Connection connection;

    public DataBase(){
        rezervationList = new LinkedList<>();
        connection = null;
    }

    /**
     * Method thaat updates DataBase.
     * @param rezervations List that contains new DataBase Objects.
     */
    public void addNewRezervations2DB(List<Rezervation> rezervations){
        rezervationList = rezervations;


    }

    /**
     * Method that gets all Objects from DataBase.
     * @return
     */
    public List<Rezervation> getAllFromDB(){
        return rezervationList;
    }
}
