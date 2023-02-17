package View;

import javax.swing.*;


import Model.MassageType;
import Model.Rezervation;
import Model.TimeEnum;
import com.toedter.calendar.JDateChooser;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class DataPanel extends JPanel {
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField mailField;
    private JComboBox<MassageType> massageTypeField;
    private JSlider intezitetField;
    private JScrollPane skrolNote;
    private JTextArea addNoteField;
    private ButtonGroup napitakIJacuzziField;
    private JRadioButton yesToJaccuzy;
    private JRadioButton noTOJacuzzy;
    private ButtonGroup spaField;
    private JRadioButton yesToSpa;
    private JRadioButton noToSpa;
    private JComboBox<TimeEnum> time;
    private JDateChooser dayField;
    private JTextField price;
    private JButton clearButton;
    private JButton submitButton;

    private DefaultComboBoxModel<MassageType> massageModel;
    private Rezervation rezervation;


    private final int napitakIJacuzzyprice = 40;
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
        Border inner = BorderFactory.createTitledBorder("Order");
        Border outter = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border brd = BorderFactory.createCompoundBorder(outter, inner);
        setBorder(brd);
    }

    private void initAll() {
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
        noTOJacuzzy = new JRadioButton("NO");
        yesToJaccuzy.setSelected(true);
        napitakIJacuzziField.add(yesToJaccuzy);
        napitakIJacuzziField.add(noTOJacuzzy);
        yesToJaccuzy.setActionCommand("Yes");
        noTOJacuzzy.setActionCommand("NO");

        // Spa
        spaField = new ButtonGroup();
        yesToSpa = new JRadioButton("YES");
        noToSpa = new JRadioButton("NO");
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
        clearButton = new JButton("CLEAR");


    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 0;
        gbc.weighty = 0;

        add(new JLabel("Name and surname: "), gbc);
        gbc.gridy++;
        add(nameField, gbc);
        gbc.gridy--;
        gbc.gridx++;
        add(new JLabel("Bevrage and Jacuzzy?"), gbc);
        gbc.gridy++;
        add(yesToJaccuzy, gbc);
        gbc.gridx++;
        add(noTOJacuzzy, gbc);

        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Phone number: "), gbc);
        gbc.gridy ++;
        add(phoneNumberField, gbc);
        gbc.gridy--;
        gbc.gridx++;
        add(new JLabel("Add SPA?"), gbc);
        gbc.gridy++;
        add(yesToSpa, gbc);
        gbc.gridx++;
        add(noToSpa, gbc);


        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Mail: "), gbc);
        gbc.gridy++;
        add(mailField, gbc);
        gbc.gridy--;
        gbc.gridx++;
        add(new JLabel("Time:"), gbc);
        gbc.gridy++;
        add(time, gbc);


        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Massage type: "), gbc);
        gbc.gridy++;
        add(massageTypeField, gbc);
        gbc.gridy--;
        gbc.gridx++;
        add(new JLabel("Day: "), gbc);
        gbc.gridy++;
        add(dayField, gbc);

        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;

        add(new JLabel("Intesity: "), gbc);
        gbc.gridy++;
        add(intezitetField, gbc);

        gbc.gridy++;
        add(new JLabel("   "), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Add note: "), gbc);
        gbc.gridx++;
        add(new JLabel("Cijena u €: "), gbc);
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

    public Rezervation getRezervation() {
        return rezervation;
    }



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


    }

}
