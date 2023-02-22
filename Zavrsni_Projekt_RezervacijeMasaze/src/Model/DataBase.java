package Model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
    /**
     * List that contains all Objects that are in DataBase.
     */
    private List<Rezervation> rezervationList;
    private Connection con;

    public DataBase(){
        rezervationList = new LinkedList<>();
        con = null;
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

    /**
     * Method for connecting to the Data Base.
     */
    public void connect()  {
        System.out.println("Connecting to database...");
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zavrsniprojektnoop", "root", "Nkzadar:2002");

                System.out.println("Connected to -> " + con.toString());
            }catch (SQLException e) {
                System.out.println("Could not load driver!!!");
            }

    }

    /**
     * Method for disconnecting from Data Base.
     */
    public void disconnect(){
        try {
            if(con != null) {
                con.close();
                JOptionPane.showMessageDialog(new JFrame(), "Disconected from server....");
            } else{
                JOptionPane.showMessageDialog(new JFrame(), "Not possible to disconnect from server!!!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Not possible to disconnect from server!!!");
        }
    }

    /**
     * Method for downloading data from Data Base.
     */
    public void uploadFromDB(){
        try{
            List<Rezervation> dataList = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM zavrsniprojektnoop.terminirezervacija;");
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String mail = rs.getString("mail");
                String massType = rs.getString("massType");
                String intensity = rs.getString("intensity");
                String addNote = rs.getString("SpecialNote");
                boolean drinkAndJacuzzy = rs.getBoolean("drinkandjac");
                boolean spa = rs.getBoolean("Spa");
                String time = rs.getString("Time");
                String day = rs.getString("Day");
                String price = rs.getString("Price");
                Rezervation rez = new Rezervation(name, phone, mail, massType,intensity, addNote, drinkAndJacuzzy,spa,time,day,price);
                dataList.add(rez);
            }
            rs.close();
            stmt.close();
            rezervationList = dataList;
            System.out.println(rezervationList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for saving current reservations to Data Base.
     */
    public void save2Server() {
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO zavrsniprojektnoop.terminirezervacija (name, phone, mail, massType, intensity, SpecialNote, drinkandjac, Spa, Time, Day, Price) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            for(Rezervation rez : rezervationList){
                stmt.setString(1, rez.getName());
                stmt.setString(2, rez.getPhoneNumber());
                stmt.setString(3, rez.getMail());
                stmt.setString(4, rez.getMassageType());
                stmt.setString(5, rez.getIntesityValue());
                stmt.setString(6, rez.getSpecNote());
                stmt.setBoolean(7, rez.isNapitakIJacuzzy());
                stmt.setBoolean(8, rez.isSpaOffer());
                stmt.setString(9, rez.getTime());
                stmt.setString(10, rez.getDay());
                stmt.setString(11, rez.getPrice());
                stmt.executeUpdate();
            }
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
