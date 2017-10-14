import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private RechercheReservation window;

    private JPanel panelTableauResults;


    ResultPanel(RechercheReservation window_){
        this.window = window_;

        this.drawPanel();
    }


    private void drawPanel(){
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();


        JButton bouttonRetours = new JButton("←");
        BoutonRetoursListener boutonRetoursListener = new BoutonRetoursListener(this.window);
        bouttonRetours.addActionListener(boutonRetoursListener);

        RechercheReservation.buttonConstraints.gridx = 0;
        RechercheReservation.buttonConstraints.gridy = 0;
        RechercheReservation.buttonConstraints.gridwidth = 1;
        RechercheReservation.buttonConstraints.anchor = GridBagConstraints.LINE_START;
        this.add(bouttonRetours, RechercheReservation.buttonConstraints);

        this.panelTableauResults = new JPanel();
        this.panelTableauResults.setLayout(new GridBagLayout());
        this.panelTableauResults.setBackground(Color.GRAY);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = RechercheReservation.marginNone;
        constraints.anchor = GridBagConstraints.NORTH;
        this.add(this.panelTableauResults, constraints);
    }
    
    void refresh(Reservation[] reservations){
        GridBagConstraints cellConstraints = new GridBagConstraints();
        cellConstraints.ipadx = 15;
        cellConstraints.ipady = 15;
        cellConstraints.weightx = 1;
        cellConstraints.weighty = 0;
        cellConstraints.fill = GridBagConstraints.BOTH;
        cellConstraints.insets = RechercheReservation.marginNone;


        this.panelTableauResults.removeAll();

        String legendes[] = {
                "Nom",
                "Prenom",
                "Reference",
                "Type de chambre",
                "Debut du sejour",
                "Duree du sejour"
        };
        for (int i = 0; i < legendes.length; i++) {
            JLabel labelLegende = new JLabel(legendes[i], JLabel.LEFT);
            cellConstraints.gridx = i;
            cellConstraints.gridy = 0;
            this.panelTableauResults.add(labelLegende, cellConstraints);
        }

        for (int i = 0; i < reservations.length; i++) {
            String[] content = {
                    reservations[i].client.lastName,
                    reservations[i].client.name,
                    reservations[i].reference,
                    TypeChambre.TYPE[reservations[i].category],
                    RechercheReservation.dateFormat.format(reservations[i].start),
                    String.valueOf(reservations[i].length) + " Jours"
            };

            for (int j = 0; j < content.length; j++) {
                JLabel contentLabel = new JLabel(content[j], JLabel.LEFT);

                if (i % 2 == 0) {
                    contentLabel.setOpaque(true);
                    contentLabel.setBackground(Color.BLUE);
                    contentLabel.setForeground(Color.WHITE);
                }

                cellConstraints.gridx = j;
                cellConstraints.gridy = i +1;

                this.panelTableauResults.add(contentLabel, cellConstraints);
            }

            JButton selectButton = new JButton("⇒");
            selectReservationListener selectListener = new selectReservationListener(this.window, reservations[i]);
            selectButton.addActionListener(selectListener);

            cellConstraints.gridx = content.length;
            cellConstraints.gridy = i +1;
            this.panelTableauResults.add(selectButton, cellConstraints);
        }
    }
}
