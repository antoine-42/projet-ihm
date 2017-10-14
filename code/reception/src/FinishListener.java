import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishListener implements ActionListener {
    private Reception reception;

    FinishListener(Reception reception_){
        this.reception = reception_;
    }

    public void actionPerformed(ActionEvent e) {
        this.reception.reset();
    }
}
