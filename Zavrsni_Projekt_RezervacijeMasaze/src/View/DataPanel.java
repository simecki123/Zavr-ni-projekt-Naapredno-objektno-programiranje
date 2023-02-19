package View;

import javax.swing.*;


import Model.MassageType;
import Model.Rezervation;
import Model.TimeEnum;
import com.toedter.calendar.JDateChooser;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class DataPanel extends JPanel {
    /**
     * Variable that presents name field.
     */
    private JTextField nameField;
    /**
     * Variable that presents phone number field.
     */
    private JTextField phoneNumberField;
    /**
     * Variable that presents e-mail field.
     */
    private JTextField mailField;
    /**
     * Variable that presents massageType combo box.
     */
    private JComboBox<MassageType> massageTypeField;
    /**
     * Variable that presents value of JSlider.
     */
    private JSlider intezitetField;
    /**
     * Variable that presents Scroll pane for "addNote".
     */
    private JScrollPane skrolNote;
    /**
     * Variable that presents addNote text Area.
     */
    private JTextArea addNoteField;
    /**
     * Variable that presents button group for some ability.
     */
    private ButtonGroup napitakIJacuzziField;
    private JRadioButton yesToJaccuzy;
    private JRadioButton noTOJacuzzy;
    /**
     * Variable that presents button group for some ability.
     */
    private ButtonGroup spaField;
    private JRadioButton yesToSpa;
    private JRadioButton noToSpa;
    /**
     * Variable that presents chosen Time for reservation.
     */
    private JComboBox<TimeEnum> time;
    /**
     * Variable that presents day chosen by dateChooser.
     */
    private JDateChooser dayField;
    /**
     * Variable that presents price of reservation.
     */
    private JTextField price;
    /**
     * Clear all.
     */
    private JButton clearButton;
    /**
     * Submit.
     */
    private JButton submitButton;

    private DefaultComboBoxModel<MassageType> massageModel;
    /**
     * Reservation.
     */
    private Rezervation rezervation;
    /**
     * Variable that presents all Objects from DataBase.
     */
    private List<Rezervation> rezervationsFromDataBase;
    /**
     * Simple date format.
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Listener for button Search.
     */
    private DataPanelListener dplSearchDate;
    /**
     * Listener for button Submit.
     */
    private DataPanelListener dplSubmitRezervation;

    /**
     * Final vale of some defined price.
     */
    private final int napitakIJacuzzyprice = 40;
    /**
     * Final vale of some defined price.
     */
    private final int spaPice = 60;





    public DataPanel(){
        Dimension dim = getPreferredSize();
        dim.height = 210;
        setPreferredSize(dim);
        setBorders2Panel();
        initAll();
        layoutComps();
        activateDataPanel();
    }


    private void setBorders2Panel() {

        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        TitledBorder borderic = new TitledBorder("Data 1: ");
        borderic.setTitleColor(Color.WHITE);
        Border inner = borderic;

        Border panelBorder = BorderFactory.createCompoundBorder(outer, inner);
        setBorder(panelBorder);
        Dimension dims = getPreferredSize();
        dims.width = 180;
        setPreferredSize(dims);
    }

    private void initAll() {
        setBackground(Color.BLACK);

        nameField = new JTextField(10);
        phoneNumberField = new JTextField(10);
        mailField = new JTextField(10);

        // MassageType
        massageTypeField = new JComboBox<>();
        massageModel = new DefaultComboBoxModel<>();
        MassageType[] arMassage = MassageType.values();
        List<MassageType> collMassage = Arrays.asList(arMassage);
        massageModel.addAll(collMassage);
        massageTypeField.setModel(massageModel);
        massageTypeField.setSelectedIndex(0);
        Dimension dms = massageTypeField.getPreferredSize();
        dms.width = 110;
        massageTypeField.setPreferredSize(dms);

        // Slider
        intezitetField = new JSlider(1,10,5);
        intezitetField.setBackground(Color.BLACK);

        intezitetField.setMinorTickSpacing(2);
        intezitetField.setMajorTickSpacing(10);
        intezitetField.setPaintTicks(true);
        intezitetField.setPaintLabels(true);

        // AddNote
        addNoteField = new JTextArea(5,20);
        skrolNote = new JScrollPane(addNoteField);


        // napitakIJacuzzy
        napitakIJacuzziField = new ButtonGroup();
        yesToJaccuzy = new JRadioButton("YES");
        yesToJaccuzy.setBackground(Color.BLACK);
        yesToJaccuzy.setForeground(Color.WHITE);
        noTOJacuzzy = new JRadioButton("NO");
        noTOJacuzzy.setForeground(Color.WHITE);
        noTOJacuzzy.setBackground(Color.BLACK);
        yesToJaccuzy.setSelected(true);
        napitakIJacuzziField.add(yesToJaccuzy);
        napitakIJacuzziField.add(noTOJacuzzy);
        yesToJaccuzy.setActionCommand("Yes");
        noTOJacuzzy.setActionCommand("NO");

        // Spa
        spaField = new ButtonGroup();
        yesToSpa = new JRadioButton("YES");
        yesToSpa.setForeground(Color.WHITE);
        yesToSpa.setBackground(Color.BLACK);
        noToSpa = new JRadioButton("NO");
        noToSpa.setBackground(Color.BLACK);
        noToSpa.setForeground(Color.WHITE);
        yesToSpa.setSelected(true);
        spaField.add(yesToSpa);
        spaField.add(noToSpa);
        yesToSpa.setActionCommand("YES");
        noToSpa.setActionCommand("NO");


        // Time
        time = new JComboBox<>();
        DefaultComboBoxModel<TimeEnum> TimeModel = new DefaultComboBoxModel<>();
        TimeEnum[] arTime = TimeEnum.values();
        List<TimeEnum> collTime = Arrays.asList(arTime);
        TimeModel.addAll(collTime);
        time.setModel(TimeModel);
        time.setSelectedIndex(0);
        Dimension dmassage = time.getPreferredSize();
        dmassage.width = 110;
        time.setPreferredSize(dmassage);

        // Day
        dayField = new JDateChooser();

        // Price
        price = new JTextField(10);
        price.setEditable(false);
        setPrice();


        // Button
        submitButton = new JButton("SUBMIT");
        submitButton.setBackground(Color.CYAN);
        clearButton = new JButton("CLEAR");
        clearButton.setBackground(Color.CYAN);

        rezervationsFromDataBase = new ArrayList<>();


    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 0;
        gbc.weighty = 0;

        JLabel labelname = new JLabel("Name and surname: ");
        labelname.setForeground(Color.WHITE);
        add(labelname, gbc);
        gbc.gridy++;
        add(nameField, gbc);
        gbc.gridy--;
        gbc.gridx++;
        JLabel labelJacuzzy = new JLabel("Bevrage and Jacuzzy?");
        labelJacuzzy.setForeground(Color.WHITE);
        add(labelJacuzzy, gbc);
        gbc.gridy++;
        add(yesToJaccuzy, gbc);
        gbc.gridx++;
        add(noTOJacuzzy, gbc);

        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel labelPhone = new JLabel("Phone number: ");
        labelPhone.setForeground(Color.WHITE);
        add(labelPhone, gbc);
        gbc.gridy ++;
        add(phoneNumberField, gbc);
        gbc.gridy--;
        gbc.gridx++;
        JLabel labelSpa = new JLabel("Add SPA?");
        labelSpa.setForeground(Color.WHITE);
        add(labelSpa, gbc);
        gbc.gridy++;
        add(yesToSpa, gbc);
        gbc.gridx++;
        add(noToSpa, gbc);


        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel labelmail = new JLabel("Mail: ");
        labelmail.setForeground(Color.WHITE);
        add(labelmail, gbc);
        gbc.gridy++;
        add(mailField, gbc);
        gbc.gridy--;
        gbc.gridx++;
        JLabel labelTime = new JLabel("Time");
        labelTime.setForeground(Color.WHITE);
        add(labelTime, gbc);
        gbc.gridy++;
        add(time, gbc);


        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel labelMasageTY = new JLabel("Massage type: ");
        labelMasageTY.setForeground(Color.WHITE);
        add(labelMasageTY, gbc);
        gbc.gridy++;
        add(massageTypeField, gbc);
        gbc.gridy--;
        gbc.gridx++;
        JLabel labelDay = new JLabel("Day: ");
        labelDay.setForeground(Color.WHITE);
        add(labelDay, gbc);
        gbc.gridy++;
        add(dayField, gbc);

        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;

        JLabel labelIntesity = new JLabel("Intesity: ");
        labelIntesity.setForeground(Color.WHITE);
        add(labelIntesity, gbc);
        gbc.gridy++;
        add(intezitetField, gbc);

        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel labelNote = new JLabel("Add Note: ");
        labelNote.setForeground(Color.WHITE);
        add(labelNote, gbc);
        gbc.gridx++;
        JLabel labelCijena = new JLabel("Price in €: ");
        labelCijena.setForeground(Color.WHITE);
        add(labelCijena, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        add(skrolNote, gbc);
        gbc.gridx++;
        add(price, gbc);

        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(clearButton, gbc);
        gbc.gridx++;
        add(submitButton, gbc);


    }


    /**
     * Method to set values from DataBase.
     * @param rezervationsFromDataBase
     */
    public void setRezervationsFromDataBase(List<Rezervation> rezervationsFromDataBase) {
        this.rezervationsFromDataBase = rezervationsFromDataBase;
    }

    /**
     * Method that sets Listener for "Search".
     * @param dplSearchDate new DataPanelListener.
     */
    public void setDplSearchDate(DataPanelListener dplSearchDate) {
        this.dplSearchDate = dplSearchDate;
    }
    /**
     * Method that sets Listener for "Submit".
     * @param dplSubmitRezervation new DataPanelListener.
     */
    public void setDplSubmitRezervation(DataPanelListener dplSubmitRezervation) {
        this.dplSubmitRezervation = dplSubmitRezervation;
    }

    /**
     * Method that sets new price.
     */
    private void setPrice(){
        MassageType tipM = (MassageType) massageModel.getSelectedItem();
        int novaCijena = 0;
        novaCijena = novaCijena+ tipM.getI();
        if(yesToJaccuzy.isSelected()){
            novaCijena = novaCijena+napitakIJacuzzyprice;
        }
        if(yesToSpa.isSelected()){
            novaCijena = novaCijena+spaPice;
        }
        price.setText(String.valueOf(novaCijena));
    }

    /**
     * Clear All method.
     */
    private void clearAll(){
        nameField.setText(null);
        phoneNumberField.setText(null);
        mailField.setText(null);
        massageTypeField.setSelectedIndex(0);
        addNoteField.setText(null);
        yesToJaccuzy.setSelected(true);
        yesToSpa.setSelected(true);
        intezitetField.setValue(5);
        setPrice();
    }

    /**
     * Set all reservations from some defined date.
     * @return return reservations.
     */
    public List<Rezervation> setZeljeneRezervacije(){
        List<Rezervation> odabraneRezervacije = new ArrayList<>();
        for (Rezervation rez : rezervationsFromDataBase) {
            if (rez.getDay().equals(simpleDateFormat.format(dayField.getDate()))) {
                odabraneRezervacije.add(rez);
            }
        }
        return odabraneRezervacije;
    }

    private void activateDataPanel() {
        massageTypeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrice();
            }
        });
        yesToSpa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrice();
            }
        });
        noToSpa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrice();
            }
        });
        yesToJaccuzy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrice();
            }
        });
        noTOJacuzzy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrice();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });

        dayField.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    List<Rezervation> odabraneRezervacije = new ArrayList<>();
                    if(dplSearchDate != null) {
                        odabraneRezervacije = setZeljeneRezervacije();
                        DataEvent dataEvent = new DataEvent(this, odabraneRezervacije);
                        dplSearchDate.dataPanelEventOccured(dataEvent);
                    }

                }

            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dplSearchDate != null){
                    if(!(rezervationsFromDataBase.isEmpty())) {
                        if (!(nameField.getText().equals("") || mailField.getText().equals("") || phoneNumberField.getText().equals("") || simpleDateFormat.format(dayField.getDate()).equals(""))) {
                            rezervation = new Rezervation(nameField.getText(), phoneNumberField.getText(), mailField.getText(),
                                    String.valueOf(massageTypeField.getSelectedItem()), intezitetField.getValue(), addNoteField.getText(),
                                    yesToJaccuzy.isSelected(), yesToSpa.isSelected(), String.valueOf(time.getSelectedItem()),
                                    simpleDateFormat.format(dayField.getDate()), price.getText());

                            int brojac = 0;
                            boolean vr = false;
                            for (Rezervation rez : rezervationsFromDataBase) {
                                if (rez.getName().equals("")) {
                                    if (rez.getDay().equals(rezervation.getDay()) & rez.getTime().equals(rezervation.getTime())) {
                                        rezervationsFromDataBase.set(brojac, rezervation);
                                        vr = true;
                                    }
                                }
                                brojac++;
                            }

                            if (vr) {
                                DataEvent dataEvent = new DataEvent(this, rezervationsFromDataBase);
                                dplSubmitRezervation.dataPanelEventOccured(dataEvent);
                                clearAll();

                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "That massage is already reserved!!!", "Warning",
                                        JOptionPane.ERROR_MESSAGE);
                            }



                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Please fill all folowing text fields!!!", "Warning",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else{
                        JOptionPane.showMessageDialog(new JFrame(), "There is no available massages, please wait!!!", "Warning",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }

}
