import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel{
    private Reception window;

    TextField lastNameTextField;
    TextField nameTextField;
    TextField referenceTextField;


    SearchPanel(Reception window_){
        this.window = window_;

        this.drawPanel();
    }


    private void drawPanel(){
        this.setLayout(new GridBagLayout());

        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.ipadx = 15;
        labelConstraints.ipady = 15;
        labelConstraints.weightx = 0;
        labelConstraints.fill = GridBagConstraints.HORIZONTAL;
        labelConstraints.insets = Reception.marginDefault;

        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.ipadx = 10;
        textFieldConstraints.ipady = 10;
        textFieldConstraints.weightx = 1;
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldConstraints.insets = Reception.marginDefault;

        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.ipadx = 0;
        panelConstraints.ipady = 0;
        panelConstraints.weightx = 1;
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;


        JPanel fullNamePanel = new JPanel();
        fullNamePanel.setLayout(new GridBagLayout());
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.weightx = 1;
        panelConstraints.insets = Reception.marginRight;
        this.add(fullNamePanel, panelConstraints);


        JLabel lastNameLabel = new JLabel("Nom du client", JLabel.LEFT);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        fullNamePanel.add(lastNameLabel, labelConstraints);

        this.lastNameTextField = new TextField("Faure");
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 0;
        fullNamePanel.add(lastNameTextField, textFieldConstraints);

        JLabel nameLabel = new JLabel("Prenom du client", JLabel.LEFT);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 1;
        fullNamePanel.add(nameLabel, labelConstraints);

        this.nameTextField = new TextField("Florian");
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 1;
        fullNamePanel.add(nameTextField, textFieldConstraints);


        JPanel panelReference = new JPanel();
        panelReference.setLayout(new GridBagLayout());
        panelConstraints.gridx = 1;
        panelConstraints.gridy = 0;
        panelConstraints.weightx = 3;
        panelConstraints.insets = Reception.marginLeft;
        this.add(panelReference, panelConstraints);


        JLabel labelReferenceReservation  = new JLabel("Reference de la reservation", JLabel.LEFT);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        panelReference.add(labelReferenceReservation, labelConstraints);

        this.referenceTextField = new TextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 0;
        panelReference.add(referenceTextField, textFieldConstraints);


        JButton bouttonRecherche = new JButton("Chercher");
        ReservationSearchListener reservationListener = new ReservationSearchListener(this.window);
        bouttonRecherche.addActionListener(reservationListener);
        Reception.buttonConstraints.gridx = 0;
        Reception.buttonConstraints.gridy = 1;
        Reception.buttonConstraints.gridwidth = 2;
        Reception.buttonConstraints.anchor = GridBagConstraints.CENTER;
        this.add(bouttonRecherche, Reception.buttonConstraints);
    }

    void reset(){
        this.nameTextField.setText("");
        this.lastNameTextField.setText("");
        this.referenceTextField.setText("");
    }
}
