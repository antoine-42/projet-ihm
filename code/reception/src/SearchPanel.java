import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel{
    private RechercheReservation window;

    TextField lastNameTextField;
    TextField nameTextField;
    TextField referenceTextField;


    SearchPanel(RechercheReservation window_){
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
        labelConstraints.insets = RechercheReservation.marginDefault;

        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.ipadx = 10;
        textFieldConstraints.ipady = 10;
        textFieldConstraints.weightx = 1;
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldConstraints.insets = RechercheReservation.marginDefault;

        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.ipadx = 0;
        panelConstraints.ipady = 0;
        panelConstraints.weightx = 1;
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;


        JPanel fullNamePanel = new JPanel();
        fullNamePanel.setLayout(new GridBagLayout());
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.insets = RechercheReservation.marginRight;
        this.add(fullNamePanel, panelConstraints);


        JLabel lastNameLabel = new JLabel("Nom du client", JLabel.LEFT);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        fullNamePanel.add(lastNameLabel, labelConstraints);

        this.lastNameTextField = new TextField("Carpentier");
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 0;
        fullNamePanel.add(lastNameTextField, textFieldConstraints);

        JLabel nameLabel = new JLabel("Prenom du client", JLabel.LEFT);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 1;
        fullNamePanel.add(nameLabel, labelConstraints);

        this.nameTextField = new TextField("Marine");
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 1;
        fullNamePanel.add(nameTextField, textFieldConstraints);


        JPanel panelReference = new JPanel();
        panelReference.setLayout(new GridBagLayout());
        panelConstraints.gridx = 1;
        panelConstraints.gridy = 0;
        panelConstraints.insets = RechercheReservation.marginLeft;
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
        RechercheReservationListener reservationListener = new RechercheReservationListener(this.window);
        bouttonRecherche.addActionListener(reservationListener);
        RechercheReservation.buttonConstraints.gridx = 0;
        RechercheReservation.buttonConstraints.gridy = 1;
        RechercheReservation.buttonConstraints.gridwidth = 2;
        RechercheReservation.buttonConstraints.anchor = GridBagConstraints.CENTER;
        this.add(bouttonRecherche, RechercheReservation.buttonConstraints);
    }
}
