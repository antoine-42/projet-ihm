import javax.swing.*;
import java.awt.*;

class ResultPanel extends JPanel {
    private RechercheReservation window;

    private JPanel panelTableauResults;


    ResultPanel(RechercheReservation window_){
        this.window = window_;

        this.drawPanel();
    }


    private void drawPanel(){
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        this.panelTableauResults = new JPanel();
        this.panelTableauResults.setLayout(new GridBagLayout());
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
            RechercheReservation.cellConstraints.gridx = i;
            RechercheReservation.cellConstraints.gridy = 0;
            this.panelTableauResults.add(labelLegende, RechercheReservation.cellConstraints);
        }

        for (int i = 0; i < reservations.length; i++) {
            String[] content = reservations[i].getInfo();

            for (int j = 0; j < content.length; j++) {
                JLabel contentLabel = new JLabel(content[j], JLabel.LEFT);

                if (i % 2 == 0) {
                    contentLabel.setOpaque(true);
                    contentLabel.setBackground(Color.LIGHT_GRAY);
                }

                RechercheReservation.cellConstraints.gridx = j;
                RechercheReservation.cellConstraints.gridy = i +1;

                this.panelTableauResults.add(contentLabel, RechercheReservation.cellConstraints);
            }

            JButton selectButton = new JButton("⇒");
            selectReservationListener selectListener = new selectReservationListener(this.window, reservations[i]);
            selectButton.addActionListener(selectListener);

            RechercheReservation.cellConstraints.gridx = content.length;
            RechercheReservation.cellConstraints.gridy = i +1;
            this.panelTableauResults.add(selectButton, RechercheReservation.cellConstraints);
        }
    }
}
