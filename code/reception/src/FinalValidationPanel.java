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

        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.ipadx = 0;
        panelConstraints.ipady = 0;
        panelConstraints.gridwidth = 1;
        panelConstraints.weightx = 1;
        panelConstraints.fill = GridBagConstraints.BOTH;
    }

    void refresh(Room selected){
        this.removeAll();


        JLabel selectedRoomLabel = new JLabel("La chambre " + String.valueOf(selected.number) + " a bien ete attribuee", JLabel.LEFT);
        selectedRoomLabel.setFont(selectedRoomLabel.getFont().deriveFont(17.0f));
        Reception.labelTitleConstraints.gridx = 0;
        Reception.labelTitleConstraints.gridy = 0;
        Reception.labelTitleConstraints.fill = GridBagConstraints.BOTH;
        this.add(selectedRoomLabel, Reception.labelTitleConstraints);

        JButton finishButton = new JButton("TERMINER");
        FinishListener finishListener = new FinishListener(this.window);
        finishButton.addActionListener(finishListener);
        Reception.buttonConstraints.gridx = 0;
        Reception.buttonConstraints.gridy = 1;
        this.add(finishButton, Reception.buttonConstraints);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.weighty = 1;
        JPanel spacer = new JPanel();
        this.add(spacer, constraints);
    }
}
