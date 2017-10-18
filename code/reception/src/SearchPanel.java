import javax.swing.*;
import java.awt.*;

class SearchPanel extends JPanel{
    private Reception window;

    JTextField lastNameTextField;
    JTextField nameTextField;

    JTextField referencePt1TextField;
    JTextField referencePt2TextField;
    JTextField referencePt3TextField;

    int reservationSubPanel = 0;


    SearchPanel(Reception window_){
        this.window = window_;

        this.drawPanel();
    }


    private void drawPanel(){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.ipadx = 15;
        labelConstraints.ipady = 15;
        labelConstraints.weightx = 0;
        labelConstraints.fill = GridBagConstraints.HORIZONTAL;
        labelConstraints.insets = Reception.marginNoDown;

        GridBagConstraints smallLabelConstraints = new GridBagConstraints();
        smallLabelConstraints.ipadx = 0;
        smallLabelConstraints.ipady = 0;
        smallLabelConstraints.weightx = 0;
        smallLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        smallLabelConstraints.insets = Reception.marginNone;

        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.ipadx = 10;
        textFieldConstraints.ipady = 10;
        textFieldConstraints.weightx = 1;
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldConstraints.insets = Reception.marginNone;

        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.ipadx = 0;
        panelConstraints.ipady = 0;
        panelConstraints.weightx = 1;
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;


        JPanel fullNamePanel = new JPanel();
        fullNamePanel.setLayout(new GridBagLayout());
        fullNamePanel.setOpaque(false);
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.weightx = 1;
        panelConstraints.insets = Reception.marginRight;
        this.add(fullNamePanel, panelConstraints);


        JLabel lastNameLabel = new JLabel("Nom du client", JLabel.LEFT);
        lastNameLabel.setFont(Reception.defaultFont);
        lastNameLabel.setForeground(Reception.secondaryColor);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.gridwidth = 1;
        fullNamePanel.add(lastNameLabel, labelConstraints);

        this.lastNameTextField = new JTextField("Faure");
        this.lastNameTextField.setFont(Reception.defaultFont);
        this.lastNameTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        textFieldConstraints.gridx = 0;
        textFieldConstraints.gridy = 1;
        fullNamePanel.add(lastNameTextField, textFieldConstraints);

        JLabel nameLabel = new JLabel("Prenom du client", JLabel.LEFT);
        nameLabel.setFont(Reception.defaultFont);
        nameLabel.setForeground(Reception.secondaryColor);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 2;
        labelConstraints.gridwidth = 1;
        fullNamePanel.add(nameLabel, labelConstraints);

        this.nameTextField = new JTextField("Florian");
        this.nameTextField.setFont(Reception.defaultFont);
        this.nameTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        textFieldConstraints.gridx = 0;
        textFieldConstraints.gridy = 3;
        fullNamePanel.add(nameTextField, textFieldConstraints);


        JPanel panelReference = new JPanel();
        panelReference.setLayout(new GridBagLayout());
        panelReference.setOpaque(false);
        panelConstraints.gridx = 1;
        panelConstraints.gridy = 0;
        panelConstraints.weightx = 3;
        panelConstraints.insets = Reception.marginLeft;
        this.add(panelReference, panelConstraints);

        JLabel labelReferenceReservation  = new JLabel("Reference de la reservation", JLabel.LEFT);
        labelReferenceReservation.setFont(Reception.defaultFont);
        labelReferenceReservation.setForeground(Reception.secondaryColor);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        panelReference.add(labelReferenceReservation, labelConstraints);

        JPanel panelReferenceText = new JPanel();
        panelReferenceText.setLayout(new GridBagLayout());
        panelReferenceText.setBackground(Color.WHITE);
        panelReferenceText.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 1;
        panelConstraints.weightx = 3;
        panelConstraints.insets = Reception.marginNone;
        panelReference.add(panelReferenceText, panelConstraints);

        this.referencePt1TextField = new JTextField();
        this.referencePt1TextField.setFont(Reception.defaultFont);
        this.referencePt1TextField.setBorder(BorderFactory.createEmptyBorder());
        this.referencePt1TextField.setHorizontalAlignment(JTextField.CENTER);
        this.referencePt1TextField.setDocument(new JTextFieldLimit(4, 0, this));
        textFieldConstraints.gridx = 0;
        textFieldConstraints.gridy = 0;
        panelReferenceText.add(referencePt1TextField, textFieldConstraints);

        JLabel dash1Label = new JLabel("-", JLabel.LEFT);
        dash1Label.setFont(Reception.defaultFont);
        dash1Label.setForeground(Reception.secondaryColor);
        smallLabelConstraints.gridx = 1;
        textFieldConstraints.gridy = 0;
        panelReferenceText.add(dash1Label, smallLabelConstraints);

        this.referencePt2TextField = new JTextField();
        this.referencePt2TextField.setFont(Reception.defaultFont);
        this.referencePt2TextField.setBorder(BorderFactory.createEmptyBorder());
        this.referencePt2TextField.setHorizontalAlignment(JTextField.CENTER);
        this.referencePt2TextField.setDocument(new JTextFieldLimit(4, 1, this));
        textFieldConstraints.gridx = 2;
        textFieldConstraints.gridy = 0;
        panelReferenceText.add(referencePt2TextField, textFieldConstraints);

        JLabel dash2Label = new JLabel("-", JLabel.LEFT);
        dash2Label.setFont(Reception.defaultFont);
        dash2Label.setForeground(Reception.secondaryColor);
        smallLabelConstraints.gridx = 3;
        textFieldConstraints.gridy = 0;
        panelReferenceText.add(dash2Label, smallLabelConstraints);

        this.referencePt3TextField = new JTextField();
        this.referencePt3TextField.setFont(Reception.defaultFont);
        this.referencePt3TextField.setBorder(BorderFactory.createEmptyBorder());
        this.referencePt3TextField.setHorizontalAlignment(JTextField.CENTER);
        this.referencePt3TextField.setDocument(new JTextFieldLimit(4));
        textFieldConstraints.gridx = 4;
        textFieldConstraints.gridy = 0;
        panelReferenceText.add(referencePt3TextField, textFieldConstraints);


        JButton searchButton = new JButton("Chercher");
        ReservationSearchListener reservationListener = new ReservationSearchListener(this.window);
        searchButton.addActionListener(reservationListener);
        searchButton.setFont(Reception.defaultFont);

        Reception.buttonConstraints.gridx = 0;
        Reception.buttonConstraints.gridy = 1;
        Reception.buttonConstraints.gridwidth = 2;
        Reception.buttonConstraints.anchor = GridBagConstraints.CENTER;
        this.add(searchButton, Reception.buttonConstraints);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 3;
        constraints.weighty = 1;
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        this.add(spacer, constraints);
    }

    void setReservationSubPanel(int n, String leftover){
        JTextField[] reservationSubPanels = {this.referencePt1TextField, this.referencePt2TextField, this.referencePt3TextField};

        this.setReservationSubPanel(n);

        reservationSubPanels[this.reservationSubPanel].setText(leftover);
    }
    void setReservationSubPanel(int n){
        JTextField[] reservationSubPanels = {this.referencePt1TextField, this.referencePt2TextField, this.referencePt3TextField};

        this.reservationSubPanel = n;
        reservationSubPanels[this.reservationSubPanel].requestFocus();
    }

    Boolean reservationNullOrEmpty(){
        return Reception.isNullOrEmpty(this.referencePt1TextField.getText()) && Reception.isNullOrEmpty(this.referencePt2TextField.getText()) && Reception.isNullOrEmpty(this.referencePt3TextField.getText());
    }
    String getReservation(){
        return this.referencePt1TextField.getText() + "-" + this.referencePt2TextField.getText() + "-" + this.referencePt3TextField.getText();
    }
    String getLastName(){
        return this.lastNameTextField.getText();
    }
    String getFirstName(){
        return this.nameTextField.getText();
    }

    void reset(){
        this.nameTextField.setText("");
        this.lastNameTextField.setText("");
        this.referencePt1TextField.setText("");
        this.referencePt2TextField.setText("");
        this.referencePt3TextField.setText("");
    }
}
