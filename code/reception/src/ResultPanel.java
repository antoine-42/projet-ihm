import javax.swing.*;
import java.awt.*;

class ResultPanel extends JPanel {
    private Reception window;

    private JLabel resultsLabel;
    private JLabel additionalResultsLabel;

    private JPanel panelTableauResults;
    private JPanel panelTableauAdditionalResults;


    ResultPanel(Reception window_){
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
        constraints.insets = Reception.marginNone;
        constraints.anchor = GridBagConstraints.NORTH;


        this.resultsLabel = new JLabel("Resultats", JLabel.LEFT);
        resultsLabel.setFont(Reception.titleFont);
        resultsLabel.setForeground(Reception.secondaryColor);
        Reception.labelTitleConstraints.gridx = 0;
        Reception.labelTitleConstraints.gridy = 0;
        Reception.labelTitleConstraints.gridwidth = 1;
        this.add(resultsLabel, Reception.labelTitleConstraints);

        this.panelTableauResults = new JPanel();
        this.panelTableauResults.setLayout(new GridBagLayout());
        this.panelTableauResults.setOpaque(false);
        constraints.gridy = 1;
        this.add(this.panelTableauResults, constraints);

        this.additionalResultsLabel = new JLabel("Autres reservations", JLabel.LEFT);
        additionalResultsLabel.setFont(Reception.titleFont);
        additionalResultsLabel.setForeground(Reception.secondaryColor);
        Reception.labelTitleConstraints.gridx = 0;
        Reception.labelTitleConstraints.gridy = 3;
        Reception.labelTitleConstraints.gridwidth = 1;
        this.add(additionalResultsLabel, Reception.labelTitleConstraints);

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
            labelTitle.setFont(Reception.defaultFont);
            labelTitle.setForeground(Reception.secondaryColor);

            Reception.cellConstraints.gridx = i;
            Reception.cellConstraints.gridy = 0;
            panel.add(labelTitle, Reception.cellConstraints);
        }

        for (int i = 0; i < reservations.length; i++) {
            String[] content = reservations[i].getInfo();

            for (int j = 0; j < content.length; j++) {
                JLabel contentLabel = new JLabel(content[j], JLabel.LEFT);
                contentLabel.setFont(Reception.defaultFont);
                contentLabel.setForeground(Reception.secondaryColor);

                if (i % 2 == 0) {
                    contentLabel.setOpaque(true);
                    contentLabel.setBackground(Reception.thirdColor);
                }

                Reception.cellConstraints.gridx = j;
                Reception.cellConstraints.gridy = i +1;

                panel.add(contentLabel, Reception.cellConstraints);
            }
            if (createButtons) {
                JButton selectButton = new JButton("⇒");
                ReservationSelectListener selectListener = new ReservationSelectListener(this.window, reservations[i]);
                selectButton.addActionListener(selectListener);
                selectButton.setFont(Reception.defaultFont);

                Reception.cellConstraints.gridx = content.length;
                Reception.cellConstraints.gridy = i +1;
                panel.add(selectButton, Reception.cellConstraints);
            }
        }
    }
}
