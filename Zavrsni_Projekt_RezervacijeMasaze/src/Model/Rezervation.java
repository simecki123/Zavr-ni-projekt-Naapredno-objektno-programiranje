package Model;

import java.io.Serializable;

public class Rezervation implements Serializable {
    private String name;
    private String phoneNumber;
    private String mail;
    private MassageType massageType;
    private int intesityValue;
    private String specNote;
    private boolean napitakIJacuzzy;
    private boolean spaOffer;
    private String time;
    private String day;
    private String price;



    public Rezervation(String name, String phoneNumber, String mail, MassageType masss
                       , int intesityValue, String specNote, boolean napitakIJacuzzy, boolean spaOffer, String time, String day, String price){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.massageType = masss;
        this.intesityValue = intesityValue;
        this.specNote = specNote;
        this.napitakIJacuzzy = napitakIJacuzzy;
        this.spaOffer = spaOffer;
        this.time = time;
        this.day = day;
        this.price = price;
    }

    public Rezervation(){}

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public MassageType getMassageType() {
        return massageType;
    }
    public int getIntesityValue() {
        return intesityValue;
    }

    public String getSpecNote() {
        return specNote;
    }

    public boolean isNapitakIJacuzzy() {
        return napitakIJacuzzy;
    }

    public boolean isSpaOffer() {
        return spaOffer;
    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Rezervation{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", massageType=" + massageType +
                ", intesityValue=" + intesityValue +
                ", specNote='" + specNote + '\'' +
                ", napitakIJacuzzy=" + napitakIJacuzzy +
                ", spaOffer=" + spaOffer +
                ", time=" + time +
                ", day=" + day +
                ", price='" + price + '\'' +
                '}';
    }
}
