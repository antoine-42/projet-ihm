import javax.swing.*;
import java.awt.*;

class RoomSelectPanel extends JPanel {
    private RechercheReservation window;

    private JPanel reservationPanel;
    private JPanel suggestedRoomPanel;
    private JPanel altRoomsPanel;


    RoomSelectPanel(RechercheReservation window_){
        this.window = window_;

        this.drawPanel();
    }


    private void drawPanel(){
        this.setLayout(new GridBagLayout());

        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.ipadx = 0;
        panelConstraints.ipady = 0;
        panelConstraints.gridwidth = 1;
        panelConstraints.weightx = 1;
        panelConstraints.fill = GridBagConstraints.BOTH;


        this.reservationPanel = new JPanel(new GridBagLayout());
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.gridwidth = 2;
        panelConstraints.weightx = 1;
        this.add(this.reservationPanel, panelConstraints);

        this.suggestedRoomPanel = new JPanel(new GridBagLayout());
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 1;
        panelConstraints.gridwidth = 1;
        panelConstraints.weightx = 1;
        this.add(this.suggestedRoomPanel, panelConstraints);

        this.altRoomsPanel = new JPanel(new GridBagLayout());
        panelConstraints.gridx = 1;
        panelConstraints.gridy = 1;
        panelConstraints.gridwidth = 1;
        panelConstraints.weightx = 4;
        this.add(this.altRoomsPanel, panelConstraints);
    }

    void refresh(Reservation reservation, Chambre suggested, Chambre[] alternatives){
        this.reservationPanel.removeAll();
        this.suggestedRoomPanel.removeAll();
        this.altRoomsPanel.removeAll();


        String[] reservationContent = reservation.getInfo();

        JLabel selectedReservationLabel = new JLabel("Reservation Selectionnee", JLabel.LEFT);
        selectedReservationLabel.setFont(selectedReservationLabel.getFont().deriveFont(17.0f));
        RechercheReservation.labelTitleConstraints.gridx = 0;
        RechercheReservation.labelTitleConstraints.gridy = 0;
        RechercheReservation.labelTitleConstraints.gridwidth = reservationContent.length;
        this.reservationPanel.add(selectedReservationLabel, RechercheReservation.labelTitleConstraints);

        for (int i = 0; i < reservationContent.length; i++) {
            JLabel contentLabel = new JLabel(reservationContent[i], JLabel.LEFT);

            RechercheReservation.cellConstraints.gridx = i;
            RechercheReservation.cellConstraints.gridy = 1;

            this.reservationPanel.add(contentLabel, RechercheReservation.cellConstraints);
        }


        JLabel suggestedRoomLabel = new JLabel("Chambre " + String.valueOf(suggested.number), JLabel.LEFT);
        suggestedRoomLabel.setFont(suggestedRoomLabel.getFont().deriveFont(17.0f));
        RechercheReservation.labelTitleConstraints.gridx = 0;
        RechercheReservation.labelTitleConstraints.gridy = 0;
        RechercheReservation.labelTitleConstraints.gridwidth = 1;
        this.suggestedRoomPanel.add(suggestedRoomLabel, RechercheReservation.labelTitleConstraints);

        JButton acceptSuggestedButton = new JButton("VALIDER");
        SelectRoomListener selectRoomListener = new SelectRoomListener(this.window, suggested);
        acceptSuggestedButton.addActionListener(selectRoomListener);

        RechercheReservation.buttonConstraints.gridx = 0;
        RechercheReservation.buttonConstraints.gridy = 1;
        this.suggestedRoomPanel.add(acceptSuggestedButton, RechercheReservation.buttonConstraints);


        JLabel alternativesLabel = new JLabel("Alternatives");
        alternativesLabel.setFont(suggestedRoomLabel.getFont().deriveFont(17.0f));
        RechercheReservation.labelTitleConstraints.gridx = 0;
        RechercheReservation.labelTitleConstraints.gridy = 0;
        RechercheReservation.labelTitleConstraints.gridwidth = 3;
        this.altRoomsPanel.add(alternativesLabel, RechercheReservation.labelTitleConstraints);

        String[] alternativesTableLabel = {"Numero", "Type"};
        for (int i = 0; i < alternativesTableLabel.length; i++){
            JLabel alternativeContentLabel = new JLabel(alternativesTableLabel[i], JLabel.LEFT);

            RechercheReservation.cellConstraints.gridx = i;
            RechercheReservation.cellConstraints.gridy = 1;

            this.altRoomsPanel.add(alternativeContentLabel, RechercheReservation.cellConstraints);
        }

        for (int i = 0; i < alternatives.length; i++) {
            String[] roomContent = alternatives[i].getInfo();

            for (int j = 0; j < roomContent.length; j++){
                JLabel alternativeContentLabel = new JLabel(roomContent[j], JLabel.LEFT);

                RechercheReservation.cellConstraints.gridx = j;
                RechercheReservation.cellConstraints.gridy = i +2;

                this.altRoomsPanel.add(alternativeContentLabel, RechercheReservation.cellConstraints);
            }

            JButton acceptAlternativeButton = new JButton("VALIDER");
            selectRoomListener = new SelectRoomListener(this.window, alternatives[i]);
            acceptAlternativeButton.addActionListener(selectRoomListener);

            RechercheReservation.buttonConstraints.gridx = roomContent.length;
            RechercheReservation.buttonConstraints.gridy = i +2;
            this.altRoomsPanel.add(acceptAlternativeButton, RechercheReservation.buttonConstraints);
        }
    }
}
