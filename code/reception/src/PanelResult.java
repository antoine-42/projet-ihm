import javax.swing.*;
import java.awt.*;

class PanelResult extends JPanel {
    private Reception window;

    private JLabel resultsLabel;
    private JLabel additionalResultsLabel;

    private JPanel panelTableauResults;
    private JPanel panelTableauAdditionalResults;


    PanelResult(Reception window_){
        this.window = window_;

        this.drawPanel();
    }


    private void drawPanel(){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = Utils.MARGIN_NONE;
        constraints.anchor = GridBagConstraints.NORTH;


        this.resultsLabel = Utils.createTitleJLabel("Resultats");
        Utils.labelTitleConstraints.gridx = 0;
        Utils.labelTitleConstraints.gridy = 0;
        Utils.labelTitleConstraints.gridwidth = 1;
        this.add(resultsLabel, Utils.labelTitleConstraints);

        this.panelTableauResults = new JPanel();
        this.panelTableauResults.setLayout(new GridBagLayout());
        this.panelTableauResults.setOpaque(false);
        constraints.gridy = 1;
        this.add(this.panelTableauResults, constraints);

        this.additionalResultsLabel = Utils.createTitleJLabel("Autres reservations");
        Utils.labelTitleConstraints.gridx = 0;
        Utils.labelTitleConstraints.gridy = 3;
        Utils.labelTitleConstraints.gridwidth = 1;
        this.add(additionalResultsLabel, Utils.labelTitleConstraints);

        this.panelTableauAdditionalResults = new JPanel();
        this.panelTableauAdditionalResults.setLayout(new GridBagLayout());
        this.panelTableauAdditionalResults.setOpaque(false);
        constraints.gridy = 4;
        this.add(this.panelTableauAdditionalResults, constraints);

        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        constraints.gridy = 2;
        constraints.weighty = 1;
        this.add(spacer, constraints);
    }

    void refresh(Reservation[] reservations, Reservation[] additionalReservations){
        if (reservations.length == 0) {
            resultsLabel.setText("Aucune reservation active");
        }
        else {
            resultsLabel.setText("Reservations actives");
            createReservationsTable(this.panelTableauResults, reservations, true);
        }

        createReservationsTable(this.panelTableauAdditionalResults, additionalReservations, false);
    }

    private void createReservationsTable(JPanel panel, Reservation[] reservations, Boolean createButtons){
        panel.removeAll();

        String labelTitles[] = {
                "Nom",
                "Prenom",
                "Reference",
                "Type de chambre",
                "Debut du sejour",
                "Duree du sejour"
        };
        for (int i = 0; i < labelTitles.length; i++) {
            JLabel labelTitle = new JLabel(labelTitles[i], JLabel.LEFT);
            labelTitle.setFont(Utils.DEFAULT_FONT);
            labelTitle.setForeground(Utils.SECONDARY_COLOR);

            Utils.cellConstraints.gridx = i;
            Utils.cellConstraints.gridy = 0;
            panel.add(labelTitle, Utils.cellConstraints);
        }

        for (int i = 0; i < reservations.length; i++) {
            String[] content = reservations[i].getInfo();

            for (int j = 0; j < content.length; j++) {
                JLabel contentLabel = new JLabel(content[j], JLabel.LEFT);
                contentLabel.setFont(Utils.DEFAULT_FONT);
                contentLabel.setForeground(Utils.SECONDARY_COLOR);

                if (i % 2 == 0) {
                    contentLabel.setOpaque(true);
                    contentLabel.setBackground(Utils.THIRD_COLOR);
                }

                Utils.cellConstraints.gridx = j;
                Utils.cellConstraints.gridy = i +1;

                panel.add(contentLabel, Utils.cellConstraints);
            }
            if (createButtons) {
                JButton selectButton = new JButton("⇒");
                ListenerReservationSelect selectListener = new ListenerReservationSelect(this.window, reservations[i]);
                selectButton.addActionListener(selectListener);
                selectButton.setFont(Utils.DEFAULT_FONT);

                Utils.cellConstraints.gridx = content.length;
                Utils.cellConstraints.gridy = i +1;
                panel.add(selectButton, Utils.cellConstraints);
            }
        }
    }
}
