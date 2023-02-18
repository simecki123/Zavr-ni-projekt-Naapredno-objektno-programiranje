package Model;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
    private List<Rezervation> rezervationList;
    private Connection connection;

    public DataBase(){
        rezervationList = new LinkedList<>();
        connection = null;
    }

    public void addNewRezervations2DB(List<Rezervation> rezervations){
        rezervationList = rezervations;


    }

    public List<Rezervation> getAllFromDB(){
        return rezervationList;
    }
}
