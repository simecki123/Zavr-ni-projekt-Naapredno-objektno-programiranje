package View;

import Model.Rezervation;

import java.util.EventObject;
import java.util.List;

public class DataEvent extends EventObject {
    private List<Rezervation> rezervations;

    public DataEvent(Object source, List<Rezervation> rezervations){
        super(source);
        this.rezervations = rezervations;


    }

    public List<Rezervation> getRezervations() {
        return rezervations;
    }
}
