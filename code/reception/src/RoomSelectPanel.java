import javax.swing.*;
import java.awt.*;

class RoomSelectPanel extends JPanel {
    private static final int maxAlternatives = 10;

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
        this.setOpaque(false);

        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.ipadx = 0;
        panelConstraints.ipady = 0;
        panelConstraints.gridwidth = 1;
        panelConstraints.weightx = 1;
        panelConstraints.fill = GridBagConstraints.BOTH;


        this.reservationPanel = new JPanel(new GridBagLayout());
        this.reservationPanel.setOpaque(false);
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.gridwidth = 2;
        panelConstraints.weightx = 1;
        this.add(this.reservationPanel, panelConstraints);

        this.suggestedRoomPanel = new JPanel(new GridBagLayout());
        this.suggestedRoomPanel.setOpaque(false);
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 2;
        panelConstraints.gridwidth = 1;
        panelConstraints.weightx = 1;
        this.add(this.suggestedRoomPanel, panelConstraints);

        this.altRoomsPanel = new JPanel(new GridBagLayout());
        this.altRoomsPanel.setOpaque(false);
        panelConstraints.gridx = 1;
        panelConstraints.gridy = 2;
        panelConstraints.gridwidth = 1;
        panelConstraints.weightx = 4;
        this.add(this.altRoomsPanel, panelConstraints);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.weighty = 1;
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        this.add(spacer, constraints);
    }

    void refresh(Reservation reservation, Room suggested, Room[] alternatives){
        this.reservationPanel.removeAll();
        this.suggestedRoomPanel.removeAll();
        this.altRoomsPanel.removeAll();


        String[] reservationContent = reservation.getInfo();

        JLabel selectedReservationLabel = new JLabel("Reservation Selectionnee", JLabel.LEFT);
        selectedReservationLabel.setFont(Utils.TITLE_FONT);
        selectedReservationLabel.setForeground(Utils.SECONDARY_COLOR);

        Utils.labelTitleConstraints.gridx = 0;
        Utils.labelTitleConstraints.gridy = 0;
        Utils.labelTitleConstraints.gridwidth = reservationContent.length;
        this.reservationPanel.add(selectedReservationLabel, Utils.labelTitleConstraints);

        for (int i = 0; i < reservationContent.length; i++) {
            JLabel contentLabel = new JLabel(reservationContent[i], JLabel.LEFT);
            contentLabel.setFont(Utils.DEFAULT_FONT);
            contentLabel.setForeground(Utils.SECONDARY_COLOR);

            Utils.cellConstraints.gridx = i;
            Utils.cellConstraints.gridy = 1;

            this.reservationPanel.add(contentLabel, Utils.cellConstraints);
        }


        JLabel suggestedRoomLabel = new JLabel("Chambre " + String.valueOf(suggested.number), JLabel.LEFT);
        suggestedRoomLabel.setFont(Utils.TITLE_FONT);
        suggestedRoomLabel.setForeground(Utils.SECONDARY_COLOR);

        Utils.labelTitleConstraints.gridx = 0;
        Utils.labelTitleConstraints.gridy = 0;
        Utils.labelTitleConstraints.gridwidth = 1;
        this.suggestedRoomPanel.add(suggestedRoomLabel, Utils.labelTitleConstraints);

        JButton acceptSuggestedButton = new JButton("VALIDER");
        SelectRoomListener selectRoomListener = new SelectRoomListener(this.window, suggested);
        acceptSuggestedButton.addActionListener(selectRoomListener);
        acceptSuggestedButton.setFont(Utils.DEFAULT_FONT);

        Utils.buttonConstraints.gridx = 0;
        Utils.buttonConstraints.gridy = 1;
        this.suggestedRoomPanel.add(acceptSuggestedButton, Utils.buttonConstraints);


        JLabel alternativesLabel = new JLabel("Alternatives");
        alternativesLabel.setFont(Utils.TITLE_FONT);
        alternativesLabel.setForeground(Utils.SECONDARY_COLOR);

        Utils.labelTitleConstraints.gridx = 0;
        Utils.labelTitleConstraints.gridy = 0;
        Utils.labelTitleConstraints.gridwidth = 3;
        this.altRoomsPanel.add(alternativesLabel, Utils.labelTitleConstraints);

        String[] alternativesTableLabel = {"Numero", "Type", "Description"};
        for (int i = 0; i < alternativesTableLabel.length; i++){
            JLabel alternativeContentLabel = new JLabel(alternativesTableLabel[i], JLabel.LEFT);
            alternativeContentLabel.setFont(Utils.DEFAULT_FONT);
            alternativeContentLabel.setForeground(Utils.SECONDARY_COLOR);

            Utils.cellConstraints.gridx = i;
            Utils.cellConstraints.gridy = 1;

            this.altRoomsPanel.add(alternativeContentLabel, Utils.cellConstraints);
        }

        int maxAltDisplay = Math.min(alternatives.length, maxAlternatives);
        for (int i = 0; i < maxAltDisplay; i++) {
            String[] roomContent = alternatives[i].getInfo();

            for (int j = 0; j < roomContent.length; j++){
                JLabel alternativeContentLabel = new JLabel(roomContent[j], JLabel.LEFT);
                alternativeContentLabel.setFont(Utils.DEFAULT_FONT);
                alternativeContentLabel.setForeground(Utils.SECONDARY_COLOR);

                Utils.cellConstraints.gridx = j;
                Utils.cellConstraints.gridy = i +2;

                if (i % 2 == 0) {
                    alternativeContentLabel.setOpaque(true);
                    alternativeContentLabel.setBackground(Utils.THIRD_COLOR);
                }

                this.altRoomsPanel.add(alternativeContentLabel, Utils.cellConstraints);
            }

            JButton acceptAlternativeButton = new JButton("VALIDER");
            SelectRoomListener selectAltRoomListener = new SelectRoomListener(this.window, alternatives[i]);
            acceptAlternativeButton.addActionListener(selectAltRoomListener);
            acceptAlternativeButton.setFont(Utils.DEFAULT_FONT);

            Utils.buttonConstraints.gridx = roomContent.length;
            Utils.buttonConstraints.gridy = i +2;
            this.altRoomsPanel.add(acceptAlternativeButton, Utils.buttonConstraints);
        }
    }
}
