import javax.swing.*;
import java.awt.*;

class FinalValidationPanel extends JPanel {
    private Reception window;


    FinalValidationPanel(Reception window_){
        this.window = window_;

        this.drawPanel();
    }

    private void drawPanel(){
        this.setLayout(new GridBagLayout());
    }

    void refresh(Room selected){
        this.removeAll();


        @SuppressWarnings("SpellCheckingInspection") JLabel selectedRoomLabel = new JLabel("La chambre " + String.valueOf(selected.number) + " a bien ete attribuee", JLabel.LEFT);
        selectedRoomLabel.setFont(selectedRoomLabel.getFont().deriveFont(17.0f));
        Reception.labelTitleConstraints.gridx = 0;
        Reception.labelTitleConstraints.gridy = 0;
        Reception.labelTitleConstraints.fill = GridBagConstraints.BOTH;
        this.add(selectedRoomLabel, Reception.labelTitleConstraints);

        @SuppressWarnings("SpellCheckingInspection") JButton finishButton = new JButton("TERMINER");
        FinishListener finishListener = new FinishListener(this.window);
        finishButton.addActionListener(finishListener);
        Reception.buttonConstraints.gridx = 0;
        Reception.buttonConstraints.gridy = 1;
        this.add(finishButton, Reception.buttonConstraints);
    }
}
