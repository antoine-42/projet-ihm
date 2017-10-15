import javax.swing.*;
import java.awt.*;

class ResultPanel extends JPanel {
    private Reception window;

    private JPanel panelTableauResults;


    ResultPanel(Reception window_){
        this.window = window_;

        this.drawPanel();
    }


    private void drawPanel(){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        GridBagConstraints constraints = new GridBagConstraints();

        this.panelTableauResults = new JPanel();
        this.panelTableauResults.setLayout(new GridBagLayout());
        this.panelTableauResults.setOpaque(false);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = Reception.marginNone;
        constraints.anchor = GridBagConstraints.NORTH;
        this.add(this.panelTableauResults, constraints);
    }

    void refresh(Reservation[] reservations){
        this.panelTableauResults.removeAll();

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
            this.panelTableauResults.add(labelTitle, Reception.cellConstraints);
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

                this.panelTableauResults.add(contentLabel, Reception.cellConstraints);
            }

            JButton selectButton = new JButton("⇒");
            ReservationSelectListener selectListener = new ReservationSelectListener(this.window, reservations[i]);
            selectButton.addActionListener(selectListener);
            selectButton.setFont(Reception.defaultFont);

            Reception.cellConstraints.gridx = content.length;
            Reception.cellConstraints.gridy = i +1;
            this.panelTableauResults.add(selectButton, Reception.cellConstraints);
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = reservations.length +1;
        constraints.weighty = 1;

        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        this.panelTableauResults.add(spacer, constraints);
    }
}
