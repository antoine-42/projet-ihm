import javax.swing.*;
import java.awt.*;

public class FinalValidationPanel extends JPanel {
    private RechercheReservation window;


    FinalValidationPanel(RechercheReservation window_){
        this.window = window_;

        this.drawPanel();
    }

    private void drawPanel(){
        this.setLayout(new GridBagLayout());
    }

    void refresh(Chambre selected){
        this.removeAll();


        JLabel selectedRoomLabel = new JLabel("La chambre " + String.valueOf(selected.number) + " a bien ete attribuee", JLabel.LEFT);
        selectedRoomLabel.setFont(selectedRoomLabel.getFont().deriveFont(17.0f));
        RechercheReservation.labelTitleConstraints.gridx = 0;
        RechercheReservation.labelTitleConstraints.gridy = 0;
        RechercheReservation.labelTitleConstraints.gridwidth = 2;
        this.add(selectedRoomLabel, RechercheReservation.labelTitleConstraints);

        JButton terminerButton = new JButton("TERMINER");
        RechercheReservation.buttonConstraints.gridx = 0;
        RechercheReservation.buttonConstraints.gridy = 1;
        this.add(terminerButton, RechercheReservation.buttonConstraints);
    }
}
