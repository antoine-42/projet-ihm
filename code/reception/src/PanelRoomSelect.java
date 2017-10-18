import javax.swing.*;
import java.awt.*;

class PanelRoomSelect extends JPanel {
    private static final int maxAlternatives = 10;

    private Reception window;

    private JPanel reservationPanel;
    private JPanel suggestedRoomPanel;
    private JPanel altRoomsPanel;


    PanelRoomSelect(Reception window_){
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


        this.reservationPanel = Utils.createJPanel();
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.gridwidth = 2;
        panelConstraints.weightx = 1;
        this.add(this.reservationPanel, panelConstraints);

        this.suggestedRoomPanel = Utils.createJPanel();
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 2;
        panelConstraints.gridwidth = 1;
        panelConstraints.weightx = 1;
        this.add(this.suggestedRoomPanel, panelConstraints);

        this.altRoomsPanel = Utils.createJPanel();
        panelConstraints.gridx = 1;
        panelConstraints.gridy = 2;
        panelConstraints.gridwidth = 1;
        panelConstraints.weightx = 4;
        this.add(this.altRoomsPanel, panelConstraints);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.weighty = 1;
        JPanel spacer = Utils.createJPanel();
        this.add(spacer, constraints);
    }

    void refresh(Reservation reservation, Room suggested, Room[] alternatives){
        this.reservationPanel.removeAll();
        this.suggestedRoomPanel.removeAll();
        this.altRoomsPanel.removeAll();


        String[] reservationContent = reservation.getInfo();

        JLabel selectedReservationLabel = Utils.createTitleJLabel("Reservation Selectionnee");
        Utils.labelTitleConstraints.gridx = 0;
        Utils.labelTitleConstraints.gridy = 0;
        Utils.labelTitleConstraints.gridwidth = reservationContent.length;
        this.reservationPanel.add(selectedReservationLabel, Utils.labelTitleConstraints);

        for (int i = 0; i < reservationContent.length; i++) {
            JLabel contentLabel = Utils.createContentJLabel(reservationContent[i]);

            Utils.cellConstraints.gridx = i;
            Utils.cellConstraints.gridy = 1;

            this.reservationPanel.add(contentLabel, Utils.cellConstraints);
        }


        JLabel suggestedRoomLabel = Utils.createTitleJLabel("Chambre " + String.valueOf(suggested.number));
        Utils.labelTitleConstraints.gridx = 0;
        Utils.labelTitleConstraints.gridy = 0;
        Utils.labelTitleConstraints.gridwidth = 1;
        this.suggestedRoomPanel.add(suggestedRoomLabel, Utils.labelTitleConstraints);

        ListenerSelectRoom selectRoomListener = new ListenerSelectRoom(this.window, suggested);
        JButton acceptSuggestedButton = Utils.createJButton("VALIDER", selectRoomListener);
        Utils.buttonConstraints.gridx = 0;
        Utils.buttonConstraints.gridy = 1;
        this.suggestedRoomPanel.add(acceptSuggestedButton, Utils.buttonConstraints);


        JLabel alternativesLabel = Utils.createTitleJLabel("Alternatives");
        Utils.labelTitleConstraints.gridx = 0;
        Utils.labelTitleConstraints.gridy = 0;
        Utils.labelTitleConstraints.gridwidth = 3;
        this.altRoomsPanel.add(alternativesLabel, Utils.labelTitleConstraints);

        String[] alternativesTableLabel = {"Numero", "Type", "Description"};
        for (int i = 0; i < alternativesTableLabel.length; i++){
            JLabel alternativeContentLabel = Utils.createContentJLabel(alternativesTableLabel[i]);

            Utils.cellConstraints.gridx = i;
            Utils.cellConstraints.gridy = 1;

            this.altRoomsPanel.add(alternativeContentLabel, Utils.cellConstraints);
        }

        int maxAltDisplay = Math.min(alternatives.length, maxAlternatives);
        for (int i = 0; i < maxAltDisplay; i++) {
            String[] roomContent = alternatives[i].getInfo();

            for (int j = 0; j < roomContent.length; j++){
                JLabel alternativeContentLabel = Utils.createContentJLabel(roomContent[j]);

                Utils.cellConstraints.gridx = j;
                Utils.cellConstraints.gridy = i +2;

                if (i % 2 == 0) {
                    alternativeContentLabel.setOpaque(true);
                    alternativeContentLabel.setBackground(Utils.THIRD_COLOR);
                }

                this.altRoomsPanel.add(alternativeContentLabel, Utils.cellConstraints);
            }

            ListenerSelectRoom selectAltRoomListener = new ListenerSelectRoom(this.window, alternatives[i]);
            JButton acceptAlternativeButton = Utils.createJButton("VALIDER", selectAltRoomListener);

            Utils.buttonConstraints.gridx = roomContent.length;
            Utils.buttonConstraints.gridy = i +2;
            this.altRoomsPanel.add(acceptAlternativeButton, Utils.buttonConstraints);
        }
    }
}