package View;

import Model.Rezervation;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class Tablica extends JTable{

    private AbstractTableModel dtm;
    private List<Rezervation> rezervations;

    public Tablica(){
        setTable();
        rezervations = new ArrayList<>();

    }


    private void setTable(){

        dtm = new AbstractTableModel() {

            String[] colNames = {"name", "phone", "mail", "mass Type", "intesity", "specialNote","drink and Jacuzzy","Spa", "Time", "Day", "Price"};



            @Override
            public String getColumnName(int column) {
                return colNames[column];
            }

            @Override
            public Object getValueAt(int row, int col) {

                Rezervation rezervation = rezervations.get(row);

                switch (col) {
                    case 0: return rezervation.getName();
                    case 1: return rezervation.getPhoneNumber();
                    case 2: return rezervation.getMail();
                    case 3: return rezervation.getMassageType();
                    case 4: return rezervation.getIntesityValue();
                    case 5: return rezervation.getSpecNote();
                    case 6: return rezervation.isNapitakIJacuzzy();
                    case 7: return rezervation.isSpaOffer();
                    case 8: return rezervation.getTime();
                    case 9: return rezervation.getDay();
                    case 10: return rezervation.getPrice();

                    default:
                        throw new IllegalArgumentException("There is no such value for the input data!!!");
                }
            }

            @Override
            public int getRowCount() {
                return rezervations.size();
            }

            @Override
            public int getColumnCount() {
                Rezervation rez = new Rezervation();
                return rez.getClass().getDeclaredFields().length;
            }
        };


        setModel(dtm);

    }


}
