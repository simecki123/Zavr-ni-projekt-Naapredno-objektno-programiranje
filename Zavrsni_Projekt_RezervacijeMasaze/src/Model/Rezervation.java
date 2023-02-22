package Model;

import java.io.Serializable;

public class Rezervation implements Serializable {
    /**
     * Variable name of Client.
     */
    private String name;
    /**
     * Variable that contains phone number of Client.
     */
    private String phoneNumber;
    /**
     * Variable that contains e-mail of Client.
     */
    private String mail;
    /**
     * Variable that contains selected massage type.
     */
    private String massageType;
    /**
     * Variable that contains intensity value.
     */
    private String intesityValue;
    /**
     * Variable that contains special note that Client requested.
     */
    private String specNote;
    /**
     * Variable that contain Clients choice.
     */
    private boolean napitakIJacuzzy;
    /**
     * Variable that contain Clients choice.
     */
    private boolean spaOffer;
    /**
     * Variable that contains wanted time of reservation.
     */
    private String time;
    /**
     * Variable that contains choosen date.
     */
    private String day;
    /**
     * Variable that holds price of reservation.
     */
    private String price;



    public Rezervation(String name, String phoneNumber, String mail, String masss
                       , String intesityValue, String specNote, boolean napitakIJacuzzy, boolean spaOffer, String time, String day, String price){
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

    public String getMassageType() {
        return massageType;
    }
    public String getIntesityValue() {
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
