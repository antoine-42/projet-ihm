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
        this.setOpaque(false);

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
        selectedRoomLabel.setFont(Utils.TITLE_FONT);
        selectedRoomLabel.setForeground(Utils.SECONDARY_COLOR);

        Utils.labelTitleConstraints.gridx = 0;
        Utils.labelTitleConstraints.gridy = 0;
        Utils.labelTitleConstraints.weightx = 1;
        Utils.labelTitleConstraints.fill = GridBagConstraints.BOTH;
        this.add(selectedRoomLabel, Utils.labelTitleConstraints);

        JButton finishButton = new JButton("TERMINER");
        FinishListener finishListener = new FinishListener(this.window);
        finishButton.addActionListener(finishListener);
        finishButton.setFont(Utils.DEFAULT_FONT);

        Utils.buttonConstraints.gridx = 0;
        Utils.buttonConstraints.gridy = 1;
        this.add(finishButton, Utils.buttonConstraints);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.weighty = 1;
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        this.add(spacer, constraints);
    }
}
