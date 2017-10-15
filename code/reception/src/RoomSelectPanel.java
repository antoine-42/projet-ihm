import javax.swing.*;
import java.awt.*;

class RoomSelectPanel extends JPanel {
    private static int maxAlternatives = 10;

    private Reception window;

    private JPanel reservationPanel;
    private JPanel suggestedRoomPanel;
    private JPanel altRoomsPanel;


    RoomSelectPanel(Reception window_){
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

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.weighty = 1;

        JPanel spacer = new JPanel();
        this.add(spacer, constraints);
    }

    void refresh(Reservation reservation, Room suggested, Room[] alternatives){
        this.reservationPanel.removeAll();
        this.suggestedRoomPanel.removeAll();
        this.altRoomsPanel.removeAll();


        String[] reservationContent = reservation.getInfo();

        JLabel selectedReservationLabel = new JLabel("Reservation Selectionnee", JLabel.LEFT);
        selectedReservationLabel.setFont(selectedReservationLabel.getFont().deriveFont(17.0f));
        Reception.labelTitleConstraints.gridx = 0;
        Reception.labelTitleConstraints.gridy = 0;
        Reception.labelTitleConstraints.gridwidth = reservationContent.length;
        this.reservationPanel.add(selectedReservationLabel, Reception.labelTitleConstraints);

        for (int i = 0; i < reservationContent.length; i++) {
            JLabel contentLabel = new JLabel(reservationContent[i], JLabel.LEFT);

            Reception.cellConstraints.gridx = i;
            Reception.cellConstraints.gridy = 1;

            this.reservationPanel.add(contentLabel, Reception.cellConstraints);
        }


        JLabel suggestedRoomLabel = new JLabel("Chambre " + String.valueOf(suggested.number), JLabel.LEFT);
        suggestedRoomLabel.setFont(suggestedRoomLabel.getFont().deriveFont(17.0f));
        Reception.labelTitleConstraints.gridx = 0;
        Reception.labelTitleConstraints.gridy = 0;
        Reception.labelTitleConstraints.gridwidth = 1;
        this.suggestedRoomPanel.add(suggestedRoomLabel, Reception.labelTitleConstraints);

        JButton acceptSuggestedButton = new JButton("VALIDER");
        SelectRoomListener selectRoomListener = new SelectRoomListener(this.window, suggested);
        acceptSuggestedButton.addActionListener(selectRoomListener);

        Reception.buttonConstraints.gridx = 0;
        Reception.buttonConstraints.gridy = 1;
        this.suggestedRoomPanel.add(acceptSuggestedButton, Reception.buttonConstraints);


        JLabel alternativesLabel = new JLabel("Alternatives");
        alternativesLabel.setFont(suggestedRoomLabel.getFont().deriveFont(17.0f));
        Reception.labelTitleConstraints.gridx = 0;
        Reception.labelTitleConstraints.gridy = 0;
        Reception.labelTitleConstraints.gridwidth = 3;
        this.altRoomsPanel.add(alternativesLabel, Reception.labelTitleConstraints);

        String[] alternativesTableLabel = {"Numero", "Type"};
        for (int i = 0; i < alternativesTableLabel.length; i++){
            JLabel alternativeContentLabel = new JLabel(alternativesTableLabel[i], JLabel.LEFT);

            Reception.cellConstraints.gridx = i;
            Reception.cellConstraints.gridy = 1;

            this.altRoomsPanel.add(alternativeContentLabel, Reception.cellConstraints);
        }

        int maxAltDisplay = Math.min(alternatives.length, maxAlternatives);
        for (int i = 0; i < maxAltDisplay; i++) {
            String[] roomContent = alternatives[i].getInfo();

            for (int j = 0; j < roomContent.length; j++){
                JLabel alternativeContentLabel = new JLabel(roomContent[j], JLabel.LEFT);

                Reception.cellConstraints.gridx = j;
                Reception.cellConstraints.gridy = i +2;

                this.altRoomsPanel.add(alternativeContentLabel, Reception.cellConstraints);
            }

            JButton acceptAlternativeButton = new JButton("VALIDER");
            SelectRoomListener selectAltRoomListener = new SelectRoomListener(this.window, alternatives[i]);
            acceptAlternativeButton.addActionListener(selectAltRoomListener);

            Reception.buttonConstraints.gridx = roomContent.length;
            Reception.buttonConstraints.gridy = i +2;
            this.altRoomsPanel.add(acceptAlternativeButton, Reception.buttonConstraints);
        }
    }
}
